package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class GastroOesophagealCancer {

	public static double Q86_oesgastric_female_raw(int age, int alcohol_cat6, int b_barratts, int b_bloodcancer,
			int b_breastcancer, int b_lungcancer, int b_oralcancer, int b_peptic, int b_type2, double bmi,
			int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999951303005219, 0.999912202358246, 0.999867975711823, 0.999822735786438,
				0.999769926071167, 0.999717116355896, 0.999664008617401, 0.999596655368805, 0.999539613723755,
				0.999473392963409, 0.999395728111267, 0.999319493770599, 0.999237120151520, 0.999145269393921,
				0.999041557312012 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, -0.1118936898379133500000000, -0.0968659176808125830000000,
				-0.0204933470982618970000000, 0.6917917227265505400000000, 0.6914911379491748300000000 };
		double Ismoke[] = { 0, 0.0824544776153183220000000, 0.5778672890109549700000000, 0.7178899834924259400000000,
				0.8774806775284287300000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 2);
		double age_2 = Math.pow(dage, 2) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);

		/* Centring the continuous variables */

		age_1 = age_1 - 20.148687362670898;
		age_2 = age_2 - 30.254657745361328;
		bmi_1 = bmi_1 - 0.151121616363525;
		bmi_2 = bmi_2 - 0.142785012722015;
		town = town - 0.161748945713043;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.4591496588645833100000000;
		a += age_2 * -0.1667587003933266300000000;
		a += bmi_1 * 6.9375480360437578000000000;
		a += bmi_2 * -13.1588621275502400000000000;
		a += town * 0.0294230617794285950000000;

		/* Sum from boolean values */

		a += b_barratts * 1.3419977053300407000000000;
		a += b_bloodcancer * 0.7625629779098090900000000;
		a += b_breastcancer * 0.2678516469326961400000000;
		a += b_lungcancer * 0.8235462224547837100000000;
		a += b_oralcancer * 1.3464504475029004000000000;
		a += b_peptic * 0.2551309806105288600000000;
		a += b_type2 * 0.2889222416353735000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_oesgastric_female_validation(int age, int alcohol_cat6, int b_barratts, int b_bloodcancer,
			int b_breastcancer, int b_lungcancer, int b_oralcancer, int b_peptic, int b_type2, double bmi,
			int smoke_cat, int surv, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {
			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_barratts)) {
			resultString += "error: b_barratts must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {
			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_breastcancer)) {
			resultString += "error: b_breastcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_lungcancer)) {
			resultString += "error: b_lungcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_oralcancer)) {
			resultString += "error: b_oralcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_peptic)) {
			resultString += "error: b_peptic must be in range (0,1)\n";
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
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

	public static double Q86_oesgastric_male_raw(int age, int alcohol_cat6, int b_barratts, int b_oralcancer,
			int b_pancreascancer, int b_peptic, int b_type2, double bmi, int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999897956848145, 0.999810755252838, 0.999715626239777, 0.999619841575623,
				0.999517440795898, 0.999406874179840, 0.999290168285370, 0.999163746833801, 0.999030172824860,
				0.998878240585327, 0.998729109764099, 0.998557686805725, 0.998369634151459, 0.998170733451843 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, -0.0604088143669727420000000, -0.1207953247253508900000000,
				-0.0647767888598297930000000, 0.2124485493274002000000000, 0.4857139337869693700000000 };
		double Ismoke[] = { 0, 0.2230463133381267400000000, 0.5469118912702083400000000, 0.6143012397369057600000000,
				0.6478492007037544000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 2);
		double age_2 = Math.pow(dage, 2) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);

		/* Centring the continuous variables */

		age_1 = age_1 - 19.620740890502930;
		age_2 = age_2 - 29.201423645019531;
		bmi_1 = bmi_1 - 0.144450336694717;
		bmi_2 = bmi_2 - 0.139742657542229;
		town = town - 0.259125590324402;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.5517904833767625100000000;
		a += age_2 * -0.2078140068178159500000000;
		a += bmi_1 * 8.4408496113154765000000000;
		a += bmi_2 * -20.6909083342470940000000000;
		a += town * 0.0274855276840479670000000;

		/* Sum from boolean values */

		a += b_barratts * 1.3981857737696197000000000;
		a += b_oralcancer * 0.9751074059450815000000000;
		a += b_pancreascancer * 1.4262126421694010000000000;
		a += b_peptic * 0.2244630452692231200000000;
		a += b_type2 * 0.1637818050685624400000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_oesgastric_male_validation(int age, int alcohol_cat6, int b_barratts, int b_oralcancer,
			int b_pancreascancer, int b_peptic, int b_type2, double bmi, int smoke_cat, int surv, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {
			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_barratts)) {
			resultString += "error: b_barratts must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_oralcancer)) {
			resultString += "error: b_oralcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_pancreascancer)) {
			resultString += "error: b_pancreascancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_peptic)) {
			resultString += "error: b_peptic must be in range (0,1)\n";
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
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

	public static double gastro_oesophageal_cancer_female_raw(int age, double bmi, int c_hb, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_heartburn, int new_indigestion,
			int new_vte, int new_weightloss, int smoke_cat) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.2108835385994093400000000, 0.4020914846651602000000000, 0.8497119766959212500000000,
				1.1020585469724540000000000 };

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

		a += age_1 * 5.5127932958160830000000000;
		a += age_2 * -70.2734062916161830000000000;
		a += bmi_1 * 2.6063377632938987000000000;
		a += bmi_2 * -1.2389834515079798000000000;

		/* Sum from boolean values */

		a += c_hb * 1.2479756970482034000000000;
		a += new_abdopain * 0.7825304005124729100000000;
		a += new_appetiteloss * 0.6514592236889243900000000;
		a += new_dysphagia * 3.7751714910656862000000000;
		a += new_gibleed * 1.4264472204617833000000000;
		a += new_heartburn * 0.8178746069193373300000000;
		a += new_indigestion * 1.4998439683677578000000000;
		a += new_vte * 0.7199894658172598700000000;
		a += new_weightloss * 1.2287925630053846000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.8746031610250764000000000;
		return score;
	}

	public static String gastro_oesophageal_cancer_female_validation(int age, double bmi, int c_hb, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_heartburn, int new_indigestion,
			int new_vte, int new_weightloss, int smoke_cat) {
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
		if (!CV.is_boolean(new_appetiteloss)) {
			resultString += "error: new_appetiteloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_dysphagia)) {
			resultString += "error: new_dysphagia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_gibleed)) {
			resultString += "error: new_gibleed must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_heartburn)) {
			resultString += "error: new_heartburn must be in range (0,1)\n";
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
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		return resultString;
	}

	public static double gastro_oesophageal_cancer_male_raw(int age, double bmi, int c_hb, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_heartburn, int new_indigestion,
			int new_necklump, int new_weightloss, int smoke_cat) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.3532685922239948200000000, 0.6343201557712291300000000, 0.6500819736904158700000000,
				0.6273413010559952800000000 };

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

		a += age_1 * 8.5841509312915623000000000;
		a += age_2 * -2.7650409450116360000000000;
		a += bmi_1 * 4.1816752831070323000000000;
		a += bmi_2 * 0.6247106288954960000000000;

		/* Sum from boolean values */

		a += c_hb * 1.1065543049459461000000000;
		a += new_abdopain * 1.0280133043080188000000000;
		a += new_appetiteloss * 1.1868017500634926000000000;
		a += new_dysphagia * 3.8253199428642568000000000;
		a += new_gibleed * 1.8454733322333583000000000;
		a += new_heartburn * 1.1727679169313121000000000;
		a += new_indigestion * 1.8843639195644077000000000;
		a += new_necklump * 0.8414696385393357600000000;
		a += new_weightloss * 1.4698638306735652000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.4208700270300625000000000;
		return score;
	}

	public static String gastro_oesophageal_cancer_male_validation(int age, double bmi, int c_hb, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_heartburn, int new_indigestion,
			int new_necklump, int new_weightloss, int smoke_cat) {
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
		if (!CV.is_boolean(new_appetiteloss)) {
			resultString += "error: new_appetiteloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_dysphagia)) {
			resultString += "error: new_dysphagia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_gibleed)) {
			resultString += "error: new_gibleed must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_heartburn)) {
			resultString += "error: new_heartburn must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_indigestion)) {
			resultString += "error: new_indigestion must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_necklump)) {
			resultString += "error: new_necklump must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		return resultString;
	}
}
