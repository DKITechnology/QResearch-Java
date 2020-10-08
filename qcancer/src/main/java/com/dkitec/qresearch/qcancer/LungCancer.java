package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.CV;

public class LungCancer {

	public static double Q86_lungcancer_female_raw(int age, int b_asthma, int b_bloodcancer, int b_breastcancer,
			int b_cervicalcancer, int b_copd, int b_oralcancer, int b_ovariancancer, int b_renalcancer,
			int b_uterinecancer, double bmi, int ethrisk, int fh_lungcancer, int smoke_cat, int surv, double town7) {
		double survivor[] = { 0, 0.999921858310699, 0.999851703643799, 0.999781906604767, 0.999706447124481,
				0.999620974063873, 0.999528229236603, 0.999430298805237, 0.999327778816223, 0.999214231967926,
				0.999084472656250, 0.998952865600586, 0.998798847198486, 0.998634696006775, 0.998456716537476,
				0.998268306255341 };

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, -0.6162002401981916400000000, -1.0040109606629677000000000,
				0.1615347503980447400000000, -0.3770021005239821400000000, -0.6617772357014364300000000,
				-0.5930950771435631300000000, 0.1492126409887218600000000, -0.4823151789826976000000000 };
		double Ismoke[] = { 0, 0.6112447838112595700000000, 1.7357934442133507000000000, 1.8830258989540045000000000,
				2.3993189507633432000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_2 = Math.pow(dage, 3);
		double age_1 = Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);
		double dtown7 = town7;
		dtown7 = dtown7 / 10;
		double town7_1 = Math.pow(dtown7, 2);
		double town7_2 = Math.pow(dtown7, 2) * Math.log(dtown7);

		/* Centring the continuous variables */

		age_1 = age_1 - 1.501477479934692;
		age_2 = age_2 - 90.417015075683594;
		bmi_1 = bmi_1 - 0.151186034083366;
		bmi_2 = bmi_2 - 0.142813667654991;
		town7_1 = town7_1 - 0.512877285480499;
		town7_2 = town7_2 - -0.171228870749474;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 5.4497000894421888000000000;
		a += age_2 * -0.0012619451260330915000000;
		a += bmi_1 * 4.0767428769940599000000000;
		a += bmi_2 * -4.3330820833700168000000000;
		a += town7_1 * 0.5130001590659359900000000;
		a += town7_2 * -0.5476537097568797700000000;

		/* Sum from boolean values */

		a += b_asthma * 0.2819892082218823400000000;
		a += b_bloodcancer * 0.6590175252774014300000000;
		a += b_breastcancer * 0.4266457608818724500000000;
		a += b_cervicalcancer * 0.4554986072252846200000000;
		a += b_copd * 0.6763865018677477400000000;
		a += b_oralcancer * 1.0406587153102334000000000;
		a += b_ovariancancer * 0.4930947375701635900000000;
		a += b_renalcancer * 0.5536602672969874700000000;
		a += b_uterinecancer * 0.4238240472459343100000000;
		a += fh_lungcancer * 0.2787662707402811500000000;

		/* Sum from interaction terms */

