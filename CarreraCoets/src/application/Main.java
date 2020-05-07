package application;

import java.util.LinkedList;
import java.util.List;

import domain.Circuit;
import domain.Propellant;
import domain.Deposit;
import domain.Rocket;


public class Main{

	public static void main(String[] args) {
		
		List<Propellant> viperXPropellants = new LinkedList<Propellant>();		
		viperXPropellants.add(new Propellant(50.0f));
		viperXPropellants.add(new Propellant(20.0f));
		viperXPropellants.add(new Propellant(38.0f));
		
		Deposit depositViperX = new Deposit(2500.0f);
		Rocket rocketViperX = new Rocket("ViperX", depositViperX);
		rocketViperX.addRocketPropellants(viperXPropellants);
		
		Circuit freeWorld = new Circuit(1200.0f, 18.0f, rocketViperX);
		
		freeWorld.updateCircuit();		

	}

}
