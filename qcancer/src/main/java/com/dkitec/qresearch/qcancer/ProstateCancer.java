package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class ProstateCancer {

	public static double Q86_prostatecancer_male_raw(int age, int b_manicschiz, int b_type1, int b_type2, double bmi,
			int ethrisk, int fh_prostatecancer, int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999701976776123, 0.999389469623566, 0.999051749706268, 0.998661398887634,
				0.998229324817657, 0.997796237468719, 0.997316062450409, 0.996800720691681, 0.996231198310852,
				0.995620191097260, 0.994975268840790, 0.994252204895020, 0.993459045886993, 0.992661058902740 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, -0.5087669923542299500000000, -0.8669821970085145300000000,
				-1.2364325693857448000000000, -0.7780536719958358600000000, 1.0440869831464479000000000,
				0.6844374194113138600000000, -0.6985030851963409700000000, 0.3930883077745548900000000 };
		double Ismoke[] = { 0, 0.0158837792172948010000000, -0.2495144655718678500000000, -0.3045761454819819200000000,
				-0.2379878606796587400000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_2 = Math.pow(dage, -.5) * Math.log(dage);
		double age_1 = Math.pow(dage, -.5);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -1);
		double bmi_2 = Math.pow(dbmi, -.5);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.475571960210800;
		age_2 = age_2 - 0.706925392150879;
		bmi_1 = bmi_1 - 0.380129784345627;
		bmi_2 = bmi_2 - 0.616546630859375;
		town = town - 0.261956512928009;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -13.0996125818166860000000000;
		a += age_2 * 55.2761086374516070000000000;
		a += bmi_1 * -14.2978771013393370000000000;
		a += bmi_2 * 18.2937339717317860000000000;
		a += town * -0.0284342617816042000000000;

		/* Sum from boolean values */

		a += b_manicschiz * -0.4404637407333301700000000;
		a += b_type1 * -0.5583466121130944400000000;
		a += b_type2 * -0.1108515934078758300000000;
		a += fh_prostatecancer * 2.0344915550609244000000000;

		/* Sum from interaction terms */

		if (smoke_cat == 1)
			a += age_1 * -2.0533059417792852000000000;
		if (smoke_cat == 2)
			a += age_1 * -2.8905463101656532000000000;
		if (smoke_cat == 3)
			a += age_1 * -9.3698369802439068000000000;
		if (smoke_cat == 4)
			a += age_1 * -8.8584506998537460000000000;
		a += age_1 * fh_prostatecancer * 2.7874621493140661000000000;
		if (smoke_cat == 1)
			a += age_2 * -9.6373770483117962000000000;
		if (smoke_cat == 2)
			a += age_2 * -3.4310745946773062000000000;
		if (smoke_cat == 3)
			a += age_2 * -21.1625704609233130000000000;
		if (smoke_cat == 4)
			a += age_2 * -24.6463781132132630000000000;
		a += age_2 * fh_prostatecancer * -29.0484958367278590000000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_prostatecancer_male_validation(int age, int b_manicschiz, int b_type1, int b_type2,
			double bmi, int ethrisk, int fh_prostatecancer, int smoke_cat, int surv, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_manicschiz)) {
			resultString += "error: b_manicschiz must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type1)) {
			resultString += "error: b_type1 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {
			resultString += "error: b_type2 must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {
			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.is_boolean(fh_prostatecancer)) {
			resultString += "error: fh_prostatecancer must be in range (0,1)\n";
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

	public static double prostate_cancer_male_raw(int age, double bmi, int fh_prostatecancer, int new_abdopain,
			int new_appetiteloss, int new_haematuria, int new_rectalbleed, int new_testespain, int new_testicularlump,
			int new_vte, int new_weightloss, int s1_impotence, int s1_nocturia, int s1_urinaryfreq,
			int s1_urinaryretention, double town) {

		/* The conditional arrays */

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

		a += age_1 * 14.8391010426566920000000000;
		a += age_2 * -4.8051341054408843000000000;
		a += bmi_1 * -2.8369035324107057000000000;
		a += bmi_2 * -0.3634984265900051400000000;
		a += town * -0.0214278653071876720000000;

		/* Sum from boolean values */

		a += fh_prostatecancer * 1.2892957682128878000000000;
		a += new_abdopain * 0.4445588372860774200000000;
		a += new_appetiteloss * 0.3425581971534915100000000;
		a += new_haematuria * 1.4890866073593347000000000;
		a += new_rectalbleed * 0.3478612952033963700000000;
		a += new_testespain * 0.6387609350076407500000000;
		a += new_testicularlump * 0.6338177436853567000000000;
		a += new_vte * 0.5758190804196261500000000;
		a += new_weightloss * 0.7528736226665873100000000;
		a += s1_impotence * 0.3692180041534241500000000;
		a += s1_nocturia * 1.0381560026453696000000000;
		a += s1_urinaryfreq * 0.7036410253080365200000000;
		a += s1_urinaryretention * 0.8525703399435586900000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -7.8871012697298699000000000;
		return score;
	}

	public static String prostate_cancer_male_validation(int age, double bmi, int fh_prostatecancer, int new_abdopain,
			int new_appetiteloss, int new_haematuria, int new_rectalbleed, int new_testespain, int new_testicularlump,
			int new_vte, int new_weightloss, int s1_impotence, int s1_nocturia, int s1_urinaryfreq,
			int s1_urinaryretention, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.is_boolean(fh_prostatecancer)) {
			resultString += "error: fh_prostatecancer must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_rectalbleed)) {
			resultString += "error: new_rectalbleed must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_impotence)) {
			resultString += "error: s1_impotence must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_nocturia)) {
			resultString += "error: s1_nocturia must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_urinaryfreq)) {
			resultString += "error: s1_urinaryfreq must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_urinaryretention)) {
			resultString += "error: s1_urinaryretention must be in range (0,1)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

}
