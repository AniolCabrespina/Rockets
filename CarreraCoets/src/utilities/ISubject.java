package utilities;

public interface ISubject {
	
	public void addObserver(IObserver observer) throws InvalidParamException;
	
	public void notifyAllObserversCircuitUpdate() throws Exception;
	
	public void notifyAllObserversCircuitHasNoWinner() throws Exception;

}
