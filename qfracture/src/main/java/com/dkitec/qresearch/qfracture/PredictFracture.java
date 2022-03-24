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

package com.dkitec.qresearch.qfracture;

import com.dkitec.qresearch.common.BmiCalculator;
import com.dkitec.qresearch.common.BmiPredictor;
import com.dkitec.qresearch.common.CV;
import com.dkitec.qresearch.common.CholRatioPredictor;
import com.dkitec.qresearch.common.Input;
import com.dkitec.qresearch.common.SbpPredictor;
import com.dkitec.redwood.api.predict.PredictScript;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PredictFracture implements PredictScript {

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
			errorMsg = fracture4_female_validation(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(),
					in.getB_anycancer(), in.getB_asthmacopd(), in.getB_corticosteroids(), in.getB_cvd(),
					in.getB_dementia(), in.getB_endocrine(), in.getB_epilepsy2(), in.getB_falls(), in.getB_fracture4(),
					in.getB_hrt_oest(), in.getB_liver(), in.getB_malabsorption(), in.getB_parkinsons(),
					in.getB_ra_sle(), in.getB_renal(), in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(),
					in.getFh_osteoporosis(), in.getSmoke_cat(), in.getSurv());
		} else {
			errorMsg = fracture4_male_validation(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(),
					in.getB_anycancer(), in.getB_asthmacopd(), in.getB_carehome(), in.getB_corticosteroids(),
					in.getB_cvd(), in.getB_dementia(), in.getB_epilepsy2(), in.getB_falls(), in.getB_fracture4(),
					in.getB_liver(), in.getB_malabsorption(), in.getB_parkinsons(), in.getB_ra_sle(), in.getB_renal(),
					in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_osteoporosis(),
					in.getSmoke_cat(), in.getSurv());
		}

		if (errorMsg.length() > 0) {
			return errorMsg;
		}

		if (in.getGender() == 0) {
			double result = fracture4_female_raw(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(),
					in.getB_anycancer(), in.getB_asthmacopd(), in.getB_corticosteroids(), in.getB_cvd(),
					in.getB_dementia(), in.getB_endocrine(), in.getB_epilepsy2(), in.getB_falls(), in.getB_fracture4(),
					in.getB_hrt_oest(), in.getB_liver(), in.getB_malabsorption(), in.getB_parkinsons(),
					in.getB_ra_sle(), in.getB_renal(), in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(),
					in.getFh_osteoporosis(), in.getSmoke_cat(), in.getSurv());

			return String.valueOf(result);
		} else {
			double result = fracture4_male_raw(in.getAge(), in.getAlcohol_cat6(), in.getB_antidepressant(),
					in.getB_anycancer(), in.getB_asthmacopd(), in.getB_carehome(), in.getB_corticosteroids(),
					in.getB_cvd(), in.getB_dementia(), in.getB_epilepsy2(), in.getB_falls(), in.getB_fracture4(),
					in.getB_liver(), in.getB_malabsorption(), in.getB_parkinsons(), in.getB_ra_sle(), in.getB_renal(),
					in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_osteoporosis(),
					in.getSmoke_cat(), in.getSurv());

			return String.valueOf(result);
		}
	}

	private String fracture4_female_validation(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer,
			int b_asthmacopd, int b_corticosteroids, int b_cvd, int b_dementia, int b_endocrine, int b_epilepsy2,
			int b_falls, int b_fracture4, int b_hrt_oest, int b_liver, int b_malabsorption, int b_parkinsons,
			int b_ra_sle, int b_renal, int b_type1, int b_type2, double bmi, int ethrisk, int fh_osteoporosis,
			int smoke_cat, int surv) {
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
		if (!CV.is_boolean(b_malabsorption)) {
			resultString += "error: b_malabsorption must be in range (0,1)\n";
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
		if (!CV.is_boolean(fh_osteoporosis)) {
			resultString += "error: fh_osteoporosis must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 18)) {
			resultString += "error: surv must be in range (1,18)\n";
		}

		return resultString;
	}

	private String fracture4_male_validation(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer,
			int b_asthmacopd, int b_carehome, int b_corticosteroids, int b_cvd, int b_dementia, int b_epilepsy2,
			int b_falls, int b_fracture4, int b_liver, int b_malabsorption, int b_parkinsons, int b_ra_sle, int b_renal,
			int b_type1, int b_type2, double bmi, int ethrisk, int fh_osteoporosis, int smoke_cat, int surv) {
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
		if (!CV.is_boolean(b_carehome)) {
			resultString += "error: b_carehome must be in range (0,1)\n";
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
		if (!CV.is_boolean(b_epilepsy2)) {
			resultString += "error: b_epilepsy2 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_falls)) {
			resultString += "error: b_falls must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_fracture4)) {
			resultString += "error: b_fracture4 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_liver)) {
			resultString += "error: b_liver must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_malabsorption)) {
			resultString += "error: b_malabsorption must be in range (0,1)\n";
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
		if (!CV.is_boolean(fh_osteoporosis)) {
			resultString += "error: fh_osteoporosis must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 18)) {
			resultString += "error: surv must be in range (1,18)\n";
		}

		return resultString;
	}

	private double fracture4_female_raw(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer,
			int b_asthmacopd, int b_corticosteroids, int b_cvd, int b_dementia, int b_endocrine, int b_epilepsy2,
			int b_falls, int b_fracture4, int b_hrt_oest, int b_liver, int b_malabsorption, int b_parkinsons,
			int b_ra_sle, int b_renal, int b_type1, int b_type2, double bmi, int ethrisk, int fh_osteoporosis,
			int smoke_cat, int surv) {
		double survivor[] = { 0, 0.998989343643188, 0.997853457927704, 0.996573925018311, 0.995212137699127,
				0.993696928024292, 0.991993606090546, 0.990109741687775, 0.988066852092743, 0.985876500606537,
				0.983377099037170, 0.980558633804321, 0.977307498455048, 0.974049806594849, 0.970507681369781,
				0.966851711273193, 0.962773561477661, 0.958411157131195, 0.953392088413239 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.0002414945264996203800000, 0.0531971614510470740000000, 0.1624289372927301400000000,
				0.4778223231666232600000000, 0.6270597140515218300000000 };
		double Iethrisk[] = { 0, 0, -0.2875917367450486200000000, -0.7824524516248326800000000,
				-0.8172794063622931300000000, -0.5861737865251788200000000, -1.4935356591327420000000000,
				-0.7355039455837261200000000, -0.4900951523299932300000000, -0.4546040850271730900000000 };
		double Ismoke[] = { 0, 0.0371938876652497460000000, 0.0951525414150192620000000, 0.1221740242710975300000000,
				0.1611412668468513200000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 2);
		double age_2 = Math.pow(dage, 3);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -1);

		/* Centring the continuous variables */

		age_1 = age_1 - 26.453824996948242;
		age_2 = age_2 - 136.060699462890620;
		bmi_1 = bmi_1 - 0.385703802108765;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.1437995480730194500000000;
		a += age_2 * -0.0093249719419669745000000;
		a += bmi_1 * 2.9094622051196999000000000;

		/* Sum from boolean values */

		a += b_antidepressant * 0.3175542392827512800000000;
		a += b_anycancer * 0.2384763167407743000000000;
		a += b_asthmacopd * 0.2389060345873167400000000;
		a += b_corticosteroids * 0.1926383637036637200000000;
		a += b_cvd * 0.1914278981809385300000000;
		a += b_dementia * 0.6757597945847583200000000;
		a += b_endocrine * 0.2105749527624362900000000;
		a += b_epilepsy2 * 0.4297240630789712600000000;
		a += b_falls * 0.4505018230780948300000000;
		a += b_fracture4 * 0.0804836468689180270000000;
		a += b_hrt_oest * -0.1586145398766347600000000;
		a += b_liver * 0.6391726322367494700000000;
		a += b_malabsorption * 0.1547851620897652300000000;
		a += b_parkinsons * 0.4958354577680105800000000;
		a += b_ra_sle * 0.2888701063403104600000000;
		a += b_renal * 0.2390562428559968600000000;
		a += b_type1 * 0.6523717632491761200000000;
		a += b_type2 * 0.2355143698342233000000000;
		a += fh_osteoporosis * 0.5517999076133333100000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	private double fracture4_male_raw(int age, int alcohol_cat6, int b_antidepressant, int b_anycancer,
			int b_asthmacopd, int b_carehome, int b_corticosteroids, int b_cvd, int b_dementia, int b_epilepsy2,
			int b_falls, int b_fracture4, int b_liver, int b_malabsorption, int b_parkinsons, int b_ra_sle, int b_renal,
			int b_type1, int b_type2, double bmi, int ethrisk, int fh_osteoporosis, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999644935131073, 0.999273777008057, 0.998869180679321, 0.998421549797058,
				0.997928738594055, 0.997390747070313, 0.996753513813019, 0.996096074581146, 0.995346963405609,
				0.994551837444305, 0.993598461151123, 0.992581725120544, 0.991482257843018, 0.990263521671295,
				0.989037752151489, 0.987761437892914, 0.986425399780273, 0.984853565692902 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, -0.0753424993511384030000000, 0.0035640920160520625000000, 0.1107180929467958700000000,
				0.2772772729818878100000000, 0.7629384134280495800000000 };
		double Iethrisk[] = { 0, 0, -0.2578247985190295600000000, -0.2739691601862618800000000,
				-1.2488100943578264000000000, -0.4478136903122282900000000, -0.9569833717832930700000000,
				-0.6454670770263975000000000, -0.2441668713268753100000000, -0.5585671879728931800000000 };
		double Ismoke[] = { 0, -0.0008039513520016420400000, 0.1560272763218023000000000, 0.2511740981322320700000000,
				0.2796740114008822700000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 0.5);
		double age_2 = dage;
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -1);
		double bmi_2 = Math.pow(dbmi, -0.5);

		/* Centring the continuous variables */

		age_1 = age_1 - 2.213409662246704;
		age_2 = age_2 - 4.899182319641113;
		bmi_1 = bmi_1 - 0.376987010240555;
		bmi_2 = bmi_2 - 0.613992691040039;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -9.0010590056070825000000000;
		a += age_2 * 2.4013416577413533000000000;
		a += bmi_1 * 18.1789865484634670000000000;
		a += bmi_2 * -18.9164740466035500000000000;

		/* Sum from boolean values */

		a += b_antidepressant * 0.4687193755788741600000000;
		a += b_anycancer * 0.4507500533865196300000000;
		a += b_asthmacopd * 0.2886693311011971400000000;
		a += b_carehome * 0.4624017599741130900000000;
		a += b_corticosteroids * 0.2959070482702296200000000;
		a += b_cvd * 0.2342575101174369000000000;
		a += b_dementia * 0.6410107589079159200000000;
		a += b_epilepsy2 * 0.7821394592420207700000000;
		a += b_falls * 0.5427801687901475700000000;
		a += b_fracture4 * 0.3037648317094442400000000;
		a += b_liver * 0.9492983471493211500000000;
		a += b_malabsorption * 0.2198043397723023800000000;
		a += b_parkinsons * 0.8971315042849318200000000;
		a += b_ra_sle * 0.4403191212798893100000000;
		a += b_renal * 0.4565029417822387700000000;
		a += b_type1 * 0.8447272010743575000000000;
		a += b_type2 * 0.2219385025905733500000000;
		a += fh_osteoporosis * 1.6999403855072708000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static void main(String[] args) {
		String input = "{ \"age\": 47, \"alcohol_cat6\": 1, \"b_antidepressant\": 0, \"b_anycancer\": 0, \"b_asthmacopd\": 0, \"b_carehome\": 0, \"b_corticosteroids\": 0, \"b_cvd\": 0, \"b_dementia\": 0, \"b_endocrine\": 0, \"b_epilepsy2\": 0, \"b_falls\": 0, \"b_fracture4\": 0, \"b_hrt_oest\": 0, \"b_liver\": 0, \"b_malabsorption\": 0, \"b_parkinsons\": 0, \"b_ra_sle\": 0, \"b_renal\": 0, \"diabetes_cat\": 0, \"fh_osteoporosis\": 0, \"smoke_cat\": 1, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		String input1 = "{ \"age\": 47, \"alcohol_cat6\": 1, \"b_antidepressant\": 0, \"b_anycancer\": 0, \"b_asthmacopd\": 0, \"b_carehome\": 0, \"b_corticosteroids\": 0, \"b_cvd\": 0, \"b_dementia\": 0, \"b_endocrine\": 0, \"b_epilepsy2\": 0, \"b_falls\": 0, \"b_fracture4\": 0, \"b_hrt_oest\": 0, \"b_liver\": 0, \"b_malabsorption\": 0, \"b_parkinsons\": 0, \"b_ra_sle\": 0, \"b_renal\": 0, \"diabetes_cat\": 0, \"fh_osteoporosis\": 0, \"smoke_cat\": 1, \"gender\": 0, \"height\": 168, \"weight\": 58}";
		PredictFracture pd = new PredictFracture();
		System.out.println("Json String = " + input);
		System.out.println("result = " + pd.predict(input));
		System.out.println("Json String = " + input1);
		System.out.println("result = " + pd.predict(input1));
	}

}
