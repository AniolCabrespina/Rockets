package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import application.dto.CircuitDTO;
import application.dto.RocketDTO;
import domain.Circuit;
import domain.CircuitFactory;
import domain.Rocket;
import domain.RocketFactory;
import utilities.InvalidParamException;

public class RaceController {

	public List<RocketDTO> createRockets() throws InvalidParamException{
		List<Rocket> rocketsList = new ArrayList<Rocket>();
		rocketsList.addAll(RocketFactory.getInstance().createRockets());
		List<RocketDTO> rocketsListDTO = convertRocketsListToRocketsListDTO(rocketsList);
		return rocketsListDTO;
	}
	
	public CircuitDTO createCircuit() throws InvalidParamException{
		Circuit circuit = CircuitFactory.getInstance().createCircuit();
		return new CircuitDTO(circuit);
	}

	public void updateCircuit(CircuitDTO circuitDTO, List<RocketDTO> rocketsListDTO) throws Exception {
		Circuit circuit = new Circuit(circuitDTO);
		List<Rocket> rocketsList = convertRocketsListDTOToRocketsList(rocketsListDTO);
		Rocket winner;
		while (circuit.timeLeft()) {
			TimeUnit.SECONDS.sleep(1);
			System.out.print(circuit.updateAllRockets(rocketsList));
			winner = circuit.getWinner(rocketsList);
			if (winner instanceof Rocket) {
				System.out.println(winnerMessage(winner, circuit));
				return;
			} else {
				circuit.updateTime();
			}
		}
		System.out.println("There is no winner.");
	}
	
	public static List<Rocket> convertRocketsListDTOToRocketsList (List<RocketDTO> rocketsListDTO) throws InvalidParamException {
		List<Rocket> rocketsList = new ArrayList<Rocket>();
		for(RocketDTO rocketDTO : rocketsListDTO) {
			rocketsList.add(new Rocket(rocketDTO));
		}
		return rocketsList;
	}
	
	public static List<RocketDTO> convertRocketsListToRocketsListDTO (List<Rocket> rocketsList) throws InvalidParamException {
		List<RocketDTO> rocketsListDTO = new ArrayList<RocketDTO>();
		for(Rocket rocket : rocketsList) {
			rocketsListDTO.add(new RocketDTO(rocket));
		}
		return rocketsListDTO;
	}

	public static String winnerMessage(Rocket winner, Circuit circuit) {
		String msg = "";
		msg = "And the winner is: " + winner.getName() + " with a time of " + circuit.getCurrentTime() + "\n"
				+ "Ha ganao pisha! En el segundo: " + circuit.getCurrentTime() + " segundo/s.";
		return msg;
	}	

}
