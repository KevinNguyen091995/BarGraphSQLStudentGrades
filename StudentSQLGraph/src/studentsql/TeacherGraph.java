package studentsql;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class TeacherGraph extends Application {
    private SQLClass SQL = new SQLClass();
    private ArrayList<String> teacherList = SQL.getTeacherList();
    private HashMap<String, HashMap<Character, Integer>> teacherMap = SQL.getTeacherMap();
    
 
    @Override public void start(Stage stage) {
    	SQL.getTeacherMap();
    	
        stage.setTitle("Grade System Chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Average Student Grades");
        xAxis.setLabel("Teachers");       
        yAxis.setLabel("Amount of Student Grade Letters");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("A's");       
        for(int i = 0; i < teacherList.size(); i++) {
            series1.getData().add(new XYChart.Data(teacherList.get(i), teacherMap.get(teacherList.get(i)).get('A')));  
        }
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("B's");       
        for(int i = 0; i < teacherList.size(); i++) {
            series2.getData().add(new XYChart.Data(teacherList.get(i), teacherMap.get(teacherList.get(i)).get('B')));  
        }
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("C's");       
        for(int i = 0; i < teacherList.size(); i++) {
            series3.getData().add(new XYChart.Data(teacherList.get(i), teacherMap.get(teacherList.get(i)).get('C')));  
        }
        
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("D's");       
        for(int i = 0; i < teacherList.size(); i++) {
            series4.getData().add(new XYChart.Data(teacherList.get(i), teacherMap.get(teacherList.get(i)).get('D')));  
        }

        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1, series2, series3, series4);
        stage.setScene(scene);
        stage.show();
       
    }

	public static void main(String[] args) {
		launch(args);
	}
}
