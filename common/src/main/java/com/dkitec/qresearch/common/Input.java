/* 
 * Copyright 2012 ClinRisk Ltd.
 * 
 * This file is part of QDiabetes-2013 (http://qdiabetes.org, http://svn.clinrisk.co.uk/opensource/qdiabetes).
 * 
 * QDiabetes-2013 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QDiabetes-2013 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with QDiabetes-2013.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The initial version of this file, to be found at http://svn.clinrisk.co.uk/opensource/qdiabetes, faithfully implements QDiabetes-2013.
 * We have released this code under the GNU Lesser General Public License to enable others to implement the algorithm faithfully.
 * However, the nature of the GNU Lesser General Public License is such that we cannot prevent, for example, someone altering the coefficients.
 * We stress, therefore, that it is the responsibility of the end user to check that the source that they receive produces the same results as the original code posted at http://svn.clinrisk.co.uk/opensource/qdiabetes.
 * Inaccurate implementations of risk scores can lead to wrong patients being given the wrong treatment.
 * 
 * This file has been auto-generated.
 * XML source: Q68_qdiabetes_2013_1_1.xml
 * STATA dta time stamp: 15 Feb 2013 11:36
 * C file create date: Fri 15 Feb 2013 11:40:42 GMT
 * XML source: Q68_qdiabetes_2013_1_0.xml
 * STATA dta time stamp: 15 Feb 2013 11:36
 * C file create date: Fri 15 Feb 2013 11:40:31 GMT
 */

package com.dkitec.qresearch.common;

/**
 * Input value Object
 * 
 * @author Created by DKI Technology
 *
 */
public class Input {

	/**
	 * Age range in 25 ~ 84
	 */
	private int age;

	/**
	 * on regular steroid tablets ? (No 0, Yes 1)
	 */
	private int b_corticosteroids;

	/**
	 * a heart attack, angina, stroke or TIA ? (No 0, Yes 1)
	 */
	private int b_cvd;

	/**
	 * high blood pressure requiring treatment ? (No 0, Yes 1)
	 */
	private int b_treatedhyp;

	/**
	 * BMI
	 */
	private double bmi;

	/**
	 * ethnicity '1' White or not stated, '2' Indian, '3' Pakistani, '4'
	 * Bangladeshi, '5' Other Asian, '6' Black Caribbean, '7' Black African, '8'
	 * Chinese, '9' Other ethnic group
	 */
	private int ethrisk = 8;

	/**
	 * family history of diabetes (No 0, Yes 1)
	 */
	private int fh_diab;

	/**
	 * smoker category '0' non-smoker '1' ex-smoker '2' light smoker (less than 10)
	 * '3' moderate smoker (10 to 19) '4' heavy smoker (20 or over)
	 */
	private int smoke_cat;

	/**
	 * prediction year (1 ~ 15)
	 */
	private int surv = 10;

	/**
	 * we don't use townsend score, so we will set default score(0)
	 */
	private double town = 0.1d;

	/**
	 * female 0, male 1
	 */
	private int gender;

	/**
	 * Diabetes status : '0' none, '1' type 1, '2' type 2
	 */
	private int diabetes_cat;

	/**
	 * Diabetes type 1 (No 0, Yes 1)
	 */
	private int b_type1;

	/**
	 * Diabetes type 2 (No 0, Yes 1)
	 */
	private int b_type2;

	/**
	 * Angina or heart attack in a 1st degree relative < 60 ? (No 0, Yes 1)
	 */
	private int fh_cvd;

	/**
	 * Chronic kidney disease (stage 4 or 5)? (No 0, Yes 1)
	 */
	private int b_renal;

	/**
	 * Atrial fibrillation ? (No 0, Yes 1)
	 */
	private int b_AF;

	/**
	 * Rheumatoid arthritis ? (No 0, Yes 1)
	 */
	private int b_ra;

	/**
	 * Cholesterol/HDL ratio
	 */
	private double rati;

	/**
	 * Systolic blood pressure (mmHg)
	 */
	private double sbp;

	/**
	 * height
	 */
	private double height;

	/**
	 * weight
	 */
	private double weight;

	/**
	 * Alcohol consumption '0' non-drinker '1' 1 unit per day '2' 1-2 units per day
	 * '3' 3+ units per day
	 */
	private int alcohol_cat4;

