package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class OvarianCancer {
	public static double Q86_ovariancancer_female_raw(int age, int b_breastcancer, int b_cervicalcancer, int b_cop,
			double bmi, int fh_ovariancancer, int surv) {
		double survivor[] = { 0, 0.999722599983215, 0.999479830265045, 0.999221026897430, 0.998951792716980,
				0.998684525489807, 0.998405218124390, 0.998142182826996, 0.997843563556671, 0.997516214847565,
				0.997178792953491, 0.996871292591095, 0.996531069278717, 0.996197998523712, 0.995813310146332,
				0.995406508445740 };

		/* The conditional arrays */

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = dage;
		double age_2 = dage * Math.log(dage);

		/* Centring the continuous variables */

		age_1 = age_1 - 4.487829208374023;
		age_2 = age_2 - 6.737888336181641;
		bmi = bmi - 25.727840423583984;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		/* Sum from continuous values */

		a += age_1 * 3.6496242385839661000000000;
		a += age_2 * -1.1991731219303103000000000;
		a += bmi * 0.0113045987694205250000000;

		/* Sum from boolean values */

		a += b_breastcancer * 0.4854695109599924700000000;
		a += b_cervicalcancer * 0.4730569425892405300000000;
		a += b_cop * -0.4300668208651634000000000;
		a += fh_ovariancancer * 1.3363594834693762000000000;

		/* Sum from interaction terms */

		a += age_1 * fh_ovariancancer * -4.2926668536565487000000000;
		a += age_2 * fh_ovariancancer * 1.6882049332933697000000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_ovariancancer_female_validation(int age, int b_breastcancer, int b_cervicalcancer,
			int b_cop, double bmi, int fh_ovariancancer, int surv) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_breastcancer)) {
			resultString += "error: b_breastcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cervicalcancer)) {
			resultString += "error: b_cervicalcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cop)) {
			resultString += "error: b_cop must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(fh_ovariancancer)) {
			resultString += "error: fh_ovariancancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {
			resultString += "error: surv must be in range (1,15)\n";
		}
		return resultString;
	}

	public static double ovarian_cancer_female_raw(int age, double bmi, int c_hb, int fh_ovariancancer, int new_abdodist,
			int new_abdopain, int new_appetiteloss, int new_haematuria, int new_indigestion, int new_pmb, int new_vte,
			int new_weightloss, int s1_bowelchange) {

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

		a += age_1 * -61.0831814462568940000000000;
		a += age_2 * 20.3028612701106890000000000;
		a += bmi_1 * -2.1261135335028407000000000;
		a += bmi_2 * 3.2168200408772472000000000;

		/* Sum from boolean values */

		a += c_hb * 1.3625636791018674000000000;
		a += fh_ovariancancer * 1.9951774809951830000000000;
		a += new_abdodist * 2.9381020883363806000000000;
		a += new_abdopain * 1.7307824546132513000000000;
		a += new_appetiteloss * 1.0606947909647773000000000;
		a += new_haematuria * 0.4958835997468107900000000;
		a += new_indigestion * 0.3843731027493998400000000;
		a += new_pmb * 1.5869592940878865000000000;
		a += new_vte * 1.6839747529852673000000000;
		a += new_weightloss * 0.4774332393821720800000000;
		a += s1_bowelchange * 0.6849850007182314300000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -7.5609929644491318000000000;
		return score;
	}

	public static String ovarian_cancer_female_validation(int age, double bmi, int c_hb, int fh_ovariancancer, int new_abdodist,
			int new_abdopain, int new_appetiteloss, int new_haematuria, int new_indigestion, int new_pmb, int new_vte,
			int new_weightloss, int s1_bowelchange) {
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
		if (!CV.is_boolean(fh_ovariancancer)) {
			resultString += "error: fh_ovariancancer must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_haematuria)) {
			resultString += "error: new_haematuria must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_indigestion)) {
			resultString += "error: new_indigestion must be in range (0,1)\n";
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
		return resultString;
	}

}
