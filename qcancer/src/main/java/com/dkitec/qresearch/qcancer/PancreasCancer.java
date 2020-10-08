package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class PancreasCancer {

	public static double Q86_pancreascancer_female_raw(int age, int b_breastcancer, int b_chronicpan, int b_renalcancer,
			int b_type2, double bmi, int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999964594841003, 0.999925315380096, 0.999884843826294, 0.999844610691071,
				0.999795138835907, 0.999749720096588, 0.999698460102081, 0.999638736248016, 0.999571800231934,
				0.999503970146179, 0.999425113201141, 0.999347090721130, 0.999259352684021, 0.999154210090637,
				0.999044001102448 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.0253757584136018450000000, 0.5699206878296300100000000, 0.6390568783974056600000000,
				0.7042894505810612900000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, -.5);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.471977025270462;
		bmi = bmi - 25.729867935180664;
		town = town - 0.161796689033508;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -23.2601899769634670000000000;
		a += bmi * 0.0097815109538049779000000;
		a += town * 0.0114020505413058150000000;

		/* Sum from boolean values */

		a += b_breastcancer * 0.3192435657707442000000000;
		a += b_chronicpan * 1.2927221506222435000000000;
		a += b_renalcancer * 0.6789970707278264500000000;
		a += b_type2 * 0.4107439191649735500000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_pancreascancer_female_validation(int age, int b_breastcancer, int b_chronicpan,
			int b_renalcancer, int b_type2, double bmi, int smoke_cat, int surv, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_breastcancer)) {
			resultString += "error: b_breastcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_chronicpan)) {
			resultString += "error: b_chronicpan must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renalcancer)) {
			resultString += "error: b_renalcancer must be in range (0,1)\n";
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

	public static double Q86_pancreascancer_male_raw(int age, int b_bloodcancer, int b_chronicpan, int b_type2, double bmi,
			int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999952435493469, 0.999906480312347, 0.999861896038055, 0.999808788299561,
				0.999760389328003, 0.999709188938141, 0.999652206897736, 0.999588072299957, 0.999510467052460,
				0.999430298805237, 0.999344885349274, 0.999251306056976, 0.999140441417694, 0.999027967453003 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.0836788783415418070000000, 0.4448949320585067200000000, 0.6729012024361618000000000,
				0.6636259714225735100000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, -.5);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.475096940994263;
		bmi_1 = bmi_1 - 0.144456043839455;
		bmi_2 = bmi_2 - 0.139745324850082;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -22.3885037718906940000000000;
		a += bmi_1 * 4.2251656934838371000000000;
		a += bmi_2 * -11.2816438726662460000000000;

		/* Sum from boolean values */

		a += b_bloodcancer * 0.5369701587873448900000000;
		a += b_chronicpan * 1.6922917636081924000000000;
		a += b_type2 * 0.6132344324406091600000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_pancreascancer_male_validation(int age, int b_bloodcancer, int b_chronicpan, int b_type2,
			double bmi, int smoke_cat, int surv) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {
			resultString += "error: b_bloodcancer must be in range (0,1)\n";
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
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {
			resultString += "error: surv must be in range (1,15)\n";
		}
		return resultString;
	}

}