	/**
	 * chronic pancreatitis ? (No 0, Yes 1)
	 */
	private int b_chronicpan;

	/**
	 * chronic obstructive airways disease (COPD) ? (No 0, Yes 1)
	 */
	private int b_copd;

	/**
	 * endometrial hyperplasia or polyp ? (No 0, Yes 1)
	 */
	private int b_endometrial;

	/**
	 * anaemia (Haemoglobin < 11g/dL) ? (No 0, Yes 1)
	 */
	private int c_hb;

	/**
	 * a family history of breast cancer? (No 0, Yes 1)
	 */
	private int fh_breastcancer;

	/**
	 * a family history of gastrointestinal cancer? (No 0, Yes 1)
	 */
	private int fh_gicancer;

	/**
	 * a family history of ovarian cancer? (No 0, Yes 1)
	 */
	private int fh_ovariancancer;

	/**
	 * Do you currently have abdominal swelling ? (No 0, Yes 1)
	 */
	private int new_abdodist;

	/**
	 * Do you currently have abdominal pain ? (No 0, Yes 1)
	 */
	private int new_abdopain;

	/**
	 * Do you currently have loss of appetite ? (No 0, Yes 1)
	 */
	private int new_appetiteloss;

	/**
	 * Do you currently have breast lump ? (No 0, Yes 1)
	 */
	private int new_breastlump;

	/**
	 * Do you currently have breast pain ? (No 0, Yes 1)
	 */
	private int new_breastpain;

	/**
	 * Do you currently have breast skin tethering or nipple discharge? (No 0, Yes
	 * 1)
	 */
	private int new_breastskin;

	/**
	 * Do you currently have difficulty swallowing? (No 0, Yes 1)
	 */
	private int new_dysphagia;
	
	/**
	 * Do you currently have blood when you vomit? (No 0, Yes 1)
	 */
	private int new_gibleed;

	/**
	 * Do you currently have blood in your urine? (No 0, Yes 1)
	 */
	private int new_haematuria;

	/**
	 * Do you currently have blood when you cough? (No 0, Yes 1)
	 */
	private int new_haemoptysis;
	
	/**
	 * Do you currently have difficulty heartburn? (No 0, Yes 1)
	 */
	private int new_heartburn;

	/**
	 * Do you currently have irregular menstrual bleeding? (No 0, Yes 1)
	 */
	private int new_imb;
	
	/**
	 * Do you currently have difficulty indigestion? (No 0, Yes 1)
	 */
	private int new_indigestion;
	
	/**
	 * Do you currently have a lump in your neck? (No 0, Yes 1)
	 */
	private int new_necklump;
	
	/**
	 * Do you currently have night sweats? (No 0, Yes 1)
	 */
	private int new_nightsweats;
	
	/**
	 * Do you currently have postmenopausal bleeding? (No 0, Yes 1)
	 */
	private int new_pmb;
	
	/**
	 * Do you currently have vaginal bleeding after sex? (No 0, Yes 1)
	 */
	private int new_postcoital;
	
	/**
	 * Do you currently have rectal bleeding? (No 0, Yes 1)
	 */
	private int new_rectalbleed;
	
	/**
	 * Do you currently have a venous thromboembolism? (No 0, Yes 1)
	 */
	private int new_vte;
	
	/**
	 * Do you currently have unintentional weight loss? (No 0, Yes 1)
	 */
	private int new_weightloss;
	
	/**
	 * In the last year have you seen your GP with change in bowel habit? (No 0, Yes 1)
	 */
	private int s1_bowelchange;
	
	/**
	 * In the last year have you seen your GP with unexplained bruising? (No 0, Yes 1)
	 */
	private int s1_bruising;
	
	/**
	 * In the last year have you seen your GP with constipation? (No 0, Yes 1)
	 */
	private int s1_constipation;
	
	/**
	 * In the last year have you seen your GP with cough? (No 0, Yes 1)
	 */
	private int s1_cough;

	/**
	 * a family history of prostate cancer? (No 0, Yes 1)
	 */
	private int fh_prostatecancer;
	
	/**
	 * Do you currently have testicular pain? (No 0, Yes 1)
	 */
	private int new_testespain;

	/**
	 * Do you currently have a testicular lump? (No 0, Yes 1)
	 */
	private int new_testicularlump;
	
