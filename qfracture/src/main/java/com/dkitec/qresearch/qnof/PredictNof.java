/* 
 * Copyright 2012 ClinRisk Ltd.
 * 
 * This file is part of QFracture-2012 (http://qfracture.org, http://svn.clinrisk.co.uk/qfracture).
 * 
 * QFracture-2012 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QFracture-2012 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with QFracture-2012.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The initial version of this file, to be found at http://svn.clinrisk.co.uk/qfracture, faithfully implements QFracture-2012.
 * We have released this code under the GNU Lesser General Public License to enable others to implement the algorithm faithfully.
 * However, the nature of the GNU Lesser General Public License is such that we cannot prevent, for example, someone altering the coefficients.
 * We stress, therefore, that it is the responsibility of the end user to check that the source that they receive produces the same results as the original code posted at http://svn.clinrisk.co.uk/qfracture.
 * Inaccurate implementations of risk scores can lead to wrong patients being given the wrong treatment.
 * 
 * This file has been auto-generated.
 * XML source: Q74_qfracture4_2012_2_1.xml
 * STATA dta time stamp: 1 Mar 2012 11:21
 * C file create date: Mon  5 Mar 2012 11:14:11 GMT
 */

package com.dkitec.qresearch.qnof;

import com.dkitec.qresearch.common.BmiCalculator;
import com.dkitec.qresearch.common.BmiPredictor;
import com.dkitec.qresearch.common.CV;
import com.dkitec.qresearch.common.CholRatioPredictor;
import com.dkitec.qresearch.common.Input;
import com.dkitec.qresearch.common.SbpPredictor;
import com.dkitec.redwood.api.predict.PredictScript;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PredictNof implements PredictScript {

	public void loadModel(String modelPath) {

	}

	public String predict(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper();

		// 입력변수 초기화
		Input in = null;
		try {
			in = mapper.readValue(jsonStr, Input.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (in.getHeight() > 0 && in.getWeight() > 0)
			in.setBmi(BmiCalculator.calculate(in.getHeight(), in.getWeight()));
		else
			in.setBmi(BmiPredictor.predict(in.getAge(), 0, 0, 0, in.getEthrisk(), in.getSmoke_cat(), in.getGender()));

		String errorMsg = null;

		if (in.getGender() == 0) {
			errorMsg = nof_female_validation(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(), in.getB_anycancer(),
					in.getB_asthmacopd(), in.getB_corticosteroids(), in.getB_cvd(), in.getB_dementia(), in.getB_endocrine(), in.getB_epilepsy2(),
					in.getB_falls(), in.getB_fracture4(), in.getB_hrt_oest(), in.getB_liver(), in.getB_parkinsons(), in.getB_ra_sle(), in.getB_renal(),
					in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getSmoke_cat(), in.getSurv());
		} else {
			errorMsg = nof_male_validation(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(), in.getB_anycancer(),
					in.getB_asthmacopd(), in.getB_carehome(), in.getB_corticosteroids(), in.getB_cvd(), in.getB_dementia(), in.getB_epilepsy2(),
					in.getB_falls(), in.getB_fracture4(), in.getB_liver(), in.getB_parkinsons(), in.getB_ra_sle(), in.getB_renal(), in.getB_type1(),
					in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_osteoporosis(), in.getSmoke_cat(), in.getSurv());
		}

		if (errorMsg.length() > 0) {
			return errorMsg;
		}

		if (in.getGender() == 0) {
			double result = nof_female_raw(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(), in.getB_anycancer(),
					in.getB_asthmacopd(), in.getB_corticosteroids(), in.getB_cvd(), in.getB_dementia(), in.getB_endocrine(), in.getB_epilepsy2(),
					in.getB_falls(), in.getB_fracture4(), in.getB_hrt_oest(), in.getB_liver(), in.getB_parkinsons(), in.getB_ra_sle(), in.getB_renal(),
					in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getSmoke_cat(), in.getSurv());

			return String.valueOf(result);
		} else {
			double result = nof_male_raw(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(), in.getB_anycancer(),
					in.getB_asthmacopd(), in.getB_carehome(), in.getB_corticosteroids(), in.getB_cvd(), in.getB_dementia(), in.getB_epilepsy2(),
					in.getB_falls(), in.getB_fracture4(), in.getB_liver(), in.getB_parkinsons(), in.getB_ra_sle(), in.getB_renal(), in.getB_type1(),
					in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_osteoporosis(), in.getSmoke_cat(), in.getSurv());

			return String.valueOf(result);
		}
	}

	private String nof_female_validation(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer,
			int b_asthmacopd, int b_corticosteroids, int b_cvd, int b_dementia, int b_endocrine, int b_epilepsy2,
			int b_falls, int b_fracture4, int b_hrt_oest, int b_liver, int b_parkinsons, int b_ra_sle, int b_renal,
			int b_type1, int b_type2, double bmi, int ethrisk, int smoke_cat, int surv) {

		String resultString = "";

		if (!CV.i_in_range(age, 30, 100)) {
			resultString += "error: age must be in range (30,100)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {
			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_antidepressant)) {
			resultString += "error: b_antidepressant must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_anycancer)) {
			resultString += "error: b_anycancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_asthmacopd)) {
			resultString += "error: b_asthmacopd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_corticosteroids)) {
			resultString += "error: b_corticosteroids must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cvd)) {
			resultString += "error: b_cvd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_dementia)) {
			resultString += "error: b_dementia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_endocrine)) {
			resultString += "error: b_endocrine must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_epilepsy2)) {
			resultString += "error: b_epilepsy2 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_falls)) {
			resultString += "error: b_falls must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_fracture4)) {
			resultString += "error: b_fracture4 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_hrt_oest)) {
			resultString += "error: b_hrt_oest must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_liver)) {
			resultString += "error: b_liver must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_parkinsons)) {
			resultString += "error: b_parkinsons must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ra_sle)) {
			resultString += "error: b_ra_sle must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renal)) {
			resultString += "error: b_renal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type1)) {
			resultString += "error: b_type1 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {
			resultString += "error: b_type2 must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {
			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 18)) {
			resultString += "error: surv must be in range (1,18)\n";
		}

		return resultString;
	}

	private String nof_male_validation(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer,
			int b_asthmacopd, int b_carehome, int b_corticosteroids, int b_cvd, int b_dementia, int b_epilepsy2,
			int b_falls, int b_fracture4, int b_liver, int b_parkinsons, int b_ra_sle, int b_renal, int b_type1,
			int b_type2, double bmi, int ethrisk, int fh_osteoporosis, int smoke_cat, int surv) {

		String resultString = "";

		if (!CV.i_in_range(age, 30, 100)) {
			resultString +=  "error: age must be in range (30,100)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {
			resultString +=  "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_antidepressant)) {
			resultString +=  "error: b_antidepressant must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_anycancer)) {
			resultString +=  "error: b_anycancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_asthmacopd)) {
			resultString +=  "error: b_asthmacopd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_carehome)) {
			resultString +=  "error: b_carehome must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_corticosteroids)) {
			resultString +=  "error: b_corticosteroids must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cvd)) {
			resultString +=  "error: b_cvd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_dementia)) {
			resultString +=  "error: b_dementia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_epilepsy2)) {
			resultString +=  "error: b_epilepsy2 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_falls)) {
			resultString +=  "error: b_falls must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_fracture4)) {
			resultString +=  "error: b_fracture4 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_liver)) {
			resultString +=  "error: b_liver must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_parkinsons)) {
			resultString +=  "error: b_parkinsons must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ra_sle)) {
			resultString +=  "error: b_ra_sle must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renal)) {
			resultString +=  "error: b_renal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type1)) {
			resultString +=  "error: b_type1 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {
			resultString +=  "error: b_type2 must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString +=  "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {
			resultString +=  "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.is_boolean(fh_osteoporosis)) {
			resultString +=  "error: fh_osteoporosis must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString +=  "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 18)) {
			resultString +=  "error: surv must be in range (1,18)\n";
		}

		return resultString;
	}

	private double nof_female_raw(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer, int b_asthmacopd,
			int b_corticosteroids, int b_cvd, int b_dementia, int b_endocrine, int b_epilepsy2, int b_falls,
			int b_fracture4, int b_hrt_oest, int b_liver, int b_parkinsons, int b_ra_sle, int b_renal, int b_type1,
			int b_type2, double bmi, int ethrisk, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999910116195679, 0.999807536602020, 0.999693453311920, 0.999558806419373,
				0.999401748180389, 0.999216556549072, 0.999019324779511, 0.998785734176636, 0.998515725135803,
				0.998187243938446, 0.997814238071442, 0.997377276420593, 0.996870458126068, 0.996309578418732,
				0.995707631111145, 0.995065331459045, 0.994253218173981, 0.993366956710815 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, -0.1286446642326926600000000, -0.0997737785682041020000000,
				0.0542649888398008330000000, 0.4431543152512633600000000, 0.6633035785016026000000000 };
		double Iethrisk[] = { 0, 0, -0.5145680493118204300000000, -0.7809041138976792200000000,
				-0.5845922612624047100000000, -0.5418443926512139800000000, -1.3017049081958438000000000,
				-2.3170037513024733000000000, -1.0406259543469680000000000, -0.7087921758363630000000000 };
		double Ismoke[] = { 0, 0.0794965480333605090000000, 0.2835141126936128200000000, 0.3121458383725539400000000,
				0.4798404329218986000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 2);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);

		/* Centring the continuous variables */

		age_1 = age_1 - 26.304763793945313;
		age_2 = age_2 - 134.912322998046870;
		bmi_1 = bmi_1 - 0.148731395602226;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.2707690096878486700000000;
		a += age_2 * -0.0178911237651764690000000;
		a += bmi_1 * 5.7829320185166670000000000;

		/* Sum from boolean values */

		a += b_antidepressant * 0.3327369489309151000000000;
		a += b_anycancer * 0.2685285338413267400000000;
		a += b_asthmacopd * 0.2109878042448810100000000;
		a += b_corticosteroids * 0.1700600172324418800000000;
		a += b_cvd * 0.2023318326470322500000000;
		a += b_dementia * 0.9427384234437306000000000;
		a += b_endocrine * 0.2873219609276543900000000;
		a += b_epilepsy2 * 0.4800826666037592000000000;
		a += b_falls * 0.4341031983222869400000000;
		a += b_fracture4 * 0.5498089896441453700000000;
		a += b_hrt_oest * -0.2717838245725980300000000;
		a += b_liver * 0.6449388096998517300000000;
		a += b_parkinsons * 0.7086700991849171900000000;
		a += b_ra_sle * 0.5226829686337491900000000;
		a += b_renal * 0.4121883090139577500000000;
		a += b_type1 * 1.5320881578751737000000000;
		a += b_type2 * 0.4487045379402456700000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	private double nof_male_raw(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer, int b_asthmacopd,
			int b_carehome, int b_corticosteroids, int b_cvd, int b_dementia, int b_epilepsy2, int b_falls,
			int b_fracture4, int b_liver, int b_parkinsons, int b_ra_sle, int b_renal, int b_type1, int b_type2,
			double bmi, int ethrisk, int fh_osteoporosis, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999958395957947, 0.999915361404419, 0.999862074851990, 0.999795675277710,
				0.999720335006714, 0.999637126922607, 0.999530315399170, 0.999413371086121, 0.999267756938934,
				0.999112963676453, 0.998929023742676, 0.998713493347168, 0.998442947864532, 0.998135447502136,
				0.997829139232636, 0.997516810894012, 0.997076392173767, 0.996633410453796 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, -0.1883071508763912700000000, -0.1456237141772618900000000,
				-0.1131015985038896200000000, 0.2669108383852995000000000, 0.7159049108970482200000000 };
		double Iethrisk[] = { 0, 0, -0.4720554035932271700000000, -0.4404885564307023900000000,
				-2.0311044284508650000000000, -0.8877544935355209400000000, -1.5093354044488063000000000,
				-0.1169655869663822200000000, -0.7810018330580403800000000, -0.2253671795533221900000000 };
		double Ismoke[] = { 0, -0.0156465395681702860000000, 0.2947168223225690200000000, 0.4319073634973120700000000,
				0.4937619134916043700000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 3);
		double age_2 = Math.pow(dage, 3) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);

		/* Centring the continuous variables */

		age_1 = age_1 - 117.376983642578130;
		age_2 = age_2 - 186.449066162109370;
		bmi_1 = bmi_1 - 0.142089113593102;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.0470956645877030970000000;
		a += age_2 * -0.0173232541198013180000000;
		a += bmi_1 * 6.9051198985719147000000000;

		/* Sum from boolean values */

		a += b_antidepressant * 0.5222696860482879400000000;
		a += b_anycancer * 0.3904642661797034200000000;
		a += b_asthmacopd * 0.2955316362120945000000000;
		a += b_carehome * 0.7180133962015686800000000;
		a += b_corticosteroids * 0.1637845766085505300000000;
		a += b_cvd * 0.2685578286436679000000000;
		a += b_dementia * 0.9660867715544014800000000;
		a += b_epilepsy2 * 0.8977271850145135400000000;
		a += b_falls * 0.5314298176292541200000000;
		a += b_fracture4 * 0.7025297516317516900000000;
		a += b_liver * 0.7566576273364045100000000;
		a += b_parkinsons * 1.0980688140356138000000000;
		a += b_ra_sle * 0.6434807364258057200000000;
		a += b_renal * 0.5918218708907634400000000;
		a += b_type1 * 1.5742324490573854000000000;
		a += b_type2 * 0.2887768858842130200000000;
		a += fh_osteoporosis * 1.2332490177632631000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static void main(String[] args) {
		String input = "{ \"age\": 47, \"alcohol_cat6\": 1, \"b_antidepressant\": 0, \"b_anycancer\": 0, \"b_asthmacopd\": 0, \"b_carehome\": 0, \"b_corticosteroids\": 0, \"b_cvd\": 0, \"b_dementia\": 0, \"b_endocrine\": 0, \"b_epilepsy2\": 0, \"b_falls\": 0, \"b_fracture4\": 0, \"b_hrt_oest\": 0, \"b_liver\": 0, \"b_parkinsons\": 0, \"b_ra_sle\": 0, \"b_renal\": 0, \"diabetes_cat\": 0, \"fh_osteoporosis\": 0, \"smoke_cat\": 1, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		String input1 = "{ \"age\": 47, \"alcohol_cat6\": 1, \"b_antidepressant\": 0, \"b_anycancer\": 0, \"b_asthmacopd\": 0, \"b_carehome\": 0, \"b_corticosteroids\": 0, \"b_cvd\": 0, \"b_dementia\": 0, \"b_endocrine\": 0, \"b_epilepsy2\": 0, \"b_falls\": 0, \"b_fracture4\": 0, \"b_hrt_oest\": 0, \"b_liver\": 0, \"b_parkinsons\": 0, \"b_ra_sle\": 0, \"b_renal\": 0, \"diabetes_cat\": 0, \"fh_osteoporosis\": 0, \"smoke_cat\": 1, \"gender\": 0, \"height\": 168, \"weight\": 58}";
		PredictNof pd = new PredictNof();
		System.out.println("Json String = " + input);
		System.out.println("result = " + pd.predict(input));
		System.out.println("Json String = " + input1);
		System.out.println("result = " + pd.predict(input1));
	}

}
