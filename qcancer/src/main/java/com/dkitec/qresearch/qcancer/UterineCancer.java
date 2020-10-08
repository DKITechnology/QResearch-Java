package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class UterineCancer {

	public static double Q86_uterinecancer_female_raw(int age, int b_breastcancer, int b_colorectal, int b_endometrial,
			int b_manicschiz, int b_pos, int b_type2, double bmi, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999801218509674, 0.999620676040649, 0.999434530735016, 0.999217212200165,
				0.998992919921875, 0.998758554458618, 0.998494207859039, 0.998200297355652, 0.997903287410736,
				0.997611343860626, 0.997285604476929, 0.996933937072754, 0.996574103832245, 0.996175885200500,
				0.995772838592529 };

		/* The conditional arrays */

		double Ismoke[] = { 0, -0.2020713821985857000000000, -0.1817584707326591600000000, -0.3021556609298930400000000,
				-0.4191963756060861400000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, .5);
		double age_2 = dage;
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, 2);

		/* Centring the continuous variables */

		age_1 = age_1 - 2.118349790573120;
		age_2 = age_2 - 4.487406253814697;
		bmi_1 = bmi_1 - 6.617829799652100;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 23.1774636105576340000000000;
		a += age_2 * -4.4743743749881331000000000;
		a += bmi_1 * 0.1463194102262433400000000;

		/* Sum from boolean values */

		a += b_breastcancer * 0.9128754137084009700000000;
		a += b_colorectal * 0.4465582900206094300000000;
		a += b_endometrial * 0.8550002865110695200000000;
		a += b_manicschiz * 0.4385483915097909700000000;
		a += b_pos * 0.6853665306190324100000000;
		a += b_type2 * 0.2969025294844695500000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_uterinecancer_female_validation(int age, int b_breastcancer, int b_colorectal,
			int b_endometrial, int b_manicschiz, int b_pos, int b_type2, double bmi, int smoke_cat, int surv) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_breastcancer)) {
			resultString += "error: b_breastcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_colorectal)) {
			resultString += "error: b_colorectal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_endometrial)) {
			resultString += "error: b_endometrial must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_manicschiz)) {
			resultString += "error: b_manicschiz must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_pos)) {
			resultString += "error: b_pos must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {
			resultString += "error: b_type2 must be in range (0,1)\n";
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
		return resultString;
	}

	public static double uterine_cancer_female_raw(int age, int b_endometrial, int b_type2, double bmi, int new_abdopain,
			int new_haematuria, int new_imb, int new_pmb, int new_vte) {

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, -2);
		double age_2 = Math.pow(dage, -2) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.039541322737932;
		age_2 = age_2 - 0.063867323100567;
		bmi_1 = bmi_1 - 0.151021569967270;
		bmi_2 = bmi_2 - 0.142740502953529;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		/* Sum from continuous values */

		a += age_1 * 2.7778124257317254000000000;
		a += age_2 * -59.5333514566633330000000000;
		a += bmi_1 * 3.7623897936404322000000000;
		a += bmi_2 * -26.8045450074654320000000000;

		/* Sum from boolean values */

		a += b_endometrial * 0.8742311851235286000000000;
		a += b_type2 * 0.2655181024063555900000000;
		a += new_abdopain * 0.6891953836735580400000000;
		a += new_haematuria * 1.6798617740998527000000000;
		a += new_imb * 1.7853122923827887000000000;
		a += new_pmb * 4.4770199876067398000000000;
		a += new_vte * 1.0362058616761669000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.9931390822564037000000000;
		return score;
	}

	public static String uterine_cancer_female_validation(int age, int b_endometrial, int b_type2, double bmi, int new_abdopain,
			int new_haematuria, int new_imb, int new_pmb, int new_vte) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.is_boolean(b_endometrial)) {
			resultString += "error: b_endometrial must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {
			resultString += "error: b_type2 must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(new_abdopain)) {
			resultString += "error: new_abdopain must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_haematuria)) {
			resultString += "error: new_haematuria must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_imb)) {
			resultString += "error: new_imb must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_pmb)) {
			resultString += "error: new_pmb must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		return resultString;
	}

}
