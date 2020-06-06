package application.dto;

import java.util.LinkedList;
import java.util.List;

import domain.Propellant;
import domain.Rocket;
import utilities.InvalidParamException;

public class RocketDTO {

	private String name;
	private float currentVelocity;
	private float currentMeters;
	private float depositTotalFuel;
	private List<Float> propellantsMaximumAcceleration;

	public RocketDTO() {

	}

	public RocketDTO(String name, float currentVelocity, float currentMeters, float depositTotalFuel,
			List<Float> ropellantsMaximumAcceleration) throws InvalidParamException {
		this.name = name;
		this.currentVelocity = currentVelocity;
		this.currentMeters = currentMeters;
		this.depositTotalFuel = depositTotalFuel;
		this.propellantsMaximumAcceleration.addAll(ropellantsMaximumAcceleration);
	}

	public RocketDTO(Rocket rocket) throws InvalidParamException {
		if (rocket == null) {
			throw new InvalidParamException();
		}
		this.name = rocket.getName();
		this.currentVelocity = 0.0f;
		this.currentMeters = 0.0f;
		this.depositTotalFuel = rocket.getDeposit().getTotalFuel();
		this.propellantsMaximumAcceleration = new LinkedList<Float>();
		for (Propellant propellant : rocket.getPropellants()) {
			this.propellantsMaximumAcceleration.add(propellant.getMaximumAcceleration());
		}
	}

	public String getName() throws InvalidParamException {
		if (name == null || name.equals("")) {
			throw new InvalidParamException();
		}
		return name;
	}

	public float getCurrentVelocity() throws InvalidParamException {
		if (currentVelocity < 0.0f) {
			throw new InvalidParamException();
		}
		return currentVelocity;
	}

	public float getCurrentMeters() throws InvalidParamException {
		if (currentMeters < 0.0f) {
			throw new InvalidParamException();
		}
		return currentMeters;
	}

	public float getDepositTotalFuel() throws InvalidParamException {
		if (depositTotalFuel < 0.0f) {
			throw new InvalidParamException();
		}
		return depositTotalFuel;
	}

	public List<Float> getPropellantsMaximumAcceleration() throws InvalidParamException {
		if (propellantsMaximumAcceleration.contains(0.0f) || propellantsMaximumAcceleration == null) {
			throw new InvalidParamException();
		}
		return propellantsMaximumAcceleration;
	}

}
