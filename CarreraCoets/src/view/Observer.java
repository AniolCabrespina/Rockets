package view;

import utilities.IObserver;

public class Observer implements IObserver{
	
	public void updateCircuit() throws Exception {
		Main.updateRace();
	}
}
