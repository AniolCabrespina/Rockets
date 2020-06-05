package application;

import java.util.concurrent.TimeUnit;

import application.dto.CircuitDTO;
import domain.Circuit;
import domain.Rocket;
import persistence.CircuitRepository;

public class RaceController {

	public CircuitDTO createCircuit(CircuitDTO circuitDTO) throws Exception {
		Circuit circuit = new Circuit(circuitDTO);
		return new CircuitDTO(circuit);
	}

	public void updateCircuit(String circuitName, CircuitDTO circuitDTO) throws Exception {
		Circuit circuit = CircuitRepository.getCircuit(circuitName);
		Rocket winner;
		while (circuit.timeLeft()) {
			TimeUnit.SECONDS.sleep(1);
			System.out.print(circuit.updateAllRockets());
			winner = circuit.getWinner();
			if (winner instanceof Rocket) {
				System.out.println(winnerMessage(winner, circuit));
				return;
			} else {
				circuit.updateTime();
			}
		}
		System.out.println("There is no winner.");
	}

	public static String winnerMessage(Rocket winner, Circuit circuit) {
		String msg = "";
		msg = "And the winner is: " + winner.getName() + " with a time of " + circuit.getCurrentTime() + "\n"
				+ "Ha ganao pisha! En el segundo: " + circuit.getCurrentTime() + " segundo/s.";
		return msg;
	}

}
