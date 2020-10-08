package com.dkitec.qresearch.common;

public class CholRatioPredictor {

	public static double ratio_female_raw(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok) {

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.1468242639073814300000000, 0.4419162446080154300000000,
				0.4924404534180317200000000, 0.1005988790849521700000000, -0.1933892434714560000000000,
				-0.1433938399123105300000000, -0.2269502691409973100000000, 0.0166521130034369930000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 3);
		double age_2 = Math.pow(dage, 3) * Math.log(dage);

		/* Centring the continuous variables */

		age_1 = age_1 - 125.123191833496090;
		age_2 = age_2 - 201.419097900390620;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];

		/* Sum from continuous values */

		a += age_1 * 0.0056076489971194285000000;
		a += age_2 * -0.0027093370105511303000000;

		/* Sum from boolean values */

		a += b_cvd * -0.2952101105567532800000000;
		a += b_treatedhyp * 0.0730408368995546510000000;
		a += b_type2 * -0.0304285044588143950000000;
		a += smok * 0.3249489105808704400000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + 3.5988367538285573000000000;
		return score;
	}

	public static double ratio_male_raw(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok) {

		/* The conditional arrays */

		double Iethrisk[] = { 0, 0, 0.1100374010434674700000000, 0.4093066105061595200000000,
				0.2874959262741853000000000, 0.1308338501645004100000000, -0.3268904406779559200000000,
				-0.2269143207410783100000000, -0.2492322107787566200000000, 0.0321044739270111010000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.log(dage) - 1;
		double age_2 = Math.pow(dage, 3);

		/* Centring the continuous variables */

		age_1 = age_1 - 1.596475720405579;
		age_2 = age_2 - 120.232498168945310;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Iethrisk[ethrisk];

		/* Sum from continuous values */

		a += age_1 * -0.1814016673631636800000000;
		a += age_2 * -0.0014903322520875473000000;

		/* Sum from boolean values */

		a += b_cvd * -0.3255409069351661300000000;
		a += b_treatedhyp * -0.0914247766297792420000000;
		a += b_type2 * -0.1363936032424035700000000;
		a += smok * 0.1129269427748361800000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = a + 4.3604054874307758000000000;
		return score;
	}
	
	public static double predict(int age, int b_cvd, int b_treatedhyp, int b_type2, int ethrisk, int smok, int gender) {
		if(gender == 0)
			return ratio_female_raw(age, b_cvd, b_treatedhyp, b_type2, ethrisk, smok);
		else
			return ratio_male_raw(age, b_cvd, b_treatedhyp, b_type2, ethrisk, smok);
	}
}
