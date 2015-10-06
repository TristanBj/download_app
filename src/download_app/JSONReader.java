package download_app;


import java.awt.peer.LightweightPeer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


import org.json.*;

public class JSONReader {

	
	public JSONReader() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
	private JSONObject readJSONFIle(String path){
		
		JSONReader parser = new JSONReader();
		 Object obj = parser;
		
		return null;
		
	}

	  public static Championship readJsonFromFile(String url, Championship champ, String DB_PATH) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      BufferedReader rd = new BufferedReader(new FileReader(DB_PATH + "teams_ligue1.json"));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      JSONArray jsonArray = new JSONArray();
	      jsonArray = json.getJSONArray("teams");
	      JSONArray jsonArrayTeam = new JSONArray();
	       ArrayList<Team> teamList = new ArrayList<Team>();
	      
	      for (int i = 0; i < jsonArray.length(); ++i) {
	    	    JSONObject rec = jsonArray.getJSONObject(i);
	    	    //int id = rec.getInt("id");
	    	    String name = rec.getString("name");
	    	    Team team = new Team();
	    	    team.setName(name);
	    	    //team.setId(id);
	    	    System.out.println(name + " i:" +i);
	    	    teamList.add(i, team);
	    	    
	    	}
	      champ.setTeamList(teamList);
	      return champ;
	    } finally {
	      is.close();
	    }
	  }
	  
	  public void  readLeagueDefinition(Championship champ,  String league, String DB_PATH) throws IOException, JSONException {
		    
		    try {
		      //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      BufferedReader rd = new BufferedReader(new FileReader(DB_PATH + "alpha_ligue1_definition.json"));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      JSONObject jsonO = new JSONObject();
		      jsonO = json.getJSONObject("_links");
		      JSONObject teamLink = jsonO.getJSONObject("teams");
		      champ.setTeamsLink(teamLink.getString("href"));
		      System.out.println(teamLink.getString("href"));
		      JSONObject fixtureLink = jsonO.getJSONObject("fixtures");
		      champ.setTeamsLink(fixtureLink.getString("href"));
		      System.out.println(fixtureLink.getString("href"));
		      JSONObject leaguetableLink = jsonO.getJSONObject("leagueTable");
		      champ.setTeamsLink(leaguetableLink.getString("href"));
		      System.out.println(leaguetableLink.getString("href"));

		      
		      
		    } finally {
		      
		    }
		  }
	  
	  public static Championship getTeambyLeague(String url, Championship champ, String DB_PATH) throws IOException, JSONException {
		    
		    String URL = "http://www.football-data.org/soccerseasons/"+url+"/teams";
		    InputStream is = new URL(URL).openStream();
		    
		    try {
		      //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      BufferedReader rd = new BufferedReader(new FileReader(DB_PATH + "teams_ligue1.json"));
		      String jsonText = readAll(rd);
		      JSONArray json = new JSONArray(jsonText);
		      ArrayList<Team> teamList = new ArrayList<Team>();
		      
		      for (int i = 0; i < json.length(); ++i) {
		    	    JSONObject rec = json.getJSONObject(i);
		    	    int id = rec.getInt("id");
		    	    String name = rec.getString("name");
		    	    //String teamValue = rec.getString("code");
		    	    Team team = new Team();
		    	    team.setName(name);
		    	    team.setId(id);
		    	    System.out.println(name + " id:" + id + " Team value:" );
		    	    teamList.add(i, team);
		    	    
		    	}
		      champ.setTeamList(teamList);
		      return champ;
		    } finally {
		      is.close();
		    }
		  }
	  
	  public void getTeamProperties(String url, Championship champ, String DB_PATH) throws IOException, JSONException {
		    
		    //String URL = "http://www.football-data.org/alpha/soccerseasons/"+url+"/teams";
		    //InputStream is = new URL(URL).openStream();
		    
		    try {
		    	
		      //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      BufferedReader rd = new BufferedReader(new FileReader(DB_PATH + "teams_ligue1_id.json"));
		      String jsonText = readAll(rd);
		      //JSONObject json = new JSONObject(jsonText);
		      JSONObject json2 = new JSONObject();
		      JSONArray jsonArr = new JSONArray(jsonText);
		      ArrayList<Team> teamList = new ArrayList<Team>();
		      
		      //jsonArr = json.getJSONArray("teams");
		      //System.out.println(jsonArr.toString());
		      
		      
		      
		      for (int i = 0; i < jsonArr.length(); ++i) {
		    	   
		    	    JSONObject rec = jsonArr.getJSONObject(i);
		    	    //int id = rec.getInt("id");
		    	    String name = rec.getString("name");
	
		    	    
		    	    Team team = new Team();
		    	    team.setName(name);
		    	    team.setId(rec.getInt("id"));
		    	    
		    	    teamList.add(team);
		    	    	
		    	    
		    	    
		    	    if(!rec.isNull("squadMarketValue")){
		    	    	
		    	    String teamValue = rec.getString("squadMarketValue");	
		    	    for(int ii =0; ii< champ.getTeamList().size(); ii++){
		    	    	
		    	    	if(champ.getTeamList().get(ii).getName().equals(name)){
		    	    		champ.getTeamList().get(ii).setTeamValue(teamValue);
		    	    		System.out.println("Team Found!!");
		    	    	}
		    	    }
		    	    
		    	    //team.setId(id);
		    	    System.out.println(name + " id:" + team.getId()  + " Team value:" + teamValue );
		    	    }else {
		    	    System.out.println(name + " id:" + team.getId() + " Team value not found");	
		    	    }
		    	    
		    	}
		    	
		      champ.setTeamList(teamList);
		      
		    } finally {
		      
		    }
		  }
	  
	  public void getTeamFixture(Championship champ, String DB_PATH) throws IOException, JSONException {
		    
		    //String URL = "http://api.football-data.org/alpha/teams/"+team.getId()+"/fixtures";
		    //InputStream is = new URL(URL).openStream();
		    
		    try {
		    	
		      //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		    	
		    for(int iter=0; iter<= champ.getTeamList().size()-1; iter++){
		    	
		      BufferedReader rd = new BufferedReader(new FileReader(DB_PATH + "fixtures_ligue1_"+ champ.getTeamList().get(iter).getId()+".json"));
		      
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      JSONObject json2 = new JSONObject();
		      JSONArray jsonArr = new JSONArray();
		      ArrayList<Team> teamList = new ArrayList<Team>();
		      
		      jsonArr = json.getJSONArray("fixtures");
		      //System.out.println(jsonArr.toString());
		      
		      
		      for (int i = 0; i < jsonArr.length(); ++i) {
		    	    JSONObject rec = jsonArr.getJSONObject(i);
		    	    //int id = rec.getInt("id");
		    	    String homeTeamName = rec.getString("homeTeamName");
		    	    String awayTeamName = rec.getString("awayTeamName");
		    	    String status = rec.getString("status");
		    	    int matchday = rec.getInt("matchday");
		    	    JSONObject result = rec.getJSONObject("result");
		    	    
		    	    int homeTeamResult = result.getInt("goalsHomeTeam");
		    	    int awayTeamResult = result.getInt("goalsAwayTeam");
		    	    
		    	    
		    	    System.out.println(homeTeamName + " Vs. "  + awayTeamName + "   " + homeTeamResult + ":" + awayTeamResult);
		    	    
		    	    
		    	}
		    	
		      
		    }
		    } finally {
		      
		    }
		    
		   
		  }
		    
		    
	  
	  public Championship doparse(Championship champ, String ChampionshipID, String DB_PATH) throws IOException, JSONException{
		  
		  Championship ch_ret = readJsonFromFile("http://www.football-data.org/soccerseasons/"+ChampionshipID+"/teams", champ, DB_PATH);
		  System.out.println(ch_ret.toString());
		  return ch_ret;
	  }
}