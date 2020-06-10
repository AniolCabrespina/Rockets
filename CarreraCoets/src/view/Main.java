package view;

import java.util.LinkedList;
import java.util.List;

import application.RaceController;
import application.dto.CircuitDTO;
import application.dto.RocketDTO;
import utilities.IObserver;

public class Main {

	private static RaceController controller = RaceController.getInstance();

	public static void main(String[] args) {
		try {

			List<RocketDTO> rocketsListDTO = new LinkedList<RocketDTO>();
			
			CircuitDTO circuitDTO = controller.createCircuit(new IObserver() {
				public void updateCircuit (CircuitDTO circuitDTO) throws Exception {
					printCircuitDTO(circuitDTO);
				}
				public void circuitHasNoWinner() {
					System.out.println("\n There is no winner.");
				}
			});
			
			rocketsListDTO = controller.createRockets();
			controller.addRockets(rocketsListDTO);

		
			
			startRace(circuitDTO);
			controller.updateCircuitRecord();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void startRace(CircuitDTO circuitDTO) throws Exception {
		System.out.println("Starting competition. Circuit: " + circuitDTO.getName() + " Length: "
				+ circuitDTO.getCircuitLength() + " Max time: " + circuitDTO.getMaximumTime());

		controller.startRace();
	}

	public static void printCircuitDTO(CircuitDTO circuitDTO) {
		try {
			System.out.println("\n" + "Current Time: " + circuitDTO.getCurrentTime() + "\n");
			for (RocketDTO rocketDTO : circuitDTO.getRocketsList()) {
				System.out.println("\t Rocket: " + rocketDTO.getName() + " Acceleration: "
						+ rocketDTO.getCurrentAcceleration() + " Speed: " + rocketDTO.getCurrentVelocity()
						+ " Distance: " + rocketDTO.getCurrentMeters() + "/" + circuitDTO.getCircuitLength() + " Fuel: "
						+ rocketDTO.getDepositCurrentFuel() + " / " + rocketDTO.getDepositTotalFuel());
			}
			if (circuitDTO.getHasWinner()) {
				System.out.println("\n" + "And the winner is: " + circuitDTO.getWinner() + " with a time of "
						+ circuitDTO.getCurrentTime() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
