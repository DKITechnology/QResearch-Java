package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class BreastCancer {

	public static double Q86_breastcancer_female_raw(int age, int alcohol_cat6, int b_benignbreast, int b_bloodcancer,
			int b_cop, int b_hrt_oest, int b_lungcancer, int b_manicschiz, int b_ovariancancer, double bmi, int ethrisk,
			int fh_breastcancer, int surv, double town7) {
		double survivor[] = { 0, 0.998308241367340, 0.996614873409271, 0.994935452938080, 0.993084847927094,
				0.991052746772766, 0.989028811454773, 0.986972689628601, 0.984719038009644, 0.982559740543365,
				0.980243861675262, 0.977741718292236, 0.975280523300171, 0.972725808620453, 0.969873607158661,
				0.967079520225525 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.0526476985453596950000000, 0.1041141448053500800000000, 0.1899907593539908200000000,
				0.2713589821997062800000000, 0.2254788050469323000000000 };
		double Iethrisk[] = { 0, 0, -0.3164284175892595500000000, -0.3385453221952767800000000,
				-0.9420784373500499200000000, -0.2468429081304340200000000, -0.1959614952489963400000000,
				-0.2926721544541227600000000, -0.3195193199292232200000000, -0.1865521604622726000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_2 = Math.pow(dage, -1) * Math.log(dage);
		double age_1 = Math.pow(dage, -1);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);
		double dtown7 = town7;
		dtown7 = dtown7 / 10;
		double town7_2 = Math.pow(dtown7, 2);
		double town7_1 = Math.pow(dtown7, -2);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.223646596074104;
		age_2 = age_2 - 0.334952861070633;
		bmi_1 = bmi_1 - 0.151265263557434;
		bmi_2 = bmi_2 - 0.142848879098892;
		town7_1 = town7_1 - 1.944750308990479;
		town7_2 = town7_2 - 0.514204859733582;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];

		/* Sum from continuous values */

		a += age_1 * -19.0805937794486700000000000;
		a += age_2 * 18.5106743793654150000000000;
		a += bmi_1 * -1.4752803248869217000000000;
		a += bmi_2 * 3.0550387484519432000000000;
		a += town7_1 * -0.0000352567686734410290000;
		a += town7_2 * -0.0834449374198989040000000;

		/* Sum from boolean values */

		a += b_benignbreast * 0.4137100693180689200000000;
		a += b_bloodcancer * 0.4497376844227650700000000;
		a += b_cop * 0.1260785630901896900000000;
		a += b_hrt_oest * 0.1692181464425702200000000;
		a += b_lungcancer * 0.6204551175334062100000000;
		a += b_manicschiz * 0.1511388013844540100000000;
		a += b_ovariancancer * 0.3510739176168313800000000;
		a += fh_breastcancer * 0.6582115273538031700000000;

		/* Sum from interaction terms */

		a += age_1 * fh_breastcancer * 5.2215614921419951000000000;
		a += age_2 * fh_breastcancer * -7.9997640557992415000000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_breastcancer_female_validation(int age, int alcohol_cat6, int b_benignbreast,
			int b_bloodcancer, int b_cop, int b_hrt_oest, int b_lungcancer, int b_manicschiz, int b_ovariancancer,
			double bmi, int ethrisk, int fh_breastcancer, int surv, double town7) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {
			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_benignbreast)) {
			resultString += "error: b_benignbreast must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {
			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cop)) {
			resultString += "error: b_cop must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_hrt_oest)) {
			resultString += "error: b_hrt_oest must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_lungcancer)) {
			resultString += "error: b_lungcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_manicschiz)) {
			resultString += "error: b_manicschiz must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ovariancancer)) {
			resultString += "error: b_ovariancancer must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {
			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.is_boolean(fh_breastcancer)) {
			resultString += "error: fh_breastcancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {
			resultString += "error: surv must be in range (1,15)\n";
		}
		if (!CV.d_in_range(town7, 0, 18)) {
			resultString += "error: town7 must be in range (0,18)\n";
		}
		return resultString;
	}

	public static double breast_cancer_female_raw(int age, int alcohol_cat4, double bmi, int fh_breastcancer,
			int new_breastlump, int new_breastpain, int new_breastskin, int new_pmb, int new_vte, double town) {
		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.0543813075945134560000000, 0.1245709972983817800000000,
				0.1855198679261514700000000 };

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

		a += Ialcohol[alcohol_cat4];

		/* Sum from continuous values */

		a += age_1 * -14.3029484067898500000000000;
		a += age_2 * -25.9301811377364260000000000;
		a += bmi_1 * -1.7540983825680900000000000;
		a += bmi_2 * 2.0601979121740364000000000;
		a += town * -0.0160766972632234440000000;

		/* Sum from boolean values */

		a += fh_breastcancer * 0.3863899675953914000000000;
		a += new_breastlump * 3.9278533274888368000000000;
		a += new_breastpain * 0.8779616078329102200000000;
		a += new_breastskin * 2.2320296233987880000000000;
		a += new_pmb * 0.4465053002248299800000000;
		a += new_vte * 0.2728610297213165400000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -6.1261694200869234000000000;
		return score;
	}

	public static String breast_cancer_female_validation(int age, int alcohol_cat4, double bmi, int fh_breastcancer,
			int new_breastlump, int new_breastpain, int new_breastskin, int new_pmb, int new_vte, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.i_in_range(alcohol_cat4, 0, 3)) {
			resultString += "error: alcohol_cat4 must be in range (0,3)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(fh_breastcancer)) {
			resultString += "error: fh_breastcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_breastlump)) {
			resultString += "error: new_breastlump must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_breastpain)) {
			resultString += "error: new_breastpain must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_breastskin)) {
			resultString += "error: new_breastskin must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_pmb)) {
			resultString += "error: new_pmb must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

}
