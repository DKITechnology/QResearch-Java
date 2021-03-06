/* 
 * Copyright 2012 ClinRisk Ltd.
 * 
 * This file is part of QDiabetes-2013 (http://qdiabetes.org, http://svn.clinrisk.co.uk/opensource/qdiabetes).
 * 
 * QDiabetes-2013 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QDiabetes-2013 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with QDiabetes-2013.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The initial version of this file, to be found at http://svn.clinrisk.co.uk/opensource/qdiabetes, faithfully implements QDiabetes-2013.
 * We have released this code under the GNU Lesser General Public License to enable others to implement the algorithm faithfully.
 * However, the nature of the GNU Lesser General Public License is such that we cannot prevent, for example, someone altering the coefficients.
 * We stress, therefore, that it is the responsibility of the end user to check that the source that they receive produces the same results as the original code posted at http://svn.clinrisk.co.uk/opensource/qdiabetes.
 * Inaccurate implementations of risk scores can lead to wrong patients being given the wrong treatment.
 * 
 * This file has been auto-generated.
 * XML source: Q68_qdiabetes_2013_1_1.xml
 * STATA dta time stamp: 15 Feb 2013 11:36
 * C file create date: Fri 15 Feb 2013 11:40:42 GMT
 * XML source: Q68_qdiabetes_2013_1_0.xml
 * STATA dta time stamp: 15 Feb 2013 11:36
 * C file create date: Fri 15 Feb 2013 11:40:31 GMT
 */

/*
 * Copyright 2017 ClinRisk Ltd.
 * 
 * This file is part of QDiabetes-2018 (https://qdiabetes.org).
 * 
 * QDiabetes-2018 is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * QDiabetes-2018 is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with QDiabetes-2018. If not, see http://www.gnu.org/licenses/.
 * 
 * Additional terms
 * 
 * The following disclaimer must be held together with any risk score score
 * generated by this code. If the score is displayed, then this disclaimer must
 * be displayed or otherwise be made easily accessible, e.g. by a prominent link
 * alongside it. The initial version of this file, to be found at
 * http://qdiabetes.org, faithfully implements QDiabetes-2018. ClinRisk Ltd.
 * have released this code under the GNU Affero General Public License to enable
 * others to implement the algorithm faithfully. However, the nature of the GNU
 * Affero General Public License is such that we cannot prevent, for example,
 * someone accidentally altering the coefficients, getting the inputs wrong, or
 * just poor programming. ClinRisk Ltd. stress, therefore, that it is the
 * responsibility of the end user to check that the source that they receive
 * produces the same results as the original code found at http://qdiabetes.org.
 * Inaccurate implementations of risk scores can lead to wrong patients being
 * given the wrong treatment.
 * 
 * End of additional terms
 *
 */

package com.dkitec.qresearch.qdiabetes;

