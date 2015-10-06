package download_app;


public class Result {

	private String result;
	private int home_team_score;
	private int away_team_score;
	
	
	public Result() {
		super();
	}

	public Result(String result, int home_team_score, int away_team_score) {
		super();
		this.result = result;
		this.home_team_score = home_team_score;
		this.away_team_score = away_team_score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getHome_team_score() {
		return home_team_score;
	}

	public void setHome_team_score(int home_team_score) {
		this.home_team_score = home_team_score;
	}

	public int getAway_team_score() {
		return away_team_score;
	}

	public void setAway_team_score(int away_team_score) {
		this.away_team_score = away_team_score;
	}
	
	
	
	
}
