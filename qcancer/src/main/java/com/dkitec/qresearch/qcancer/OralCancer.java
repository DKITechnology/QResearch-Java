package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class OralCancer {
	public static double Q86_oralcancer_female_raw(int age, int alcohol_cat6, int b_bloodcancer, int b_ovariancancer,
			int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999955236911774, 0.999913334846497, 0.999870777130127, 0.999827504158020,
				0.999773323535919, 0.999722063541412, 0.999673902988434, 0.999617516994476, 0.999561011791229,
				0.999490976333618, 0.999421477317810, 0.999349534511566, 0.999266982078552, 0.999185383319855,
				0.999104440212250 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.0279995739917904340000000, 0.1619523334857955700000000, 0.4690455612958099900000000,
				1.0501137095629722000000000, 1.4775640612495535000000000 };
		double Ismoke[] = { 0, 0.2027716105642686500000000, 0.7513111690119473900000000, 0.9223813686857182900000000,
				1.2580143206211538000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, -2);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.049628291279078;
		town = town - 0.161764934659004;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -31.5979089020347100000000000;
		a += town * 0.0273229755826072530000000;

		/* Sum from boolean values */

		a += b_bloodcancer * 1.5136324928800686000000000;
		a += b_ovariancancer * 1.4208557298354756000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_oralcancer_female_validation(int age, int alcohol_cat6, int b_bloodcancer, int b_ovariancancer,
			int smoke_cat, int surv, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {
			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {
			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ovariancancer)) {
			resultString += "error: b_ovariancancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {
			resultString += "error: surv must be in range (1,15)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

	public static double Q86_oralcancer_male_raw(int age, int alcohol_cat6, int b_bloodcancer, int b_colorectal,
			int b_lungcancer, double bmi, int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999936580657959, 0.999877035617828, 0.999810338020325, 0.999735474586487,
				0.999666094779968, 0.999586403369904, 0.999507844448090, 0.999420881271362, 0.999327778816223,
				0.999226868152618, 0.999124884605408, 0.998995721340179, 0.998847126960754, 0.998718261718750 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, -0.1181513722111787300000000, 0.0173694463218143670000000, 0.3091981516010892700000000,
				0.9526687746830955200000000, 1.3101896377229811000000000 };
		double Ismoke[] = { 0, 0.1413136301072311400000000, 0.8376232661839703200000000, 0.8540114479282996400000000,
				1.0831750895355423000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, -.5);
		double age_2 = Math.pow(dage, -.5) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_2 = Math.pow(dbmi, -1);
		double bmi_1 = Math.pow(dbmi, -2);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.475125968456268;
		age_2 = age_2 - 0.707154035568237;
		bmi_1 = bmi_1 - 0.144458159804344;
		bmi_2 = bmi_2 - 0.380076527595520;
		town = town - 0.258994281291962;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -5.3604756931843474000000000;
		a += age_2 * 18.4654946257000350000000000;
		a += bmi_1 * 16.7531872033111960000000000;
		a += bmi_2 * -10.3137864786102560000000000;
		a += town * 0.0466767947260026480000000;

		/* Sum from boolean values */

		a += b_bloodcancer * 0.8500539225369179200000000;
		a += b_colorectal * 0.4808786236483906800000000;
		a += b_lungcancer * 1.0545122535321239000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_oralcancer_male_validation(int age, int alcohol_cat6, int b_bloodcancer, int b_colorectal,
			int b_lungcancer, double bmi, int smoke_cat, int surv, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {
			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {
			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_colorectal)) {
			resultString += "error: b_colorectal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_lungcancer)) {
			resultString += "error: b_lungcancer must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {
			resultString += "error: surv must be in range (1,15)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

}
