package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class OtherCancer {

	public static double other_cancer_female_raw(int age, int alcohol_cat4, int b_copd, double bmi, int c_hb, int new_abdodist,
			int new_abdopain, int new_appetiteloss, int new_breastlump, int new_dysphagia, int new_gibleed,
			int new_haematuria, int new_indigestion, int new_necklump, int new_pmb, int new_vte, int new_weightloss,
			int s1_constipation, int smoke_cat) {

		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.1129292517088995400000000, 0.1389183205617967600000000,
				0.3428114766789586200000000 };
		double Ismoke[] = { 0, 0.0643839792551647580000000, 0.1875068101660691500000000, 0.3754052152821668000000000,
				0.5007337952210844100000000 };

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

		a += Ialcohol[alcohol_cat4];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 35.8208987302204780000000000;
		a += age_2 * -68.3294741037719150000000000;
		a += bmi_1 * 1.8969796480108396000000000;
		a += bmi_2 * -3.7755945945329574000000000;

		/* Sum from boolean values */

		a += b_copd * 0.2823021429107943600000000;
		a += c_hb * 1.0476364795173587000000000;
		a += new_abdodist * 0.9628688090459262000000000;
		a += new_abdopain * 0.8335710066715610300000000;
		a += new_appetiteloss * 0.8450972438476546100000000;
		a += new_breastlump * 1.0400807427059522000000000;
		a += new_dysphagia * 0.8905342895684595900000000;
		a += new_gibleed * 0.3839632265134078600000000;
		a += new_haematuria * 0.6143184647549447800000000;
		a += new_indigestion * 0.2457016002992454300000000;
		a += new_necklump * 2.1666504706191545000000000;
		a += new_pmb * 0.4219383252623540900000000;
		a += new_vte * 1.0630784861733920000000000;
		a += new_weightloss * 1.1058752771736007000000000;
		a += s1_constipation * 0.3780143641299491500000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -6.7864501668594306000000000;
		return score;
	}

	public static String other_cancer_female_validation(int age, int alcohol_cat4, int b_copd, double bmi, int c_hb,
			int new_abdodist, int new_abdopain, int new_appetiteloss, int new_breastlump, int new_dysphagia,
			int new_gibleed, int new_haematuria, int new_indigestion, int new_necklump, int new_pmb, int new_vte,
			int new_weightloss, int s1_constipation, int smoke_cat) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.i_in_range(alcohol_cat4, 0, 3)) {
			resultString += "error: alcohol_cat4 must be in range (0,3)\n";
		}
		if (!CV.is_boolean(b_copd)) {
			resultString += "error: b_copd must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_breastlump)) {
			resultString += "error: new_breastlump must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_dysphagia)) {
			resultString += "error: new_dysphagia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_gibleed)) {
			resultString += "error: new_gibleed must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_haematuria)) {
			resultString += "error: new_haematuria must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_indigestion)) {
			resultString += "error: new_indigestion must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_necklump)) {
			resultString += "error: new_necklump must be in range (0,1)\n";
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
		if (!CV.is_boolean(s1_constipation)) {
			resultString += "error: s1_constipation must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		return resultString;
	}

	public static double other_cancer_male_raw(int age, int b_copd, int b_type2, double bmi, int c_hb, int new_abdodist,
			int new_abdopain, int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_haematuria,
			int new_haemoptysis, int new_indigestion, int new_necklump, int new_vte, int new_weightloss,
			int s1_bowelchange, int s1_constipation, int smoke_cat) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.1306282330648657900000000, 0.4156824612593108500000000, 0.4034160393541376700000000,
				0.5290383323065179800000000 };

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

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 4.1156415170875666000000000;
		a += age_2 * -1.2786588534988286000000000;
		a += bmi_1 * 2.4067691257533248000000000;
		a += bmi_2 * 0.2566799616335219100000000;

		/* Sum from boolean values */

		a += b_copd * 0.2364397443316423000000000;
		a += b_type2 * 0.2390212489103255300000000;
		a += c_hb * 0.9765525865177192600000000;
		a += new_abdodist * 0.7203822227648433200000000;
		a += new_abdopain * 0.8372159579979499000000000;
		a += new_appetiteloss * 1.1647610659454599000000000;
		a += new_dysphagia * 1.0747326525064285000000000;
		a += new_gibleed * 0.4468867932306167000000000;
		a += new_haematuria * 0.5276884520139836200000000;
		a += new_haemoptysis * 0.6465976131208517300000000;
		a += new_indigestion * 0.3156125379576864000000000;
		a += new_necklump * 2.9472448787274570000000000;
		a += new_vte * 1.0954486585194212000000000;
		a += new_weightloss * 1.0550815022699203000000000;
		a += s1_bowelchange * 0.5059485944682162700000000;
		a += s1_constipation * 0.6035170412091727100000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -6.7132875682858542000000000;
		return score;
	}

	public static String other_cancer_male_validation(int age, int b_copd, int b_type2, double bmi, int c_hb, int new_abdodist,
			int new_abdopain, int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_haematuria,
			int new_haemoptysis, int new_indigestion, int new_necklump, int new_vte, int new_weightloss,
			int s1_bowelchange, int s1_constipation, int smoke_cat) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.is_boolean(b_copd)) {
			resultString += "error: b_copd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {
			resultString += "error: b_type2 must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_gibleed)) {
			resultString += "error: new_gibleed must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_bowelchange)) {
			resultString += "error: s1_bowelchange must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_constipation)) {
			resultString += "error: s1_constipation must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		return resultString;
	}

}
