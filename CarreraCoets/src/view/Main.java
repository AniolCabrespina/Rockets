package view;

import java.util.List;

import application.RaceController;
import application.dto.CircuitDTO;
import application.dto.RocketDTO;
import utilities.InvalidParamException;

public class Main {

	private static RaceController controller = new RaceController();

	public static void main(String[] args) {

		try {
			List<RocketDTO> rocketsList = createRockets();
			CircuitDTO circuit = createCircuit();
			startRace(circuit, rocketsList);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static List<RocketDTO> createRockets() throws InvalidParamException {
		return controller.createRockets();
	}

	public static CircuitDTO createCircuit() throws InvalidParamException{
		return controller.createCircuit();
	}

	public static void startRace(CircuitDTO circuitDTO, List<RocketDTO> rocketsListDTO) throws Exception{
		System.out.println("Starting competition. Circuit length: " + circuitDTO.getCircuitLength() + " Max time: "
				+ circuitDTO.getMaximumTime());
		controller.updateCircuit(circuitDTO, rocketsListDTO);
	}
}
