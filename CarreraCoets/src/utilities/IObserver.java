package utilities;

import application.dto.CircuitDTO;

public interface IObserver {

	public void updateCircuit(CircuitDTO circuitDTO) throws Exception;

	public void circuitHasNoWinner();
}
