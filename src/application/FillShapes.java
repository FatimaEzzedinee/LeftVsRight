package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.ScaleTransition;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FillShapes extends Game{
	public static ArrayList<Shape> shapes =new ArrayList<Shape>();
	
	public static ArrayList<Integer> choosed=new ArrayList<Integer>();
	public static int p=3,m=0;
	public static ScaleTransition scaleTransition = new ScaleTransition(); 
	public static Rectangle r2=null,r1= new Rectangle(250,250,100,100);
	public static double xr2=0,yr2=0,xr1=0,yr1=0;
	public static Circle c2=null,c1=new  Circle(50);
	public static double cur=0;
	public static int wins=0,loses=0,levell=0;
	public static int ss=-1;
	public static int s1=-1;
	
	
	//public static Hint 
	public static Button hints=new Button("?");
	public static Tooltip t =new Tooltip();
	public  static void startGame(Stage primaryStage,int level,String User)
	{
		primaryStage.setFullScreen(true);
		levell=level;
		if(level<10)
		{
	Random r=new Random();

	 s1=r.nextInt(2);
	
	Pane root=new Pane();
	Scene scene=new Scene(root,700,700);
	
	primaryStage.setScene(scene);
	primaryStage.setTitle("Fill The shapes");
	primaryStage.setFullScreen(true);
	
	root.setStyle("-fx-background-color: linear-gradient(lightcyan,navy);");
	
	accept(primaryStage,new Hint());
	
  root.getChildren().add(hints);
	if(s1==0)    //display circle
	{
		c2=new Circle(20);
		c1.setLayoutX(300);
		c1.setLayoutY(300);
		c2.setFill(Color.BLUEVIOLET);
		c2.setLayoutX(300);
		c2.setLayoutY(300);
		cur=c2.getRadius();
		root.getChildren().add(c1);
		root.getChildren().add(c2);
		
		c2.setFill(Color.FIREBRICK);
		c1.setFill(Color.NAVAJOWHITE);
	}
	else {
		root.getChildren().add(r1);
		//int x=r.nextInt(20);
		r1.setFill(Color.AQUA);
	    r2=new Rectangle(300-10/2,300-10/2,10,10);
		r2.setFill(Color.BLUE);
		xr2=r2.getX();
		yr2=r2.getY();
		r1.setFill(Color.PALEGOLDENROD);
		r2.setFill(Color.INDIGO);
		root.getChildren().add(r2);
     	}
	
	if(s1==0) {
	//Setting the duration for the transition 
	scaleTransition.setDuration(Duration.millis(20000)); 
		}
	else {
		scaleTransition.setDuration(Duration.millis(7000)); 
	}
	//Setting the dimensions for scaling 
	scaleTransition.setByY(60.0); 
	scaleTransition.setByX(60.0);
	//Setting the cycle count for the translation 
	scaleTransition.setCycleCount(1); 
	//Setting auto reverse value to true 
	if(s1==0)
	{
	 scaleTransition.setNode(c2); }
	else {
		scaleTransition.setNode(r2);
	}
	
	if(s1==0)
	{
		//System.out.println("scale 2");
		//c2.setRadius(c2.getRadius()+1);
		scaleTransition.setAutoReverse(false); 
		scaleTransition.play(); 
	}
	else {
		//r2.setX(r2.getX()+1);
	//	r2.setX(r2.getY()+1);
		scaleTransition.setAutoReverse(false); 
		scaleTransition.play(); 	
	}
	

	
	Button stop=new Button("Stop");
	stop.setLayoutX(500);
	stop.setLayoutY(500);
	
	stop.setStyle("-fx-background-color: lightskyblue; -fx-text-fill: white;"
				+ "   -fx-background-radius: 100;\r\n" + 
				"   -fx-font-size:40px;\r\n" + 
				"   -fx-font-weight: bold;");
	
	hints.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
				+ "   -fx-background-radius: 100;\r\n" + 
				"   -fx-font-weight: bold;");
		
		hints.setLayoutX(550);

	stop.setOnAction(e->{
		scaleTransition.stop();
		if(s1==1)
		{
		xr1=r2.getScaleX();
		yr1=r2.getScaleY();
		
		System.out.println("xr1  :" +xr1 +" yr1 :"+yr1);
		if(xr1<=10.5 && xr1>=9.5 )
		{
			wins++;
			levell++;
			System.out.println("Yes");
			startGame(primaryStage,levell,User);
		}
		else {
			loses++;
			levell++;
			System.out.println("Nopes");
			startGame(primaryStage,levell,User);
		}
		}
		else {
			double ra=c2.getScaleX();
			System.out.println("Radius :"+ra);
			if(ra<=2.8 && ra>=2.3)
			{
				wins++;
				levell++;
				System.out.println("Yes");
				startGame(primaryStage,levell,User);
			}
			else
				{
				loses++;
				levell++;
				System.out.println("Nopes");
				startGame(primaryStage,levell,User);
				}
		}
			
	});
	
	root.getChildren().add(stop);
	//stop.setLayoutX(50);
	primaryStage.show();
	}
		else {
			if(wins>=5)
			{
				Connection MyConn=new DBconnect().getConn();
				try {
					Statement stmt = MyConn.createStatement();
					//String sql = "update stats set PatienceWinRate= ?"+" where Username='"+User+"'";
					String sql2="Select * from stats";
					String sql3 = "update stats set PatienceNb= ?"+" where Username='"+User+"'";
		            PreparedStatement pstmt = MyConn.prepareStatement(sql3);
		            ResultSet res;
		            res=stmt.executeQuery(sql2);
		            int l;
		            int lm=0;
		            while(res.next())
					{
		            	if(res.getString("Username").equals(User))
		            	{
		            		System.out.println(res.getString("Username"));
		              l=res.getInt("PatienceNb");
		              l++;
		              System.out.println(l+" "+lm);
		            pstmt.setInt(1,l);
		           pstmt.executeUpdate(); 
		           if(wins>3)
		           {
		        	   //pstmt = MyConn.prepareStatement(sql);
		        	   lm=res.getInt("PatienceWinRate");
		               lm++;
		               pstmt.setInt(1,lm);
		               pstmt.executeUpdate(); 
		           }
		            break;
		            	}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Button b=new Button("Go back");
				b.setStyle("-fx-background-color:mediumturquoise ; -fx-text-fill: white;"
		  				+ "   -fx-background-radius: 100;\r\n" + 
		  				"   -fx-font-weight: bold;");
				System.out.println("Wins : "+wins);
				System.out.println("Loss : "+loses);
				FlowPane root = new FlowPane();
				Label l1=new Label("You won !!!!!!!!!");
				l1.setStyle("-fx-font-size: 20px;\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-text-fill: white;\r\n");
				Scene scene = new Scene(root,700,700);
				root.setOrientation(Orientation.VERTICAL);
				root.setStyle("-fx-background-color: linear-gradient(lightcyan,navy);");
				root.setAlignment(Pos.CENTER);
				root.getChildren().add(l1);
				root.getChildren().add(b);
				b.setOnAction(e->{
						Main.MainPage(primaryStage,User);
						});
				primaryStage.setScene(scene);
			    primaryStage.setFullScreen(true);
				primaryStage.show();
			}
			else {
				Connection MyConn=new DBconnect().getConn();
				try {
					Statement stmt = MyConn.createStatement();
					String sql2="Select * from stats";
					String sql3 = "update stats set PatienceNb= ?"+" where Username='"+User+"'";
		            PreparedStatement pstmt = MyConn.prepareStatement(sql3);
		            ResultSet res;
		            res=stmt.executeQuery(sql2);
		            int l;
		            int lm=0;
		            while(res.next())
					{
		            	if(res.getString("Username").equals(User))
		            	{
		            		System.out.println(res.getString("Username"));
		              l=res.getInt("PatienceNb");
		              l++;
		              System.out.println(l+" "+lm);
		            pstmt.setInt(1,l);
		           pstmt.executeUpdate();
		           break;
		              }
					}
				Button b=new Button("Go back");
				b.setStyle("-fx-background-color:mediumturquoise ; -fx-text-fill: white;"
		  				+ "   -fx-background-radius: 100;\r\n" + 
		  				"   -fx-font-weight: bold;");
				System.out.println("Wins : "+wins);
				System.out.println("Loss : "+loses);
				FlowPane root = new FlowPane();
				Label l1=new Label("You lose !!!!!!!!!");
				l1.setStyle("-fx-font-size: 20px;\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-text-fill: white;\r\n");
				Scene scene = new Scene(root,700,700);
				root.setOrientation(Orientation.VERTICAL);
				root.setAlignment(Pos.CENTER);
				root.getChildren().add(l1);
				root.getChildren().add(b);
				root.setStyle("-fx-background-color: linear-gradient(lightcyan,navy);");
				b.setOnAction(e->{
					Main.MainPage(primaryStage,User);
					});
				primaryStage.setScene(scene);
				primaryStage.setFullScreen(true);
				primaryStage.show();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}
				}
					}
	
	
	
	public static void accept(Stage p,Hint hint)
	{
		String h=null;
		h=hint.visit(new FillShapes());
	//	hints.setLayoutX(40);
	//	hints.setLayoutY(700);
		
		Tooltip.install(hints,new Tooltip(h));
	}
						}

	
	
	