import com.dkitec.qresearch.common.BmiCalculator;
import com.dkitec.qresearch.common.BmiPredictor;
import com.dkitec.qresearch.common.CV;
import com.dkitec.qresearch.common.Input;
import com.dkitec.redwood.api.predict.PredictScript;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PredictDiabete implements PredictScript {

	public void loadModel(String modelPath) {

	}

	public String predict(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper();

		Input in = null;
		try {
			in = mapper.readValue(jsonStr, Input.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String errorMsg = type2_validation(in.getAge(), in.getB_atypicalantipsy(), in.getB_corticosteroids(),
				in.getB_cvd(), in.getB_gestdiab(), in.getB_learning(), in.getB_manicschiz(), in.getB_pos(),
				in.getB_statin(), in.getB_treatedhyp(), in.getEthrisk(), in.getFbs(), in.getFh_diab(), in.getHba1c(),
				in.getSmoke_cat(), in.getSurv(), in.getTown(), in.getGender());
		if (errorMsg.length() > 0) {
			return errorMsg;
		}

		if (in.getHeight() > 0 && in.getWeight() > 0)
			in.setBmi(BmiCalculator.calculate(in.getHeight(), in.getWeight()));
		else
			in.setBmi(BmiPredictor.predict(in.getAge(), in.getB_cvd(), in.getB_treatedhyp(), in.getFh_diab(),
					in.getEthrisk(), in.getSmoke_cat(), in.getGender()));

		if (!CV.d_in_range(in.getBmi(), 20, 40)) {
			errorMsg = "error: bmi must be in range (20,40)\n";
			return errorMsg;
		}

		double result = type2(in.getAge(), in.getB_atypicalantipsy(), in.getB_corticosteroids(), in.getB_cvd(),
				in.getB_gestdiab(), in.getB_learning(), in.getB_manicschiz(), in.getB_pos(), in.getB_statin(),
				in.getB_treatedhyp(), in.getBmi(), in.getEthrisk(), in.getFbs(), in.getFh_diab(), in.getHba1c(),
				in.getSmoke_cat(), in.getSurv(), in.getTown(), in.getGender());

		return String.valueOf(result);
	}

	private double type2_male_raw(int age, int b_corticosteroids, int b_cvd, int b_treatedhyp, double bmi, int ethrisk,
			int fh_diab, int smoke_cat, int surv, double town) {

		double survivor[] = { 0, 0.998213708400726, 0.996353209018707, 0.994382798671722, 0.992213606834412,
				0.989733397960663, 0.987064540386200, 0.984254062175751, 0.981255292892456, 0.977990627288818,
				0.974455237388611, 0.970843732357025, 0.967315018177032, 0.963437378406525, 0.959633111953735,
				0.955690681934357 };

		/* The conditional arrays �씤醫낅퀎 媛�以묒튂 */
		double Iethrisk[] = { 0, 0, 1.2366090720913343000000000, 1.4716746107789032000000000,
				1.8073235649498174000000000, 1.2056055595936399000000000, 0.6032369975938766100000000,
				0.9095436207452737300000000, 0.9137604632927512900000000, 0.7123719045990779500000000 };

		/* �씉�뿰�웾 媛�以묒튂 */
		double Ismoke[] = { 0, 0.1618238582395977700000000, 0.1902020385619117000000000, 0.3210636179312467100000000,
				0.4140001301797494600000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */
		double dage = age;
		dage = dage / 10;
		double age_1 = Math.log(dage);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, 2);
		double bmi_2 = Math.pow(dbmi, 3);

		/* Centring the continuous variables */
		age_1 = age_1 - 1.496771812438965;
		age_2 = age_2 - 89.149559020996094;
		bmi_1 = bmi_1 - 6.832604885101318;
		bmi_2 = bmi_2 - 17.859918594360352;
		town = town - -0.132148191332817;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */
		a += age_1 * 4.4205598323371680000000000;
		a += age_2 * -0.0041132238299394193000000;
		a += bmi_1 * 1.1169895991721528000000000;
		a += bmi_2 * -0.1793529530251269100000000;
		a += town * 0.0291530815903822650000000;

		/* Sum from boolean values */
		a += b_corticosteroids * 0.2059811979905692400000000;
		a += b_cvd * 0.3914728454990503100000000;
		a += b_treatedhyp * 0.5010787979849035100000000;
		a += fh_diab * 0.8385800403428993500000000;

		/* Sum from interaction terms */
		a += age_1 * bmi_1 * 0.5051031253768063500000000;
		a += age_1 * bmi_2 * -0.1375233635462656000000000;
		a += age_1 * fh_diab * -1.1463560542602569000000000;
		a += age_2 * bmi_1 * -0.0015800686452772700000000;
		a += age_2 * bmi_2 * 0.0003394090057824062300000;
		a += age_2 * fh_diab * 0.0018524160353981260000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));

		return score;
	}

	private double type2_female_raw(int age, int b_corticosteroids, int b_cvd, int b_treatedhyp, double bmi,
			int ethrisk, int fh_diab, int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.998714804649353, 0.997435748577118, 0.996052920818329, 0.994562506675720,
				0.992949724197388, 0.991141080856323, 0.989293158054352, 0.987293541431427, 0.985133886337280,
				0.982810735702515, 0.980465650558472, 0.978020071983337, 0.975493073463440, 0.972945988178253,
				0.970350146293640 };

		/* The conditional arrays */
		double Iethrisk[] = { 0, 0, 1.2672136244963337000000000, 1.4277605208830098000000000,
				1.8624060798103199000000000, 1.2379988338989651000000000, 0.4709034172907677900000000,
				0.3476400901703160500000000, 1.1587283467731935000000000, 0.7335499325010315100000000 };
		double Ismoke[] = { 0, 0.1012537024947505100000000, 0.1915520564380613400000000, 0.3091894136143333900000000,
				0.4646730392693820800000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, .5);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = dbmi;
		double bmi_2 = Math.pow(dbmi, 3);

		/* Centring the continuous variables */

		age_1 = age_1 - 2.135220289230347;
		age_2 = age_2 - 94.766799926757813;
		bmi_1 = bmi_1 - 2.549620866775513;
		bmi_2 = bmi_2 - 16.573980331420898;
		town = town - -0.224075347185135;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 4.3848331212989669000000000;
		a += age_2 * -0.0049763964406541149000000;
		a += bmi_1 * 3.3753336326064329000000000;
		a += bmi_2 * -0.0631628488667318330000000;
		a += town * 0.0432726992998635970000000;

		/* Sum from boolean values */

		a += b_corticosteroids * 0.2681990966241487000000000;
		a += b_cvd * 0.3596176830984252900000000;
		a += b_treatedhyp * 0.5314598436974725700000000;
		a += fh_diab * 0.7315358845837640600000000;

		/* Sum from interaction terms */

		a += age_1 * bmi_1 * 1.3037832873997990000000000;
		a += age_1 * bmi_2 * -0.0708293717769046120000000;
		a += age_1 * fh_diab * -0.7968266815834251800000000;
		a += age_2 * bmi_1 * -0.0067725323761278549000000;
		a += age_2 * bmi_2 * 0.0002374980728666116700000;
		a += age_2 * fh_diab * 0.0017048228889394394000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	private double type2(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd, int b_gestdiab,
			int b_learning, int b_manicschiz, int b_pos, int b_statin, int b_treatedhyp, double bmi, int ethrisk,
			double fbs, int fh_diab, double hba1c, int smoke_cat, int surv, double town, int gender) {

		double result = 0;
		if (gender == 0) {
			result = type2_A_female_raw(age, b_atypicalantipsy, b_corticosteroids, b_cvd, b_gestdiab, b_learning,
					b_manicschiz, b_pos, b_statin, b_treatedhyp, bmi, ethrisk, fh_diab, smoke_cat, surv, town);
			if(result >= 5.6 && fbs > 0) {
				result = type2_B_female_raw(age, b_atypicalantipsy, b_corticosteroids, b_cvd, b_gestdiab, b_learning,
						b_manicschiz, b_pos, b_statin, b_treatedhyp, bmi, ethrisk, fbs, fh_diab, smoke_cat, surv, town);
				
			} else if(result >= 5.6 && hba1c > 0) {
				result = type2_C_female_raw(age, b_atypicalantipsy, b_corticosteroids, b_cvd, b_gestdiab, b_learning,
						b_manicschiz, b_pos, b_statin, b_treatedhyp, bmi, ethrisk, fh_diab, hba1c, smoke_cat, surv, town);
			}
		} else {
			result = type2_A_male_raw(age, b_atypicalantipsy, b_corticosteroids, b_cvd, b_learning,
					b_manicschiz, b_statin, b_treatedhyp, bmi, ethrisk, fh_diab, smoke_cat, surv, town);
			if(result >= 5.6 && fbs > 0) {
				result = type2_B_male_raw(age, b_atypicalantipsy, b_corticosteroids, b_cvd, b_learning,
						b_manicschiz, b_statin, b_treatedhyp, bmi, ethrisk, fbs, fh_diab, smoke_cat, surv, town);
				
			} else if(result >= 5.6 && hba1c > 0) {
				result = type2_C_male_raw(age, b_atypicalantipsy, b_corticosteroids, b_cvd, b_learning,
						b_manicschiz, b_statin, b_treatedhyp, bmi, ethrisk, fh_diab, hba1c, smoke_cat, surv, town);
			}
		}
		
		return result;
	}

	// Model A
	private double type2_A_female_raw(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd,
			int b_gestdiab, int b_learning, int b_manicschiz, int b_pos, int b_statin, int b_treatedhyp, double bmi,
			int ethrisk, int fh_diab, int smoke_cat, int surv, double town) {
		surv = 10;
		double survivor[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.986227273941040 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 1.0695857881565456000000000, 1.3430172097414006000000000,
				1.8029022579794518000000000, 1.1274654517708020000000000, 0.4214631490239910100000000,
				0.2850919645908353000000000, 0.8815108797589199500000000, 0.3660573343168487300000000 };
		double Ismoke[] = { 0, 0.0656016901750590550000000, 0.2845098867369837400000000, 0.3567664381700702000000000,
				0.5359517110678775300000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_2 = Math.pow(dage, 3);
		double age_1 = Math.pow(dage, .5);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = dbmi;
		double bmi_2 = Math.pow(dbmi, 3);

		/* Centring the continuous variables */

		age_1 = age_1 - 2.123332023620606;
		age_2 = age_2 - 91.644744873046875;
		bmi_1 = bmi_1 - 2.571253299713135;
		bmi_2 = bmi_2 - 16.999439239501953;
		town = town - 0.391116052865982;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 4.3400852699139278000000000;
		a += age_2 * -0.0048771702696158879000000;
		a += bmi_1 * 2.9320361259524925000000000;
		a += bmi_2 * -0.0474002058748434900000000;
		a += town * 0.0373405696180491510000000;

		/* Sum from boolean values */

		a += b_atypicalantipsy * 0.5526764611098438100000000;
		a += b_corticosteroids * 0.2679223368067459900000000;
		a += b_cvd * 0.1779722905458669100000000;
		a += b_gestdiab * 1.5248871531467574000000000;
		a += b_learning * 0.2783514358717271700000000;
		a += b_manicschiz * 0.2618085210917905900000000;
		a += b_pos * 0.3406173988206666100000000;
		a += b_statin * 0.6590728773280821700000000;
		a += b_treatedhyp * 0.4394758285813711900000000;
		a += fh_diab * 0.5313359456558733900000000;

		/* Sum from interaction terms */

		a += age_1 * b_atypicalantipsy * -0.8031518398316395100000000;
		a += age_1 * b_learning * -0.8641596002882057100000000;
		a += age_1 * b_statin * -1.9757776696583935000000000;
		a += age_1 * bmi_1 * 0.6553138757562945200000000;
		a += age_1 * bmi_2 * -0.0362096572016301770000000;
		a += age_1 * fh_diab * -0.2641171450558896200000000;
		a += age_2 * b_atypicalantipsy * 0.0004684041181021049800000;
		a += age_2 * b_learning * 0.0006724968808953360200000;
		a += age_2 * b_statin * 0.0023750534194347966000000;
		a += age_2 * bmi_1 * -0.0044719662445263054000000;
		a += age_2 * bmi_2 * 0.0001185479967753342000000;
		a += age_2 * fh_diab * 0.0004161025828904768300000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	// Model B
	private double type2_B_female_raw(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd,
			int b_gestdiab, int b_learning, int b_manicschiz, int b_pos, int b_statin, int b_treatedhyp, double bmi,
			int ethrisk, double fbs, int fh_diab, int smoke_cat, int surv, double town) {
		surv = 10;
		double survivor[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.990905702114105 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.9898906127239111000000000, 1.2511504196326508000000000,
				1.4934757568196120000000000, 0.9673887434565966400000000, 0.4844644519593178100000000,
				0.4784214955360102700000000, 0.7520946270805577400000000, 0.4050880741541424400000000 };
		double Ismoke[] = { 0, 0.0374156307236963230000000, 0.2252973672514482800000000, 0.3099736428023662800000000,
				0.4361942139496417500000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, .5);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_2 = Math.pow(dbmi, 3);
		double bmi_1 = dbmi;
		double dfbs = fbs;
		;
		double fbs_2 = Math.pow(dfbs, -1) * Math.log(dfbs);
		double fbs_1 = Math.pow(dfbs, -1);

		/* Centring the continuous variables */

		age_1 = age_1 - 2.123332023620606;
		age_2 = age_2 - 91.644744873046875;
		bmi_1 = bmi_1 - 2.571253299713135;
		bmi_2 = bmi_2 - 16.999439239501953;
		fbs_1 = fbs_1 - 0.208309367299080;
		fbs_2 = fbs_2 - 0.326781362295151;
		town = town - 0.391116052865982;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 3.7650129507517280000000000;
		a += age_2 * -0.0056043343436614941000000;
		a += bmi_1 * 2.4410935031672469000000000;
		a += bmi_2 * -0.0421526334799096420000000;
		a += fbs_1 * -2.1887891946337308000000000;
		a += fbs_2 * -69.9608419828660290000000000;
		a += town * 0.0358046297663126500000000;

		/* Sum from boolean values */

		a += b_atypicalantipsy * 0.4748378550253853400000000;
		a += b_corticosteroids * 0.3767933443754728500000000;
		a += b_cvd * 0.1967261568066525100000000;
		a += b_gestdiab * 1.0689325033692647000000000;
		a += b_learning * 0.4542293408951034700000000;
		a += b_manicschiz * 0.1616171889084260500000000;
		a += b_pos * 0.3565365789576717100000000;
		a += b_statin * 0.5809287382718667500000000;
		a += b_treatedhyp * 0.2836632020122907300000000;
		a += fh_diab * 0.4522149766206111600000000;

		/* Sum from interaction terms */

		a += age_1 * b_atypicalantipsy * -0.7683591642786522500000000;
		a += age_1 * b_learning * -0.7983128124297588200000000;
		a += age_1 * b_statin * -1.9033508839833257000000000;
		a += age_1 * bmi_1 * 0.4844747602404915200000000;
		a += age_1 * bmi_2 * -0.0319399883071813450000000;
		a += age_1 * fbs_1 * 2.2442903047404350000000000;
		a += age_1 * fbs_2 * 13.0068388699783030000000000;
		a += age_1 * fh_diab * -0.3040627374034501300000000;
		a += age_2 * b_atypicalantipsy * 0.0005194455624413476200000;
		a += age_2 * b_learning * 0.0003028327567161890600000;
		a += age_2 * b_statin * 0.0024397111406018711000000;
		a += age_2 * bmi_1 * -0.0041572976682154057000000;
		a += age_2 * bmi_2 * 0.0001126882194204252200000;
		a += age_2 * fbs_1 * 0.0199345308534312550000000;
		a += age_2 * fbs_2 * -0.0716677187529306680000000;
		a += age_2 * fh_diab * 0.0004523639671202325400000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	// Model C
	private double type2_C_female_raw(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd,
			int b_gestdiab, int b_learning, int b_manicschiz, int b_pos, int b_statin, int b_treatedhyp, double bmi,
			int ethrisk, int fh_diab, double hba1c, int smoke_cat, int surv, double town) {
		surv = 10;
		double survivor[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.988788545131683 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.5990951599291540800000000, 0.7832030965635389300000000,
				1.1947351247960103000000000, 0.7141744699168143300000000, 0.1195328468388768800000000,
				0.0136688728784904270000000, 0.5709226537693945500000000, 0.1709107628106929200000000 };
		double Ismoke[] = { 0, 0.0658482585100006730000000, 0.1458413689734224000000000, 0.1525864247480118700000000,
				0.3078741679661397600000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, .5);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_2 = Math.pow(dbmi, 3);
		double bmi_1 = dbmi;
		double dhba1c = hba1c;
		dhba1c = dhba1c / 10;
		double hba1c_1 = Math.pow(dhba1c, .5);
		double hba1c_2 = dhba1c;

		/* Centring the continuous variables */

		age_1 = age_1 - 2.123332023620606;
		age_2 = age_2 - 91.644744873046875;
		bmi_1 = bmi_1 - 2.571253299713135;
		bmi_2 = bmi_2 - 16.999439239501953;
		hba1c_1 = hba1c_1 - 1.886751174926758;
		hba1c_2 = hba1c_2 - 3.559829950332642;
		town = town - 0.391116052865982;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 3.5655214891947722000000000;
		a += age_2 * -0.0056158243572733135000000;
		a += bmi_1 * 2.5043028874544841000000000;
		a += bmi_2 * -0.0428758018926904610000000;
		a += hba1c_1 * 8.7368031307362184000000000;
		a += hba1c_2 * -0.0782313866699499700000000;
		a += town * 0.0358668220563482940000000;

		/* Sum from boolean values */

		a += b_atypicalantipsy * 0.5497633311042200400000000;
		a += b_corticosteroids * 0.1687220550638970400000000;
		a += b_cvd * 0.1644330036273934400000000;
		a += b_gestdiab * 1.1250098105171140000000000;
		a += b_learning * 0.2891205831073965800000000;
		a += b_manicschiz * 0.3182512249068407700000000;
		a += b_pos * 0.3380644414098174500000000;
		a += b_statin * 0.4559396847381116400000000;
		a += b_treatedhyp * 0.4040022295023758000000000;
		a += fh_diab * 0.4428015404826031700000000;

		/* Sum from interaction terms */

		a += age_1 * b_atypicalantipsy * -0.8125434197162131300000000;
		a += age_1 * b_learning * -0.9084665765269808200000000;
		a += age_1 * b_statin * -1.8557960585560658000000000;
		a += age_1 * bmi_1 * 0.6023218765235252000000000;
		a += age_1 * bmi_2 * -0.0344950383968044700000000;
		a += age_1 * fh_diab * -0.2727571351506187200000000;
		a += age_1 * hba1c_1 * 25.4412033227367150000000000;
		a += age_1 * hba1c_2 * -6.8076080421556107000000000;
		a += age_2 * b_atypicalantipsy * 0.0004665611306005428000000;
		a += age_2 * b_learning * 0.0008518980139928006500000;
		a += age_2 * b_statin * 0.0022627250963352537000000;
		a += age_2 * bmi_1 * -0.0043386645663133425000000;
		a += age_2 * bmi_2 * 0.0001162778561671208900000;
		a += age_2 * fh_diab * 0.0004354519795220774900000;
		a += age_2 * hba1c_1 * -0.0522541355885925220000000;
		a += age_2 * hba1c_2 * 0.0140548259061144530000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	// Model A
	private double type2_A_male_raw(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd,
			int b_learning, int b_manicschiz, int b_statin, int b_treatedhyp, double bmi, int ethrisk, int fh_diab,
			int smoke_cat, int surv, double town) {
		surv = 10;
		double survivor[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.978732228279114 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 1.1000230829124793000000000, 1.2903840126147210000000000,
				1.6740908848727458000000000, 1.1400446789147816000000000, 0.4682468169065580600000000,
				0.6990564996301544800000000, 0.6894365712711156800000000, 0.4172222846773820900000000 };
		double Ismoke[] = { 0, 0.1638740910548557300000000, 0.3185144911395897900000000, 0.3220726656778343200000000,
				0.4505243716340953100000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_2 = Math.pow(dage, 3);
		double age_1 = Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_2 = Math.pow(dbmi, 3);
		double bmi_1 = Math.pow(dbmi, 2);

		/* Centring the continuous variables */

		age_1 = age_1 - 1.496392488479614;
		age_2 = age_2 - 89.048171997070313;
		bmi_1 = bmi_1 - 6.817805767059326;
		bmi_2 = bmi_2 - 17.801923751831055;
		town = town - 0.515986680984497;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 4.4642324388691348000000000;
		a += age_2 * -0.0040750108019255568000000;
		a += bmi_1 * 0.9512902786712067500000000;
		a += bmi_2 * -0.1435248827788547500000000;
		a += town * 0.0259181820676787250000000;

		/* Sum from boolean values */

		a += b_atypicalantipsy * 0.4210109234600543600000000;
		a += b_corticosteroids * 0.2218358093292538400000000;
		a += b_cvd * 0.2026960575629002100000000;
		a += b_learning * 0.2331532140798696100000000;
		a += b_manicschiz * 0.2277044952051772700000000;
		a += b_statin * 0.5849007543114134200000000;
		a += b_treatedhyp * 0.3337939218350107800000000;
		a += fh_diab * 0.6479928489936953600000000;

		/* Sum from interaction terms */

		a += age_1 * b_atypicalantipsy * -0.9463772226853415200000000;
		a += age_1 * b_learning * -0.9384237552649983300000000;
		a += age_1 * b_statin * -1.7479070653003299000000000;
		a += age_1 * bmi_1 * 0.4514759924187976600000000;
		a += age_1 * bmi_2 * -0.1079548126277638100000000;
		a += age_1 * fh_diab * -0.6011853042930119800000000;
		a += age_2 * b_atypicalantipsy * -0.0000519927442172335000000;
		a += age_2 * b_learning * 0.0007102643855968814100000;
		a += age_2 * b_statin * 0.0013508364599531669000000;
		a += age_2 * bmi_1 * -0.0011797722394560309000000;
		a += age_2 * bmi_2 * 0.0002147150913931929100000;
		a += age_2 * fh_diab * 0.0004914185594087803400000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	// Model B
	private double type2_B_male_raw(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd,
			int b_learning, int b_manicschiz, int b_statin, int b_treatedhyp, double bmi, int ethrisk, double fbs,
			int fh_diab, int smoke_cat, int surv, double town) {
		surv = 10;
		double survivor[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.985019445419312 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 1.0081475800686235000000000, 1.3359138425778705000000000,
				1.4815419524892652000000000, 1.0384996851820663000000000, 0.5202348070887524700000000,
				0.8579673418258558800000000, 0.6413108960765615500000000, 0.4838340220821504800000000 };
		double Ismoke[] = { 0, 0.1119475792364162500000000, 0.3110132095412204700000000, 0.3328898469326042100000000,
				0.4257069026941993100000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.log(dage);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, 2);
		double bmi_2 = Math.pow(dbmi, 3);
		double dfbs = fbs;
		;
		double fbs_1 = Math.pow(dfbs, -.5);
		double fbs_2 = Math.pow(dfbs, -.5) * Math.log(dfbs);

		/* Centring the continuous variables */

		age_1 = age_1 - 1.496392488479614;
		age_2 = age_2 - 89.048171997070313;
		bmi_1 = bmi_1 - 6.817805767059326;
		bmi_2 = bmi_2 - 17.801923751831055;
		fbs_1 = fbs_1 - 0.448028832674026;
		fbs_2 = fbs_2 - 0.719442605972290;
		town = town - 0.515986680984497;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 4.1149143302364717000000000;
		a += age_2 * -0.0047593576668505362000000;
		a += bmi_1 * 0.8169361587644297100000000;
		a += bmi_2 * -0.1250237740343336200000000;
		a += fbs_1 * -54.8417881280971070000000000;
		a += fbs_2 * -53.1120784984813600000000000;
		a += town * 0.0253741755198943560000000;

		/* Sum from boolean values */

		a += b_atypicalantipsy * 0.4417934088889577400000000;
		a += b_corticosteroids * 0.3413547348339454100000000;
		a += b_cvd * 0.2158977454372756600000000;
		a += b_learning * 0.4012885027585300100000000;
		a += b_manicschiz * 0.2181769391399779300000000;
		a += b_statin * 0.5147657600111734700000000;
		a += b_treatedhyp * 0.2467209287407037300000000;
		a += fh_diab * 0.5749437333987512700000000;

		/* Sum from interaction terms */

		a += age_1 * b_atypicalantipsy * -0.9502224313823126600000000;
		a += age_1 * b_learning * -0.8358370163090045300000000;
		a += age_1 * b_statin * -1.8141786919269460000000000;
		a += age_1 * bmi_1 * 0.3748482092078384600000000;
		a += age_1 * bmi_2 * -0.0909836579562487420000000;
		a += age_1 * fbs_1 * 21.0117301217643340000000000;
		a += age_1 * fbs_2 * 23.8244600447469740000000000;
		a += age_1 * fh_diab * -0.6780647705291665800000000;
		a += age_2 * b_atypicalantipsy * 0.0001472972077162874300000;
		a += age_2 * b_learning * 0.0006012919264966409100000;
		a += age_2 * b_statin * 0.0016393484911405418000000;
		a += age_2 * bmi_1 * -0.0010774782221531713000000;
		a += age_2 * bmi_2 * 0.0001911048730458310100000;
		a += age_2 * fbs_1 * -0.0390046079223835270000000;
		a += age_2 * fbs_2 * -0.0411277198058959470000000;
		a += age_2 * fh_diab * 0.0006257588248859499300000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	// Model C
	private double type2_C_male_raw(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd,
			int b_learning, int b_manicschiz, int b_statin, int b_treatedhyp, double bmi, int ethrisk, int fh_diab,
			double hba1c, int smoke_cat, int surv, double town) {
		surv = 10;
		double survivor[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.981181740760803 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.6757120705498780300000000, 0.8314732504966345600000000,
				1.0969133802228563000000000, 0.7682244636456048200000000, 0.2089752925910850200000000,
				0.3809159378197057900000000, 0.3423583679661269500000000, 0.2204647785343308300000000 };
		double Ismoke[] = { 0, 0.1159289120687865100000000, 0.1462418263763327100000000, 0.1078142411249314200000000,
				0.1984862916366847400000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.log(dage);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, 2);
		double bmi_2 = Math.pow(dbmi, 3);
		double dhba1c = hba1c;
		dhba1c = dhba1c / 10;
		double hba1c_1 = Math.pow(dhba1c, .5);
		double hba1c_2 = dhba1c;

		/* Centring the continuous variables */

		age_1 = age_1 - 1.496392488479614;
		age_2 = age_2 - 89.048171997070313;
		bmi_1 = bmi_1 - 6.817805767059326;
		bmi_2 = bmi_2 - 17.801923751831055;
		hba1c_1 = hba1c_1 - 1.900265336036682;
		hba1c_2 = hba1c_2 - 3.611008167266846;
		town = town - 0.515986680984497;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 4.0193435623978031000000000;
		a += age_2 * -0.0048396442306278238000000;
		a += bmi_1 * 0.8182916890534932500000000;
		a += bmi_2 * -0.1255880870135964200000000;
		a += hba1c_1 * 8.0511642238857934000000000;
		a += hba1c_2 * -0.1465234689391449500000000;
		a += town * 0.0252299651849007270000000;

		/* Sum from boolean values */

		a += b_atypicalantipsy * 0.4554152522017330100000000;
		a += b_corticosteroids * 0.1381618768682392200000000;
		a += b_cvd * 0.1454698889623951800000000;
		a += b_learning * 0.2596046658040857000000000;
		a += b_manicschiz * 0.2852378849058589400000000;
		a += b_statin * 0.4255195190118552500000000;
		a += b_treatedhyp * 0.3316943000645931100000000;
		a += fh_diab * 0.5661232594368061900000000;

		/* Sum from interaction terms */

		a += age_1 * b_atypicalantipsy * -1.0013331909079835000000000;
		a += age_1 * b_learning * -0.8916465737221592700000000;
		a += age_1 * b_statin * -1.7074561167819817000000000;
		a += age_1 * bmi_1 * 0.4507452747267244300000000;
		a += age_1 * bmi_2 * -0.1085185980916560100000000;
		a += age_1 * fh_diab * -0.6141009388709716100000000;
		a += age_1 * hba1c_1 * 27.6705938271465650000000000;
		a += age_1 * hba1c_2 * -7.4006134846785434000000000;
		a += age_2 * b_atypicalantipsy * 0.0002245597398574240700000;
		a += age_2 * b_learning * 0.0006604436076569648200000;
		a += age_2 * b_statin * 0.0013873509357389619000000;
		a += age_2 * bmi_1 * -0.0012224736160287865000000;
		a += age_2 * bmi_2 * 0.0002266731010346126000000;
		a += age_2 * fh_diab * 0.0005060258289477209100000;
		a += age_2 * hba1c_1 * -0.0592014581247543300000000;
		a += age_2 * hba1c_2 * 0.0155920894851499880000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	private String type2_validation(int age, int b_atypicalantipsy, int b_corticosteroids, int b_cvd, int b_gestdiab,
			int b_learning, int b_manicschiz, int b_pos, int b_statin, int b_treatedhyp, int ethrisk, double fbs,
			int fh_diab, double hba1c, int smoke_cat, int surv, double town, int gender) {

		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_atypicalantipsy)) {
			resultString += "error: b_atypicalantipsy must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_corticosteroids)) {
			resultString += "error: b_corticosteroids must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cvd)) {
			resultString += "error: b_cvd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_gestdiab)) {
			resultString += "error: b_gestdiab must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_learning)) {
			resultString += "error: b_learning must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_manicschiz)) {
			resultString += "error: b_manicschiz must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_pos)) {
			resultString += "error: b_pos must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_statin)) {
			resultString += "error: b_statin must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_treatedhyp)) {
			resultString += "error: b_treatedhyp must be in range (0,1)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {
			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (fbs != 0 && !CV.d_in_range(fbs, 2, 6.99)) {
			resultString += "error: fbs must be in range (2,6.99)\n";
		}
		if (!CV.is_boolean(fh_diab)) {
			resultString += "error: fh_diab must be in range (0,1)\n";
		}
		if (hba1c != 0 && !CV.d_in_range(hba1c, 15, 47.99)) {
			resultString += "error: hba1c must be in range (15,47.99)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (surv != 10) {
			resultString += "error: surv must be in range (1,10)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		if (!CV.is_boolean(gender)) {
			resultString += "error: b_cvd must be in range (0,1)\n";
		}

		return resultString;
	}

	public static void main(String[] args) {
		String input = "{ \"age\": 47, \"b_atypicalantipsy\": 0, \"b_corticosteroids\": 0, \"b_cvd\": 0, \"b_gestdiab\": 0, \"b_learning\": 0, \"b_manicschiz\": 0, \"b_pos\":0, \"b_statin\":0, \"b_treatedhyp\": 0, \"ethrisk\": 8, \"fh_diab\": 1, \"smoke_cat\": 4, \"surv\": 10, \"town\": 0.0, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		String input1= "{ \"age\": 47, \"b_atypicalantipsy\": 0, \"b_corticosteroids\": 0, \"b_cvd\": 0, \"b_gestdiab\": 0, \"b_learning\": 0, \"b_manicschiz\": 0, \"b_pos\":0, \"b_statin\":0, \"b_treatedhyp\": 0, \"ethrisk\": 8, \"fh_diab\": 1, \"hba1c\": 47, \"smoke_cat\": 4, \"surv\": 10, \"town\": 0.0, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		String input2= "{ \"age\": 47, \"b_atypicalantipsy\": 0, \"b_corticosteroids\": 0, \"b_cvd\": 0, \"b_gestdiab\": 0, \"b_learning\": 0, \"b_manicschiz\": 0, \"b_pos\":0, \"b_statin\":0, \"b_treatedhyp\": 0, \"ethrisk\": 8, \"fbs\": 80, \"fh_diab\": 1, \"smoke_cat\": 4, \"surv\": 10, \"town\": 0.0, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		PredictDiabete pd = new PredictDiabete();
		System.out.println("Json String = " + input);
		System.out.println("result = " + pd.predict(input));
		System.out.println("Json String = " + input1);
		System.out.println("result = " + pd.predict(input1));
		System.out.println("Json String = " + input2);
		System.out.println("result = " + pd.predict(input2));
	}

}
