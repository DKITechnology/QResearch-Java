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
 * XML source: Q54_kidney_xml_51_neph3_0.xml
 * STATA dta time stamp: 25 Mar 2010 07:28
 * C file create date: Wed Nov 10 13:59:22 GMT 2010
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

public class PredictKidneyNeph3 implements PredictScript {

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

		if (in.getGender() == 0) {
			errorMsg = neph3_female_validation(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_nsaid(),
					in.getB_pvd(), in.getB_ra(), in.getB_renalstones(), in.getB_sle(), in.getB_treatedhyp(),
					in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_kidney(), in.getSbp(),
					in.getSmoke_cat(), in.getSurv(), in.getTown());
		} else {
			errorMsg = neph3_male_validation(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_nsaid(),
					in.getB_pvd(), in.getB_ra(), in.getB_treatedhyp(), in.getB_type1(), in.getB_type2(), in.getBmi(),
					in.getEthrisk(), in.getFh_kidney(), in.getSbp(), in.getSmoke_cat(), in.getSurv(), in.getTown());
		}

		if (errorMsg.length() > 0) {
			return errorMsg;
		}

		if (in.getGender() == 0) {
			double result = neph3_female_raw(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_nsaid(),
					in.getB_pvd(), in.getB_ra(), in.getB_renalstones(), in.getB_sle(), in.getB_treatedhyp(),
					in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_kidney(), in.getSbp(),
					in.getSmoke_cat(), in.getSurv(), in.getTown());

			return String.valueOf(result);
		} else {
			double result = neph3_male_raw(in.getAge(), in.getB_CCF(), in.getB_cvd(), in.getB_nsaid(),
					in.getB_pvd(), in.getB_ra(), in.getB_treatedhyp(), in.getB_type1(), in.getB_type2(), in.getBmi(),
					in.getEthrisk(), in.getFh_kidney(), in.getSbp(), in.getSmoke_cat(), in.getSurv(), in.getTown());

			return String.valueOf(result);
		}
	}

	private String neph3_female_validation(int age, int b_CCF, int b_cvd, int b_nsaid, int b_pvd, int b_ra,
			int b_renalstones, int b_sle, int b_treatedhyp, int b_type1, int b_type2, double bmi, int ethrisk,
			int fh_kidney, double sbp, int smoke_cat, int surv, double town) {
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
		if (!CV.is_boolean(b_nsaid)) {
			resultString += "error: b_nsaid must be in range (0,1)\n";
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

	private String neph3_male_validation(int age, int b_CCF, int b_cvd, int b_nsaid, int b_pvd, int b_ra,
			int b_treatedhyp, int b_type1, int b_type2, double bmi, int ethrisk, int fh_kidney, double sbp,
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
		if (!CV.is_boolean(b_nsaid)) {
			resultString += "error: b_nsaid must be in range (0,1)\n";
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

	private double neph3_female_raw(int age, int b_CCF, int b_cvd, int b_nsaid, int b_pvd, int b_ra, int b_renalstones,
			int b_sle, int b_treatedhyp, int b_type1, int b_type2, double bmi, int ethrisk, int fh_kidney, double sbp,
			int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999198436737061, 0.998197913169861, 0.996961772441864, 0.995605170726776,
				0.994150519371033 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.0297650380606283470000000, 0.4355484308510417800000000,
				0.4023403121262871300000000, 0.1510682354063334300000000, -0.7276014058191869700000000,
				-0.5721584379149783400000000, 0.1202074407607906200000000, 0.2033300432421270600000000 };
		double Ismoke[] = { 0, 0.1729462730516562200000000, 0.2650781109145369800000000, 0.2382936894338313600000000,
				0.3575551750075979800000000 };

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

		age_1 = age_1 - 134.033187866210940;
		age_2 = age_2 - 218.835433959960940;
		bmi_1 = bmi_1 - 0.143204286694527;
		bmi_2 = bmi_2 - 0.139157548546791;
		sbp_1 = sbp_1 - 0.583845973014832;
		sbp_2 = sbp_2 - 0.157089039683342;
		town = town - -0.607831299304962;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.0484371529894231380000000;
		a += age_2 * -0.0176947701133479510000000;
		a += bmi_1 * 1.1105635237480727000000000;
		a += bmi_2 * -12.3690966232258340000000000;
		a += sbp_1 * -2.1086300793705433000000000;
		a += sbp_2 * -5.6186919081620603000000000;
		a += town * 0.0292990623642085740000000;

		/* Sum from boolean values */

		a += b_CCF * 0.8212875143069400300000000;
		a += b_cvd * 0.3154860928614339800000000;
		a += b_nsaid * 0.2632188743293689700000000;
		a += b_pvd * 0.3010113193737247200000000;
		a += b_ra * 0.4833725257627346500000000;
		a += b_renalstones * 0.2396724646518308700000000;
		a += b_sle * 0.8756342355289874500000000;
		a += b_treatedhyp * 0.9124721274488373200000000;
		a += b_type1 * 2.1047508138768976000000000;
		a += b_type2 * 1.5036808227335385000000000;
		a += fh_kidney * 0.7566335640404944200000000;

		/* Sum from interaction terms */

		a += age_1 * b_treatedhyp * -0.0160237949945248210000000;
		a += age_1 * b_type1 * -0.0315227477366860010000000;
		a += age_1 * b_type2 * -0.0280393344533675890000000;
		a += age_2 * b_treatedhyp * 0.0064950068136830665000000;
		a += age_2 * b_type1 * 0.0123621801957671720000000;
		a += age_2 * b_type2 * 0.0112916792022569050000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	private double neph3_male_raw(int age, int b_CCF, int b_cvd, int b_nsaid, int b_pvd, int b_ra, int b_treatedhyp,
			int b_type1, int b_type2, double bmi, int ethrisk, int fh_kidney, double sbp, int smoke_cat, int surv,
			double town) {
		double survivor[] = { 0, 0.999482631683350, 0.998838543891907, 0.997995197772980, 0.997083306312561,
				0.996157050132751 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.1503591855029934300000000, 0.6943866103945839400000000,
				0.3024824702548107900000000, 0.3642866825390685400000000, -0.1882622611530610500000000,
				0.1607974560923533000000000, 0.3066453676549667500000000, 0.2851705993387653200000000 };
		double Ismoke[] = { 0, 0.1214425082432345500000000, 0.1382410437354289800000000, 0.2137214276315931300000000,
				0.2196760294878585000000000 };

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

		age_1 = age_1 - 129.786895751953120;
		age_2 = age_2 - 210.509735107421870;
		bmi_1 = bmi_1 - 0.138382270932198;
		bmi_2 = bmi_2 - 0.136841759085655;
		sbp_1 = sbp_1 - 0.551519691944122;
		sbp_2 = sbp_2 - 0.861767768859863;
		town = town - -0.442225903272629;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.0372566306865022980000000;
		a += age_2 * -0.0124595530436500700000000;
		a += bmi_1 * 4.0321363736131879000000000;
		a += bmi_2 * -16.7941762226878630000000000;
		a += sbp_1 * 5.0163247808020985000000000;
		a += sbp_2 * -14.8269303572075850000000000;
		a += town * 0.0196103062056626580000000;

		/* Sum from boolean values */

		a += b_CCF * 1.0432290439580310000000000;
		a += b_cvd * 0.3335059339906052400000000;
		a += b_nsaid * 0.2536071471853483300000000;
		a += b_pvd * 0.3838631945620650800000000;
		a += b_ra * 0.3906860206278776100000000;
		a += b_treatedhyp * 1.0232261464655623000000000;
		a += b_type1 * 2.5075410232894906000000000;
		a += b_type2 * 1.8034937790520313000000000;
		a += fh_kidney * 1.2749066751148557000000000;

		/* Sum from interaction terms */

		a += age_1 * b_treatedhyp * -0.0259849886240969550000000;
		a += age_1 * b_type1 * -0.0426774334972716550000000;
		a += age_1 * b_type2 * -0.0233621962388147270000000;
		a += age_2 * b_treatedhyp * 0.0109255079387530760000000;
		a += age_2 * b_type1 * 0.0172196192195575690000000;
		a += age_2 * b_type2 * 0.0087552424939960610000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static void main(String[] args) {
		String input = "{ \"age\": 47, \"b_CCF\": 0, \"b_cvd\": 0, \"b_pvd\": 0, \"b_ra\": 0, \"b_renalstones\": 0, \"b_sle\": 0, \"b_treatedhyp\": 0, \"diabetes_cat\": 0, \"fh_kidney\": 0, \"smoke_cat\": 1, \"gender\": 0, \"height\": 168, \"weight\": 58}";
		String input1 = "{ \"age\": 47, \"b_CCF\": 0, \"b_cvd\": 0, \"b_pvd\": 0, \"b_ra\": 0, \"b_renalstones\": 0, \"b_sle\": 0, \"b_treatedhyp\": 0, \"diabetes_cat\": 0, \"fh_kidney\": 0, \"smoke_cat\": 1, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		PredictKidneyNeph3 pd = new PredictKidneyNeph3();
		System.out.println("Json String = " + input);
		System.out.println("result = " + pd.predict(input));
		System.out.println("Json String = " + input1);
		System.out.println("result = " + pd.predict(input1));
	}

}
