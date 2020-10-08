package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class PancreaticCancer {
	public static double pancreatic_cancer_female_raw(int age, int b_chronicpan, int b_type2, double bmi, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_indigestion, int new_vte,
			int new_weightloss, int s1_bowelchange, int smoke_cat) {

		/* The conditional arrays */

		double Ismoke[] = { 0, -0.0631301848152044240000000, 0.3523695950528934500000000, 0.7146003670327156800000000,
				0.8073207410335441200000000 };

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

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -6.8219654517231225000000000;
		a += age_2 * -65.6404897305188650000000000;
		a += bmi_1 * 3.9715559458995728000000000;
		a += bmi_2 * -3.1161107999130500000000000;

		/* Sum from boolean values */

		a += b_chronicpan * 1.1948138830441282000000000;
		a += b_type2 * 0.7951745325664703000000000;
		a += new_abdopain * 1.9230379689782926000000000;
		a += new_appetiteloss * 1.5209568259888571000000000;
		a += new_dysphagia * 1.0107551560302726000000000;
		a += new_gibleed * 0.9324059153254259400000000;
		a += new_indigestion * 1.1134012616631439000000000;
		a += new_vte * 1.4485586969016084000000000;
		a += new_weightloss * 1.5791912580663912000000000;
		a += s1_bowelchange * 0.9361738611941444700000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -9.2782129678657608000000000;
		return score;
	}

	public static String pancreatic_cancer_female_validation(int age, int b_chronicpan, int b_type2, double bmi, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_indigestion, int new_vte,
			int new_weightloss, int s1_bowelchange, int smoke_cat) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.is_boolean(b_chronicpan)) {
			resultString += "error: b_chronicpan must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_appetiteloss)) {
			resultString += "error: new_appetiteloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_dysphagia)) {
			resultString += "error: new_dysphagia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_gibleed)) {
			resultString += "error: new_gibleed must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_indigestion)) {
			resultString += "error: new_indigestion must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_bowelchange)) {
			resultString += "error: s1_bowelchange must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		return resultString;
	}

	public static double pancreatic_cancer_male_raw(int age, int b_chronicpan, int b_type2, double bmi, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_indigestion, int new_vte,
			int new_weightloss, int s1_constipation, int smoke_cat, double town) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.2783298172089973500000000, 0.3079418928917603300000000, 0.5647359394991128300000000,
				0.7765125427126866600000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = dage;
		double age_2 = dage * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = dbmi;

		/* Centring the continuous variables */

		age_1 = age_1 - 4.800777912139893;
		age_2 = age_2 - 7.531354427337647;
		bmi_1 = bmi_1 - 0.146067067980766;
		bmi_2 = bmi_2 - 2.616518735885620;
		town = town - -0.264977723360062;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 8.0275778709105907000000000;
		a += age_2 * -2.6082429130982798000000000;
		a += bmi_1 * 1.7819574994736820000000000;
		a += bmi_2 * -0.0249600064895699750000000;
		a += town * -0.0352288140617050480000000;

		/* Sum from boolean values */

		a += b_chronicpan * 0.9913246347991823100000000;
		a += b_type2 * 0.7396905098202540800000000;
		a += new_abdopain * 2.1506984011721579000000000;
		a += new_appetiteloss * 1.4272326009960661000000000;
		a += new_dysphagia * 0.9168689207526066200000000;
		a += new_gibleed * 0.9881061033081149900000000;
		a += new_indigestion * 1.2837402377092237000000000;
		a += new_vte * 1.1741805346104719000000000;
		a += new_weightloss * 2.0466064239967046000000000;
		a += s1_constipation * 0.6240548033048214400000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -9.2275729512009956000000000;
		return score;
	}

	public static String pancreatic_cancer_male_validation(int age, int b_chronicpan, int b_type2, double bmi, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_indigestion, int new_vte,
			int new_weightloss, int s1_constipation, int smoke_cat, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.is_boolean(b_chronicpan)) {
			resultString += "error: b_chronicpan must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_appetiteloss)) {
			resultString += "error: new_appetiteloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_dysphagia)) {
			resultString += "error: new_dysphagia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_gibleed)) {
			resultString += "error: new_gibleed must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_indigestion)) {
			resultString += "error: new_indigestion must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_constipation)) {
			resultString += "error: s1_constipation must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

}
