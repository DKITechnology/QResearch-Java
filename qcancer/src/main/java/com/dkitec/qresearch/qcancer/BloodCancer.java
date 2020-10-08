package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class BloodCancer {

	public static double Q86_bloodcancer_female_raw(int age, int b_braincancer, int b_ovariancancer, int b_type1,
			double bmi, int fh_bloodcancer, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999728083610535, 0.999484181404114, 0.999221444129944, 0.998965024948120,
				0.998681783676147, 0.998394668102264, 0.998100757598877, 0.997779428958893, 0.997428894042969,
				0.997045099735260, 0.996690928936005, 0.996283531188965, 0.995863080024719, 0.995415091514587,
				0.994949996471405 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.0067585720587060227000000, 0.1786060334216023600000000, 0.2000236983380095400000000,
				0.2824295828543657000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 2);
		double age_2 = Math.pow(dage, 3);

		/* Centring the continuous variables */

		age_1 = age_1 - 20.135219573974609;
		age_2 = age_2 - 90.351325988769531;
		bmi = bmi - 25.724174499511719;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.1837588586730924700000000;
		a += age_2 * -0.0147172575575694360000000;
		a += bmi * 0.0162413827296514320000000;

		/* Sum from boolean values */

		a += b_braincancer * 1.4166705727938707000000000;
		a += b_ovariancancer * 0.4611088666746058200000000;
		a += b_type1 * 0.4133397797532169000000000;
		a += fh_bloodcancer * 1.4057490757127600000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_bloodcancer_female_validation(int age, int b_braincancer, int b_ovariancancer, int b_type1,
			double bmi, int fh_bloodcancer, int smoke_cat, int surv) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_braincancer)) {
			resultString += "error: b_braincancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ovariancancer)) {
			resultString += "error: b_ovariancancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type1)) {
			resultString += "error: b_type1 must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(fh_bloodcancer)) {
			resultString += "error: fh_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {
			resultString += "error: surv must be in range (1,15)\n";
		}
		return resultString;
	}

	public static double Q86_bloodcancer_male_raw(int age, int b_renalcancer, int b_type1, double bmi,
			int fh_bloodcancer, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999643683433533, 0.999320745468140, 0.998986303806305, 0.998629689216614,
				0.998259723186493, 0.997866392135620, 0.997473299503326, 0.997049689292908, 0.996594071388245,
				0.996101081371307, 0.995572328567505, 0.995007038116455, 0.994394004344940, 0.993781149387360 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.0214136853862729180000000, 0.0996684707979798300000000, 0.1599083906205098800000000,
				0.2100027093511991100000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 2);
		double age_2 = Math.pow(dage, 3);

		/* Centring the continuous variables */

		age_1 = age_1 - 19.604045867919922;
		age_2 = age_2 - 86.799774169921875;
		bmi = bmi - 26.309041976928711;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.1877409171405840800000000;
		a += age_2 * -0.0147456536768349810000000;
		a += bmi * 0.0080906902408843691000000;

		/* Sum from boolean values */

		a += b_renalcancer * 0.3770974417657937400000000;
		a += b_type1 * 0.4735838801969793900000000;
		a += fh_bloodcancer * 1.3664654694337226000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_bloodcancer_male_validation(int age, int b_renalcancer, int b_type1, double bmi,
			int fh_bloodcancer, int smoke_cat, int surv) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_renalcancer)) {
			resultString += "error: b_renalcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type1)) {
			resultString += "error: b_type1 must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(fh_bloodcancer)) {
			resultString += "error: fh_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {
			resultString += "error: surv must be in range (1,15)\n";
		}
		return resultString;
	}

	public static double blood_cancer_female_raw(int age, double bmi, int c_hb, int new_abdopain, int new_haematuria,
			int new_necklump, int new_nightsweats, int new_pmb, int new_vte, int new_weightloss, int s1_bowelchange,
			int s1_bruising) {

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

		a += age_1 * 35.9405666896283120000000000;
		a += age_2 * -68.8496375977904480000000000;
		a += bmi_1 * 0.0785171223057501980000000;
		a += bmi_2 * -5.3730627788681424000000000;

		/* Sum from boolean values */

		a += c_hb * 1.7035866502297630000000000;
		a += new_abdopain * 0.3779206239385797800000000;
		a += new_haematuria * 0.4086662974598894700000000;
		a += new_necklump * 2.9539029476671903000000000;
		a += new_nightsweats * 1.3792892192392403000000000;
		a += new_pmb * 0.4689216313440992500000000;
		a += new_vte * 0.6036630662990674100000000;
		a += new_weightloss * 0.8963398932306315700000000;
		a += s1_bowelchange * 0.7291379612468620300000000;
		a += s1_bruising * 1.0255003552753392000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -7.4207849482565749000000000;
		return score;
	}

	public static String blood_cancer_female_validation(int age, double bmi, int c_hb, int new_abdopain, int new_haematuria,
			int new_necklump, int new_nightsweats, int new_pmb, int new_vte, int new_weightloss, int s1_bowelchange,
			int s1_bruising) {
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
		if (!CV.is_boolean(new_necklump)) {
			resultString += "error: new_necklump must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_nightsweats)) {
			resultString += "error: new_nightsweats must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_pmb)) {
			resultString += "error: new_pmb must be in range (0,1)\n";
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
		if (!CV.is_boolean(s1_bruising)) {
			resultString += "error: s1_bruising must be in range (0,1)\n";
		}
		return resultString;
	}

	public static double blood_cancer_male_raw(int age, double bmi, int c_hb, int new_abdodist, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_haematuria, int new_haemoptysis, int new_indigestion,
			int new_necklump, int new_nightsweats, int new_testicularlump, int new_vte, int new_weightloss,
			double town) {

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

		/* Sum from continuous values */

		a += age_1 * 3.4970179354556610000000000;
		a += age_2 * -1.0806801421562633000000000;
		a += bmi_1 * 0.9519259479511792400000000;
		a += bmi_2 * 0.1714669358410085800000000;
		a += town * -0.0277062426752491610000000;

		/* Sum from boolean values */

		a += c_hb * 1.8905802113004144000000000;
		a += new_abdodist * 0.8430432197211393800000000;
		a += new_abdopain * 0.6226473288294992500000000;
		a += new_appetiteloss * 1.0672150380753760000000000;
		a += new_dysphagia * 0.5419443056595199000000000;
		a += new_haematuria * 0.4607538085363521700000000;
		a += new_haemoptysis * 0.9501446899241836600000000;
		a += new_indigestion * 0.5635686569331337400000000;
		a += new_necklump * 3.1567783466839603000000000;
		a += new_nightsweats * 1.5201300180753576000000000;
		a += new_testicularlump * 0.9957524928245107300000000;
		a += new_vte * 0.6142589726132866600000000;
		a += new_weightloss * 1.2233663263194712000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -7.2591289466850277000000000;
		return score;
	}

	public static String blood_cancer_male_validation(int age, double bmi, int c_hb, int new_abdodist, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_haematuria, int new_haemoptysis, int new_indigestion,
			int new_necklump, int new_nightsweats, int new_testicularlump, int new_vte, int new_weightloss,
			double town) {
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
		if (!CV.is_boolean(new_abdodist)) {
			resultString += "error: new_abdodist must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_haematuria)) {
			resultString += "error: new_haematuria must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_haemoptysis)) {
			resultString += "error: new_haemoptysis must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_indigestion)) {
			resultString += "error: new_indigestion must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_necklump)) {
			resultString += "error: new_necklump must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_nightsweats)) {
			resultString += "error: new_nightsweats must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_testicularlump)) {
			resultString += "error: new_testicularlump must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

}
