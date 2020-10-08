package com.dkitec.redwood.api.predict;


public interface PredictScript {
	
	public void loadModel(String modelPath);
	public String predict(String jsonStr);
}
