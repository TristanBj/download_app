

import java.util.ArrayList;


public class Championship {



	private String teamsLink;
	private String fixturesLink;
	private String leaguetableLink;
	private ArrayList<Team> teamList = new ArrayList<Team>();
	private ArrayList<MatchDay> matchList = new ArrayList<MatchDay>();
	
	public Championship() {
		
	}
	
	public Championship(ArrayList<Team> teamList, ArrayList<MatchDay> matchList) {
		super();
		this.teamList = teamList;
		this.matchList = matchList;
	}

	public ArrayList<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(ArrayList<Team> teamList) {
		this.teamList = teamList;
	}

	public ArrayList<MatchDay> getMatchList() {
		return matchList;
	}

	public void setMatchList(ArrayList<MatchDay> matchList) {
		this.matchList = matchList;
	}
	
	public String getTeamsLink() {
		return teamsLink;
	}

	public void setTeamsLink(String teamsLink) {
		this.teamsLink = teamsLink;
	}

	public String getFixturesLink() {
		return fixturesLink;
	}

	public void setFixturesLink(String fixturesLink) {
		this.fixturesLink = fixturesLink;
	}

	public String getLeaguetableLink() {
		return leaguetableLink;
	}

	public void setLeaguetableLink(String leaguetableLink) {
		this.leaguetableLink = leaguetableLink;
	}
	
}
