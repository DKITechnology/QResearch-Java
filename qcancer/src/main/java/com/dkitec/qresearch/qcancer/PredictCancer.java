package com.dkitec.qresearch.qcancer;

import com.dkitec.qresearch.common.BmiCalculator;
import com.dkitec.qresearch.common.BmiPredictor;
import com.dkitec.qresearch.common.CV;
import com.dkitec.qresearch.common.Input;
import com.dkitec.redwood.api.predict.PredictScript;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PredictCancer implements PredictScript {

	public void loadModel(String modelPath) {
	}

	public String predict(String jsonStr) {

		ObjectMapper mapper = new ObjectMapper();

		Input in = null;
		try {
			in = mapper.readValue(jsonStr, Input.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String errorMsg = null;

		if (in.getHeight() > 0 && in.getWeight() > 0)
			in.setBmi(BmiCalculator.calculate(in.getHeight(), in.getWeight()));
		else
			in.setBmi(BmiPredictor.predict(in.getAge(), 0, 0, 0, in.getEthrisk(), in.getSmoke_cat(), in.getGender()));

		if (!CV.d_in_range(in.getBmi(), 20, 40)) {
			errorMsg = "error: bmi must be in range (20,40)\n";
			return errorMsg;
		}

		errorMsg = checkValidation2(in);
		if (errorMsg != null) {
			return errorMsg;
		}

		return calculateAllScores2(in.getAge(), in.getAlcohol_cat6(), in.getB_asbestos(), in.getB_asthma(),
				in.getB_barratts(), in.getB_benignbreast(), in.getB_bloodcancer(), in.getB_braincancer(),
				in.getB_breastcancer(), in.getB_cervicalcancer(), in.getB_chronicpan(), in.getB_colitis(),
				in.getB_colorectal(), in.getB_cop(), in.getB_copd(), in.getB_endometrial(), in.getB_hrt_oest(),
				in.getB_lungcancer(), in.getB_manicschiz(), in.getB_oesgastric(), in.getB_oralcancer(),
				in.getB_pancreascancer(), in.getB_ovariancancer(), in.getB_peptic(), in.getB_polyp(), in.getB_pos(),
				in.getB_prostatecancer(), in.getB_renalcancer(), in.getB_type1(), in.getB_type2(),
				in.getB_uterinecancer(), in.getEthrisk(), in.getFh_bloodcancer(), in.getFh_breastcancer(),
				in.getFh_gicancer(), in.getFh_lungcancer(), in.getFh_ovariancancer(), in.getFh_prostatecancer(),
				in.getSmoke_cat(), in.getSurv(), in.getBmi(), in.getTown(), in.getGender());
	}

	private String checkValidation(Input in) {
		String resultString = "";
		if (in.getGender() == 0) {
			resultString += BloodCancer.blood_cancer_female_validation(in.getAge(), in.getBmi(), in.getC_hb(),
					in.getNew_abdopain(), in.getNew_haematuria(), in.getNew_necklump(), in.getNew_nightsweats(),
					in.getNew_pmb(), in.getNew_vte(), in.getNew_weightloss(), in.getS1_bowelchange(),
					in.getS1_bruising());
			resultString += BreastCancer.breast_cancer_female_validation(in.getAge(), in.getAlcohol_cat4(), in.getBmi(),
					in.getFh_breastcancer(), in.getNew_breastlump(), in.getNew_breastpain(), in.getNew_breastskin(),
					in.getNew_pmb(), in.getNew_vte(), in.getTown());
			resultString += CervicalCancer.cervical_cancer_female_validation(in.getAge(), in.getBmi(), in.getC_hb(),
					in.getNew_abdopain(), in.getNew_haematuria(), in.getNew_imb(), in.getNew_pmb(),
					in.getNew_postcoital(), in.getNew_vte(), in.getSmoke_cat(), in.getTown());
			resultString += ColorectalCancer.colorectal_cancer_female_validation(in.getAge(), in.getAlcohol_cat4(),
					in.getBmi(), in.getC_hb(), in.getFh_gicancer(), in.getNew_abdodist(), in.getNew_abdopain(),
					in.getNew_appetiteloss(), in.getNew_rectalbleed(), in.getNew_vte(), in.getNew_weightloss(),
					in.getS1_bowelchange(), in.getS1_constipation());
			resultString += GastroOesophagealCancer.gastro_oesophageal_cancer_female_validation(in.getAge(),
					in.getBmi(), in.getC_hb(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_dysphagia(),
					in.getNew_gibleed(), in.getNew_heartburn(), in.getNew_indigestion(), in.getNew_vte(),
					in.getNew_weightloss(), in.getSmoke_cat());
			resultString += LungCancer.lung_cancer_female_validation(in.getAge(), in.getB_copd(), in.getBmi(),
					in.getC_hb(), in.getNew_appetiteloss(), in.getNew_dysphagia(), in.getNew_haemoptysis(),
					in.getNew_indigestion(), in.getNew_necklump(), in.getNew_vte(), in.getNew_weightloss(),
					in.getS1_cough(), in.getSmoke_cat(), in.getTown());
			resultString += OtherCancer.other_cancer_female_validation(in.getAge(), in.getAlcohol_cat4(),
					in.getB_copd(), in.getBmi(), in.getC_hb(), in.getNew_abdodist(), in.getNew_abdopain(),
					in.getNew_appetiteloss(), in.getNew_breastlump(), in.getNew_dysphagia(), in.getNew_gibleed(),
					in.getNew_haematuria(), in.getNew_indigestion(), in.getNew_necklump(), in.getNew_pmb(),
					in.getNew_vte(), in.getNew_weightloss(), in.getS1_constipation(), in.getSmoke_cat());
			resultString += OvarianCancer.ovarian_cancer_female_validation(in.getAge(), in.getBmi(), in.getC_hb(),
					in.getFh_ovariancancer(), in.getNew_abdodist(), in.getNew_abdopain(), in.getNew_appetiteloss(),
					in.getNew_haematuria(), in.getNew_indigestion(), in.getNew_pmb(), in.getNew_vte(),
					in.getNew_weightloss(), in.getS1_bowelchange());
			resultString += PancreaticCancer.pancreatic_cancer_female_validation(in.getAge(), in.getB_chronicpan(),
					in.getB_type2(), in.getBmi(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_dysphagia(),
					in.getNew_gibleed(), in.getNew_indigestion(), in.getNew_vte(), in.getNew_weightloss(),
					in.getS1_bowelchange(), in.getSmoke_cat());
			resultString += RenalCancer.renal_tract_cancer_female_validation(in.getAge(), in.getBmi(), in.getC_hb(),
					in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_haematuria(), in.getNew_indigestion(),
					in.getNew_pmb(), in.getNew_weightloss(), in.getSmoke_cat());
			resultString += UterineCancer.uterine_cancer_female_validation(in.getAge(), in.getB_endometrial(),
					in.getB_type2(), in.getBmi(), in.getNew_abdopain(), in.getNew_haematuria(), in.getNew_imb(),
					in.getNew_pmb(), in.getNew_vte());

		} else {
			resultString += BloodCancer.blood_cancer_male_validation(in.getAge(), in.getBmi(), in.getC_hb(),
					in.getNew_abdodist(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_dysphagia(),
					in.getNew_haematuria(), in.getNew_haemoptysis(), in.getNew_indigestion(), in.getNew_necklump(),
					in.getNew_nightsweats(), in.getNew_testicularlump(), in.getNew_vte(), in.getNew_weightloss(),
					in.getTown());
			resultString += ColorectalCancer.colorectal_cancer_male_validation(in.getAge(), in.getAlcohol_cat4(),
					in.getBmi(), in.getC_hb(), in.getFh_gicancer(), in.getNew_abdodist(), in.getNew_abdopain(),
					in.getNew_appetiteloss(), in.getNew_rectalbleed(), in.getNew_weightloss(), in.getS1_bowelchange(),
					in.getS1_constipation());
			resultString += GastroOesophagealCancer.gastro_oesophageal_cancer_male_validation(in.getAge(), in.getBmi(),
					in.getC_hb(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_dysphagia(),
					in.getNew_gibleed(), in.getNew_heartburn(), in.getNew_indigestion(), in.getNew_necklump(),
					in.getNew_weightloss(), in.getSmoke_cat());
			resultString += LungCancer.lung_cancer_male_validation(in.getAge(), in.getB_copd(), in.getBmi(),
					in.getC_hb(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_dysphagia(),
					in.getNew_haemoptysis(), in.getNew_indigestion(), in.getNew_necklump(), in.getNew_nightsweats(),
					in.getNew_vte(), in.getNew_weightloss(), in.getS1_cough(), in.getSmoke_cat(), in.getTown());
			resultString += OtherCancer.other_cancer_male_validation(in.getAge(), in.getB_copd(), in.getB_type2(),
					in.getBmi(), in.getC_hb(), in.getNew_abdodist(), in.getNew_abdopain(), in.getNew_appetiteloss(),
					in.getNew_dysphagia(), in.getNew_gibleed(), in.getNew_haematuria(), in.getNew_haemoptysis(),
					in.getNew_indigestion(), in.getNew_necklump(), in.getNew_vte(), in.getNew_weightloss(),
					in.getS1_bowelchange(), in.getS1_constipation(), in.getSmoke_cat());
			resultString += PancreaticCancer.pancreatic_cancer_male_validation(in.getAge(), in.getB_chronicpan(),
					in.getB_type2(), in.getBmi(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_dysphagia(),
					in.getNew_gibleed(), in.getNew_indigestion(), in.getNew_vte(), in.getNew_weightloss(),
					in.getS1_constipation(), in.getSmoke_cat(), in.getTown());
			resultString += ProstateCancer.prostate_cancer_male_validation(in.getAge(), in.getBmi(),
					in.getFh_prostatecancer(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_haematuria(),
					in.getNew_rectalbleed(), in.getNew_testespain(), in.getNew_testicularlump(), in.getNew_vte(),
					in.getNew_weightloss(), in.getS1_impotence(), in.getS1_nocturia(), in.getS1_urinaryfreq(),
					in.getS1_urinaryretention(), in.getTown());
			resultString += RenalCancer.renal_tract_cancer_male_validation(in.getAge(), in.getBmi(),
					in.getNew_abdopain(), in.getNew_haematuria(), in.getNew_nightsweats(), in.getNew_weightloss(),
					in.getSmoke_cat());
			resultString += TesticularCancer.testicular_cancer_male_validation(in.getAge(), in.getBmi(),
					in.getNew_testespain(), in.getNew_testicularlump(), in.getNew_vte());

		}

		if (resultString.length() > 0)
			return resultString;
		else
			return null;
	}

	private String checkValidation2(Input in) {
		String resultString = "";
		if (in.getGender() == 0) {

			resultString += BloodCancer.Q86_bloodcancer_female_validation(in.getAge(), in.getB_braincancer(),
					in.getB_ovariancancer(), in.getB_type1(), in.getBmi(), in.getFh_bloodcancer(), in.getSmoke_cat(),
					in.getSurv());
			resultString += BreastCancer.Q86_breastcancer_female_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_benignbreast(), in.getB_bloodcancer(), in.getB_cop(), in.getB_hrt_oest(),
					in.getB_lungcancer(), in.getB_manicschiz(), in.getB_ovariancancer(), in.getBmi(), in.getEthrisk(),
					in.getFh_breastcancer(), in.getSurv(), in.getTown());
			resultString += ColorectalCancer.Q86_colorectal_female_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_breastcancer(), in.getB_cervicalcancer(), in.getB_colitis(), in.getB_ovariancancer(),
					in.getB_polyp(), in.getB_type2(), in.getB_uterinecancer(), in.getEthrisk(), in.getFh_gicancer(),
					in.getSmoke_cat(), in.getSurv());
			resultString += GastroOesophagealCancer.Q86_oesgastric_female_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_barratts(), in.getB_bloodcancer(), in.getB_breastcancer(), in.getB_lungcancer(),
					in.getB_oralcancer(), in.getB_peptic(), in.getB_type2(), in.getBmi(), in.getSmoke_cat(),
					in.getSurv(), in.getTown());
			resultString += LungCancer.Q86_lungcancer_female_validation(in.getAge(), in.getB_asthma(),
					in.getB_bloodcancer(), in.getB_breastcancer(), in.getB_cervicalcancer(), in.getB_copd(),
					in.getB_oralcancer(), in.getB_ovariancancer(), in.getB_renalcancer(), in.getB_uterinecancer(),
					in.getBmi(), in.getEthrisk(), in.getFh_lungcancer(), in.getSmoke_cat(), in.getSurv(), in.getTown());
			resultString += OralCancer.Q86_oralcancer_female_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_bloodcancer(), in.getB_ovariancancer(), in.getSmoke_cat(), in.getSurv(), in.getTown());
			resultString += OvarianCancer.Q86_ovariancancer_female_validation(in.getAge(), in.getB_breastcancer(),
					in.getB_cervicalcancer(), in.getB_cop(), in.getBmi(), in.getFh_ovariancancer(), in.getSurv());
			resultString += PancreasCancer.Q86_pancreascancer_female_validation(in.getAge(), in.getB_breastcancer(),
					in.getB_chronicpan(), in.getB_renalcancer(), in.getB_type2(), in.getBmi(), in.getSmoke_cat(),
					in.getSurv(), in.getTown());
			resultString += RenalCancer.Q86_renalcancer_female_validation(in.getAge(), in.getB_bloodcancer(),
					in.getB_braincancer(), in.getB_cervicalcancer(), in.getB_colorectal(), in.getB_ovariancancer(),
					in.getB_type2(), in.getB_uterinecancer(), in.getBmi(), in.getSmoke_cat(), in.getSurv(),
					in.getTown());
			resultString += UterineCancer.Q86_uterinecancer_female_validation(in.getAge(), in.getB_breastcancer(),
					in.getB_colorectal(), in.getB_endometrial(), in.getB_manicschiz(), in.getB_pos(), in.getB_type2(),
					in.getBmi(), in.getSmoke_cat(), in.getSurv());
		} else {
			resultString += BloodCancer.Q86_bloodcancer_male_validation(in.getAge(), in.getB_renalcancer(),
					in.getB_type1(), in.getBmi(), in.getFh_bloodcancer(), in.getSmoke_cat(), in.getSurv());
			resultString += ColorectalCancer.Q86_colorectal_male_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_bloodcancer(), in.getB_colitis(), in.getB_lungcancer(), in.getB_oralcancer(),
					in.getB_polyp(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_gicancer(),
					in.getSmoke_cat(), in.getSurv(), in.getTown());
			resultString += GastroOesophagealCancer.Q86_oesgastric_male_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_barratts(), in.getB_oralcancer(), in.getB_pancreascancer(), in.getB_peptic(),
					in.getB_type2(), in.getBmi(), in.getSmoke_cat(), in.getSurv(), in.getTown());
			resultString += LungCancer.Q86_lungcancer_male_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_asbestos(), in.getB_asthma(), in.getB_bloodcancer(), in.getB_colorectal(), in.getB_copd(),
					in.getB_oesgastric(), in.getB_oralcancer(), in.getB_renalcancer(), in.getBmi(), in.getEthrisk(),
					in.getFh_lungcancer(), in.getSmoke_cat(), in.getSurv(), in.getTown());
			resultString += OralCancer.Q86_oralcancer_male_validation(in.getAge(), in.getAlcohol_cat6(),
					in.getB_bloodcancer(), in.getB_colorectal(), in.getB_lungcancer(), in.getBmi(), in.getSmoke_cat(),
					in.getSurv(), in.getTown());
			resultString += PancreasCancer.Q86_pancreascancer_male_validation(in.getAge(), in.getB_bloodcancer(),
					in.getB_chronicpan(), in.getB_type2(), in.getBmi(), in.getSmoke_cat(), in.getSurv());
			resultString += ProstateCancer.Q86_prostatecancer_male_validation(in.getAge(), in.getB_manicschiz(),
					in.getB_type1(), in.getB_type2(), in.getBmi(), in.getEthrisk(), in.getFh_prostatecancer(),
					in.getSmoke_cat(), in.getSurv(), in.getTown());
			resultString += RenalCancer.Q86_renalcancer_male_validation(in.getAge(), in.getB_colorectal(),
					in.getB_lungcancer(), in.getB_prostatecancer(), in.getB_type2(), in.getBmi(), in.getSmoke_cat(),
					in.getSurv());
		}

		if (resultString.length() > 0)
			return resultString;
		else
			return null;
	}

	private String calculateAllScores(Input in) {
		if (in.getGender() == 0) {
			return calculateAllFemaleScores(in.getAge(), in.getAlcohol_cat4(), in.getB_chronicpan(), in.getB_copd(),
					in.getB_endometrial(), in.getB_type2(), in.getBmi(), in.getC_hb(), in.getFh_breastcancer(),
					in.getFh_gicancer(), in.getFh_ovariancancer(), in.getNew_abdodist(), in.getNew_abdopain(),
					in.getNew_appetiteloss(), in.getNew_breastlump(), in.getNew_breastpain(), in.getNew_breastskin(),
					in.getNew_dysphagia(), in.getNew_gibleed(), in.getNew_haematuria(), in.getNew_haemoptysis(),
					in.getNew_heartburn(), in.getNew_imb(), in.getNew_indigestion(), in.getNew_necklump(),
					in.getNew_nightsweats(), in.getNew_pmb(), in.getNew_postcoital(), in.getNew_rectalbleed(),
					in.getNew_vte(), in.getNew_weightloss(), in.getS1_bowelchange(), in.getS1_bruising(),
					in.getS1_constipation(), in.getS1_cough(), in.getSmoke_cat(), in.getTown());
		} else {
			return calculateAllMaleScores(in.getAge(), in.getAlcohol_cat4(), in.getB_chronicpan(), in.getB_copd(),
					in.getB_type2(), in.getBmi(), in.getC_hb(), in.getFh_gicancer(), in.getFh_prostatecancer(),
					in.getNew_abdodist(), in.getNew_abdopain(), in.getNew_appetiteloss(), in.getNew_dysphagia(),
					in.getNew_gibleed(), in.getNew_haematuria(), in.getNew_haemoptysis(), in.getNew_heartburn(),
					in.getNew_indigestion(), in.getNew_necklump(), in.getNew_nightsweats(), in.getNew_rectalbleed(),
					in.getNew_testespain(), in.getNew_testicularlump(), in.getNew_vte(), in.getNew_weightloss(),
					in.getS1_bowelchange(), in.getS1_constipation(), in.getS1_cough(), in.getS1_impotence(),
					in.getS1_nocturia(), in.getS1_urinaryfreq(), in.getS1_urinaryretention(), in.getSmoke_cat(),
					in.getTown());
		}
	}

	private String calculateAllScores2(int age, int alcohol_cat6, int b_asbestos, int b_asthma, int b_barratts,
			int b_benignbreast, int b_bloodcancer, int b_braincancer, int b_breastcancer, int b_cervicalcancer,
			int b_chronicpan, int b_colitis, int b_colorectal, int b_cop, int b_copd, int b_endometrial, int b_hrt_oest,
			int b_lungcancer, int b_manicschiz, int b_oesgastric, int b_oralcancer, int b_pancreascancer,
			int b_ovariancancer, int b_peptic, int b_polyp, int b_pos, int b_prostatecancer, int b_renalcancer,
			int b_type1, int b_type2, int b_uterinecancer, int ethrisk, int fh_bloodcancer, int fh_breastcancer,
			int fh_gicancer, int fh_lungcancer, int fh_ovariancancer, int fh_prostatecancer, int smoke_cat, int surv,
			double bmi, double town, int gender) {
		if (gender == 0) {
			return calculateAllFemaleScores2(age, alcohol_cat6, b_asthma, b_barratts, b_benignbreast, b_bloodcancer,
					b_braincancer, b_breastcancer, b_cervicalcancer, b_chronicpan, b_colitis, b_colorectal, b_cop,
					b_copd, b_endometrial, b_hrt_oest, b_lungcancer, b_manicschiz, b_oralcancer, b_ovariancancer,
					b_peptic, b_polyp, b_pos, b_renalcancer, b_type1, b_type2, b_uterinecancer, ethrisk, fh_bloodcancer,
					fh_breastcancer, fh_gicancer, fh_lungcancer, fh_ovariancancer, smoke_cat, surv, bmi, town);
		} else {
			return calculateAllMaleScores2(age, alcohol_cat6, b_asbestos, b_asthma, b_barratts, b_bloodcancer,
					b_chronicpan, b_colitis, b_colorectal, b_copd, b_lungcancer, b_manicschiz, b_oesgastric,
					b_oralcancer, b_pancreascancer, b_peptic, b_polyp, b_prostatecancer, b_renalcancer, b_type1,
					b_type2, ethrisk, fh_bloodcancer, fh_gicancer, fh_lungcancer, fh_prostatecancer, smoke_cat, surv,
					bmi, town);
		}
	}

	private String calculateAllFemaleScores(int age, int alcohol_cat4, int b_chronicpan, int b_copd, int b_endometrial,
			int b_type2, double bmi, int c_hb, int fh_breastcancer, int fh_gicancer, int fh_ovariancancer,
			int new_abdodist, int new_abdopain, int new_appetiteloss, int new_breastlump, int new_breastpain,
			int new_breastskin, int new_dysphagia, int new_gibleed, int new_haematuria, int new_haemoptysis,
			int new_heartburn, int new_imb, int new_indigestion, int new_necklump, int new_nightsweats, int new_pmb,
			int new_postcoital, int new_rectalbleed, int new_vte, int new_weightloss, int s1_bowelchange,
			int s1_bruising, int s1_constipation, int s1_cough, int smoke_cat, double town) {
		double sum = 1;
		int i = 1;
		double[] resultsArray = new double[12];

		double blood_cancer_score = BloodCancer.blood_cancer_female_raw(age, bmi, c_hb, new_abdopain, new_haematuria,
				new_necklump, new_nightsweats, new_pmb, new_vte, new_weightloss, s1_bowelchange, s1_bruising);
		resultsArray[i] = Math.exp(blood_cancer_score);
		sum += resultsArray[i++];

		double breast_cancer_score = BreastCancer.breast_cancer_female_raw(age, alcohol_cat4, bmi, fh_breastcancer,
				new_breastlump, new_breastpain, new_breastskin, new_pmb, new_vte, town);
		resultsArray[i] = Math.exp(breast_cancer_score);
		sum += resultsArray[i++];

		double cervical_cancer_score = CervicalCancer.cervical_cancer_female_raw(age, bmi, c_hb, new_abdopain,
				new_haematuria, new_imb, new_pmb, new_postcoital, new_vte, smoke_cat, town);
		resultsArray[i] = Math.exp(cervical_cancer_score);
		sum += resultsArray[i++];

		double colorectal_cancer_score = ColorectalCancer.colorectal_cancer_female_raw(age, alcohol_cat4, bmi, c_hb,
				fh_gicancer, new_abdodist, new_abdopain, new_appetiteloss, new_rectalbleed, new_vte, new_weightloss,
				s1_bowelchange, s1_constipation);
		resultsArray[i] = Math.exp(colorectal_cancer_score);
		sum += resultsArray[i++];

		double gastro_oesophageal_cancer_score = GastroOesophagealCancer.gastro_oesophageal_cancer_female_raw(age, bmi,
				c_hb, new_abdopain, new_appetiteloss, new_dysphagia, new_gibleed, new_heartburn, new_indigestion,
				new_vte, new_weightloss, smoke_cat);
		resultsArray[i] = Math.exp(gastro_oesophageal_cancer_score);
		sum += resultsArray[i++];

		double lung_cancer_score = LungCancer.lung_cancer_female_raw(age, b_copd, bmi, c_hb, new_appetiteloss,
				new_dysphagia, new_haemoptysis, new_indigestion, new_necklump, new_vte, new_weightloss, s1_cough,
				smoke_cat, town);
		resultsArray[i] = Math.exp(lung_cancer_score);
		sum += resultsArray[i++];

		double other_cancer_score = OtherCancer.other_cancer_female_raw(age, alcohol_cat4, b_copd, bmi, c_hb,
				new_abdodist, new_abdopain, new_appetiteloss, new_breastlump, new_dysphagia, new_gibleed,
				new_haematuria, new_indigestion, new_necklump, new_pmb, new_vte, new_weightloss, s1_constipation,
				smoke_cat);
		resultsArray[i] = Math.exp(other_cancer_score);
		sum += resultsArray[i++];

		double ovarian_cancer_score = OvarianCancer.ovarian_cancer_female_raw(age, bmi, c_hb, fh_ovariancancer,
				new_abdodist, new_abdopain, new_appetiteloss, new_haematuria, new_indigestion, new_pmb, new_vte,
				new_weightloss, s1_bowelchange);
		resultsArray[i] = Math.exp(ovarian_cancer_score);
		sum += resultsArray[i++];

		double pancreatic_cancer_score = PancreaticCancer.pancreatic_cancer_female_raw(age, b_chronicpan, b_type2, bmi,
				new_abdopain, new_appetiteloss, new_dysphagia, new_gibleed, new_indigestion, new_vte, new_weightloss,
				s1_bowelchange, smoke_cat);
		resultsArray[i] = Math.exp(pancreatic_cancer_score);
		sum += resultsArray[i++];

		double renal_tract_cancer_score = RenalCancer.renal_tract_cancer_female_raw(age, bmi, c_hb, new_abdopain,
				new_appetiteloss, new_haematuria, new_indigestion, new_pmb, new_weightloss, smoke_cat);
		resultsArray[i] = Math.exp(renal_tract_cancer_score);
		sum += resultsArray[i++];

		double uterine_cancer_score = UterineCancer.uterine_cancer_female_raw(age, b_endometrial, b_type2, bmi,
				new_abdopain, new_haematuria, new_imb, new_pmb, new_vte);
		resultsArray[i] = Math.exp(uterine_cancer_score);
		sum += resultsArray[i++];

		/* normalise each score and express it as a percentage */

		double sum2;
		for (i = 1, sum2 = 0; i < 12; i++) {
			resultsArray[i] *= 100 / sum;
			sum2 += resultsArray[i];
		}

		/* Add the risk of no event to the start of the result array */
		resultsArray[0] = 100 - sum2;

		StringBuffer sb = new StringBuffer();
		sb.append("{ \"No Event\" : ").append(resultsArray[0]);
		sb.append(", \"Blood Cancer\" : ").append(resultsArray[1]);
		sb.append(", \"Breast Cancer\" : ").append(resultsArray[2]);
		sb.append(", \"Cervical Cancer\" : ").append(resultsArray[3]);
		sb.append(", \"Colorectal Cancer\" : ").append(resultsArray[4]);
		sb.append(", \"GastroOesophageal Cancer\" : ").append(resultsArray[5]);
		sb.append(", \"Lung Cancer\" : ").append(resultsArray[6]);
		sb.append(", \"Other Cancer\" : ").append(resultsArray[7]);
		sb.append(", \"Ovarian Cancer\" : ").append(resultsArray[8]);
		sb.append(", \"Pancreatic Cancer\" : ").append(resultsArray[9]);
		sb.append(", \"Renal Cancer\" : ").append(resultsArray[10]);
		sb.append(", \"Uterine Cancer\" : ").append(resultsArray[11]);
		sb.append("}");
		return sb.toString();
	}

	private String calculateAllFemaleScores2(int age, int alcohol_cat6, int b_asthma, int b_barratts,
			int b_benignbreast, int b_bloodcancer, int b_braincancer, int b_breastcancer, int b_cervicalcancer,
			int b_chronicpan, int b_colitis, int b_colorectal, int b_cop, int b_copd, int b_endometrial, int b_hrt_oest,
			int b_lungcancer, int b_manicschiz, int b_oralcancer, int b_ovariancancer, int b_peptic, int b_polyp,
			int b_pos, int b_renalcancer, int b_type1, int b_type2, int b_uterinecancer, int ethrisk,
			int fh_bloodcancer, int fh_breastcancer, int fh_gicancer, int fh_lungcancer, int fh_ovariancancer,
			int smoke_cat, int surv, double bmi, double town) {
		int i = 0;
		double[] resultsArray = new double[10];

		resultsArray[i++] = BloodCancer.Q86_bloodcancer_female_raw(age, b_braincancer, b_ovariancancer, b_type1, bmi,
				fh_bloodcancer, smoke_cat, surv);

		resultsArray[i++] = BreastCancer.Q86_breastcancer_female_raw(age, alcohol_cat6, b_benignbreast, b_bloodcancer,
				b_cop, b_hrt_oest, b_lungcancer, b_manicschiz, b_ovariancancer, bmi, ethrisk, fh_breastcancer, surv,
				town);

		resultsArray[i++] = ColorectalCancer.Q86_colorectal_female_raw(age, alcohol_cat6, b_breastcancer,
				b_cervicalcancer, b_colitis, b_ovariancancer, b_polyp, b_type2, b_uterinecancer, ethrisk, fh_gicancer,
				smoke_cat, surv);

		resultsArray[i++] = GastroOesophagealCancer.Q86_oesgastric_female_raw(age, alcohol_cat6, b_barratts,
				b_bloodcancer, b_breastcancer, b_lungcancer, b_oralcancer, b_peptic, b_type2, bmi, smoke_cat, surv,
				town);

		resultsArray[i++] = LungCancer.Q86_lungcancer_female_raw(age, b_asthma, b_bloodcancer, b_breastcancer,
				b_cervicalcancer, b_copd, b_oralcancer, b_ovariancancer, b_renalcancer, b_uterinecancer, bmi, ethrisk,
				fh_lungcancer, smoke_cat, surv, town);

		resultsArray[i++] = OralCancer.Q86_oralcancer_female_raw(age, alcohol_cat6, b_bloodcancer, b_ovariancancer,
				smoke_cat, surv, town);

		resultsArray[i++] = OvarianCancer.Q86_ovariancancer_female_raw(age, b_breastcancer, b_cervicalcancer, b_cop,
				bmi, fh_ovariancancer, surv);

		resultsArray[i++] = PancreasCancer.Q86_pancreascancer_female_raw(age, b_breastcancer, b_chronicpan,
				b_renalcancer, b_type2, bmi, smoke_cat, surv, town);

		resultsArray[i++] = RenalCancer.Q86_renalcancer_female_raw(age, b_bloodcancer, b_braincancer, b_cervicalcancer,
				b_colorectal, b_ovariancancer, b_type2, b_uterinecancer, bmi, smoke_cat, surv, town);

		resultsArray[i++] = UterineCancer.Q86_uterinecancer_female_raw(age, b_breastcancer, b_colorectal, b_endometrial,
				b_manicschiz, b_pos, b_type2, bmi, smoke_cat, surv);

		i = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("[ ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append("0.0");
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append("]");
		return sb.toString();
	}

	private String calculateAllMaleScores(int age, int alcohol_cat4, int b_chronicpan, int b_copd, int b_type2,
			double bmi, int c_hb, int fh_gicancer, int fh_prostatecancer, int new_abdodist, int new_abdopain,
			int new_appetiteloss, int new_dysphagia, int new_gibleed, int new_haematuria, int new_haemoptysis,
			int new_heartburn, int new_indigestion, int new_necklump, int new_nightsweats, int new_rectalbleed,
			int new_testespain, int new_testicularlump, int new_vte, int new_weightloss, int s1_bowelchange,
			int s1_constipation, int s1_cough, int s1_impotence, int s1_nocturia, int s1_urinaryfreq,
			int s1_urinaryretention, int smoke_cat, double town) {
		double sum = 1;
		int i = 1;
		double[] resultsArray = new double[10];

		double blood_cancer_score = BloodCancer.blood_cancer_male_raw(age, bmi, c_hb, new_abdodist, new_abdopain,
				new_appetiteloss, new_dysphagia, new_haematuria, new_haemoptysis, new_indigestion, new_necklump,
				new_nightsweats, new_testicularlump, new_vte, new_weightloss, town);
		resultsArray[i] = Math.exp(blood_cancer_score);
		sum += resultsArray[i++];

		double colorectal_cancer_score = ColorectalCancer.colorectal_cancer_male_raw(age, alcohol_cat4, bmi, c_hb,
				fh_gicancer, new_abdodist, new_abdopain, new_appetiteloss, new_rectalbleed, new_weightloss,
				s1_bowelchange, s1_constipation);
		resultsArray[i] = Math.exp(colorectal_cancer_score);
		sum += resultsArray[i++];

		double gastro_oesophageal_cancer_score = GastroOesophagealCancer.gastro_oesophageal_cancer_male_raw(age, bmi,
				c_hb, new_abdopain, new_appetiteloss, new_dysphagia, new_gibleed, new_heartburn, new_indigestion,
				new_necklump, new_weightloss, smoke_cat);
		resultsArray[i] = Math.exp(gastro_oesophageal_cancer_score);
		sum += resultsArray[i++];

		double lung_cancer_score = LungCancer.lung_cancer_male_raw(age, b_copd, bmi, c_hb, new_abdopain,
				new_appetiteloss, new_dysphagia, new_haemoptysis, new_indigestion, new_necklump, new_nightsweats,
				new_vte, new_weightloss, s1_cough, smoke_cat, town);
		resultsArray[i] = Math.exp(lung_cancer_score);
		sum += resultsArray[i++];

		double other_cancer_score = OtherCancer.other_cancer_male_raw(age, b_copd, b_type2, bmi, c_hb, new_abdodist,
				new_abdopain, new_appetiteloss, new_dysphagia, new_gibleed, new_haematuria, new_haemoptysis,
				new_indigestion, new_necklump, new_vte, new_weightloss, s1_bowelchange, s1_constipation, smoke_cat);
		resultsArray[i] = Math.exp(other_cancer_score);
		sum += resultsArray[i++];

		double pancreatic_cancer_score = PancreaticCancer.pancreatic_cancer_male_raw(age, b_chronicpan, b_type2, bmi,
				new_abdopain, new_appetiteloss, new_dysphagia, new_gibleed, new_indigestion, new_vte, new_weightloss,
				s1_constipation, smoke_cat, town);
		resultsArray[i] = Math.exp(pancreatic_cancer_score);
		sum += resultsArray[i++];

		double prostate_cancer_score = ProstateCancer.prostate_cancer_male_raw(age, bmi, fh_prostatecancer,
				new_abdopain, new_appetiteloss, new_haematuria, new_rectalbleed, new_testespain, new_testicularlump,
				new_vte, new_weightloss, s1_impotence, s1_nocturia, s1_urinaryfreq, s1_urinaryretention, town);
		resultsArray[i] = Math.exp(prostate_cancer_score);
		sum += resultsArray[i++];

		double renal_tract_cancer_score = RenalCancer.renal_tract_cancer_male_raw(age, bmi, new_abdopain,
				new_haematuria, new_nightsweats, new_weightloss, smoke_cat);
		resultsArray[i] = Math.exp(renal_tract_cancer_score);
		sum += resultsArray[i++];

		double testicular_cancer_score = TesticularCancer.testicular_cancer_male_raw(age, bmi, new_testespain,
				new_testicularlump, new_vte);
		resultsArray[i] = Math.exp(testicular_cancer_score);
		sum += resultsArray[i++];

		/* normalise each score and express it as a percentage */

		double sum2;
		for (i = 1, sum2 = 0; i < 10; i++) {
			resultsArray[i] *= 100 / sum;
			sum2 += resultsArray[i];
		}

		/* Add the risk of no event to the start of the result array */
		resultsArray[0] = 100 - sum2;

		StringBuffer sb = new StringBuffer();
		sb.append("{ \"No Event\" : ").append(resultsArray[0]);
		sb.append(", \"Blood Cancer\" : ").append(resultsArray[1]);
		sb.append(", \"Colorectal Cancer\" : ").append(resultsArray[2]);
		sb.append(", \"GastroOesophageal Cancer\" : ").append(resultsArray[3]);
		sb.append(", \"Lung Cancer\" : ").append(resultsArray[4]);
		sb.append(", \"Other Cancer\" : ").append(resultsArray[5]);
		sb.append(", \"Pancreatic Cancer\" : ").append(resultsArray[6]);
		sb.append(", \"Prostate Cancer\" : ").append(resultsArray[7]);
		sb.append(", \"Renal Cancer\" : ").append(resultsArray[8]);
		sb.append(", \"Testicular Cancer\" : ").append(resultsArray[9]);
		sb.append("}");
		return sb.toString();
	}

	private String calculateAllMaleScores2(int age, int alcohol_cat6, int b_asbestos, int b_asthma, int b_barratts,
			int b_bloodcancer, int b_chronicpan, int b_colitis, int b_colorectal, int b_copd, int b_lungcancer,
			int b_manicschiz, int b_oesgastric, int b_oralcancer, int b_pancreascancer, int b_peptic, int b_polyp,
			int b_prostatecancer, int b_renalcancer, int b_type1, int b_type2, int ethrisk, int fh_bloodcancer,
			int fh_gicancer, int fh_lungcancer, int fh_prostatecancer, int smoke_cat, int surv, double bmi,
			double town) {
		int i = 0;
		double[] resultsArray = new double[8];

		resultsArray[i++] = BloodCancer.Q86_bloodcancer_male_raw(age, b_renalcancer, b_type1, bmi, fh_bloodcancer,
				smoke_cat, surv);

		resultsArray[i++] = ColorectalCancer.Q86_colorectal_male_raw(age, alcohol_cat6, b_bloodcancer, b_colitis,
				b_lungcancer, b_oralcancer, b_polyp, b_type2, bmi, ethrisk, fh_gicancer, smoke_cat, surv, town);

		resultsArray[i++] = GastroOesophagealCancer.Q86_oesgastric_male_raw(age, alcohol_cat6, b_barratts, b_oralcancer,
				b_pancreascancer, b_peptic, b_type2, bmi, smoke_cat, surv, town);

		resultsArray[i++] = LungCancer.Q86_lungcancer_male_raw(age, alcohol_cat6, b_asbestos, b_asthma, b_bloodcancer,
				b_colorectal, b_copd, b_oesgastric, b_oralcancer, b_renalcancer, bmi, ethrisk, fh_lungcancer, smoke_cat,
				surv, town);

		resultsArray[i++] = OralCancer.Q86_oralcancer_male_raw(age, alcohol_cat6, b_bloodcancer, b_colorectal,
				b_lungcancer, bmi, smoke_cat, surv, town);

		resultsArray[i++] = PancreasCancer.Q86_pancreascancer_male_raw(age, b_bloodcancer, b_chronicpan, b_type2, bmi,
				smoke_cat, surv);

		resultsArray[i++] = ProstateCancer.Q86_prostatecancer_male_raw(age, b_manicschiz, b_type1, b_type2, bmi,
				ethrisk, fh_prostatecancer, smoke_cat, surv, town);

		resultsArray[i++] = RenalCancer.Q86_renalcancer_male_raw(age, b_colorectal, b_lungcancer, b_prostatecancer,
				b_type2, bmi, smoke_cat, surv);

		i = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("[ ").append(resultsArray[i++]);
		sb.append(", ").append("0.0");
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append("0.0");
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append(resultsArray[i++]);
		sb.append(", ").append("0.0");
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {

		//String inFemaleJson = "{\"age\" : 47, \"b_cvd\": 0, \"b_treatedhyp\": 0, \"ethrisk\": 8, \"surv\": 10, \"height\": 178, \"weight\": 78, \"gender\" : 0, \"alcohol_cat4\" : 0, \"b_chronicpan\" : 0, \"b_copd\" : 0, \"b_endometrial\" : 0, \"b_type2\" : 0, \"bmi\" : 24.6, \"c_hb\" : 0, \"fh_breastcancer\" : 0, \"fh_gicancer\" : 0, \"fh_ovariancancer\" : 0, \"new_abdodist\" : 0, \"new_abdopain\" : 0, \"new_appetiteloss\" : 0, \"new_breastlump\" : 0, \"new_breastpain\" : 0, \"new_breastskin\" : 0, \"new_dysphagia\" : 0, \"new_gibleed\" : 0, \"new_haematuria\" : 0, \"new_haemoptysis\" : 0, \"new_heartburn\" : 0, \"new_imb\" : 0, \"new_indigestion\" : 0, \"new_necklump\" : 0, \"new_nightsweats\" : 0, \"new_pmb\" : 0, \"new_postcoital\" : 0, \"new_rectalbleed\" : 0, \"new_vte\" : 0, \"new_weightloss\" : 0, \"s1_bowelchange\" : 0, \"s1_bruising\" : 0, \"s1_constipation\" : 0, \"s1_cough\" : 0, \"smoke_cat\" : 4, \"town\" : 0 }";
		//String inMaleJson = "{\"age\" : 47, \"b_cvd\": 0, \"b_treatedhyp\": 0, \"ethrisk\": 8, \"surv\": 10, \"height\": 178, \"weight\": 78, \"gender\" : 1, \"alcohol_cat4\" : 0, \"b_chronicpan\" : 0, \"b_copd\" : 0, \"b_type2\" : 0, \"bmi\" : 0, \"c_hb\" : 0, \"fh_gicancer\" : 0, \"fh_prostatecancer\" : 0, \"new_abdodist\" : 0, \"new_abdopain\" : 0, \"new_appetiteloss\" : 0, \"new_dysphagia\" : 0, \"new_gibleed\" : 0, \"new_haematuria\" : 0, \"new_haemoptysis\" : 0, \"new_heartburn\" : 0, \"new_indigestion\" : 0, \"new_necklump\" : 0, \"new_nightsweats\" : 0, \"new_rectalbleed\" : 0, \"new_testespain\" : 0, \"new_testicularlump\" : 0, \"new_vte\" : 0, \"new_weightloss\" : 0, \"s1_bowelchange\" : 0, \"s1_constipation\" : 0, \"s1_cough\" : 0, \"s1_impotence\" : 0, \"s1_nocturia\" : 0, \"s1_urinaryfreq\" : 0, \"s1_urinaryretention\" : 0, \"smoke_cat\" : 4, \"town\" : 0}";
		
		String inMaleJson = "{" + 
				"	\"age\":47," + 
				"	\"alcohol_cat6\":0," + 
				"	\"b_asbestos\":0," + 
				"	\"b_asthma\":0," + 
				"	\"b_barratts\":0," + 
				"	\"b_benignbreast\":0," + 
				"	\"b_bloodcancer\":0," + 
				"	\"b_braincancer\":0," + 
				"	\"b_breastcancer\":0," + 
				"	\"b_cervicalcancer\":0," + 
				"	\"b_chronicpan\":0," + 
				"	\"b_colitis\":0," + 
				"	\"b_colorectal\":0," + 
				"	\"b_cop\":0," + 
				"	\"b_copd\":0," + 
				"	\"b_endometrial\":0," + 
				"	\"b_hrt_oest\":0," + 
				"	\"b_lungcancer\":0," + 
				"	\"b_manicschiz\":0," + 
				"	\"b_oesgastric\":0," + 
				"	\"b_oralcancer\":0," + 
				"	\"b_pancreascancer\":0," + 
				"	\"b_ovariancancer\":0," + 
				"	\"b_peptic\":0," + 
				"	\"b_polyp\":0," + 
				"	\"b_pos\":0," + 
				"	\"b_prostatecancer\":0," + 
				"	\"b_renalcancer\":0," + 
				"	\"diabetes_cat\":0," + 
				"	\"b_uterinecancer\":0," + 
				"	\"fh_bloodcancer\":0," + 
				"	\"fh_breastcancer\":0," + 
				"	\"fh_gicancer\":0," + 
				"	\"fh_lungcancer\":0," + 
				"	\"fh_ovariancancer\":0," + 
				"	\"fh_prostatecancer\":0," + 
				"	\"smoke_cat\":4," + 
				"	\"height\":178.0," + 
				"	\"weight\":78.0," + 
				"	\"gender\":1" + 
				"}";
		String inFemaleJson = "{" + 
				"	\"age\":47," + 
				"	\"alcohol_cat6\":0," + 
				"	\"b_asbestos\":0," + 
				"	\"b_asthma\":0," + 
				"	\"b_barratts\":0," + 
				"	\"b_benignbreast\":0," + 
				"	\"b_bloodcancer\":0," + 
				"	\"b_braincancer\":0," + 
				"	\"b_breastcancer\":0," + 
				"	\"b_cervicalcancer\":0," + 
				"	\"b_chronicpan\":0," + 
				"	\"b_colitis\":0," + 
				"	\"b_colorectal\":0," + 
				"	\"b_cop\":0," + 
				"	\"b_copd\":0," + 
				"	\"b_endometrial\":0," + 
				"	\"b_hrt_oest\":0," + 
				"	\"b_lungcancer\":0," + 
				"	\"b_manicschiz\":0," + 
				"	\"b_oesgastric\":0," + 
				"	\"b_oralcancer\":0," + 
				"	\"b_pancreascancer\":0," + 
				"	\"b_ovariancancer\":0," + 
				"	\"b_peptic\":0," + 
				"	\"b_polyp\":0," + 
				"	\"b_pos\":0," + 
				"	\"b_prostatecancer\":0," + 
				"	\"b_renalcancer\":0," + 
				"	\"diabetes_cat\":0," + 
				"	\"b_uterinecancer\":0," + 
				"	\"fh_bloodcancer\":0," + 
				"	\"fh_breastcancer\":0," + 
				"	\"fh_gicancer\":0," + 
				"	\"fh_lungcancer\":0," + 
				"	\"fh_ovariancancer\":0," + 
				"	\"fh_prostatecancer\":0," + 
				"	\"smoke_cat\":4," + 
				"	\"height\":178.0," + 
				"	\"weight\":78.0," + 
				"	\"gender\":0" + 
				"}";
		
		PredictCancer pc = new PredictCancer();
		System.out.println("inFemaleJson Json:" + inFemaleJson);
		System.out.println("inMaleJson Json:" + inMaleJson);
		String result = pc.predict(inFemaleJson);
		System.out.println("inFemaleJson result:" + result);

		result = pc.predict(inMaleJson);
		System.out.println("inMaleJson result:" + result);

	}
}