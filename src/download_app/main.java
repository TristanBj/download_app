


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
import java.sql.*;



public class main {

	private static Championship Ligue1 = new Championship();
	private static String DB_Path = "/home/tristan/Documents/Project/DB/";
	
	// MySQL config and PATH
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String USER = "tristan";
	static final String PASS = "root";
	static Connection conn = null;
	static Statement stmt = null;
	
	
	public static boolean checkDBExists(String dbName){

	    try{
	        Class.forName(JDBC_DRIVER); //Register JDBC Driver

	        System.out.println("Creating a connection...");
	        conn = DriverManager.getConnection(DB_URL, USER, PASS); //Open a connection

	        ResultSet resultSet = conn.getMetaData().getCatalogs();

	        while (resultSet.next()) {

	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(dbName)){
	                return true;
	            }
	        }
	        resultSet.close();

	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }

	    return false;
	}
	
	
	public static void main(String[] args) throws JSONException, SQLException {
		// TODO Auto-generated method stub


		
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      
		      
		      if(!checkDBExists("SOCCER_PRONO")){
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      //STEP 4: Execute a query
		      System.out.println("Creating database...");
		      stmt = conn.createStatement();
		      
		      
		      String sql = "CREATE DATABASE SOCCER_PRONO";
		      stmt.executeUpdate(sql);
		      System.out.println("Database created successfully...");
		      }else {
		    	  
		  		  
		    	  conn = DriverManager.getConnection(DB_URL+"SOCCER_PRONO", USER, PASS);
			      stmt = conn.createStatement();
			      String sql = "DROP TABLE IF EXISTS Team";
			      stmt.executeUpdate(sql);
			      sql = "CREATE TABLE Team (teamID int(11) NOT NULL, Name varchar(45) NOT NULL, SquadMarketValue varchar(45) , PRIMARY KEY (teamID)) ENGINE=InnoDB;";
			      stmt.executeUpdate(sql);
		    	  System.out.println("Create Team Table...");
		    	  
		      }
		      
		      
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   
		   }
		   
		   
	
		JSONReader JSON = new JSONReader();
		
		try {
			
			//saveUrlWithHeader( DB_Path+ "teams_ligue1_id.json", "http://api.football-data.org/soccerseasons/396/teams");
			//saveUrlWithHeader( DB_Path+ "teams_ligue1.json", "http://www.football-data.org/alpha/soccerseasons/396/teams");
			//saveUrlWithHeader( DB_Path+ "alpha_ligue1_definition.json", "http://api.football-data.org/alpha/soccerseasons/396");
			//saveUrlWithHeader( DB_Path+ "alpha_ligue1_fixtures.json", "http://api.football-data.org/alpha/soccerseasons/396/fixtures");
			
			JSON.readLeagueDefinition(Ligue1, "", DB_Path, stmt);
			JSON.getTeamProperties("", Ligue1, DB_Path, stmt);
			
			
			for(int i=0;i<Ligue1.getTeamList().size();i++){
				//System.out.println(Ligue1.getTeamList().get(i).getName());
				//saveUrlWithHeader(DB_Path + "fixtures_ligue1_"+ Ligue1.getTeamList().get(i).getId() +".json", "http://api.football-data.org/alpha/teams/"+ Ligue1.getTeamList().get(i).getId() +"/fixtures");
				
			}
			JSON.getTeamFixture(Ligue1, DB_Path);
			System.out.println(Ligue1.getLeaguetableLink());
			//saveUrlWithHeader(DB_Path + "leagueTable_ligue1.json", Ligue1.getLeaguetableLink());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{

		
	    
		    try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		      
		}catch(Exception e){
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