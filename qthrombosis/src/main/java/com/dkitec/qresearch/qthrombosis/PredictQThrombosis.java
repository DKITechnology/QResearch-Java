/* 
 * Copyright 2014 ClinRisk Ltd.
 * 
 * This file is part of QThrombosis-2014 (http://qthrombosis.org, http://svn.clinrisk.co.uk/opensource/qthrombosis).
 * 
 * QThrombosis-2014 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * QThrombosis-2014 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with QThrombosis-2014.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * The initial version of this file, to be found at http://svn.clinrisk.co.uk/opensource/qthrombosis, faithfully implements QThrombosis-2014.
 * We have released this code under the GNU Affero General Public License to enable others to implement the algorithm faithfully.
 * However, the nature of the GNU Affero General Public License is such that we cannot prevent, for example, someone accidentally 
 * altering the coefficients, getting the inputs wrong, or just poor programming.
 * We stress, therefore, that it is the responsibility of the end user to check that the source that they receive produces the same results as the original code posted at http://svn.clinrisk.co.uk/opensource/qthrombosis.
 * Inaccurate implementations of risk scores can lead to wrong patients being given the wrong treatment.
 * 
 * This file has been auto-generated.
 * XML source: Q88_vte_2014_0.xml
 * STATA dta time stamp: 17 Jun 2014 22:53
 * C file create date: Fri 11 Sep 2015 10:36:27 BST
 */

package com.dkitec.qresearch.qthrombosis;

