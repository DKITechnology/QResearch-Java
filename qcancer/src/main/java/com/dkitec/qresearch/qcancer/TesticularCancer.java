package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class TesticularCancer {

	public static double testicular_cancer_male_raw(int age, double bmi, int new_testespain, int new_testicularlump,
			int new_vte) {

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

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		/* Sum from continuous values */

		a += age_1 * 3.9854184482476338000000000;
		a += age_2 * -1.7426970576325218000000000;
		a += bmi_1 * 2.0160796798276812000000000;
		a += bmi_2 * -0.0427340437454773740000000;

		/* Sum from boolean values */

		a += new_testespain * 2.7411880902787775000000000;
		a += new_testicularlump * 5.2200886149323269000000000;
		a += new_vte * 2.2416746922896493000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.7592209887895898000000000;
		return score;
	}

	public static String testicular_cancer_male_validation(int age, double bmi, int new_testespain, int new_testicularlump,
			int new_vte) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(new_testespain)) {
			resultString += "error: new_testespain must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_testicularlump)) {
			resultString += "error: new_testicularlump must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		return resultString;
	}

}
