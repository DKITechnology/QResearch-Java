/* 
 * Copyright 2010 ClinRisk Ltd.
 * 
 * This file is part of QKidney-2010 (http://qkidney.org, http://svn.clinrisk.co.uk/opensource/qkidney).
 * 
 * QKidney-2010 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QKidney-2010 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with QKidney-2010.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The initial version of this file, to be found at http://svn.clinrisk.co.uk/opensource/qkidney, faithfully implements QKidney-2010.
 * We have released this code under the GNU Lesser General Public License to enable others to implement the algorithm faithfully.
 * However, the nature of the GNU Lesser General Public License is such that we cannot prevent, for example, someone altering the coefficients.
 * We stress, therefore, that it is the responsibility of the end user to check that the source that they receive produces the same results as the original code posted at http://svn.clinrisk.co.uk/opensource/qkidney.
 * Inaccurate implementations of risk scores can lead to wrong patients being given the wrong treatment.
 * 
 * This file has been auto-generated.
 * XML source: Q54_kidney_xml_51_neph5_0.xml
 * STATA dta time stamp: 25 Mar 2010 07:28
 * C file create date: Wed Nov 10 13:59:57 GMT 2010
 */

package com.dkitec.qresearch.qkidney;

import com.dkitec.qresearch.common.BmiCalculator;
import com.dkitec.qresearch.common.BmiPredictor;
import com.dkitec.qresearch.common.CV;
import com.dkitec.qresearch.common.CholRatioPredictor;
import com.dkitec.qresearch.common.Input;
import com.dkitec.qresearch.common.SbpPredictor;
import com.dkitec.redwood.api.predict.PredictScript;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PredictKidneyNeph5 implements PredictScript {

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

		if (in.getSbp() == 0)
			in.setSbp(SbpPredictor.predict(in.getAge(), in.getB_cvd(), in.getB_treatedhyp(), in.getFh_diab(),
					in.getEthrisk(), in.getSmoke_cat(), in.getGender()));

		String errorMsg = null;
		
		if(in.getSurv() == 10) in.setSurv(5);
		in.setEthrisk(9);

		if (in.getGender() == 0) {
			errorMsg = neph5_female_validation(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_pvd(), in.getB_ra(),
					in.getB_renalstones(), in.getB_sle(), in.getB_treatedhyp(), in.getB_type1(), in.getB_type2(),
					in.getBmi(), in.getEthrisk(), in.getFh_kidney(), in.getSbp(), in.getSmoke_cat(), in.getSurv(),
					in.getTown());
		} else {
			errorMsg = neph5_male_validation(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_pvd(), in.getB_ra(),
					in.getB_treatedhyp(), in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(),
					in.getFh_kidney(), in.getSbp(), in.getSmoke_cat(), in.getSurv(), in.getTown());
		}

		if (errorMsg.length() > 0) {
			return errorMsg;
		}

		if (in.getGender() == 0) {
			double result = neph5_female_raw(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_pvd(), in.getB_ra(),
					in.getB_renalstones(), in.getB_sle(), in.getB_treatedhyp(), in.getB_type1(), in.getB_type2(),
					in.getBmi(), in.getEthrisk(), in.getFh_kidney(), in.getSbp(), in.getSmoke_cat(), in.getSurv(),
					in.getTown());

			return String.valueOf(result);
		} else {
			double result = neph5_male_raw(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_pvd(), in.getB_ra(),
					in.getB_treatedhyp(), in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(),
					in.getFh_kidney(), in.getSbp(), in.getSmoke_cat(), in.getSurv(), in.getTown());

			return String.valueOf(result);
		}
	}

	private String neph5_female_validation(int age, int b_CCF, int b_cvd, int b_pvd, int b_ra, int b_renalstones,
			int b_sle, int b_treatedhyp, int b_type1, int b_type2, double bmi, int ethrisk, int fh_kidney, double sbp,
			int smoke_cat, int surv, double town) {
		String resultString = "";

		if (!CV.i_in_range(age, 35, 74)) {
			resultString += "error: age must be in range (35,74)\n";
		}
		if (!CV.is_boolean(b_CCF)) {
			resultString += "error: b_CCF must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cvd)) {
			resultString += "error: b_cvd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_pvd)) {
			resultString += "error: b_pvd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ra)) {
			resultString += "error: b_ra must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renalstones)) {
			resultString += "error: b_renalstones must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_sle)) {
			resultString += "error: b_sle must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_treatedhyp)) {
			resultString += "error: b_treatedhyp must be in range (0,1)\n";
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
		if (!CV.is_boolean(fh_kidney)) {
			resultString += "error: fh_kidney must be in range (0,1)\n";
		}
		if (!CV.d_in_range(sbp, 70, 210)) {
			resultString += "error: sbp must be in range (70,210)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 5)) {
			resultString += "error: surv must be in range (1,5)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}

		return resultString;
	}

	private String neph5_male_validation(int age, int b_CCF, int b_cvd, int b_pvd, int b_ra, int b_treatedhyp,
			int b_type1, int b_type2, double bmi, int ethrisk, int fh_kidney, double sbp, int smoke_cat, int surv,
			double town) {
		String resultString = "";

		if (!CV.i_in_range(age, 35, 74)) {
			resultString += "error: age must be in range (35,74)\n";
		}
		if (!CV.is_boolean(b_CCF)) {
			resultString += "error: b_CCF must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cvd)) {
			resultString += "error: b_cvd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_pvd)) {
			resultString += "error: b_pvd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ra)) {
			resultString += "error: b_ra must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_treatedhyp)) {
			resultString += "error: b_treatedhyp must be in range (0,1)\n";
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
		if (!CV.is_boolean(fh_kidney)) {
			resultString += "error: fh_kidney must be in range (0,1)\n";
		}
		if (!CV.d_in_range(sbp, 70, 210)) {
			resultString += "error: sbp must be in range (70,210)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 5)) {
			resultString += "error: surv must be in range (1,5)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}

		return resultString;
	}

	private double neph5_female_raw(int age, int b_CCF, int b_cvd, int b_pvd, int b_ra, int b_renalstones, int b_sle,
			int b_treatedhyp, int b_type1, int b_type2, double bmi, int ethrisk, int fh_kidney, double sbp,
			int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999950349330902, 0.999883890151978, 0.999821722507477, 0.999749183654785,
				0.999661386013031 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.1467681155300852000000000, 0.9069329917093114100000000,
				-0.0769419044292020130000000, 1.1224305908740781000000000, 0.0118706084001904200000000,
				0.4913159784965747100000000, 1.2513498001807077000000000, 0.3049631962804313400000000 };
		double Ismoke[] = { 0, 0.1982163678862090000000000, 0.3696807115042248800000000, 0.0805652774486685930000000,
				0.3552419997737879500000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 3);
		double age_2 = Math.pow(dage, 3) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);
		double dsbp = sbp;
		dsbp = dsbp / 100;
		double sbp_1 = Math.pow(dsbp, -2);
		double sbp_2 = Math.pow(dsbp, -2) * Math.log(dsbp);

		/* Centring the continuous variables */

		age_1 = age_1 - 135.125152587890630;
		age_2 = age_2 - 220.983749389648440;
		bmi_1 = bmi_1 - 0.142878264188766;
		bmi_2 = bmi_2 - 0.139003574848175;
		sbp_1 = sbp_1 - 0.582698464393616;
		sbp_2 = sbp_2 - 0.157353490591049;
		town = town - -0.601529955863953;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.0044905211680529760000000;
		a += age_2 * 0.0013741026779469333000000;
		a += bmi_1 * 6.2772064718078511000000000;
		a += bmi_2 * -16.2325496798837570000000000;
		a += sbp_1 * -3.4926352766269355000000000;
		a += sbp_2 * -9.8319594804682797000000000;
		a += town * 0.0418789835204452860000000;

		/* Sum from boolean values */

		a += b_CCF * 1.4931034261471803000000000;
		a += b_cvd * 0.2965409385629423400000000;
		a += b_pvd * 0.5311042284323143100000000;
		a += b_ra * 0.4182846073717931300000000;
		a += b_renalstones * 0.7263237599546400500000000;
		a += b_sle * 1.5446429177437644000000000;
		a += b_treatedhyp * 1.5689061804732225000000000;
		a += b_type1 * 3.1035727563953652000000000;
		a += b_type2 * 1.5424275118825657000000000;
		a += fh_kidney * 1.8581736287905835000000000;

		/* Sum from interaction terms */

		a += age_1 * b_treatedhyp * -0.0962810052821665010000000;
		a += age_1 * b_type1 * 0.1286871175982452500000000;
		a += age_1 * b_type2 * 0.0669892258796831310000000;
		a += age_2 * b_treatedhyp * 0.0423111167651291990000000;
		a += age_2 * b_type1 * -0.0630262286739049910000000;
		a += age_2 * b_type2 * -0.0317986736216282070000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	private double neph5_male_raw(int age, int b_CCF, int b_cvd, int b_pvd, int b_ra, int b_treatedhyp, int b_type1,
			int b_type2, double bmi, int ethrisk, int fh_kidney, double sbp, int smoke_cat, int surv, double town) {
		
		double survivor[] = { 0, 0.999939739704132, 0.999862551689148, 0.999781429767609, 0.999686837196350,
				0.999580204486847 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.3118422281234448600000000, 0.6046214991605259900000000,
				0.1997351989363300700000000, 0.8719077058136131000000000, 0.4472807032846115000000000,
				0.6239995832114189100000000, -39.8772311231235220000000000, -0.0403646857587661450000000 };
		double Ismoke[] = { 0, 0.1448433047793812300000000, 0.1604938050389068700000000, 0.2879969011526650200000000,
				0.0857451407666546960000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 3);
		double age_2 = Math.pow(dage, 3) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);
		double dsbp = sbp;
		dsbp = dsbp / 100;
		double sbp_1 = Math.pow(dsbp, -2);
		double sbp_2 = Math.pow(dsbp, -.5);

		/* Centring the continuous variables */

		age_1 = age_1 - 130.458740234375000;
		age_2 = age_2 - 211.823989868164060;
		bmi_1 = bmi_1 - 0.138219580054283;
		bmi_2 = bmi_2 - 0.136762171983719;
		sbp_1 = sbp_1 - 0.551189959049225;
		sbp_2 = sbp_2 - 0.861638963222504;
		town = town - -0.442067831754684;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.0184006601576099170000000;
		a += age_2 * -0.0048768304842849858000000;
		a += bmi_1 * 10.3099642227217410000000000;
		a += bmi_2 * -23.3594109892901290000000000;
		a += sbp_1 * 6.4536856404376355000000000;
		a += sbp_2 * -19.1115753263925220000000000;
		a += town * 0.0191429999185772260000000;

		/* Sum from boolean values */

		a += b_CCF * 1.3921380045634140000000000;
		a += b_cvd * 0.2955530208386106300000000;
		a += b_pvd * 0.6843946875219021300000000;
		a += b_ra * 0.4270658680222138600000000;
		a += b_treatedhyp * 1.9121501989990664000000000;
		a += b_type1 * 2.4260612036077056000000000;
		a += b_type2 * 1.0249996940958706000000000;
		a += fh_kidney * 2.2703957707797713000000000;

		/* Sum from interaction terms */

		a += age_1 * b_treatedhyp * -0.0814284149461020350000000;
		a += age_1 * b_type1 * 0.0058630274302377793000000;
		a += age_1 * b_type2 * 0.0409331247189833530000000;
		a += age_2 * b_treatedhyp * 0.0349522187288968940000000;
		a += age_2 * b_type1 * -0.0053628404874913266000000;
		a += age_2 * b_type2 * -0.0195394363574293430000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static void main(String[] args) {
		String input = "{ \"age\": 47, \"b_CCF\": 0, \"b_cvd\": 0, \"b_pvd\": 0, \"b_ra\": 0, \"b_renalstones\": 0, \"b_sle\": 0, \"b_treatedhyp\": 0, \"diabetes_cat\": 0, \"fh_kidney\": 0, \"smoke_cat\": 1, \"gender\": 0, \"height\": 168, \"weight\": 58}";
		String input1 = "{ \"age\": 47, \"b_CCF\": 0, \"b_cvd\": 0, \"b_pvd\": 0, \"b_ra\": 0, \"b_renalstones\": 0, \"b_sle\": 0, \"b_treatedhyp\": 0, \"diabetes_cat\": 0, \"fh_kidney\": 0, \"smoke_cat\": 1, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		PredictKidneyNeph5 pd = new PredictKidneyNeph5();
		System.out.println("Json String = " + input);
		System.out.println("result = " + pd.predict(input));
		System.out.println("Json String = " + input1);
		System.out.println("result = " + pd.predict(input1));
	}

}
