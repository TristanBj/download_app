package download_app;

import java.util.ArrayList;


public class Team {

	private String name;
	private String teamValue;
	private int id;
	private int point;
	private int budget;
	private int supporter_average;
	private TeamStat homeStat;
	private TeamStat awayStat;
	
	public Team() {
		super();
	}

	public Team(String name, int point, int budget, int supporter_average,
			TeamStat homeStat, TeamStat awayStat) {
		super();
		this.name = name;
		this.point = point;
		this.budget = budget;
		this.supporter_average = supporter_average;
		this.homeStat = homeStat;
		this.awayStat = awayStat;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTeamValue() {
		return teamValue;
	}

	public void setTeamValue(String teamValue) {
		this.teamValue = teamValue;
	}
	
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getSupporter_average() {
		return supporter_average;
	}

	public void setSupporter_average(int supporter_average) {
		this.supporter_average = supporter_average;
	}

	public TeamStat getHomeStat() {
		return homeStat;
	}

	public void setHomeStat(TeamStat homeStat) {
		this.homeStat = homeStat;
	}

	public TeamStat getAwayStat() {
		return awayStat;
	}

	public void setAwayStat(TeamStat awayStat) {
		this.awayStat = awayStat;
	}
	
}