	/**
	 * In the last year have you seen your GP with impotence? (No 0, Yes 1)
	 */
	private int s1_impotence;

	/**
	 * In the last year have you seen your GP with nocturia (passing urine at night)? (No 0, Yes 1)
	 */
	private int s1_nocturia;

	/**
	 * In the last year have you seen your GP with urinary frequency? (No 0, Yes 1)
	 */
	private int s1_urinaryfreq;

	/**
	 * In the last year have you seen your GP with urinary retention? (No 0, Yes 1)
	 */
	private int s1_urinaryretention;
    
	/**
	 * Learning disabilities ? (No 0, Yes 1)
	 */
	private int b_learning;
	
	/**
	 * Manic depression or schizophrenia ? (No 0, Yes 1)
	 */
	private int b_manicschiz;
	
	/**
	 * Are you on statins ? (No 0, Yes 1)
	 */
	private int b_statin;
	
	/**
	 * On atypical antipsychotic medication ? (No 0, Yes 1)
	 */
	private int b_atypicalantipsy;
	
	/**
	 * Do you have polycystic ovaries ? (No 0, Yes 1)
	 */
	private int b_pos;
	
	/**
	 * Do you have gestational diabetes (i.e. diabetes that arose during pregnancy)? (No 0, Yes 1)
	 */
	private int b_gestdiab;
	
	/**
	 * Fasting blood glucose (mmol/l)
	 */
	private double fbs;
	
	/**
	 * HBA1c (mmol/mol)
	 */
	private double hba1c;
	
	/**
	 * Do you have migraines? (No 0, Yes 1)
	 */
	private int b_migraine;
	
	/**
	 * Severe mental illness?  (No 0, Yes 1)
	 * (this includes schizophrenia, bipolar disorder and moderate/severe depression)
	 */
	private int b_semi; 
	
	/**
	 * Systemic lupus erythematosus (SLE)?  (No 0, Yes 1)
	 */
	private int b_sle;
	
	/**
	 * A diagnosis of or treatment for erectile disfunction?
	 */
	private int b_impotence2;
	
	/**
	 * Standard deviation of at least two most recent systolic blood pressure readings (mmHg)
	 */
	private double sbps5;
	
	/**
	 * Have you had brain tumour?
	 */
	private int b_braincancer;
	
	/**
	 * Have you had ovarian cancer?
	 */
	private int b_ovariancancer;
	
	/**
	 * Do you have a family history of a blood cancer ?
	 */
	private int fh_bloodcancer;
	
	/**
	 * alcohol_cat6
	 * 	'0' none
	 *  '1' 1 unit per day
	 *  '2' 1-2 units per day
	 *  '3' 3-6 units per day
	 *  '4' 7-9 units per day
	 *  '5' > 9+ units per day
	 */
	private int alcohol_cat6;
	
	/**
	 * Do you currently have benign breast disease?	
	 */
	private int b_benignbreast;
	
	/**
	 * Have you had blood cancer?
	 */
	private int b_bloodcancer;
	
	/**
	 * Do you currently have chronic obstructive pulmonary disease?
	 */
	private int b_cop;
	
	/**
	 * Do you currently have taking oestrogen containing HRT?
	 */
	private int b_hrt_oest;
	
	/**
	 * Have you had blood lung cancer?
	 */
	private int b_lungcancer;
	
	/**
	 * Have you had breast cancer?
	 */
	private int b_breastcancer;
	
	/**
	 * Have you had cervical cancer?
	 */
	private int b_cervicalcancer;
	
	/**
	 * Do you currently have ulcerative colitis?
	 */
	private int b_colitis;
	
	/**
	 * Do you currently have colonic polyps?
	 */
	private int b_polyp;
	
	/**
	 * Have you had uterine cancer?
	 */
	private int b_uterinecancer;
	
	/**
	 * Do you currently have Barratts oesophagus?
	 */
	private int b_barratts;
	
	/**
	 * Have you had oral cancer?
	 */
	private int b_oralcancer;
	
	/**
	 * Do you currently have peptic ulcer?
	 */
	private int b_peptic;
	
	/**
	 * Do you currently have asthma?
	 */
	private int b_asthma;
	
	/**
	 * Have you had bladder or kidney cancer?
	 */
	private int b_renalcancer;
	
