package domain;

import java.util.ArrayList;
import java.util.List;

public class Strategy {

	private static Circuit circuit;
	private static Rocket rocket;
	private static int trialsCounter;
	private static int trialsImproveCounter;
	private static int improvesCounter;
	private static List<Float> strategySolution = new ArrayList<Float>();
	private static List<Float> strategyBest = new ArrayList<Float>();
	private static Solution solution;
	private static Solution best;

	public Strategy() {
		trialsCounter = 1000000000;
		trialsImproveCounter = 100;
		improvesCounter = 2;
		strategySolution.clear();
		strategyBest.clear();
		solution = new Solution();
		best = new Solution(30, -1);
	}

	public List<Float> calculateStrategy(Circuit circuit, Rocket rocket) throws Exception {
		Strategy.circuit = circuit;
		Strategy.rocket = rocket;
		backBestSolution(0);
		if (trialsCounter <= 0 && best.getMetersRun() == -1) {
			if(strategySolution == null || strategySolution.contains(null) || strategySolution.size() == 0) {
				
				for(int i = 0; i <= circuit.getMaximumTime(); i++) {
					strategySolution.add(rocket.getMaximumAcceleration());
				}
			}
			return strategySolution;
		}
		return strategyBest;
	}
	/*
	 * private void calculateStrategy(Circuit circuit, Rocket rocketStrat) {
	 * strategy = new ArrayList<Float>(); Rocket rocket = new Rocket(); rocket =
	 * rocketStrat; while (rocket.getDeposit().getCurrentFuel() > 0) {
	 * strategy.add(); } }
	 */

	public static void backBestSolution(int currentTime) throws Exception {
		float acceleration = rocket.getMaximumAcceleration();
		float currentFuel = rocket.getDeposit().getCurrentFuel();
		float currentVelocity = rocket.getCurrentVelocity();
		float currentMeters = rocket.getCurrentMeters();
		while (acceleration >= 0 && currentTime <= circuit.getMaximumTime() && improvesCounter > 0 && trialsImproveCounter > 0
				&& trialsCounter > 0) { // Recorregut de tot l’arbre
			if (improvesCounter == 1) {
				trialsImproveCounter--;
			}
			trialsCounter--;
			currentFuel = rocket.getDeposit().getCurrentFuel();
			currentVelocity = rocket.getCurrentVelocity();
			currentMeters = rocket.getCurrentMeters();
			rocket.updateRocket(acceleration);
			if (acceptable()) { // no viola les restriccions
				strategySolution.add(acceleration);
				if (finalSolution()) {
					solution.setTimeMark(currentTime);
					solution.setMetersRun(rocket.getCurrentMeters());
					if (betterSolution()) {
						improvesCounter--;
						best = solution;
						strategyBest.addAll(strategySolution);
						// else res
					}
				} else if (completable()) {
					backBestSolution(currentTime + 1);
				}
				accelerationBack(acceleration);
				
			} // fi if
			goBackButton(currentFuel, currentVelocity, currentMeters);
			acceleration--;
			// passem al següent germà a la dreta
		} // fi while
	} // fi procediment

	public static boolean acceptable() {
		return rocket.getDeposit().getCurrentFuel() > 0;
	}

	public static boolean finalSolution() {
		return rocket.getCurrentMeters() >= circuit.getCircuitLength();
	}

	public static boolean betterSolution() {
		if (solution.getTimeMark() < best.getTimeMark()) {
			return true;
		} else if (solution.getTimeMark() == best.getTimeMark()) {
			if (solution.getMetersRun() > best.getMetersRun()) {
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
