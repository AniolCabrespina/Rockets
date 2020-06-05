package application.dto;

import domain.Circuit;
import utilities.InvalidParamException;

public class CircuitDTO {
	
	private String name;
	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	
	public CircuitDTO() {
		
	}
	
	public CircuitDTO (String name, float circuitLength, float maximumTime) throws InvalidParamException{
		this.name = name;
		this.circuitLength = circuitLength;
		this.maximumTime = maximumTime;
		this.currentTime = 0;
	}
	
	
	public CircuitDTO (Circuit circuit) throws InvalidParamException {
		if (circuit == null) {
			throw new InvalidParamException();
		}
		this.name = circuit.getName();
		this.circuitLength = circuit.getCircuitLength();
		this.maximumTime = circuit.getMaximumTime();
		this.currentTime = circuit.getCurrentTime();
	}
	
	public float getCircuitLength() throws InvalidParamException {
		if(circuitLength <= 0.0f) {
			throw new InvalidParamException();
		}
		return circuitLength;
	}
	public float getMaximumTime() throws InvalidParamException {
		if(maximumTime <= 0.0f) {
			throw new InvalidParamException();
		}
		return maximumTime;
	}
	public float getCurrentTime() throws InvalidParamException {
		if(currentTime < 0.0f) {
			throw new InvalidParamException();
		}
		return currentTime;
	}

	public String getName() throws InvalidParamException {
		if (name == null || name.equals("")) {
			throw new InvalidParamException();
		}
		return name;
	}

}