	/**
	 * Do you have a family history of a lung cancer?
	 */
	private int fh_lungcancer;
	
	/**
	 * Have you had colorectal cancer?
	 */
	private int b_colorectal;
	
	/**
	 * Have you had pancreatic cancer?
	 */
	private int b_pancreascancer;
	
	/**
	 * Do you currently have exposure to asbestos?
	 */
	private int b_asbestos;
	
	/**
	 * Have you had gastro-oesophageal cancer?
	 */
	private int b_oesgastric;
	
	/**
	 * Have you had prostate cancer?
	 */
	private int b_prostatecancer;
	
	
	public int getB_prostatecancer() {
		return b_prostatecancer;
	}

	public void setB_prostatecancer(int b_prostatecancer) {
		this.b_prostatecancer = b_prostatecancer;
	}

	public int getB_pancreascancer() {
		return b_pancreascancer;
	}

	public void setB_pancreascancer(int b_pancreascancer) {
		this.b_pancreascancer = b_pancreascancer;
	}

	public int getB_asbestos() {
		return b_asbestos;
	}

	public void setB_asbestos(int b_asbestos) {
		this.b_asbestos = b_asbestos;
	}

	public int getB_oesgastric() {
		return b_oesgastric;
	}

	public void setB_oesgastric(int b_oesgastric) {
		this.b_oesgastric = b_oesgastric;
	}

	public int getB_colorectal() {
		return b_colorectal;
	}

	public void setB_colorectal(int b_colorectal) {
		this.b_colorectal = b_colorectal;
	}

	public int getB_asthma() {
		return b_asthma;
	}

	public void setB_asthma(int b_asthma) {
		this.b_asthma = b_asthma;
	}

	public int getB_renalcancer() {
		return b_renalcancer;
	}

	public void setB_renalcancer(int b_renalcancer) {
		this.b_renalcancer = b_renalcancer;
	}

	public int getFh_lungcancer() {
		return fh_lungcancer;
	}

	public void setFh_lungcancer(int fh_lungcancer) {
		this.fh_lungcancer = fh_lungcancer;
	}

	public int getB_barratts() {
		return b_barratts;
	}

	public void setB_barratts(int b_barratts) {
		this.b_barratts = b_barratts;
	}

	public int getB_oralcancer() {
		return b_oralcancer;
	}

	public void setB_oralcancer(int b_oralcancer) {
		this.b_oralcancer = b_oralcancer;
	}

	public int getB_peptic() {
		return b_peptic;
	}

	public void setB_peptic(int b_peptic) {
		this.b_peptic = b_peptic;
	}

	public int getB_breastcancer() {
		return b_breastcancer;
	}

	public void setB_breastcancer(int b_breastcancer) {
		this.b_breastcancer = b_breastcancer;
	}

	public int getB_cervicalcancer() {
		return b_cervicalcancer;
	}

	public void setB_cervicalcancer(int b_cervicalcancer) {
		this.b_cervicalcancer = b_cervicalcancer;
	}

	public int getB_colitis() {
		return b_colitis;
	}

	public void setB_colitis(int b_colitis) {
		this.b_colitis = b_colitis;
	}

	public int getB_polyp() {
		return b_polyp;
	}

	public void setB_polyp(int b_polyp) {
		this.b_polyp = b_polyp;
	}

	public int getB_uterinecancer() {
		return b_uterinecancer;
	}

	public void setB_uterinecancer(int b_uterinecancer) {
		this.b_uterinecancer = b_uterinecancer;
	}

	public int getAlcohol_cat6() {
		return alcohol_cat6;
	}

	public void setAlcohol_cat6(int alcohol_cat6) {
		this.alcohol_cat6 = alcohol_cat6;
	}

	public int getB_benignbreast() {
		return b_benignbreast;
	}

	public void setB_benignbreast(int b_benignbreast) {
		this.b_benignbreast = b_benignbreast;
	}

	public int getB_bloodcancer() {
		return b_bloodcancer;
	}

	public void setB_bloodcancer(int b_bloodcancer) {
		this.b_bloodcancer = b_bloodcancer;
	}

	public int getB_cop() {
		return b_cop;
	}

	public void setB_cop(int b_cop) {
		this.b_cop = b_cop;
	}

	public int getB_hrt_oest() {
		return b_hrt_oest;
	}

