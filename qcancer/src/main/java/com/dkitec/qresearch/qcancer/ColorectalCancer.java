package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class ColorectalCancer {

	public static double Q86_colorectal_female_raw(int age, int alcohol_cat6, int b_breastcancer, int b_cervicalcancer,
			int b_colitis, int b_ovariancancer, int b_polyp, int b_type2, int b_uterinecancer, int ethrisk,
			int fh_gicancer, int smoke_cat, int surv) {
		double survivor[] = { 0, 0.999769449234009, 0.999535322189331, 0.999294936656952, 0.999053359031677,
				0.998782038688660, 0.998504579067230, 0.998211085796356, 0.997897207736969, 0.997562766075134,
				0.997171759605408, 0.996776700019836, 0.996326744556427, 0.995871961116791, 0.995379388332367,
				0.994890332221985 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.0169323170224846170000000, 0.0504716118793323290000000, 0.0813022183502749850000000,
				0.3188427641003217500000000, 0.3071138493330506400000000 };
		double Iethrisk[] = { 0, 0, -1.0494971746256023000000000, -0.7580085872167761100000000,
				-0.1620641824282297600000000, -0.5207551589289394200000000, -0.3390081073116003000000000,
				-0.3663991962571118100000000, -0.4962552732151416800000000, -0.2228977014850979700000000 };
		double Ismoke[] = { 0, 0.0679430658242369530000000, 0.1001705670170613500000000, 0.1940325149380217600000000,
				0.1609524248529591600000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, -2);
		double age_2 = Math.pow(dage, -2) * Math.log(dage);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.049712907522917;
		age_2 = age_2 - 0.074606411159039;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 33.9278823480095400000000000;
		a += age_2 * -83.9240508519935560000000000;

		/* Sum from boolean values */

		a += b_breastcancer * 0.1509613687361799900000000;
		a += b_cervicalcancer * 0.5513465936958277200000000;
		a += b_colitis * 0.5608754512000964100000000;
		a += b_ovariancancer * 0.6840022206721720900000000;
		a += b_polyp * 0.7490409118794703100000000;
		a += b_type2 * 0.1482878087477876300000000;
		a += b_uterinecancer * 0.4792395386564791100000000;
		a += fh_gicancer * 0.6616045248544631900000000;

		/* Sum from interaction terms */

		a += age_1 * fh_gicancer * 10.9854968919725080000000000;
		a += age_2 * fh_gicancer * -0.0948979335841994980000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_colorectal_female_validation(int age, int alcohol_cat6, int b_breastcancer,
			int b_cervicalcancer, int b_colitis, int b_ovariancancer, int b_polyp, int b_type2, int b_uterinecancer,
			int ethrisk, int fh_gicancer, int smoke_cat, int surv) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {

			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {

			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_breastcancer)) {

			resultString += "error: b_breastcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cervicalcancer)) {

			resultString += "error: b_cervicalcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_colitis)) {

			resultString += "error: b_colitis must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ovariancancer)) {

			resultString += "error: b_ovariancancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_polyp)) {

			resultString += "error: b_polyp must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {

			resultString += "error: b_type2 must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_uterinecancer)) {

			resultString += "error: b_uterinecancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {

			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.is_boolean(fh_gicancer)) {

			resultString += "error: fh_gicancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {

			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {

			resultString += "error: surv must be in range (1,15)\n";
		}
		return resultString;
	}

	public static double Q86_colorectal_male_raw(int age, int alcohol_cat6, int b_bloodcancer, int b_colitis,
			int b_lungcancer, int b_oralcancer, int b_polyp, int b_type2, double bmi, int ethrisk, int fh_gicancer,
			int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999779522418976, 0.999549508094788, 0.999336361885071, 0.999095499515533,
				0.998839616775513, 0.998584389686584, 0.998288571834564, 0.997958302497864, 0.997620642185211,
				0.997250497341156, 0.996843814849854, 0.996381342411041, 0.995906531810761, 0.995393931865692,
				0.994814336299896 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.0479227173271524270000000, 0.1306450076527492000000000, 0.2642416976066509500000000,
				0.4824807862366953800000000, 0.4433887052486962800000000 };
		double Iethrisk[] = { 0, 0, -0.5852347175990283400000000, -0.5864337780506865300000000,
				-0.8811080936145391200000000, -0.4825211311076027000000000, -0.3502985702195468600000000,
				-0.2868643042389117900000000, -0.2125845990082229600000000, -0.5250625721689498000000000 };
		double Ismoke[] = { 0, 0.0544365203781649460000000, 0.0669679839432098520000000, 0.0268254930577433230000000,
				0.1230134719805828800000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = dage;
		double age_2 = Math.pow(dage, 2);

		/* Centring the continuous variables */

		age_1 = age_1 - 4.425716876983643;
		age_2 = age_2 - 19.586969375610352;
		bmi = bmi - 26.309040069580078;
		town = town - 0.260672301054001;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 2.6296558591088894000000000;
		a += age_2 * -0.1495728139219066300000000;
		a += bmi * 0.0156452119427206510000000;
		a += town * 0.0089992920978670451000000;

		/* Sum from boolean values */

		a += b_bloodcancer * 0.4284078752708641600000000;
		a += b_colitis * 0.6016726220869758100000000;
		a += b_lungcancer * 0.6273185618450067800000000;
		a += b_oralcancer * 0.4825177045812838500000000;
		a += b_polyp * 0.4092716863938517000000000;
		a += b_type2 * 0.2389208244341949300000000;
		a += fh_gicancer * 0.7802989526167445300000000;

		/* Sum from interaction terms */

		a += age_1 * fh_gicancer * -1.6277021020802243000000000;
		a += age_2 * fh_gicancer * 0.1248377530713149100000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_colorectal_male_validation(int age, int alcohol_cat6, int b_bloodcancer, int b_colitis,
			int b_lungcancer, int b_oralcancer, int b_polyp, int b_type2, double bmi, int ethrisk, int fh_gicancer,
			int smoke_cat, int surv, double town) {

		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {

			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {

			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {

			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_colitis)) {

			resultString += "error: b_colitis must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_lungcancer)) {

			resultString += "error: b_lungcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_oralcancer)) {

			resultString += "error: b_oralcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_polyp)) {

			resultString += "error: b_polyp must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_type2)) {

			resultString += "error: b_type2 must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 18, 47)) {

			resultString += "error: bmi must be in range (18,47)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {

			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.is_boolean(fh_gicancer)) {

			resultString += "error: fh_gicancer must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {

			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 15)) {

			resultString += "error: surv must be in range (1,15)\n";
		}
		if (!CV.d_in_range(town, -8, 11)) {

			resultString += "error: town must be in range (-8,11)\n";
		}
		return resultString;
	}

	public static double colorectal_cancer_female_raw(int age, int alcohol_cat4, double bmi, int c_hb, int fh_gicancer,
			int new_abdodist, int new_abdopain, int new_appetiteloss, int new_rectalbleed, int new_vte,
			int new_weightloss, int s1_bowelchange, int s1_constipation) {
		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.2429014262884695900000000, 0.2359224520197608100000000,
				0.4606605934539446100000000 };

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

		/* Sum from continuous values */

		a += age_1 * -11.6175606616390770000000000;
		a += age_2 * -42.9098057686870220000000000;
		a += bmi_1 * -0.5344237822753052900000000;
		a += bmi_2 * 2.6900552265408226000000000;

		/* Sum from boolean values */

		a += c_hb * 1.4759238359186861000000000;
		a += fh_gicancer * 0.4044501048847998200000000;
		a += new_abdodist * 0.6630074287856559900000000;
		a += new_abdopain * 1.4990872468711913000000000;
		a += new_appetiteloss * 0.5068020107261922400000000;
		a += new_rectalbleed * 2.7491673095810105000000000;
		a += new_vte * 0.7072816884002932600000000;
		a += new_weightloss * 1.0288860866585736000000000;
		a += s1_bowelchange * 0.7664414123199643200000000;
		a += s1_constipation * 0.3375158123121173600000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -7.5466948789670942000000000;
		return score;
	}

	public static String colorectal_cancer_female_validation(int age, int alcohol_cat4, double bmi, int c_hb, int fh_gicancer,
			int new_abdodist, int new_abdopain, int new_appetiteloss, int new_rectalbleed, int new_vte,
			int new_weightloss, int s1_bowelchange, int s1_constipation) {
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
		if (!CV.is_boolean(c_hb)) {
			resultString += "error: c_hb must be in range (0,1)\n";
		}
		if (!CV.is_boolean(fh_gicancer)) {
			resultString += "error: fh_gicancer must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_rectalbleed)) {
			resultString += "error: new_rectalbleed must be in range (0,1)\n";
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
		return resultString;
	}

	public static double colorectal_cancer_male_raw(int age, int alcohol_cat4, double bmi, int c_hb, int fh_gicancer,
			int new_abdodist, int new_abdopain, int new_appetiteloss, int new_rectalbleed, int new_weightloss,
			int s1_bowelchange, int s1_constipation) {
		/* The conditional arrays */

		double Ialcohol[] = { 0, 0.0674431700268591780000000, 0.2894952197787854000000000,
				0.4419539984974097400000000 };

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

		a += Ialcohol[alcohol_cat4];

		/* Sum from continuous values */

		a += age_1 * 7.2652842514036369000000000;
		a += age_2 * -2.3119103657424414000000000;
		a += bmi_1 * 0.4591530847132721000000000;
		a += bmi_2 * 0.1402651669090599400000000;

		/* Sum from boolean values */

		a += c_hb * 1.4066322376473517000000000;
		a += fh_gicancer * 0.4057285321010044600000000;
		a += new_abdodist * 1.3572627165452165000000000;
		a += new_abdopain * 1.5179997924486877000000000;
		a += new_appetiteloss * 0.5421335457752113300000000;
		a += new_rectalbleed * 2.8846500840638964000000000;
		a += new_weightloss * 1.1082218896963933000000000;
		a += s1_bowelchange * 1.2962496832506105000000000;
		a += s1_constipation * 0.2284256115498967100000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -7.6876342765226262000000000;
		return score;
	}

	public static String colorectal_cancer_male_validation(int age, int alcohol_cat4, double bmi, int c_hb, int fh_gicancer,
			int new_abdodist, int new_abdopain, int new_appetiteloss, int new_rectalbleed, int new_weightloss,
			int s1_bowelchange, int s1_constipation) {
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
		if (!CV.is_boolean(c_hb)) {
			resultString += "error: c_hb must be in range (0,1)\n";
		}
		if (!CV.is_boolean(fh_gicancer)) {
			resultString += "error: fh_gicancer must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_rectalbleed)) {
			resultString += "error: new_rectalbleed must be in range (0,1)\n";
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
		return resultString;
	}

}
