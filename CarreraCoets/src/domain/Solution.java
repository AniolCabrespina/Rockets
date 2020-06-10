package domain;

public class Solution {
	
	private float timeMark;
	private float metersRun;
	
	public Solution() {
	}
	
	public Solution(float timeMark, float metersRun) {
		this.timeMark = timeMark;
		this.metersRun = metersRun;
	}
	
	public void setTimeMark(float timeMark) {
		this.timeMark = timeMark;
	}
	public void setMetersRun(float metersRun) {
		this.metersRun = metersRun;
	}
	public float getTimeMark() {
		return timeMark;
	}
	public float getMetersRun() {
		return metersRun;
	}
	

}