	public void setB_hrt_oest(int b_hrt_oest) {
		this.b_hrt_oest = b_hrt_oest;
	}

	public int getB_lungcancer() {
		return b_lungcancer;
	}

	public void setB_lungcancer(int b_lungcancer) {
		this.b_lungcancer = b_lungcancer;
	}

	public int getB_braincancer() {
		return b_braincancer;
	}

	public void setB_braincancer(int b_braincancer) {
		this.b_braincancer = b_braincancer;
	}

	public int getB_ovariancancer() {
		return b_ovariancancer;
	}

	public void setB_ovariancancer(int b_ovariancancer) {
		this.b_ovariancancer = b_ovariancancer;
	}

	public int getFh_bloodcancer() {
		return fh_bloodcancer;
	}

	public void setFh_bloodcancer(int fh_bloodcancer) {
		this.fh_bloodcancer = fh_bloodcancer;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getB_corticosteroids() {
		return b_corticosteroids;
	}

	public void setB_corticosteroids(int b_corticosteroids) {
		this.b_corticosteroids = b_corticosteroids;
	}

	public int getB_cvd() {
		return b_cvd;
	}

	public void setB_cvd(int b_cvd) {
		this.b_cvd = b_cvd;
	}

	public int getB_treatedhyp() {
		return b_treatedhyp;
	}

	public void setB_treatedhyp(int b_treatedhyp) {
		this.b_treatedhyp = b_treatedhyp;
	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public int getEthrisk() {
		return ethrisk;
	}

	public void setEthrisk(int ethrisk) {
		this.ethrisk = ethrisk;
	}

	public int getFh_diab() {
		return fh_diab;
	}

	public void setFh_diab(int fh_diab) {
		this.fh_diab = fh_diab;
	}

	public int getSmoke_cat() {
		return smoke_cat;
	}

	public void setSmoke_cat(int smoke_cat) {
		this.smoke_cat = smoke_cat;
	}

	public int getSurv() {
		return surv;
	}

	public void setSurv(int surv) {
		this.surv = surv;
	}

	public double getTown() {
		return town;
	}

	public void setTown(double town) {
		this.town = town;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getDiabetes_cat() {
		return diabetes_cat;
	}

	public void setDiabetes_cat(int diabetes_cat) {
		this.diabetes_cat = diabetes_cat;
		switch (this.diabetes_cat) {
		case 1:
			this.b_type1 = 1;
			this.b_type2 = 0;
			break;
		case 2:
			this.b_type1 = 0;
			this.b_type2 = 1;
			break;
		default:
			this.b_type1 = 0;
			this.b_type2 = 0;
		}
	}

	public int getB_type1() {
		return b_type1;
	}

	public void setB_type1(int b_type1) {
		this.b_type1 = b_type1;
	}

	public int getB_type2() {
		return b_type2;
	}

	public void setB_type2(int b_type2) {
		this.b_type2 = b_type2;
	}

	public int getFh_cvd() {
		return fh_cvd;
	}

	public void setFh_cvd(int fh_cvd) {
		this.fh_cvd = fh_cvd;
	}

	public int getB_renal() {
		return b_renal;
	}

	public void setB_renal(int b_renal) {
		this.b_renal = b_renal;
	}

	public int getB_AF() {
		return b_AF;
	}

	public void setB_AF(int b_AF) {
		this.b_AF = b_AF;
	}

	public int getB_ra() {
		return b_ra;
	}

	public void setB_ra(int b_ra) {
		this.b_ra = b_ra;
	}

	public double getRati() {
		return rati;
	}

	public void setRati(double rati) {
		this.rati = rati;
	}

	public double getSbp() {
		return sbp;
	}

	public void setSbp(double sbp) {
		this.sbp = sbp;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getAlcohol_cat4() {
		return alcohol_cat4;
	}

	public void setAlcohol_cat4(int alcohol_cat4) {
		this.alcohol_cat4 = alcohol_cat4;
	}

	public int getB_chronicpan() {
		return b_chronicpan;
	}

	public void setB_chronicpan(int b_chronicpan) {
		this.b_chronicpan = b_chronicpan;
	}

	public int getB_copd() {
		return b_copd;
	}

	public void setB_copd(int b_copd) {
		this.b_copd = b_copd;
	}

	public int getB_endometrial() {
		return b_endometrial;
	}

	public void setB_endometrial(int b_endometrial) {
		this.b_endometrial = b_endometrial;
	}

	public int getC_hb() {
		return c_hb;
	}

	public void setC_hb(int c_hb) {
		this.c_hb = c_hb;
	}

	public int getFh_breastcancer() {
		return fh_breastcancer;
	}

	public void setFh_breastcancer(int fh_breastcancer) {
		this.fh_breastcancer = fh_breastcancer;
	}

	public int getFh_gicancer() {
		return fh_gicancer;
	}

	public void setFh_gicancer(int fh_gicancer) {
		this.fh_gicancer = fh_gicancer;
	}

	public int getFh_ovariancancer() {
		return fh_ovariancancer;
	}

	public void setFh_ovariancancer(int fh_ovariancancer) {
		this.fh_ovariancancer = fh_ovariancancer;
	}

	public int getNew_abdodist() {
		return new_abdodist;
	}

	public void setNew_abdodist(int new_abdodist) {
		this.new_abdodist = new_abdodist;
	}

	public int getNew_abdopain() {
		return new_abdopain;
	}

	public void setNew_abdopain(int new_abdopain) {
		this.new_abdopain = new_abdopain;
	}

	public int getNew_appetiteloss() {
		return new_appetiteloss;
	}

	public void setNew_appetiteloss(int new_appetiteloss) {
		this.new_appetiteloss = new_appetiteloss;
	}

	public int getNew_breastlump() {
		return new_breastlump;
	}

	public void setNew_breastlump(int new_breastlump) {
		this.new_breastlump = new_breastlump;
	}

	public int getNew_breastpain() {
		return new_breastpain;
	}

	public void setNew_breastpain(int new_breastpain) {
		this.new_breastpain = new_breastpain;
	}

	public int getNew_breastskin() {
		return new_breastskin;
	}

	public void setNew_breastskin(int new_breastskin) {
		this.new_breastskin = new_breastskin;
	}

	public int getNew_dysphagia() {
		return new_dysphagia;
	}

	public void setNew_dysphagia(int new_dysphagia) {
		this.new_dysphagia = new_dysphagia;
	}

	public int getNew_gibleed() {
		return new_gibleed;
	}

	public void setNew_gibleed(int new_gibleed) {
		this.new_gibleed = new_gibleed;
	}

	public int getNew_haematuria() {
		return new_haematuria;
	}

	public void setNew_haematuria(int new_haematuria) {
		this.new_haematuria = new_haematuria;
	}

	public int getNew_haemoptysis() {
		return new_haemoptysis;
	}

	public void setNew_haemoptysis(int new_haemoptysis) {
		this.new_haemoptysis = new_haemoptysis;
	}

	public int getNew_imb() {
		return new_imb;
	}

	public void setNew_imb(int new_imb) {
		this.new_imb = new_imb;
	}

	public int getNew_necklump() {
		return new_necklump;
	}

	public void setNew_necklump(int new_necklump) {
		this.new_necklump = new_necklump;
	}

	public int getNew_nightsweats() {
		return new_nightsweats;
	}

	public void setNew_nightsweats(int new_nightsweats) {
		this.new_nightsweats = new_nightsweats;
	}

	public int getNew_pmb() {
		return new_pmb;
	}

	public void setNew_pmb(int new_pmb) {
		this.new_pmb = new_pmb;
	}

	public int getNew_postcoital() {
		return new_postcoital;
	}

	public void setNew_postcoital(int new_postcoital) {
		this.new_postcoital = new_postcoital;
	}

	public int getNew_rectalbleed() {
		return new_rectalbleed;
	}

	public void setNew_rectalbleed(int new_rectalbleed) {
		this.new_rectalbleed = new_rectalbleed;
	}

	public int getNew_vte() {
		return new_vte;
	}

	public void setNew_vte(int new_vte) {
		this.new_vte = new_vte;
	}

	public int getNew_weightloss() {
		return new_weightloss;
	}

	public void setNew_weightloss(int new_weightloss) {
		this.new_weightloss = new_weightloss;
	}

	public int getS1_bowelchange() {
		return s1_bowelchange;
	}

	public void setS1_bowelchange(int s1_bowelchange) {
		this.s1_bowelchange = s1_bowelchange;
	}

	public int getS1_bruising() {
		return s1_bruising;
	}

	public void setS1_bruising(int s1_bruising) {
		this.s1_bruising = s1_bruising;
	}

	public int getS1_constipation() {
		return s1_constipation;
	}

	public void setS1_constipation(int s1_constipation) {
		this.s1_constipation = s1_constipation;
	}

	public int getS1_cough() {
		return s1_cough;
	}

	public void setS1_cough(int s1_cough) {
		this.s1_cough = s1_cough;
	}

	public int getNew_heartburn() {
		return new_heartburn;
	}

	public void setNew_heartburn(int new_heartburn) {
		this.new_heartburn = new_heartburn;
	}

	public int getNew_indigestion() {
		return new_indigestion;
	}

	public void setNew_indigestion(int new_indigestion) {
		this.new_indigestion = new_indigestion;
	}

	public int getFh_prostatecancer() {
		return fh_prostatecancer;
	}

	public void setFh_prostatecancer(int fh_prostatecancer) {
		this.fh_prostatecancer = fh_prostatecancer;
	}

	public int getNew_testespain() {
		return new_testespain;
	}

	public void setNew_testespain(int new_testespain) {
		this.new_testespain = new_testespain;
	}

	public int getNew_testicularlump() {
		return new_testicularlump;
	}

	public void setNew_testicularlump(int new_testicularlump) {
		this.new_testicularlump = new_testicularlump;
	}

	public int getS1_impotence() {
		return s1_impotence;
	}

	public void setS1_impotence(int s1_impotence) {
		this.s1_impotence = s1_impotence;
	}

	public int getS1_nocturia() {
		return s1_nocturia;
	}

	public void setS1_nocturia(int s1_nocturia) {
		this.s1_nocturia = s1_nocturia;
	}

	public int getS1_urinaryfreq() {
		return s1_urinaryfreq;
	}

	public void setS1_urinaryfreq(int s1_urinaryfreq) {
		this.s1_urinaryfreq = s1_urinaryfreq;
	}

	public int getS1_urinaryretention() {
		return s1_urinaryretention;
	}

	public void setS1_urinaryretention(int s1_urinaryretention) {
		this.s1_urinaryretention = s1_urinaryretention;
	}

	public int getB_learning() {
		return b_learning;
	}

	public void setB_learning(int b_learning) {
		this.b_learning = b_learning;
	}

	public int getB_manicschiz() {
		return b_manicschiz;
	}

	public void setB_manicschiz(int b_manicschiz) {
		this.b_manicschiz = b_manicschiz;
	}

	public int getB_statin() {
		return b_statin;
	}

	public void setB_statin(int b_statin) {
		this.b_statin = b_statin;
	}

	public int getB_atypicalantipsy() {
		return b_atypicalantipsy;
	}

	public void setB_atypicalantipsy(int b_atypicalantipsy) {
		this.b_atypicalantipsy = b_atypicalantipsy;
	}

	public int getB_pos() {
		return b_pos;
	}

	public void setB_pos(int b_pos) {
		this.b_pos = b_pos;
	}

	public int getB_gestdiab() {
		return b_gestdiab;
	}

	public void setB_gestdiab(int b_gestdiab) {
		this.b_gestdiab = b_gestdiab;
	}

	public double getFbs() {
		return fbs;
	}

	public void setFbs(double fbs) {
		this.fbs = fbs/18.018018;
	}

	public double getHba1c() {
		return hba1c;
	}

	public void setHba1c(double hba1c) {
		this.hba1c = hba1c;
	}

	public int getB_migraine() {
		return b_migraine;
	}

	public void setB_migraine(int b_migraine) {
		this.b_migraine = b_migraine;
	}

	public int getB_semi() {
		return b_semi;
	}

	public void setB_semi(int b_semi) {
		this.b_semi = b_semi;
	}

	public int getB_sle() {
		return b_sle;
	}

	public void setB_sle(int b_sle) {
		this.b_sle = b_sle;
	}

	public double getSbps5() {
		return sbps5;
	}

	public void setSbps5(double sbps5) {
		this.sbps5 = sbps5;
	}

	public int getB_impotence2() {
		return b_impotence2;
	}

	public void setB_impotence2(int b_impotence2) {
		this.b_impotence2 = b_impotence2;
	}

}
