package com.dkitec.qresearch.common;

public class SbpPredictor {

	public static double sbp_female_raw(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok) {

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, -3.1372986821513531000000000, -2.8096588173045363000000000,
				-5.2977085859660846000000000, -3.2837794310616761000000000, 0.5890406593603405600000000,
				0.3741647368200529100000000, -5.3948087300620449000000000, -1.3608152376825660000000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, -2);
		double age_2 = Math.pow(dage, -1);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.039973739534616;
		age_2 = age_2 - 0.199934333562851;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];

		/* Sum from continuous values */

		a += age_1 * 402.5034788703453600000000000;
		a += age_2 * -291.5453298040637800000000000;

		/* Sum from boolean values */

		a += b_cvd * -3.2990976705649593000000000;
		a += b_treatedhyp * 6.2902315019787061000000000;
		a += b_type2 * -0.1756970743427888100000000;
		a += smok * 0.6380099362933755500000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + 126.5490586921422400000000000;
		return score;
	}

	public static double sbp_male_raw(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok) {

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, -2.6575122017017767000000000, -3.6719455288020395000000000,
				-6.6398799560809314000000000, -2.7054245333350311000000000, -0.7443941957366780900000000,
				0.0791306106362349020000000, -5.7089381165785502000000000, -1.5425653100488361000000000 };

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

		a += age_1 * 0.1927385838236413700000000;
		a += age_2 * -0.0816642766037025540000000;

		/* Sum from boolean values */

		a += b_cvd * -5.0838542121410324000000000;
		a += b_treatedhyp * 5.2560326042378573000000000;
		a += b_type2 * -1.0241509264990079000000000;
		a += smok * 0.3100222616752005300000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + 132.2417609004568000000000000;
		return score;
	}

	public static double predict(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok, int gender) {
		if(gender == 0) {
			return sbp_female_raw(age, b_cvd, b_treatedhyp, b_type2, ethrisk, smok);
		} else {
			return sbp_male_raw(age, b_cvd, b_treatedhyp, b_type2, ethrisk, smok);
		}
	}
}
