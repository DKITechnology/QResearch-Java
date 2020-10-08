package com.dkitec.qresearch.common;

public class BmiPredictor {

	public static double bmi_female_raw(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok) {
		/* The conditional arrays */
		double Iethrisk[] = { 0, 0, -0.9703160790216869300000000, 0.8832220125642421500000000,
				-0.6050921977744153600000000, -1.3697197182005882000000000, 1.4640679013442004000000000,
				2.2112056131463573000000000, -3.6493586434306677000000000, 0.3762577392966572300000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */
		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 2);
		double age_2 = Math.pow(dage, 3);

		/* Centring the continuous variables */
		age_1 = age_1 - 25.016422271728516;
		age_2 = age_2 - 125.123191833496090;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];

		/* Sum from continuous values */
		a += age_1 * 0.1713808254697129800000000;
		a += age_2 * -0.0197852889247459970000000;

		/* Sum from boolean values */
		a += b_cvd * 0.4032865359038771400000000;
		a += b_treatedhyp * 2.0018830167096011000000000;
		a += b_type2 * 3.1712226528496901000000000;
		a += smok * -0.4242604288233052800000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + 26.9197720894885530000000000;
		return score;
	}

	public static double bmi_male_raw(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok) {
		/* The conditional arrays */
		double Iethrisk[] = { 0, 0, -2.1042160128013245000000000, -1.4419229744210469000000000,
				-2.8553325672263821000000000, -1.7150155796255639000000000, -0.5947710630002244200000000,
				-0.9690210547452095000000000, -3.2251765711555191000000000, -0.8458696734492524600000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */
		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 3);
		double age_2 = Math.pow(dage, 3) * Math.log(dage);

		/* Centring the continuous variables */
		age_1 = age_1 - 120.232498168945310;
		age_2 = age_2 - 191.948272705078120;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */
		a += Iethrisk[ethrisk];

		/* Sum from continuous values */
		a += age_1 * 0.0110800150275389630000000;
		a += age_2 * -0.0072500972309126341000000;

		/* Sum from boolean values */
		a += b_cvd * 0.3803271155735959400000000;
		a += b_treatedhyp * 1.6470485255197904000000000;
		a += b_type2 * 2.2897921814125404000000000;
		a += smok * -1.0622108176736400000000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + 27.7661149628103270000000000;
		return score;
	}
	
	public static double predict(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok, int gender) {
		
		if(gender == 0) {
			return bmi_female_raw(age, b_cvd, b_treatedhyp, b_type2, ethrisk, smok);
		} else {
			return bmi_male_raw(age, b_cvd, b_treatedhyp, b_type2, ethrisk, smok);
		}
	}

}
