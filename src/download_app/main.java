


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;



public class main {

	private static Championship Ligue1 = new Championship();
	private static String DB_Path = "/home/tristan/Documents/Project/DB/";
	
	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub

		JSONReader JSON = new JSONReader();
		
		try {
			
			saveUrlWithHeader( DB_Path+ "teams_ligue1_id.json", "http://api.football-data.org/soccerseasons/396/teams");
			saveUrlWithHeader( DB_Path+ "teams_ligue1.json", "http://www.football-data.org/alpha/soccerseasons/396/teams");
			saveUrlWithHeader( DB_Path+ "alpha_ligue1_definition.json", "http://api.football-data.org/alpha/soccerseasons/396");
			saveUrlWithHeader( DB_Path+ "alpha_ligue1_fixtures.json", "http://api.football-data.org/alpha/soccerseasons/396/fixtures");
			
			JSON.readLeagueDefinition(Ligue1, "", DB_Path);
			JSON.getTeamProperties("", Ligue1, DB_Path);
			
			
			for(int i=0;i<Ligue1.getTeamList().size();i++){
				//System.out.println(Ligue1.getTeamList().get(i).getName());
				saveUrlWithHeader(DB_Path + "fixtures_ligue1_"+ Ligue1.getTeamList().get(i).getId() +".json", "http://api.football-data.org/alpha/teams/"+ Ligue1.getTeamList().get(i).getId() +"/fixtures");
				
			}
			JSON.getTeamFixture(Ligue1, DB_Path);
			System.out.println(Ligue1.getLeaguetableLink());
			saveUrlWithHeader(DB_Path + "leagueTable_ligue1.json", Ligue1.getLeaguetableLink());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void saveUrl(final String filename, final String urlString)
	        throws MalformedURLException, IOException {
		String dataPath = urlString;
		 
		
	    InputStream in = null;
	    FileOutputStream fout = null;
	    try {
	        in = (InputStream) new URL(dataPath).openStream();
	        
	        fout = new FileOutputStream(filename);

	        final byte data[] = new byte[1024];
	        int count;
	        while ((count = in.read(data, 0, 1024)) != -1) {
	            fout.write(data, 0, count);
	        }
	    } finally {
	        if (in != null) {
	            in.close();
	        }
	        if (fout != null) {
	            fout.close();
	        }
	    }
	}
	
	
	public static void saveUrlWithHeader(final String filename, final String urlString)
	        throws MalformedURLException, IOException {
		
		String url = urlString;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("X-Auth-Token", "0fcb8ee67be945cdb41c399113f4b95b");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		InputStream in = con.getInputStream();
		
		

		FileOutputStream fout = null;
		fout = new FileOutputStream(filename);
		
		try {
	  
	        final byte data[] = new byte[1024]; 
	        int count;
	        while ((count = in.read(data, 0, 1024)) != -1) {
	            fout.write(data, 0, count);
	        }
	    } finally {
	        if (in != null) {
	            in.close();
	        }
	        if (fout != null) {
	            fout.close();
	        }
	    }
		
		
		//exemple test
		
		//print result
		
	}

}