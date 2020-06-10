package domain;

import java.util.ArrayList;
import java.util.List;


public class Strategy {

	private static Circuit circuit;
	private static Rocket rocket;
	private static List<Float> strategySolution = new ArrayList<Float>();
	private static List<Float> strategyBest = new ArrayList<Float>();
	private static Solution solution = new Solution();
	private static Solution best = new Solution(30, -1);

	public List<Float> calculateStrategy(Circuit circuit, Rocket rocket) throws Exception {
		Strategy.circuit = circuit;
		Strategy.rocket = rocket;

		System.out.println("arribo");
		BackMillorSolucio(0);
		return strategyBest;
	}
/*
	private void calculateStrategy(Circuit circuit, Rocket rocketStrat) {
		strategy = new ArrayList<Float>();
		Rocket rocket = new Rocket();
		rocket = rocketStrat;
		while (rocket.getDeposit().getCurrentFuel() > 0) {
			strategy.add();
		}
	}*/

	public static void BackMillorSolucio(int k) throws Exception{
			float j = rocket.getMaximumAcceleration();
			float currentFuel = rocket.getDeposit().getCurrentFuel();
			float currentVelocity = rocket.getCurrentVelocity();
			float currentMeters = rocket.getCurrentMeters();
			while (j >= 0 && k <= circuit.getMaximumTime()){ //Recorregut de tot l’arbre
				currentFuel = rocket.getDeposit().getCurrentFuel();
				currentVelocity = rocket.getCurrentVelocity();
				currentMeters = rocket.getCurrentMeters();	
				rocket.updateRocket(j);
				if (acceptable()){ //no viola les restriccions
					strategySolution.add(j);
					if (finalSolution()) {
						solution.setTimeMark(k);
						solution.setMetersRun(rocket.getCurrentMeters());
						if (betterSolution()) {							
							best = solution;
							strategyBest.addAll(strategySolution);
							//else res
						}
					}			
					else if (completable()) {
						currentFuel = rocket.getDeposit().getCurrentFuel();
						currentVelocity = rocket.getCurrentVelocity();
						currentMeters = rocket.getCurrentMeters();						
						BackMillorSolucio(k+1);
					}
					accelerationBack(j);
				} //fi if
				goBackButton(currentFuel, currentVelocity, currentMeters);
				j--;
					//passem al següent germà a la dreta
			} // fi while
		} // fi procediment
	
	public static boolean acceptable() {
		return rocket.getDeposit().getCurrentFuel() > 0;
	}
	
	public static boolean finalSolution() {
		return rocket.getCurrentMeters() >= circuit.getCircuitLength();
	}
	
	public static boolean betterSolution() {
		if(solution.getTimeMark() < best.getTimeMark()) {
			return true;
		}
		else if(solution.getTimeMark() == best.getTimeMark()) {
			if(solution.getMetersRun() > best.getMetersRun()) {
				return true;
			}			
		}
		return false;
	}
	
	public static boolean completable() {
		return rocket.getDeposit().getCurrentFuel() > 0 && rocket.getCurrentMeters() < circuit.getCircuitLength();
	}
	
	public static void accelerationBack(float j) {
		strategySolution.remove(strategySolution.lastIndexOf(j));
	}
	
	public static void goBackButton(float currentFuel, float currentVelocity, float currentMeters) {
		rocket.getDeposit().setCurrentFuel(currentFuel);
		rocket.setCurrentVelocity(currentVelocity);
		rocket.setCurrentMeters(currentMeters);
	}
	
}
