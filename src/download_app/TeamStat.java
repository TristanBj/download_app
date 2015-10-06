package download_app;

import java.util.ArrayList;


abstract class TeamStat {

	private float point_average;
	private int win;
	private int draw;
	private int loss;
	private int goal_for;
	private int goal_against;
	
	ArrayList<Result> resultList = new ArrayList<Result>();

	public TeamStat(int win, int draw, int loss, int goal_for, int goal_against) {
		super();
		this.win = win;
		this.draw = draw;
		this.loss = loss;
		this.goal_for = goal_for;
		this.goal_against = goal_against;
	}

	public float getPoint_average() {
		return point_average;
	}

	public void setPoint_average(float point_average) {
		this.point_average = point_average;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public int getGoal_for() {
		return goal_for;
	}

	public void setGoal_for(int goal_for) {
		this.goal_for = goal_for;
	}

	public int getGoal_against() {
		return goal_against;
	}

	public void setGoal_against(int goal_against) {
		this.goal_against = goal_against;
	}

	public ArrayList<Result> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<Result> resultList) {
		this.resultList = resultList;
	}
	
	abstract void computeStat();
	
	public void printResultList() {
		
		for(int i=0;i<resultList.size();i++){
			System.out.println(resultList.get(i).getResult());
		}
		
	}
}
