package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class CervicalCancer {
	
	public static double cervical_cancer_female_raw(int age, double bmi, int c_hb, int new_abdopain, int new_haematuria,
			int new_imb, int new_pmb, int new_postcoital, int new_vte, int smoke_cat, double town) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.3247875277095715300000000, 0.7541211259076738800000000, 0.7448343035139659600000000,
				0.6328348533913806800000000 };

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
		town = town - -0.383295059204102;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 10.1663393107505800000000000;
		a += age_2 * -16.9118902491100020000000000;
		a += bmi_1 * -0.5675143308052614800000000;
		a += bmi_2 * -2.6377586334504044000000000;
		a += town * 0.0573200669650633030000000;

		/* Sum from boolean values */

		a += c_hb * 1.2205973555195053000000000;
		a += new_abdopain * 0.7229870191773574200000000;
		a += new_haematuria * 1.6126499968790107000000000;
		a += new_imb * 1.9527008812518938000000000;
		a += new_pmb * 3.3618997560756485000000000;
		a += new_postcoital * 3.1391568551730864000000000;
		a += new_vte * 1.1276327958138455000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.8309098444401926000000000;
		return score;
	}

	static String cervical_cancer_female_validation(int age, double bmi, int c_hb, int new_abdopain, int new_haematuria,
			int new_imb, int new_pmb, int new_postcoital, int new_vte, int smoke_cat, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(c_hb)) {
			resultString += "error: c_hb must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_postcoital)) {
			resultString += "error: new_postcoital must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
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
