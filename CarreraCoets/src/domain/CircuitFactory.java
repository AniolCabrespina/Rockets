package domain;

import java.util.concurrent.ThreadLocalRandom;

import utilities.InvalidParamException;

public class CircuitFactory {

	public static Circuit createCircuit() throws InvalidParamException {
		int randomNum = ThreadLocalRandom.current().nextInt(0,4);
		switch(randomNum) {
		case 0:
			return new Circuit("MadMax", 1300.0f, 22.0f);
		case 1:
			return new Circuit("SpeedTrack", 800.0f, 10.0f);
		case 2:
			return new Circuit("FreeWorld", 1200.0f, 18.0f);
		case 3:
			return new Circuit("RisingLap", 900.0f, 15.0f);
		default:
			throw new InvalidParamException();
		}
	}

}
