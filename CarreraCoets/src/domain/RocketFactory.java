package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utilities.InvalidParamException;

public class RocketFactory {

	public static List<Rocket> createRockets() throws InvalidParamException {
		List<Rocket> rocketsList = new ArrayList<Rocket>();
		rocketsList.add(createViperX());
		rocketsList.add(createStarV());
		rocketsList.add(createFalconIX());
		rocketsList.add(createSpeedyV());
		return rocketsList;
	}

	public static Rocket createViperX() throws InvalidParamException{
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(40.0f));
		rocketPropellants.add(new Propellant(50.0f));
		rocketPropellants.add(new Propellant(20.0f));
		rocketPropellants.add(new Propellant(38.0f));

		Deposit fuelTank = new Deposit(2500.0f);
		Rocket rocket = new Rocket("ViperX", fuelTank);
		rocket.addRocketPropellants(rocketPropellants);

		return rocket;
	}
	
	public static Rocket createStarV() throws InvalidParamException {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(30.0f));
		rocketPropellants.add(new Propellant(18.0f));
		rocketPropellants.add(new Propellant(24.0f));
		rocketPropellants.add(new Propellant(38.0f));

		Deposit fuelTank = new Deposit(2800.0f);
		Rocket rocket = new Rocket("StarV", fuelTank);
		rocket.addRocketPropellants(rocketPropellants);

		return rocket;
	}
	
	public static Rocket createFalconIX() throws InvalidParamException {
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(40.0f));
		rocketPropellants.add(new Propellant(29.0f));
		rocketPropellants.add(new Propellant(60.0f));

		Deposit fuelTank = new Deposit(1900.0f);
		Rocket rocket = new Rocket("FalconIX", fuelTank);
		rocket.addRocketPropellants(rocketPropellants);

		return rocket;
	}
	//S'HA DE PREGUNTAR DIPÒSIT D'AQUEST COET
	public static Rocket createSpeedyV() throws InvalidParamException { 
		List<Propellant> rocketPropellants = new LinkedList<Propellant>();
		rocketPropellants.add(new Propellant(10.0f));
		rocketPropellants.add(new Propellant(3.0f));
		rocketPropellants.add(new Propellant(20.0f));
		rocketPropellants.add(new Propellant(82.0f));

		Deposit fuelTank = new Deposit(1900.0f);
		Rocket rocket = new Rocket("SpeedyV", fuelTank);
		rocket.addRocketPropellants(rocketPropellants);

		return rocket;
	}
	
}
