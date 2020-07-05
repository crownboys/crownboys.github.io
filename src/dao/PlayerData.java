package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PlayerData implements PlaydataUnit {
	  
  	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
  	String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=PlayData";//PlayDataΪ������ݿ���
  	String userName="sa";//������ݿ��û���
  	String userPwd="lc02070329.";//�������
      Connection conn;
      Statement stmt;
      
      /** The nick list. */
      ArrayList<String> nickList;
      
      /** The score list. */
      ArrayList<Integer> scoreList;
      
      /** The current nick. */
      String currentNick = "now";
      
      /** The current pass. */
      String currentPass = "";
      
   /**
    * Instantiates a new player data.
    */
   public PlayerData() {
	   try {
		Class.forName(driverName);
		conn=DriverManager.getConnection(dbURL,userName,userPwd);
		stmt = conn.createStatement();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}catch(Exception e)
		{
		    e.printStackTrace();
		}   
	   creatTable();
	   getInfo();
	   }
   
 
   void creatTable() {  
	   String sql = "create table players(nick CHAR(20) NOT NULL,pass CHAR(20) NOT null,score INT NOT NULL)";
   try {
	stmt.execute(sql);
} catch (SQLException e) {
}
   }
 

 /* (non-Javadoc)
 * @see dao.PlaydataUnit#add(int)
 */
@Override
public void add(int score) {
	 String sql = "insert into players(nick,pass,score) values('"+ currentNick +"','" + currentPass+"','"+ score +"')";
	 try {
		stmt.execute(sql);
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
 
 
 //���ݲ�ѯ
 /* (non-Javadoc)
 * @see dao.PlaydataUnit#getInfo()
 */
@Override
public void getInfo() {
	 String sql = "select top 3 nick,score from players order by score desc";
	 nickList = new ArrayList<String>();
	 scoreList = new ArrayList<Integer>();
	 try {
		ResultSet res = stmt.executeQuery(sql);
		while(res.next()) {
			nickList.add(res.getString("nick"));
			scoreList.add(res.getInt("score"));
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
 }
 
 //��������
 /* (non-Javadoc)
 * @see dao.PlaydataUnit#getNicks()
 */
@Override
public ArrayList<String> getNicks(){
	return nickList; 
 }
 
//���ط���
 /* (non-Javadoc)
 * @see dao.PlaydataUnit#getScore()
 */
@Override
public ArrayList<Integer> getScore(){
	return scoreList; 
 }
 

 //��¼����,��ѯ��¼����
 /* (non-Javadoc)
 * @see dao.PlaydataUnit#Login(java.lang.String, java.lang.String)
 */
@Override
public boolean Login(String nick,String pass) {
     String sql = "select * from players where nick ='"+ nick +"'";
	 try {
		ResultSet res = stmt.executeQuery(sql);
		if(res.next()) {
			System.out.println("132132132");
			if(!pass.equals(res.getString(2))) {
			 return false;
			}	
		}
		currentNick = nick;
		currentPass = pass;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 return true; 
 }
}

