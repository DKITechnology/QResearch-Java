package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class RenalCancer {

	public static double Q86_renalcancer_female_raw(int age, int b_bloodcancer, int b_braincancer, int b_cervicalcancer,
			int b_colorectal, int b_ovariancancer, int b_type2, int b_uterinecancer, double bmi, int smoke_cat,
			int surv, double town7) {
		double survivor[] = { 0, 0.999904513359070, 0.999804437160492, 0.999707579612732, 0.999607563018799,
				0.999499320983887, 0.999381363391876, 0.999268293380737, 0.999141454696655, 0.998996496200562,
				0.998839735984802, 0.998658537864685, 0.998484671115875, 0.998308479785919, 0.998088419437408,
				0.997885107994080 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.2424163198283673100000000, 0.5543936463038150500000000, 0.8002798566619827600000000,
				0.8525551235122267300000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = dage;
		double age_2 = Math.pow(dage, 2);
		double dtown7 = town7;
		dtown7 = dtown7 / 10;
		double town7_2 = Math.pow(dtown7, 3) * Math.log(dtown7);
		double town7_1 = Math.pow(dtown7, 3);

		/* Centring the continuous variables */

		age_1 = age_1 - 4.487745761871338;
		age_2 = age_2 - 20.139862060546875;
		bmi = bmi - 25.724693298339844;
		town7_1 = town7_1 - 0.367364495992661;
		town7_2 = town7_2 - -0.122626356780529;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 1.9825594098708867000000000;
		a += age_2 * -0.1049706382067783800000000;
		a += bmi * 0.0103316897130362220000000;
		a += town7_1 * 0.1348154117725887400000000;
		a += town7_2 * -0.3759169258209755800000000;

		/* Sum from boolean values */

		a += b_bloodcancer * 0.4895818590804914500000000;
		a += b_braincancer * 2.3203524608631203000000000;
		a += b_cervicalcancer * 0.9419205046249473600000000;
		a += b_colorectal * 0.3619448262924286900000000;
		a += b_ovariancancer * 0.9618153259560409100000000;
		a += b_type2 * 0.2958800720898281400000000;
		a += b_uterinecancer * 0.7529011050166346500000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_renalcancer_female_validation(int age, int b_bloodcancer, int b_braincancer,
			int b_cervicalcancer, int b_colorectal, int b_ovariancancer, int b_type2, int b_uterinecancer, double bmi,
			int smoke_cat, int surv, double town7) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {
			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_braincancer)) {
			resultString += "error: b_braincancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cervicalcancer)) {
			resultString += "error: b_cervicalcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_colorectal)) {
			resultString += "error: b_colorectal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ovariancancer)) {
			resultString += "error: b_ovariancancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {
			resultString += "error: b_type2 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_uterinecancer)) {
			resultString += "error: b_uterinecancer must be in range (0,1)\n";
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
		if (!CV.d_in_range(town7, 0, 18)) {
			resultString += "error: town7 must be in range (0,18)\n";
		}
		return resultString;
	}

	public static double Q86_renalcancer_male_raw(int age, int b_colorectal, int b_lungcancer, int b_prostatecancer,
			int b_type2, double bmi, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999814987182617, 0.999643385410309, 0.999469637870789, 0.999274313449860,
				0.999062120914459, 0.998849034309387, 0.998619675636292, 0.998361170291901, 0.998091459274292,
				0.997790038585663, 0.997460484504700, 0.997091531753540, 0.996718108654022, 0.996276795864105 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.2103930701451942600000000, 0.4940311436429265500000000, 0.7176516047980381300000000,
				0.8061213908771099900000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = dage;
		double age_2 = Math.pow(dage, 3);

		/* Centring the continuous variables */

		age_1 = age_1 - 4.426476955413818;
		age_2 = age_2 - 86.731056213378906;
		bmi = bmi - 26.308208465576172;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 1.6422685726886450000000000;
		a += age_2 * -0.0070040687611871225000000;
		a += bmi * 0.0190285663689910570000000;

		/* Sum from boolean values */

		a += b_colorectal * 0.2254498084030430200000000;
		a += b_lungcancer * 0.5756428964279548500000000;
		a += b_prostatecancer * 0.3760753095767558900000000;
		a += b_type2 * 0.1872039397585974800000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_renalcancer_male_validation(int age, int b_colorectal, int b_lungcancer,
			int b_prostatecancer, int b_type2, double bmi, int smoke_cat, int surv) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_colorectal)) {
			resultString += "error: b_colorectal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_lungcancer)) {
			resultString += "error: b_lungcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_prostatecancer)) {
			resultString += "error: b_prostatecancer must be in range (0,1)\n";
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

	public static double renal_tract_cancer_female_raw(int age, double bmi, int c_hb, int new_abdopain, int new_appetiteloss,
			int new_haematuria, int new_indigestion, int new_pmb, int new_weightloss, int smoke_cat) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.2752175727739372700000000, 0.5498656631475861100000000, 0.6536242182136680100000000,
				0.9053763661785879700000000 };

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

		a += age_1 * -0.0323226569626617470000000;
		a += age_2 * -56.3551410786635780000000000;
		a += bmi_1 * 1.2103910535779330000000000;
		a += bmi_2 * -4.7221299079939785000000000;

		/* Sum from boolean values */

		a += c_hb * 1.2666531852544143000000000;
		a += new_abdopain * 0.6155954984707594500000000;
		a += new_appetiteloss * 0.6842184594676019600000000;
		a += new_haematuria * 4.1791444537241542000000000;
		a += new_indigestion * 0.5694329224821874600000000;
		a += new_pmb * 1.2541097882792864000000000;
		a += new_weightloss * 0.7711610560290518300000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.9440775553776248000000000;
		return score;
	}

	public static String renal_tract_cancer_female_validation(int age, double bmi, int c_hb, int new_abdopain,
			int new_appetiteloss, int new_haematuria, int new_indigestion, int new_pmb, int new_weightloss,
			int smoke_cat) {
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
		if (!CV.is_boolean(new_haematuria)) {
			resultString += "error: new_haematuria must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_indigestion)) {
			resultString += "error: new_indigestion must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_pmb)) {
			resultString += "error: new_pmb must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		return resultString;
	}

	public static double renal_tract_cancer_male_raw(int age, double bmi, int new_abdopain, int new_haematuria,
			int new_nightsweats, int new_weightloss, int smoke_cat) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.4183007995792849000000000, 0.6335162368278742800000000, 0.7847230879322205600000000,
				0.9631091411295211700000000 };

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

		a += age_1 * 6.2113803461111061000000000;
		a += age_2 * -1.9835661506953870000000000;
		a += bmi_1 * -1.5995682550089132000000000;
		a += bmi_2 * -0.0777696836930753120000000;

		/* Sum from boolean values */

		a += new_abdopain * 0.6089465678909584700000000;
		a += new_haematuria * 4.1596453389556789000000000;
		a += new_nightsweats * 1.0520790556587876000000000;
		a += new_weightloss * 0.6824635274408537000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.3006555398942510000000000;
		return score;
	}

	public static String renal_tract_cancer_male_validation(int age, double bmi, int new_abdopain, int new_haematuria,
			int new_nightsweats, int new_weightloss, int smoke_cat) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
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
		if (!CV.is_boolean(new_nightsweats)) {
			resultString += "error: new_nightsweats must be in range (0,1)\n";
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
