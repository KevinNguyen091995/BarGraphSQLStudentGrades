package studentsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SQLClass
{
    PreparedStatement preparedState = null;
    ResultSet rsult = null;
    
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private String serverName = "jdbc:mysql://remotemysql.com:3306/DmrmDDa2RC";
	private String userName = "DmrmDDa2RC";
	private String password = "VmZwyXQo4v";
	
	private HashMap<String, HashMap<Character, Integer>> teacherMap = new HashMap<>();
	private Character[] gradesArray = new Character[] {'A', 'B', 'C', 'D'};
	private ArrayList<String> studentList = new ArrayList<>();
	private ArrayList<String> teacherList = new ArrayList<>();
	
	public SQLClass() {
		teacherList();
		studentList();
		averageGrades();
	}
	
	public void teacherList() {
		//DB connection
		try {
			Class.forName(jdbcDriver);
			Connection con = DriverManager.getConnection(serverName, userName, password);
			
			//Prepare and result statement to query teacher names
			preparedState = con.prepareStatement("SELECT * FROM School;");
			rsult = preparedState.executeQuery();
			
			//Add teachers to hashmap indexing them by INT values
			while(rsult.next()) {
				teacherMap.put(rsult.getString(2) + " " + rsult.getString(3), new HashMap<Character, Integer>());
			}
	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < teacherMap.size(); i++) {
			teacherList.add((String) teacherMap.keySet().toArray()[i]);
		}
		
		for(int i = 0; i < gradesArray.length; i++) {
			for(int j = 0; j < teacherMap.size(); j++) {
				teacherMap.get(teacherList.get(i)).put(gradesArray[j], 0);
			}
		}
	}
	
	public void studentList(){
		//DB connection
		try {
			Class.forName(jdbcDriver);
			Connection con = DriverManager.getConnection(serverName, userName, password);
			
			//Prepare and result statement to query student names
			preparedState = con.prepareStatement("SELECT * FROM student;");
			rsult = preparedState.executeQuery();
			
			//Add students full name to an arraylist to display.
			while(rsult.next()) {
				studentList.add(rsult.getString(2) + " " + rsult.getString(3));
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void averageGrades() {
		try {
			Class.forName(jdbcDriver);
			Connection con = DriverManager.getConnection(serverName, userName, password);
			
			//Prepare and result statement to query student names
			preparedState = con.prepareStatement("SELECT * from student");
			rsult = preparedState.executeQuery();
			
			while(rsult.next()) {
				int avg = ((Integer.parseInt(rsult.getString(4)) + Integer.parseInt(rsult.getString(5)) + Integer.parseInt(rsult.getString(6)) + Integer.parseInt(rsult.getString(7))) / 4);
				if(Integer.parseInt(rsult.getString(8)) == 1){
					if(avg >= 90) {
						teacherMap.get("James Hamilton").put('A', teacherMap.get("James Hamilton").get('A')+1);
					}
					if(avg >= 80 && avg < 90) {
						teacherMap.get("James Hamilton").put('B', teacherMap.get("James Hamilton").get('B')+1);
					}
					if(avg >= 70 && avg < 80) {
						teacherMap.get("James Hamilton").put('C', teacherMap.get("James Hamilton").get('C')+1);
					}
					if(avg < 70) {
						teacherMap.get("James Hamilton").put('D', teacherMap.get("James Hamilton").get('D')+1);
					}
				}
				
				if(Integer.parseInt(rsult.getString(8)) == 2){
					if(avg >= 90) {
						teacherMap.get("Natalie Brookes").put('A', teacherMap.get("Natalie Brookes").get('A')+1);
					}
					if(avg >= 80 && avg < 90) {
						teacherMap.get("Natalie Brookes").put('B', teacherMap.get("Natalie Brookes").get('B')+1);
					}
					if(avg >= 70 && avg < 80) {
						teacherMap.get("Natalie Brookes").put('C', teacherMap.get("Natalie Brookes").get('C')+1);
					}
					if(avg < 70) {
						teacherMap.get("Natalie Brookes").put('D', teacherMap.get("Natalie Brookes").get('D')+1);
					}
				}
				
				if(Integer.parseInt(rsult.getString(8)) == 3){
					if(avg >= 90) {
						teacherMap.get("Kevin Nguyen").put('A', teacherMap.get("Kevin Nguyen").get('A')+1);
					}
					if(avg >= 80 && avg < 90) {
						teacherMap.get("Kevin Nguyen").put('B', teacherMap.get("Kevin Nguyen").get('B')+1);
					}
					if(avg >= 70 && avg < 80) {
						teacherMap.get("Kevin Nguyen").put('C', teacherMap.get("Kevin Nguyen").get('C')+1);
					}
					if(avg < 70) {
						teacherMap.get("Kevin Nguyen").put('D', teacherMap.get("Kevin Nguyen").get('D')+1);
					}
				}
				
				if(Integer.parseInt(rsult.getString(8)) == 4){
					if(avg >= 90) {
						teacherMap.get("John Adams").put('A', teacherMap.get("John Adams").get('A')+1);
					}
					if(avg >= 80 && avg < 90) {
						teacherMap.get("John Adams").put('B', teacherMap.get("John Adams").get('B')+1);
					}
					if(avg >= 70 && avg < 80) {
						teacherMap.get("John Adams").put('C', teacherMap.get("John Adams").get('C')+1);
					}
					if(avg < 70) {
						teacherMap.get("John Adams").put('D', teacherMap.get("John Adams").get('D')+1);
					}
				}
			}
			System.out.println(teacherMap);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getTeacherList() {		
		return this.teacherList;
	}
	
	public ArrayList<String> getStudentList() {
		return this.studentList;
	}
	
	public HashMap<String, HashMap<Character, Integer>> getTeacherMap() {
		return this.teacherMap;
	}
}
