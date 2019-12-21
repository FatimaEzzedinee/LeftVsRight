package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Freeze extends Game{
	public static ArrayList<Shape> shapes =new ArrayList<Shape>();
	static Shape sh=null;
	public static ArrayList<Integer> choosed=new ArrayList<Integer>();
	public static int p=3,m=0;
	public static  TranslateTransition translateTransition = new TranslateTransition(); 
	public static Rectangle r2=null,r1= new Rectangle(250,250,100,100);
	public static double xr2=0,yr2=0,xr1=0,yr1=0;
	public static Circle c2=null,c1=new  Circle(40);
	public static double cur=0;
	public static int wins=0,loses=0,levell=0;
	public static int ss=-1;
	public static int s1=-1;
	static int x=0,y=0;
	public  static void startGame(Stage primaryStage,int level,String User)
	{
		
		levell=level;
		if(level<10)
		{
	Random r=new Random();
	

	 s1=r.nextInt(2);
	
	Pane root=new Pane();
	Scene scene=new Scene(root,700,700);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Freeze");
	primaryStage.setFullScreen(true);
	accept(primaryStage,new Hint());
	hints.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
				+ "   -fx-background-radius: 100;\r\n" + 
				"   -fx-font-weight: bold;");
		
		hints.setLayoutX(550);
	  root.getChildren().add(hints);

	if(s1==0)    //display circle
	{
		c2=new Circle(20);
		c1.setLayoutX(300);
		c1.setLayoutY(300);
		c1.setFill(Color.MIDNIGHTBLUE);
		x=r.nextInt(500);
		y=r.nextInt(250);
		c2.setLayoutX(x);
		c2.setLayoutY(y);
		cur=c2.getRadius();
		c2.setFill(Color.AZURE);
		root.getChildren().add(c1);
		root.getChildren().add(c2);
	}
	else {
		root.getChildren().add(r1);
		//int x=r.nextInt(20)
		x=r.nextInt(500);
		y=r.nextInt(250);
	    r2=new Rectangle(x,y,100,100);
		r2.setFill(Color.BISQUE);
		xr2=r2.getX();
		yr2=r2.getY();
		r1.setFill(Color.INDIGO);

		root.getChildren().add(r2);
     	}
	
	
	
	
	
	
	if(s1==0) {
	//Setting the duration for the transition 
	translateTransition.setDuration(Duration.millis(10000)); 
		}
	else {
		translateTransition.setDuration(Duration.millis(10000)); 
	}
	//Setting the dimensions for scaling 
	//translateTransition.setByX(60.0); 
	translateTransition.setByY(1000.0);
	//Setting the cycle count for the translation 
	translateTransition.setCycleCount(1); 
	//Setting auto reverse value to true 
	if(s1==0)
	{
	 translateTransition.setNode(c2); }
	else {
		translateTransition.setNode(r2);
	}
	
	
		translateTransition.setAutoReverse(false); 
		translateTransition.play(); 	
	
	
		root.setStyle("-fx-background-color: linear-gradient(indianred,white);");
  		
	Button stop=new Button("Stop");
	
	root.getChildren().add(stop);
	stop.setLayoutX(500);
	stop.setLayoutY(500);
	stop.setStyle("-fx-background-color:firebrick ; -fx-text-fill: white;"
				+ "   -fx-background-radius: 100;\r\n" + 
				"   -fx-font-weight: bold;"
				+ "-fx-font-size:20px");
		
	
	//hints.setLayoutX(678);

	stop.setOnAction(e->{
		translateTransition.stop();
		if(s1==1)
		{
		//xr1=r2.getTranslateX();
		yr1=r2.getTranslateY();
		
		System.out.println("yr1 :"+yr1+y);
		System.out.println("y :"+y);
		if(yr1+y<=270 && yr1+y>=250 )
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
			double ra=c2.getTranslateY();
			System.out.println("Radius :"+ra);
			if(ra+y<=325 && ra+y>=275)
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
	
	
	//stop.setLayoutX(50);
	primaryStage.show();
	}
		else {
			if(wins>=5)
			{
				Connection MyConn=new DBconnect().getConn();
				try {
					Statement stmt = MyConn.createStatement();
					//String sql = "update stats set PrecisionWinRate= ?"+" where Username='"+User+"'";
					String sql2="Select * from stats";
					String sql3 = "update stats set PrecisionNb= ?"+" where Username='"+User+"'";
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
		              l=res.getInt("PrecisionNb");
		              l++;
		              System.out.println(l+" "+lm);
		            pstmt.setInt(1,l);
		           pstmt.executeUpdate(); 
		           if(wins>3)
		           {
		        	 //  pstmt = MyConn.prepareStatement(sql);
		        	   lm=res.getInt("PrecisionWinRate");
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
				root.setStyle("-fx-background-color: linear-gradient(indianred,white);");
				Label l1=new Label("You won !!!!!!!!!");
				l1.setStyle("-fx-font-size: 20px;\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-text-fill: white;\r\n");
				Scene scene = new Scene(root,700,700);
				root.setOrientation(Orientation.VERTICAL);
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
					String sql3 = "update stats set PrecisionNb= ?"+" where Username='"+User+"'";
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
		              l=res.getInt("PrecisionNb");
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
				root.setStyle("-fx-background-color: linear-gradient(indianred,white);");
				Label l1=new Label("You lose !!!!!!!!!");
				l1.setStyle("-fx-font-size: 20px;\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-text-fill: white;\r\n");
				Scene scene = new Scene(root,700,700);
				root.setOrientation(Orientation.VERTICAL);
				root.setAlignment(Pos.CENTER);
				root.getChildren().add(l1);
				root.getChildren().add(b);
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
	
	public static Button hints=new Button("?");
	public static void accept(Stage p,Hint hint)
	{
		String h=null;
		h=hint.visit(new Freeze());
		Tooltip.install(hints,new Tooltip(h));
	}
						}

	
	
	