		if (smoke_cat == 1)
			a += age_1 * 2.4894005284225766000000000;
		if (smoke_cat == 2)
			a += age_1 * 2.4560227226070861000000000;
		if (smoke_cat == 3)
			a += age_1 * 2.4320841256493297000000000;
		if (smoke_cat == 4)
			a += age_1 * 1.7510523118074026000000000;
		if (smoke_cat == 1)
			a += age_2 * -0.0027978525295064924000000;
		if (smoke_cat == 2)
			a += age_2 * -0.0033488391521371424000000;
		if (smoke_cat == 3)
			a += age_2 * -0.0028167729480567654000000;
		if (smoke_cat == 4)
			a += age_2 * -0.0022070093830298793000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_lungcancer_female_validation(int age, int b_asthma, int b_bloodcancer, int b_breastcancer,
			int b_cervicalcancer, int b_copd, int b_oralcancer, int b_ovariancancer, int b_renalcancer,
			int b_uterinecancer, double bmi, int ethrisk, int fh_lungcancer, int smoke_cat, int surv, double town7) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {

			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_asthma)) {

			resultString += "error: b_asthma must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {

			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_breastcancer)) {

			resultString += "error: b_breastcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cervicalcancer)) {

			resultString += "error: b_cervicalcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_copd)) {

			resultString += "error: b_copd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_oralcancer)) {

			resultString += "error: b_oralcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ovariancancer)) {

			resultString += "error: b_ovariancancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renalcancer)) {

			resultString += "error: b_renalcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_uterinecancer)) {

			resultString += "error: b_uterinecancer must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {

			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {

			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.is_boolean(fh_lungcancer)) {

			resultString += "error: fh_lungcancer must be in range (0,1)\n";
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

	public static double Q86_lungcancer_male_raw(int age, int alcohol_cat6, int b_asbestos, int b_asthma,
			int b_bloodcancer, int b_colorectal, int b_copd, int b_oesgastric, int b_oralcancer, int b_renalcancer,
			double bmi, int ethrisk, int fh_lungcancer, int smoke_cat, int surv, double town7) {
		double survivor[] = { 0, 0.999934911727905, 0.999871730804443, 0.999810755252838, 0.999744892120361,
				0.999673485755920, 0.999599277973175, 0.999516308307648, 0.999427139759064, 0.999328792095184,
				0.999227404594421, 0.999115765094757, 0.998985946178436, 0.998845696449280, 0.998694002628326 };

		/* The conditional arrays */

		double Ialcohol[] = { 0, -0.0976469653364018390000000, -0.0879200027365035360000000,
				-0.0278597758194441820000000, 0.1241637053296593500000000, 0.2226908618750974600000000 };
		double Iethrisk[] = { 0, 0, -1.0329606238232618000000000, -0.9040340734258540700000000,
				-0.1694997390935355200000000, -1.0081395942946074000000000, -0.5478058685981370700000000,
				-0.8006260712380517400000000, -0.4445250009469628400000000, -0.6746246877133228500000000 };
		double Ismoke[] = { 0, 0.8642971386547151400000000, 1.7805875732861698000000000, 1.9642308923116378000000000,
				2.3266790251560496000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_2 = dage * Math.log(dage);
		double age_1 = dage;
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -1);
		double bmi_2 = Math.pow(dbmi, -1) * Math.log(dbmi);
		double dtown7 = town7;
		dtown7 = dtown7 / 10;
		double town7_2 = Math.pow(dtown7, 2) * Math.log(dtown7);
		double town7_1 = Math.pow(dtown7, 2);

		/* Centring the continuous variables */

		age_1 = age_1 - 4.428966999053955;
		age_2 = age_2 - 6.591039657592773;
		bmi_1 = bmi_1 - 0.380103737115860;
		bmi_2 = bmi_2 - 0.367678552865982;
		town7_1 = town7_1 - 0.526906073093414;
		town7_2 = town7_2 - -0.168803051114082;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ialcohol[alcohol_cat6];
		a += Iethrisk[ethrisk];
		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 6.4302344272843293000000000;
		a += age_2 * -1.9010604177860093000000000;
		a += bmi_1 * 1.9426789925637902000000000;
		a += bmi_2 * -6.8934141805901561000000000;
		a += town7_1 * 0.5194559211143612300000000;
		a += town7_2 * -0.5458171567892952100000000;

		/* Sum from boolean values */

		a += b_asbestos * 0.6146298072418803200000000;
		a += b_asthma * 0.1671847013911105200000000;
		a += b_bloodcancer * 0.6466151320955584300000000;
		a += b_colorectal * 0.2532632537969352100000000;
		a += b_copd * 0.6503977941968708300000000;
		a += b_oesgastric * 0.5814056719367335400000000;
		a += b_oralcancer * 1.0504922458361461000000000;
		a += b_renalcancer * 0.4056466939105595700000000;
		a += fh_lungcancer * 0.2499856185080559200000000;

		/* Sum from interaction terms */

		if (smoke_cat == 1)
			a += age_1 * 1.6231899673882400000000000;
		if (smoke_cat == 2)
			a += age_1 * 1.9123668227082782000000000;
		if (smoke_cat == 3)
			a += age_1 * 1.7351810159906245000000000;
		if (smoke_cat == 4)
			a += age_1 * 1.7643679086449198000000000;
		if (smoke_cat == 1)
			a += age_2 * -0.6179690496015832800000000;
		if (smoke_cat == 2)
			a += age_2 * -0.7425081658927767900000000;
		if (smoke_cat == 3)
			a += age_2 * -0.6638437299823460900000000;
		if (smoke_cat == 4)
			a += age_2 * -0.7032486249187754900000000;

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static String Q86_lungcancer_male_validation(int age, int alcohol_cat6, int b_asbestos, int b_asthma,
			int b_bloodcancer, int b_colorectal, int b_copd, int b_oesgastric, int b_oralcancer, int b_renalcancer,
			double bmi, int ethrisk, int fh_lungcancer, int smoke_cat, int surv, double town7) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 84)) {

			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.i_in_range(alcohol_cat6, 0, 5)) {

			resultString += "error: alcohol_cat6 must be in range (0,5)\n";
		}
		if (!CV.is_boolean(b_asbestos)) {

			resultString += "error: b_asbestos must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_asthma)) {

			resultString += "error: b_asthma must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_bloodcancer)) {

			resultString += "error: b_bloodcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_colorectal)) {

			resultString += "error: b_colorectal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_copd)) {

			resultString += "error: b_copd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_oesgastric)) {

			resultString += "error: b_oesgastric must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_oralcancer)) {

			resultString += "error: b_oralcancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renalcancer)) {

			resultString += "error: b_renalcancer must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {

			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(ethrisk, 1, 9)) {

			resultString += "error: ethrisk must be in range (1,9)\n";
		}
		if (!CV.is_boolean(fh_lungcancer)) {

			resultString += "error: fh_lungcancer must be in range (0,1)\n";
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

	public static double lung_cancer_female_raw(int age, int b_copd, double bmi, int c_hb, int new_appetiteloss,
			int new_dysphagia, int new_haemoptysis, int new_indigestion, int new_necklump, int new_vte,
			int new_weightloss, int s1_cough, int smoke_cat, double town) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 1.3397416191950409000000000, 1.9500839456663224000000000, 2.1881694694325233000000000,
				2.4828660433307768000000000 };

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

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * -117.2405737502962500000000000;
		a += age_2 * 25.1702254741268090000000000;
		a += bmi_1 * 2.5845488133924350000000000;
		a += bmi_2 * -0.6083523966762799400000000;
		a += town * 0.0406920461830567460000000;

		/* Sum from boolean values */

		a += b_copd * 0.7942901962671364800000000;
		a += c_hb * 0.8627980324401628400000000;
		a += new_appetiteloss * 0.7170232121379446200000000;
		a += new_dysphagia * 0.6718426806077323300000000;
		a += new_haemoptysis * 2.9286439157734474000000000;
		a += new_indigestion * 0.3634893730114273600000000;
		a += new_necklump * 1.2097240380091590000000000;
		a += new_vte * 0.8907072670032341000000000;
		a += new_weightloss * 1.1384524885073082000000000;
		a += s1_cough * 0.6439917053275602300000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.6449002971789692000000000;
		return score;
	}

	public static String lung_cancer_female_validation(int age, int b_copd, double bmi, int c_hb, int new_appetiteloss,
			int new_dysphagia, int new_haemoptysis, int new_indigestion, int new_necklump, int new_vte,
			int new_weightloss, int s1_cough, int smoke_cat, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
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
		if (!CV.is_boolean(new_appetiteloss)) {
			resultString += "error: new_appetiteloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_dysphagia)) {
			resultString += "error: new_dysphagia must be in range (0,1)\n";
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
		if (!CV.is_boolean(s1_cough)) {
			resultString += "error: s1_cough must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

	public static double lung_cancer_male_raw(int age, int b_copd, double bmi, int c_hb, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_haemoptysis, int new_indigestion, int new_necklump,
			int new_nightsweats, int new_vte, int new_weightloss, int s1_cough, int smoke_cat, double town) {

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.8408574737524464600000000, 1.4966499028172435000000000, 1.7072509513243501000000000,
				1.8882615411851338000000000 };

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

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 11.9178089602254960000000000;
		a += age_2 * -3.8503786390624457000000000;
		a += bmi_1 * 1.8605584222949920000000000;
		a += bmi_2 * -0.1132750038800869900000000;
		a += town * 0.0285745703610741780000000;

		/* Sum from boolean values */

		a += b_copd * 0.5526127629694074200000000;
		a += c_hb * 0.8243789117069311200000000;
		a += new_abdopain * 0.3996424879103057700000000;
		a += new_appetiteloss * 0.7487413720163385000000000;
		a += new_dysphagia * 1.0410482089004374000000000;
		a += new_haemoptysis * 2.8241680746676243000000000;
		a += new_indigestion * 0.2689673675929089000000000;
		a += new_necklump * 1.1065323833644807000000000;
		a += new_nightsweats * 0.7890696583845964200000000;
		a += new_vte * 0.7991150296038754800000000;
		a += new_weightloss * 1.3738119234931856000000000;
		a += s1_cough * 0.5154179003437485700000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + -8.7166918098019277000000000;
		return score;
	}

	public static String lung_cancer_male_validation(int age, int b_copd, double bmi, int c_hb, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_haemoptysis, int new_indigestion, int new_necklump,
			int new_nightsweats, int new_vte, int new_weightloss, int s1_cough, int smoke_cat, double town) {
		String resultString = "";
		if (!CV.i_in_range(age, 25, 89)) {
			resultString += "error: age must be in range (25,89)\n";
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
		if (!CV.is_boolean(new_abdopain)) {
			resultString += "error: new_abdopain must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_appetiteloss)) {
			resultString += "error: new_appetiteloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_dysphagia)) {
			resultString += "error: new_dysphagia must be in range (0,1)\n";
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
		if (!CV.is_boolean(new_vte)) {
			resultString += "error: new_vte must be in range (0,1)\n";
		}
		if (!CV.is_boolean(new_weightloss)) {
			resultString += "error: new_weightloss must be in range (0,1)\n";
		}
		if (!CV.is_boolean(s1_cough)) {
			resultString += "error: s1_cough must be in range (0,1)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}
		return resultString;
	}

}
