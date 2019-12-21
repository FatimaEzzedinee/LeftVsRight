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
//import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HowManyMoved extends Game{
	public static ArrayList<Shape> shapes =new ArrayList<Shape>();
	static Shape sh=null;
	public static int wins=0;
	 public static int loses=0;
	
	public static ArrayList<Integer> choosed=new ArrayList<Integer>();
	public static int w=10;
	public static int levell=0;
    public static ArrayList<Integer> moved=new ArrayList<Integer>();
    public static ArrayList<Button> butt=new ArrayList<Button>();
		
	public static ArrayList<Integer> bu=new ArrayList<Integer>();
	
	public static void createShapes()
	{
		choosed.clear();
		moved.clear();
		bu.clear();
		butt.clear();

		
		for(int i=0;i<5;i++)
		{
			if(i%2==0)
			{
				
				sh=new Rectangle(50,50);
				sh.setFill(Color.RED);
				 shapes.add(sh);
				 sh.setLayoutX(w);
				 sh.setLayoutY(w);
				w=2*w;
			}
			
			else {
				sh=new Circle(20);
				sh.setFill(Color.DARKGRAY);
				 sh.setLayoutX(w);
				 sh.setLayoutY(w);
				 w=2*w;
				shapes.add(sh);
			}
		}
	}

	public static  void startGame(Stage primaryStage,int level,String User)
	{
		
		if(level<5)
	{
			
			//shapes.clear();
		createShapes();
		
		Pane root=new Pane();
		Scene scene=new Scene(root,600,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("How Many moved");
		primaryStage.setFullScreen(true);
		accept(primaryStage,new Hint());
		root.getChildren().add(hints);
		
		root.setStyle("-fx-background-color: linear-gradient(#00008b,#1e90ff);");
		
		int nbShapes=0;
		Random r=new Random();
		int l=0;
		nbShapes=r.nextInt(5);
		while(nbShapes<2)
		nbShapes=r.nextInt(5);
		System.out.println("NB sh "+nbShapes);
		for(int i=0;i<nbShapes;i++)      
		{
			while(choosed.contains(l))
			l=r.nextInt(5);
			
			System.out.println(""+l);
			
			choosed.add(l);
			
		}
		
		for(int i=0;i<nbShapes;i++)
		{
			
			//System.out.println("Out");
			Circle c=null;
			Rectangle rec=null;
			if(choosed.get(i)%2==0)
			{
			
			rec=(Rectangle) shapes.get(choosed.get(i));
			root.getChildren().add(rec);
			}
			
			else {
			c=(Circle)shapes.get(choosed.get(i));
			c.setRadius(40);
			root.getChildren().add(c);
			}
		}
		
		
		int nbMoved=r.nextInt(nbShapes);
		
		while(nbMoved==0) {
			nbMoved=r.nextInt(nbShapes);
		}
	      System.out.println("nb Moved ::"+nbMoved);
	         
	    
	  	for(int i=0;i<nbMoved;i++)
		{
	  
	  		int s1=r.nextInt(nbShapes);
	  		while(!choosed.contains(s1) || moved.contains(s1))
	  		{
	  			s1=r.nextInt(nbShapes);
	  			
	  		}
	  		
	  		moved.add(s1);
	  		
	  		 //Creating Translate Transition 
		      TranslateTransition translateTransition = new TranslateTransition(); 
	  		System.out.println("Moved");
	  	   //Setting the node for the transition 
	  		
	  			 translateTransition.setNode(shapes.get(s1)); 
	  			  //Setting the duration of the transition  
			      translateTransition.setDuration(Duration.millis(3000));
		      //Setting the value of the transition along the x axis. 
		      translateTransition.setByX(150);
		      translateTransition.setByY(150); 
		       
		      //Setting the cycle count for the transition 
		      translateTransition.setCycleCount(1); 
		      
		      //Setting auto reverse value to false 
		      translateTransition.setAutoReverse(false); 
		      
		      //Playing the animation 
		      translateTransition.play(); 
	  		
		}
	     
	  	
	  		Button b1=new Button();
	  		b1.setText(""+nbMoved);
	  		
	  		int b22=r.nextInt(5);
	  		
	  		while(b22==nbMoved)
	  		{
	  			b22=r.nextInt(5);
	  		}
	  		
	  		Button b2=new Button(""+b22);
	  		
	  		//b2.setLayoutX(150);
	  		
	  		
	  		int b33=r.nextInt(5);
	  		
	  		while(b33==nbMoved || b33==b22)
	  		{
	  			b33=r.nextInt(5);
	  		}
	  		Button b3= new Button(""+b33);
	  		//b3.setLayoutX(400);
	  		
	  		butt.add(b2);
	  		butt.add(b1);
	  		butt.add(b3);
	  		
	  		//root.setHgap(20);
	  		//root.setVgap(20);
	  		hints.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
	  				+ "   -fx-background-radius: 100;\r\n" + 
	  				"   -fx-font-weight: bold;");
	  		
	  		hints.setLayoutX(550);
	  		
	  		b1.setLayoutY(400);
	  		b2.setLayoutY(400);
	  		b3.setLayoutY(400);
	  		b1.setLayoutX(350);
	  		b2.setLayoutX(410);
	  		b3.setLayoutX(470);
	  		b1.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;"
	  				+ "   -fx-background-radius: 100;\r\n" + 
	  				"   -fx-font-size:30px;\r\n" + 
	  				"   -fx-font-weight: bold;");
	  		b2.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;"
	  				+ "   -fx-background-radius: 100;\r\n" + 
	  				"   -fx-font-size:30px;\r\n" + 
	  				"   -fx-font-weight: bold;");
	  		b3.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;"
	  				+ "   -fx-background-radius: 100;\r\n" + 
	  				"   -fx-font-size:30px;\r\n" + 
	  				"   -fx-font-weight: bold;");
	  		
	  		int y;
	  		
	  		for(int u=0;u<3;u++)
	  		{
	  			
	  			y=r.nextInt(3);
	  			while(bu.contains(y))
	  			{
	  				y=r.nextInt(3);
	  			}
	  			
	  			System.out.println("y : "+y);
	  			bu.add(y);
	  			if(butt.get(y)==b1)
	  			{
	  				root.getChildren().add(butt.get(y));
	  				butt.get(y).setOnAction(e->{
	  		  			System.out.println("Right");
	  		  			levell++;
	  		  			wins++;
	  		  			startGame(primaryStage,levell,User);
	  		  			
	  		  		});
	  			}
	  			
	  			else {
	  				root.getChildren().add(butt.get(y));
	  				butt.get(y).setOnAction(e->{
	  		  			System.out.println("Fault");
	  		  			levell++;
	  		  			loses++;
	  		  			startGame(primaryStage,levell,User);
	  		  			
	  		  		});
	  			}
	  				
	  			
	  			
	  		}
	  		
	  		
	  		
	  		
	  		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		}
		
		else {
			if(wins>3)
			{
				Connection MyConn=new DBconnect().getConn();
		
				try {
					Statement stmt = MyConn.createStatement();
				//	String sql = "update stats set ReflexWinRate= ?"+" where Username='"+User+"'";
					String sql2="Select * from stats";
					String sql3 = "update stats set ReflexNb= ?"+" where Username='"+User+"'";
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
		              l=res.getInt("ReflexNb");
		              l++;

		              System.out.println(l+" "+lm);
		            
		           pstmt.setInt(1,l);
		            
		           pstmt.executeUpdate(); 
		           
		           if(wins>3)
		           {
		        	 //  pstmt = MyConn.prepareStatement(sql);
		        	   
		        	   lm=res.getInt("ReflexWinRate");
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
				root.setStyle("-fx-background-color: linear-gradient(#00008b,#1e90ff);");
				Label l1=new Label("You won !!!!!!!!!");
				l1.setStyle("-fx-font-size: 20px;\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-text-fill: white;\r\n");
				Scene scene = new Scene(root,600,600);
				root.setOrientation(Orientation.VERTICAL);
				  root.setAlignment(Pos.CENTER);
				  root.getChildren().add(l1);
				  root.getChildren().add(b);
					b.setOnAction(e->{
						Main.MainPage(primaryStage,User);
						});
					//primarySt
				primaryStage.setScene(scene);
				primaryStage.setFullScreen(true);
				primaryStage.show();
			}
			
			else {
				
				Connection MyConn=new DBconnect().getConn();
				
				try {
					Statement stmt = MyConn.createStatement();
					//String sql = "update stats set ReflexWinRate= ?"+" where Username='"+User+"'";
					String sql2="Select * from stats";
					String sql3 = "update stats set ReflexNb= ?"+" where Username='"+User+"'";
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
		              l=res.getInt("ReflexNb");
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
				root.setStyle("-fx-background-color: linear-gradient(#00008b,#1e90ff);");
				Label l1=new Label("You lose !!!!!!!!!");
				l1.setStyle("-fx-font-size: 20px;\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-text-fill: white;\r\n");
				Scene scene = new Scene(root,600,600);
				root.setOrientation(Orientation.VERTICAL);
				root.setAlignment(Pos.CENTER);
				root.getChildren().add(l1);
				root.getChildren().add(b);
				b.setOnAction(e->{
					Main.MainPage(primaryStage,User);
					});
				primaryStage.setScene(scene);
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
		h=hint.visit(new HowManyMoved());
		Tooltip.install(hints,new Tooltip(h));
	}
}