import com.dkitec.qresearch.common.BmiCalculator;
import com.dkitec.qresearch.common.BmiPredictor;
import com.dkitec.qresearch.common.CV;
import com.dkitec.qresearch.common.Input;
import com.dkitec.qresearch.common.SbpPredictor;
import com.dkitec.redwood.api.predict.PredictScript;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PredictQThrombosis implements PredictScript {

	public void loadModel(String modelPath) {

	}

	public String predict(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper();

		// 입력변수 초기화
		Input in = null;
		try {
			in = mapper.readValue(jsonStr, Input.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (in.getHeight() > 0 && in.getWeight() > 0)
			in.setBmi(BmiCalculator.calculate(in.getHeight(), in.getWeight()));
		else
			in.setBmi(BmiPredictor.predict(in.getAge(), 0, 0, 0, in.getEthrisk(), in.getSmoke_cat(), in.getGender()));

		if (in.getSbp() == 0)
			in.setSbp(SbpPredictor.predict(in.getAge(), in.getB_cvd(), in.getB_treatedhyp(), in.getFh_diab(),
					in.getEthrisk(), in.getSmoke_cat(), in.getGender()));

		String errorMsg = null;
		
		if(in.getSurv() == 10) in.setSurv(5);
		
		if (in.getGender() == 0) {
			errorMsg = vte_female_validation(in.getAge(), in.getB_CCF(), in.getB_admit(), in.getB_antipsychotic(),
					in.getB_anycancer(), in.getB_cop(), in.getB_copd(), in.getB_hrt(), in.getB_ibd(), in.getB_renal(),
					in.getB_tamoxifen(), in.getB_varicosevein(), in.getBmi(), in.getSmoke_cat(), in.getSurv(),
					in.getTown());
		} else {
			errorMsg = vte_male_validation(in.getAge(), in.getB_CCF(), in.getB_admit(), in.getB_antipsychotic(),
					in.getB_anycancer(), in.getB_copd(), in.getB_ibd(), in.getB_renal(), in.getB_varicosevein(),
					in.getBmi(), in.getSmoke_cat(), in.getSurv(), in.getTown());
		}

		if (errorMsg.length() > 0) {
			return errorMsg;
		}

		if (in.getGender() == 0) {
			double result = vte_female_raw(in.getAge(), in.getB_CCF(), in.getB_admit(), in.getB_antipsychotic(),
					in.getB_anycancer(), in.getB_cop(), in.getB_copd(), in.getB_hrt(), in.getB_ibd(), in.getB_renal(),
					in.getB_tamoxifen(), in.getB_varicosevein(), in.getBmi(), in.getSmoke_cat(), in.getSurv(),
					in.getTown());

			return String.valueOf(result);
		} else {
			double result = vte_male_raw(in.getAge(), in.getB_CCF(), in.getB_admit(), in.getB_antipsychotic(),
					in.getB_anycancer(), in.getB_copd(), in.getB_ibd(), in.getB_renal(), in.getB_varicosevein(),
					in.getBmi(), in.getSmoke_cat(), in.getSurv(), in.getTown());

			return String.valueOf(result);
		}
	}

	private String vte_female_validation(int age, int b_CCF, int b_admit, int b_antipsychotic, int b_anycancer,
			int b_cop, int b_copd, int b_hrt, int b_ibd, int b_renal, int b_tamoxifen, int b_varicosevein, double bmi,
			int smoke_cat, int surv, double town) {
		String resultString = "";

		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_CCF)) {
			resultString += "error: b_CCF must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_admit)) {
			resultString += "error: b_admit must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_antipsychotic)) {
			resultString += "error: b_antipsychotic must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_anycancer)) {
			resultString += "error: b_anycancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_cop)) {
			resultString += "error: b_cop must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_copd)) {
			resultString += "error: b_copd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_hrt)) {
			resultString += "error: b_hrt must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ibd)) {
			resultString += "error: b_ibd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renal)) {
			resultString += "error: b_renal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_tamoxifen)) {
			resultString += "error: b_tamoxifen must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_varicosevein)) {
			resultString += "error: b_varicosevein must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 5)) {
			resultString += "error: surv must be in range (1,5)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}

		return resultString;
	}

	private String vte_male_validation(int age, int b_CCF, int b_admit, int b_antipsychotic, int b_anycancer,
			int b_copd, int b_ibd, int b_renal, int b_varicosevein, double bmi, int smoke_cat, int surv, double town) {
		String resultString = "";

		if (!CV.i_in_range(age, 25, 84)) {
			resultString += "error: age must be in range (25,84)\n";
		}
		if (!CV.is_boolean(b_CCF)) {
			resultString += "error: b_CCF must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_admit)) {
			resultString += "error: b_admit must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_antipsychotic)) {
			resultString += "error: b_antipsychotic must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_anycancer)) {
			resultString += "error: b_anycancer must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_copd)) {
			resultString += "error: b_copd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_ibd)) {
			resultString += "error: b_ibd must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_renal)) {
			resultString += "error: b_renal must be in range (0,1)\n";
		}
		if (!CV.is_boolean(b_varicosevein)) {
			resultString += "error: b_varicosevein must be in range (0,1)\n";
		}
		if (!CV.d_in_range(bmi, 20, 40)) {
			resultString += "error: bmi must be in range (20,40)\n";
		}
		if (!CV.i_in_range(smoke_cat, 0, 4)) {
			resultString += "error: smoke_cat must be in range (0,4)\n";
		}
		if (!CV.i_in_range(surv, 1, 5)) {
			resultString += "error: surv must be in range (1,5)\n";
		}
		if (!CV.d_in_range(town, -7, 11)) {
			resultString += "error: town must be in range (-7,11)\n";
		}

		return resultString;
	}

	private double vte_female_raw(int age, int b_CCF, int b_admit, int b_antipsychotic, int b_anycancer, int b_cop,
			int b_copd, int b_hrt, int b_ibd, int b_renal, int b_tamoxifen, int b_varicosevein, double bmi,
			int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999402642250061, 0.998779714107513, 0.998076260089874, 0.997314155101776,
				0.996479928493500 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.0899056072614921040000000, 0.2096026499560841000000000, 0.2698567860827917900000000,
				0.3777926716180949300000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_2 = Math.log(dage);
		double age_1 = Math.pow(dage, -.5);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);

		/* Centring the continuous variables */

		age_1 = age_1 - 0.461668938398361;
		age_2 = age_2 - 1.545814394950867;
		bmi_1 = bmi_1 - 0.146233677864075;
		bmi_2 = bmi_2 - 0.140570744872093;
		town = town - 0.081886291503906;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 44.3830463834610500000000000;
		a += age_2 * 12.4309633619714290000000000;
		a += bmi_1 * 4.2938468556841043000000000;
		a += bmi_2 * -22.6864658094973740000000000;
		a += town * 0.0243256958103135540000000;

		/* Sum from boolean values */

		a += b_CCF * 0.3203585274547171600000000;
		a += b_admit * 0.3648270417062697800000000;
		a += b_antipsychotic * 0.5419744307906361200000000;
		a += b_anycancer * 0.5073551208032194300000000;
		a += b_cop * 0.2651727310274107400000000;
		a += b_copd * 0.3973172060275547700000000;
		a += b_hrt * 0.0728779427830783390000000;
		a += b_ibd * 0.4023036851423945100000000;
		a += b_renal * 0.4367724008370839100000000;
		a += b_tamoxifen * 0.3673289784136273300000000;
		a += b_varicosevein * 0.3907194593022829700000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	private double vte_male_raw(int age, int b_CCF, int b_admit, int b_antipsychotic, int b_anycancer, int b_copd,
			int b_ibd, int b_renal, int b_varicosevein, double bmi, int smoke_cat, int surv, double town) {
		double survivor[] = { 0, 0.999353885650635, 0.998677849769592, 0.997899055480957, 0.997062921524048,
				0.996150791645050 };

		/* The conditional arrays */

		double Ismoke[] = { 0, 0.0732279786093590760000000, 0.2090383346821476100000000, 0.2352668393059219600000000,
				0.2891966714984033300000000 };

		/* Applying the fractional polynomial transforms */
		/* (which includes scaling) */

		double dage = age;
		dage = dage / 10;
		double age_1 = Math.pow(dage, 3);
		double age_2 = Math.pow(dage, 3) * Math.log(dage);
		double dbmi = bmi;
		dbmi = dbmi / 10;
		double bmi_1 = Math.pow(dbmi, -2);
		double bmi_2 = Math.pow(dbmi, -2) * Math.log(dbmi);

		/* Centring the continuous variables */

		age_1 = age_1 - 98.675651550292969;
		age_2 = age_2 - 151.034210205078120;
		bmi_1 = bmi_1 - 0.141316697001457;
		bmi_2 = bmi_2 - 0.138260856270790;
		town = town - 0.217443808913231;

		/* Start of Sum */
		double a = 0;

		/* The conditional sums */

		a += Ismoke[smoke_cat];

		/* Sum from continuous values */

		a += age_1 * 0.0338786420157746950000000;
		a += age_2 * -0.0134355813870903100000000;
		a += bmi_1 * 8.0146563036940002000000000;
		a += bmi_2 * -26.8544544727998090000000000;
		a += town * 0.0118277945308610120000000;

		/* Sum from boolean values */

		a += b_CCF * 0.2945134928144034200000000;
		a += b_admit * 0.3732581842634301000000000;
		a += b_antipsychotic * 0.3992543441751350600000000;
		a += b_anycancer * 0.6337244810913510400000000;
		a += b_copd * 0.3463179824315285600000000;
		a += b_ibd * 0.4879618121644490000000000;
		a += b_renal * 0.4871456661811857100000000;
		a += b_varicosevein * 0.4131507059810912200000000;

		/* Sum from interaction terms */

		/* Calculate the score itself */
		double score = 100.0 * (1 - Math.pow(survivor[surv], Math.exp(a)));
		return score;
	}

	public static void main(String[] args) {
		String input = "{ \"age\": 47, \"b_CCF\": 0, \"b_admit\": 0, \"b_antipsychotic\": 0, \"b_anycancer\": 0, \"b_cop\": 0, \"b_copd\": 0, \"b_hrt\": 0, \"b_ibd\": 0, \"b_renal\": 0, \"b_tamoxifen\": 0, \"b_varicosevein\": 0, \"smoke_cat\": 1, \"gender\": 0, \"height\": 168, \"weight\": 58}";
		String input1 = "{ \"age\": 47, \"b_CCF\": 0, \"b_admit\": 0, \"b_antipsychotic\": 0, \"b_anycancer\": 0, \"b_copd\": 0, \"b_ibd\": 0, \"b_renal\": 0, \"b_varicosevein\": 0, \"smoke_cat\": 1, \"gender\": 1, \"height\": 178, \"weight\": 78}";
		PredictQThrombosis pd = new PredictQThrombosis();
		System.out.println("Json String = " + input);
		System.out.println("result = " + pd.predict(input));
		System.out.println("Json String = " + input1);
		System.out.println("result = " + pd.predict(input1));
	}

}
