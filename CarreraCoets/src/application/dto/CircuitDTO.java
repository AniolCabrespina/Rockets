package application.dto;

import java.util.LinkedList;
import java.util.List;

import domain.Circuit;
import domain.Rocket;
import utilities.InvalidParamException;

public class CircuitDTO {

	private String name;
	private float circuitLength;
	private float maximumTime;
	private float currentTime;
	private boolean hasWinner;
	private String winner;
	private List<RocketDTO> rocketsList = new LinkedList<RocketDTO>();

	public CircuitDTO() {

	}

	public CircuitDTO(String name, float circuitLength, float maximumTime, String winner, List<RocketDTO> rocketsList) throws InvalidParamException {
		this.name = name;
		this.circuitLength = circuitLength;
		this.maximumTime = maximumTime;
		this.currentTime = 0.0f;
		this.hasWinner = false;
		this.winner = winner;
		this.rocketsList.addAll(rocketsList);
	}	

	public CircuitDTO(Circuit circuit) throws InvalidParamException {
		if (circuit == null) {
			throw new InvalidParamException();
		}
		this.name = circuit.getName();
		this.circuitLength = circuit.getCircuitLength();
		this.maximumTime = circuit.getMaximumTime();
		this.hasWinner = circuit.getHasWinner();
		this.currentTime = circuit.getCurrentTime();
		for(Rocket rocket : circuit.getRocketsList()) {
			rocketsList.add(new RocketDTO(rocket));
		}
		this.winner = circuit.getWinner();
	}

	

	public float getCircuitLength() throws InvalidParamException {
		if (circuitLength <= 0.0f) {
			throw new InvalidParamException();
		}
		return circuitLength;
	}

	public float getMaximumTime() throws InvalidParamException {
		if (maximumTime <= 0.0f) {
			throw new InvalidParamException();
		}
		return maximumTime;
	}

	public String getName() throws InvalidParamException {
		if (name == null || name.equals("")) {
			throw new InvalidParamException();
		}
		return name;
	}
	
	public float getCurrentTime() throws InvalidParamException {
		if (currentTime < 0.0f) {
			throw new InvalidParamException();
		}
		return currentTime;
	}

	public boolean getHasWinner() {
		return hasWinner;
	}
	
	public List<RocketDTO> getRocketsList() throws InvalidParamException {
		if (rocketsList == null || rocketsList.contains(null)) {
			throw new InvalidParamException();
		}
		return rocketsList;
	}

	public String getWinner() throws InvalidParamException {
		return winner;
	}
	
	public String toString() {
		String response = "\n" + "Current Time: " + currentTime + "\n";
		for (RocketDTO rocketDTO : rocketsList) {
				response += rocketDTO.toString() + circuitLength + rocketDTO.toStringV2() + "\n";			
		}
		if (hasWinner) {
			response += "\n" + "And the winner is: " + winner + " with a time of "
					+ currentTime + ".";
		}
		return response;		
	}

}
