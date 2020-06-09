package application;

import java.util.ArrayList;
import java.util.List;

import application.dto.CircuitDTO;
import application.dto.RocketDTO;
import domain.Circuit;
import domain.Rocket;
import utilities.InvalidParamException;
import view.Main;

public class RaceController{

	private static Circuit circuit;
	private static RaceController instance;

	public static synchronized RaceController getInstance() {
		if (instance == null) {
			return new RaceController();
		}
		return instance;
	}

	private RaceController() {

	}

	public List<RocketDTO> createRockets() throws InvalidParamException {
		List<Rocket> rocketsList = new ArrayList<Rocket>();
		rocketsList.addAll(RocketFactory.getInstance().createRockets());
		List<RocketDTO> rocketsListDTO = convertRocketsListToRocketsListDTO(rocketsList);
		return rocketsListDTO;
	}

	public CircuitDTO createCircuit(Main main) throws InvalidParamException {
		circuit = CircuitFactory.getInstance().createCircuit();
		circuit.addObserver(main);
		return new CircuitDTO(circuit);
	}

	public void startRace() throws Exception {
		circuit.startRace();
	}

	public static List<RocketDTO> convertRocketsListToRocketsListDTO(List<Rocket> rocketsList)
			throws InvalidParamException {
		List<RocketDTO> rocketsListDTO = new ArrayList<RocketDTO>();
		for (Rocket rocket : rocketsList) {
			rocketsListDTO.add(new RocketDTO(rocket));
		}
		return rocketsListDTO;
	}

	public static List<Rocket> convertRocketsListDTOToRocketsList(List<RocketDTO> rocketsListDTO)
			throws InvalidParamException {
		List<Rocket> rocketsList = new ArrayList<Rocket>();
		for (RocketDTO rocket : rocketsListDTO) {
			rocketsList.add(new Rocket(rocket));
		}
		return rocketsList;
	}

	public void addRockets(List<RocketDTO> rocketsListDTO) throws InvalidParamException {
		circuit.addRockets(convertRocketsListDTOToRocketsList(rocketsListDTO));
	}

}
