package application.dto;

import java.util.List;

import domain.Circuit;
import domain.Rocket;
import utilities.InvalidParamException;

public class CircuitDTO {
	
	private String name;
	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private List<Rocket> rocketsList;
	
	public CircuitDTO() {
		
	}
	
	public CircuitDTO (String name, float circuitLength, float maximumTime, List<Rocket> rocketsList) throws InvalidParamException{
		this.name = name;
		this.circuitLength = circuitLength;
		this.maximumTime = maximumTime;
		this.currentTime = 0;
		this.rocketsList.addAll(rocketsList);
	}
	
	
	public CircuitDTO (Circuit circuit) throws InvalidParamException {
		if (circuit == null) {
			throw new InvalidParamException();
		}
		this.name = circuit.getName();
		this.circuitLength = circuit.getCircuitLength();
		this.maximumTime = circuit.getMaximumTime();
		this.currentTime = circuit.getCurrentTime();
		this.rocketsList.addAll(circuit.getRocketsList());
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
	public List<Rocket> getRocketsList() throws InvalidParamException {
		if(rocketsList == null || rocketsList.size() == 0 || rocketsList.contains(null)) {
			throw new InvalidParamException();
		}
		return rocketsList;
	}

	public String getName() throws InvalidParamException {
		if (name == null || name.equals("")) {
			throw new InvalidParamException();
		}
		return name;
	}

}
