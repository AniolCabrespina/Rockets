package application;

import java.util.LinkedList;
import java.util.List;

import domain.Circuit;
import domain.Deposit;
import domain.Propellant;
import domain.Rocket;
import domain.Strategy;

public class RocketFactory {

	private static RocketFactory instance;

	private RocketFactory() {

	}

	public synchronized static RocketFactory getInstance() {
		if (instance == null) {
			instance = new RocketFactory();
		}
		return instance;
	}

	public List<Rocket> createRockets(Circuit circuit) throws Exception {
		List<Rocket> rocketsList = new LinkedList<Rocket>();
		rocketsList.add(createViperX(circuit));
		rocketsList.add(createStarV(circuit));
		rocketsList.add(createFalconIX(circuit));
		rocketsList.add(createSpeedyV(circuit));
		return rocketsList;
	}

	public Rocket createViperX(Circuit circuit) throws Exception {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(40.0f));
		rocketPropellants.add(new Propellant(50.0f));
		rocketPropellants.add(new Propellant(20.0f));
		rocketPropellants.add(new Propellant(38.0f));

		Deposit fuelTank = new Deposit(2500.0f);
		Rocket rocket = new Rocket("ViperX", fuelTank, rocketPropellants);

		rocket.addStrategy(new Strategy().calculateStrategy(circuit, rocket));
		
		return rocket;
	}

	public Rocket createStarV(Circuit circuit) throws Exception {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(30.0f));
		rocketPropellants.add(new Propellant(18.0f));
		rocketPropellants.add(new Propellant(24.0f));
		rocketPropellants.add(new Propellant(38.0f));

		Deposit fuelTank = new Deposit(2800.0f);
		Rocket rocket = new Rocket("StarV", fuelTank, rocketPropellants);
		
		rocket.addStrategy(new Strategy().calculateStrategy(circuit, rocket));

		return rocket;
	}

	public Rocket createFalconIX(Circuit circuit) throws Exception {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(40.0f));
		rocketPropellants.add(new Propellant(29.0f));
		rocketPropellants.add(new Propellant(60.0f));

		Deposit fuelTank = new Deposit(1900.0f);
		Rocket rocket = new Rocket("FalconIX", fuelTank, rocketPropellants);
		
		rocket.addStrategy(new Strategy().calculateStrategy(circuit, rocket));

		return rocket;
	}

	
	public Rocket createSpeedyV(Circuit circuit) throws Exception {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(10.0f));
		rocketPropellants.add(new Propellant(3.0f));
		rocketPropellants.add(new Propellant(20.0f));
		rocketPropellants.add(new Propellant(82.0f));

		Deposit fuelTank = new Deposit(3200.0f);
		Rocket rocket = new Rocket("SpeedyV", fuelTank, rocketPropellants);
		
		rocket.addStrategy(new Strategy().calculateStrategy(circuit, rocket));

		return rocket;
	}

}
