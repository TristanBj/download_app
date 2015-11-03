


import java.awt.peer.LightweightPeer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.Statement;
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
	      System.out.println("---------- END OF READ JSON FILE ----------");
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
		      champ.setFixturesLink(fixtureLink.getString("href"));
		      System.out.println(fixtureLink.getString("href"));
		      JSONObject leaguetableLink = jsonO.getJSONObject("leagueTable");
		      champ.setLeaguetableLink(leaguetableLink.getString("href"));
		      System.out.println(leaguetableLink.getString("href"));

		      
		      
		    } finally {
		    	System.out.println("---------- END OF LEAGUE DEFINITION ----------");
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
		      System.out.println("---------- END OF GET TEAM  ----------");
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
		    	System.out.println("---------- END OF TEAM PROPERTIES ----------");
		    }
		  }
	  
	  public void getTeamProperties(String url, Championship champ, String DB_PATH, Statement stmt) throws IOException, JSONException, SQLException {
		    
		    
		    try {
		    	
		    	

		      ArrayList<Team> teamList = new ArrayList<Team>();
		      
		      
		      // Team Config file 	
		      //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      BufferedReader rd2 = new BufferedReader(new FileReader(DB_PATH + "teams_ligue1.json"));
		      String jsonText2 = readAll(rd2);
		      //JSONObject json = new JSONObject(jsonText);
		      JSONObject json2 = new JSONObject(jsonText2);
		      JSONArray jsonArr2 = json2.getJSONArray("teams");
		      
		      

	
		    	    
		    	    
		    	    //team.setId(rec.getInt("id"));
		    	    
	    	
		    	    
		    	    	    
		    	    for(int y=0; y < jsonArr2.length(); y++){
		    	    	
		    	    	JSONObject rec2 = jsonArr2.getJSONObject(y);
		    	    	String teamName = rec2.getString("name");
		    	    	
		    	    	JSONObject links_ = rec2.getJSONObject("_links");
		    	    	JSONObject self = links_.getJSONObject("self");
		    	    	String href = self.getString("href");
		    	    	System.out.println("reference : " + href);
		    	    	
		    	    	int teamID = getTeamID(href);
		    	    	
		    	    	
		    	    	Team team = new Team();
			    	    team.setName(teamName);
		    	    	team.setId(teamID);
			    	    
		    	    	if(rec2.getString("squadMarketValue")!=null){
			    	    	String teamValue = rec2.getString("squadMarketValue");	
			    	    	
			    	    			int int_teamValue = getValuefromString(teamValue);
				    	    		System.out.println("Team Found!!");
				    	    		String sql = "INSERT INTO Team (teamID,Name,SquadMarketValue) VALUES ("+teamID+",'"+rec2.getString("name")+"','"+int_teamValue+"');";
								    stmt.executeUpdate(sql);
				    	    		
				    	    	
			    	    	}else{
			    	    		String sql = "INSERT INTO Team (teamID,Name) VALUES ("+teamID+",'"+rec2.getString("name")+"');";
			 					stmt.executeUpdate(sql);
			    	    	}
		    	    	teamList.add(team);
		    	    }
		    	    
		    	    //team.setId(id);
		    	    //System.out.println(name + " id:" + team.getId()  + " Team value:" + teamValue );
		    	    
		    	   	
		    	    	
		    	    		    	    
		    	
		    	
		      champ.setTeamList(teamList);
		      
		    } finally {
		    	System.out.println("---------- END OF TEAM PROPERTIES ----------");
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
		    	System.out.println("---------- END OF TEAM FIXTURES ----------");
		    }
		    
		   
		  }
		    
	  public void getLeagueFixtures(Championship champ, String DB_PATH, Statement stmt) throws IOException, JSONException, SQLException {
		    
		    //String URL = "http://api.football-data.org/alpha/teams/"+team.getId()+"/fixtures";
		    //InputStream is = new URL(URL).openStream();
		    
		    try {
		    	
		      //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		    	
		    
		    	
		      BufferedReader rd = new BufferedReader(new FileReader(DB_PATH + "alpha_ligue1_fixtures.json"));
		      
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
		    	    JSONObject rec2 = rec.getJSONObject("_links");
		    	    JSONObject rec3 = rec2.getJSONObject("self");
		    	    String fixtureIDLink = rec3.getString("href");
		    	    JSONObject homeTeamNameOBJ = rec2.getJSONObject("homeTeam");
		    	    JSONObject awayTeamNameOBJ = rec2.getJSONObject("awayTeam");
		    	    String homeTeamName = homeTeamNameOBJ.getString("href");
		    	    String awayTeamName = awayTeamNameOBJ.getString("href");
		    	    
		    	    String status = rec.getString("status");
		    	    int matchday = rec.getInt("matchday");
		    	    JSONObject result = rec.getJSONObject("result");
		    	    
		    	    int homeTeamResult = result.getInt("goalsHomeTeam");
		    	    int awayTeamResult = result.getInt("goalsAwayTeam");
		    	    
		    	    String sql = "INSERT INTO Fixtures (fixtureID,homeTeamId,awayTeamId,homeTeamScore,awayTeamScore, matchDay, status) VALUES ("+getTeamID(fixtureIDLink)+",'"+getTeamID(homeTeamName)+"','"+getTeamID(awayTeamName)+"','"+homeTeamResult+"','"+awayTeamResult+"','"+matchday+"','"+status+"');";
				    stmt.executeUpdate(sql);
		    	    System.out.println(homeTeamName + " Vs. "  + awayTeamName + "   " + homeTeamResult + ":" + awayTeamResult);
		    	    
		    	    
		    	}
		    	
		      
		    
		    } finally {
		    	System.out.println("---------- END OF TEAM FIXTURES ----------");
		    }
		    
		   
		  }
	  
	  public Championship doparse(Championship champ, String ChampionshipID, String DB_PATH) throws IOException, JSONException{
		  
		  Championship ch_ret = readJsonFromFile("http://www.football-data.org/soccerseasons/"+ChampionshipID+"/teams", champ, DB_PATH);
		  System.out.println(ch_ret.toString());
		  return ch_ret;
	  }
	  
	  private int getTeamID(String href) {
		  
		  String[] array = href.split("/", -1);
		  System.out.println( "Team ID found :" + array[array.length -1]);
		  
		  try{
		  return Integer.parseInt(array[array.length -1]);
		  }catch(NumberFormatException e){
			  e.printStackTrace();
			  return -1;
		  }
	  }
	  
	  private int getValuefromString(String href) {
		  
		  String[] array = href.split(" ", -1);
		  System.out.println( array[array.length -1]);
		  
		  
		  String[] array2 = array[0].split(",", -1);
		  
		  int squadValue = 0;
		  
		  for(int i=0; i <= array2.length-1;i++ ){
			
			  if(i==array2.length-3){
				  squadValue = squadValue + ( Integer.parseInt(array2[array2.length-1])* 1);
				  System.out.println("i==0");
			  }
			  if(i==array2.length-2){
				  squadValue = squadValue + ( Integer.parseInt(array2[array2.length-2])* 1000);
				  System.out.println("i==0");
			  }
			  if(i==array2.length-1){
				  squadValue = squadValue +(Integer.parseInt(array2[array2.length-3]) * 1000000);
				  System.out.println("i==0");
			  }
			  
		  System.out.println(" array["+ i +"] "+ array2[i]);
		  
		  }
		  
		  System.out.println("Squad market value found: " + squadValue);
		  
		  
		  return squadValue;
	  }
	  

	public void readLeagueDefinition(Championship champ, String string, String DB_PATH, Statement stmt) throws IOException, JSONException{
		// TODO Auto-generated method stub
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
		      champ.setFixturesLink(fixtureLink.getString("href"));
		      System.out.println(fixtureLink.getString("href"));
		      JSONObject leaguetableLink = jsonO.getJSONObject("leagueTable");
		      champ.setLeaguetableLink(leaguetableLink.getString("href"));
		      System.out.println(leaguetableLink.getString("href"));

		      
		      
		      
		    } finally {
		    	System.out.println("---------- END OF LEAGUE DEFINITION ----------");
		    }
	}
}