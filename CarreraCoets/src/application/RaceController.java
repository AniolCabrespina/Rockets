package application;

import java.util.ArrayList;
import java.util.List;

import application.dto.CircuitDTO;
import application.dto.RocketDTO;
import domain.Circuit;
import domain.CircuitFactory;
import domain.Rocket;
import domain.RocketFactory;
import utilities.InvalidParamException;

public class RaceController {

	private static Circuit circuit;

	public List<RocketDTO> createRockets() throws InvalidParamException {
		List<Rocket> rocketsList = new ArrayList<Rocket>();
		rocketsList.addAll(RocketFactory.getInstance().createRockets());
		List<RocketDTO> rocketsListDTO = convertRocketsListToRocketsListDTO(rocketsList);
		return rocketsListDTO;
	}

	public CircuitDTO createCircuit() throws InvalidParamException {
		circuit = CircuitFactory.getInstance().createCircuit();
		return new CircuitDTO(circuit);
	}

	public String updateCircuit(CircuitDTO circuitDTO, float currentTime) throws Exception {
		hasEnded = false;
		Circuit circuit = new Circuit(circuitDTO);
		Rocket winner;
		String response;
		response = "Current Time: " + currentTime + "\n";
		response += circuit.updateAllRockets(rocketsList, currentTime);
		winner = circuit.getWinner(rocketsList);
		if (winner instanceof Rocket) {
			this.hasEnded = true;
			response += winner.winnerMessage(currentTime);
			return response;
		}
		return response;
	}

	public static List<RocketDTO> convertRocketsListToRocketsListDTO(List<Rocket> rocketsList)
			throws InvalidParamException {
		List<RocketDTO> rocketsListDTO = new ArrayList<RocketDTO>();
		for (Rocket rocket : rocketsList) {
			rocketsListDTO.add(new RocketDTO(rocket));
		}
		return rocketsListDTO;
	}

}
