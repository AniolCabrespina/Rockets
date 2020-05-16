package domain;

public class Strategy {

	private static Strategy instance;

	public static Strategy getInstance() throws Exception {
		if (instance == null) {
			instance = new Strategy();
		}
		return instance;
	}
	
	public float move(float currentTime) throws Exception {
		switch((int) currentTime) {
		case 0:
			return 0;
		case 1:
			return 30;
		case 2:
			return 5;
		case 3:
			return 5;
		case 4:
			return 0;
		case 5:
			return 0;
		case 6:
			return 0;
		case 7:
			return 0;
		case 8:
			return 0;
		case 9:
			return 0;
		case 10:
			return 0;
		case 11:
			return 1;
		case 12:
			return 0;
		case 13:
			return 0;
		case 14:
			return 0;
		case 15:
			return 0;
		case 16:
			return 0;
		case 17:
			return 0;
		case 18:
			return 0;
		default:
			throw new Exception("Error: no time left.");
			
		
		}
	}
}
