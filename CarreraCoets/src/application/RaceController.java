package application;

import java.util.ArrayList;
import java.util.List;

import application.dto.CircuitDTO;
import application.dto.RocketDTO;
import domain.Circuit;
import domain.Rocket;
import persistence.RecordRepository;
import utilities.IObserver;
import utilities.InvalidParamException;

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

	public List<RocketDTO> createRockets() throws Exception {
		List<Rocket> rocketsList = new ArrayList<Rocket>();
		rocketsList.addAll(RocketFactory.getInstance().createRockets(circuit));
		List<RocketDTO> rocketsListDTO = convertRocketsListToRocketsListDTO(rocketsList);
		return rocketsListDTO;
	}

	public CircuitDTO createCircuit(IObserver observer) throws InvalidParamException{
		circuit = CircuitFactory.getInstance().createCircuit();
		circuit.addObserver(observer);
		return new CircuitDTO(circuit);
	}
	
	public void updateCircuitRecord() throws Exception {
		float record = RecordRepository.getCircuitRecord(circuit.getName());
		if(record <= 0 || record > circuit.getCurrentTime()) {
			RecordRepository.updateCircuitRecord(circuit);
		}
	}

	public void startRace() throws Exception  {
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

	public void addRockets(List<RocketDTO> rocketsListDTO) throws InvalidParamException {
		List<Rocket> rocketsList = circuit.convertRocketsListDTOToRocketsList(rocketsListDTO);
		circuit.addRockets(rocketsList);
	}

}
