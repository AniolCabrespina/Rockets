package utilities;

public interface ISubject {
	
	public void addObserver(IObserver observer) throws InvalidParamException;
	
	public void notifyAllObservers() throws Exception;

}
