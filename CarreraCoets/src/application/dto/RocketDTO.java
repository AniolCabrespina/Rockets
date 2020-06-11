package application.dto;

import java.util.ArrayList;
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
	private float depositCurrentFuel;
	private float currentAcceleration;
	private List<Float> propellantsMaximumAcceleration;
	private List<Float> strategy;
	

	public RocketDTO() {

	}

	public RocketDTO(String name, float currentVelocity, float currentMeters, float depositTotalFuel, float depositCurrentFuel,
			List<Float> propellantsMaximumAcceleration, float currrentAcceleration, List<Float> strategy) throws InvalidParamException {
		this.name = name;
		this.currentVelocity = currentVelocity;
		this.currentMeters = currentMeters;
		this.depositTotalFuel = depositTotalFuel;
		this.depositCurrentFuel = depositCurrentFuel;
		this.propellantsMaximumAcceleration.addAll(propellantsMaximumAcceleration);
		this.currentAcceleration = currrentAcceleration;
		this.strategy.addAll(strategy);
	}

	public RocketDTO(Rocket rocket) throws InvalidParamException {
		if (rocket == null) {
			throw new InvalidParamException();
		}
		this.name = rocket.getName();
		this.currentVelocity = rocket.getCurrentVelocity();
		this.currentMeters = rocket.getCurrentMeters();
		this.depositTotalFuel = rocket.getDeposit().getTotalFuel();
		this.depositCurrentFuel = rocket.getDeposit().getCurrentFuel();
		this.propellantsMaximumAcceleration = new LinkedList<Float>();
		for (Propellant propellant : rocket.getPropellants()) {
			this.propellantsMaximumAcceleration.add(propellant.getMaximumAcceleration());
			this.currentAcceleration += propellant.getCurrentAcceleration();
		}
		this.strategy = new ArrayList<Float>();
		this.strategy.addAll(rocket.getStrategy());
		System.out.println("RocketDTO");
		System.out.println(this.strategy);
		
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
		if (propellantsMaximumAcceleration.contains(0.0f) || propellantsMaximumAcceleration == null || propellantsMaximumAcceleration.size() == 0) {
			throw new InvalidParamException();
		}
		return propellantsMaximumAcceleration;
	}

	public float getDepositCurrentFuel() throws InvalidParamException {
		if (depositCurrentFuel < 0.0f || depositCurrentFuel > depositTotalFuel) {
			throw new InvalidParamException();
		}
		return depositCurrentFuel;
	}

	public float getCurrentAcceleration() throws InvalidParamException {
		if (currentAcceleration < 0.0f) {
			throw new InvalidParamException();
		}
		return currentAcceleration;
	}

	public List<Float> getStrategy() throws InvalidParamException {
		if (strategy.contains(0.0f) || strategy == null || strategy.size() == 0) {
			throw new InvalidParamException();
		}
		return strategy;
	}

}
