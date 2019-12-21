package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
//import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Match extends Game{
	
	static Color[] colors = {Color.YELLOW,Color.RED,Color.AQUA,Color.BLUE,Color.GREEN};
	static String[] CNames = {"Yellow","Red","Aqua","Blue","Green"};
	
	static int click = -1, lb, k1, k2, NbRight, NbTry;
	static ArrayList<Rectangle> r =new ArrayList<Rectangle>();
	static ArrayList<Label> l =new ArrayList<Label>();
	static ArrayList<Integer> L1 = new ArrayList<Integer>();  //les couleur choisi pour chaque rectangle successivement
	static ArrayList<Integer> L2 = new ArrayList<Integer>();  //les Label choisi pour chaque rectangle successivement
	static Connection MyConn=new DBconnect().getConn();
	static ArrayList<FlowPane> FP = new ArrayList<FlowPane>();
	
	public static void startGame(Stage primaryStage, String User)
	{
		NbRight = 0;
		NbTry = 0;
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root,700,700);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Match");
		primaryStage.setFullScreen(true);
		root.setOrientation(Orientation.VERTICAL);
		root.setAlignment(Pos.CENTER);
		accept(primaryStage,new Hint());
		
		root.setStyle("-fx-background-color: linear-gradient(gray,aliceblue);");
		
		root.getChildren().add(hints);
		
		hints.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
  				+ "   -fx-background-radius: 100;\r\n" + 
  				"   -fx-font-weight: bold;");
  		
  		
  		
		Random r1 = new Random(), r2 = new Random();
		
		int rand1, rand2 ;
		
		
		
		for(int j=0;j<4;j++)
		{
			
			r.add(new Rectangle(30,30,120,60));
			l.add(new Label());
			r.get(j).setStyle("   -fx-background-radius: 700;");
			l.get(j).setStyle("-fx-font-size: 60px;"
								+"-fx-font-family:\"Comic Sans ms\";"
					            +"-fx-font-weight: bold;");
			FP.add(new FlowPane());
			
			FP.get(j).setHgap(100);
			FP.get(j).setVgap(100);
			
			FP.get(j).getChildren().add(r.get(j));
			FP.get(j).getChildren().add(l.get(j));
			root.getChildren().add(FP.get(j));
		}
		
		
		for (int j=0;j<r.size();j++) {
			rand1 = r1.nextInt(colors.length);  // numero du couleur choisi pour r.get(j)
			rand2 = r2.nextInt(r.size());		// numero du Label choisi pour r.get(j)
			
			while (L1.contains(rand1))
				rand1=r1.nextInt(colors.length);
			
			L1.add(rand1);
			
			while (L2.contains(rand2))
				rand2=r2.nextInt(r.size());
			
			L2.add(rand2);
			
			r.get(j).setFill(colors[rand1]);
			l.get(rand2).setText(CNames[rand1]);
			l.get(rand2).setTextFill(colors[rand2]);
			
		}
		
		for(k1=0;k1<4;k1++) {
			
			r.get(k1).setOnMousePressed(new EventHandler<MouseEvent>()
				{
				int c= k1;
				@Override
				public void handle(MouseEvent t)
				{
				
					click = c;
					System.out.println(c + " Has been clicked ");
				
				}
				}
				);
		}
		
		for (k2=0;k2<4;k2++) {
			l.get(k2).setOnMousePressed(new EventHandler<MouseEvent>()
				{
				int c = k2;
				@Override
				public void handle(MouseEvent t)
				{
					NbTry++;
					System.out.println(c + " Label has been clicked ");
					if (L2.get(click) == c)
					{System.out.println("right!");
					l.get(c).setVisible(false);
					NbRight++;
					}
					else System.out.println("wrong");
				
					if (NbTry-NbRight == 3) {
						loser(primaryStage,User);
					}
					
					if (NbTry-NbRight == 3 || NbRight==4) {  //Game finiched
					
					try {
						Statement stmt = MyConn.createStatement();
						String sql2="Select * from stats";
						String sql3 = "update stats set AdaptabilityNb= ?"+" where Username='"+User+"'";
			            PreparedStatement pstmt = MyConn.prepareStatement(sql3);
			            
			            ResultSet res;
			            res=stmt.executeQuery(sql2);
			            
			            while (res.next()) {
			            	
			            	if(res.getString("Username").equals(User)) {
			            		 int l=res.getInt("AdaptabilityNb");
			                     l++;

			                     pstmt.setInt(1,l);
			                     pstmt.executeUpdate();
			                     
			                     if (NbRight == 4) {
			                    	 winner(primaryStage,User);
			                    	 
			                    	 int w=res.getInt("AdaptabilityWinRate");
			                         w++;
			                         pstmt.setInt(1,w);
			                         pstmt.executeUpdate(); 
			                        
			                     }
			                     
			                     
			            	}
			            }
			            
			 
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
					
				}
				}
				);
			}
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		
	}

	static void loser(Stage primaryStage,String User) {
		
	
		FlowPane root = new FlowPane ();
		root.setStyle("-fx-background-color: linear-gradient(gray,aliceblue);");
		Scene scene = new Scene (root,700,700);
		root.setOrientation(Orientation.VERTICAL);
		root.setAlignment(Pos.CENTER);
		
		Label tf = new Label("You Lose! :(");
		tf.setStyle("-fx-font-size: 20px;\r\n" + 
				"    -fx-font-weight: bold;\r\n" + 
				"    -fx-text-fill: white;\r\n");
		Button b = new Button ("Back");
		b.setStyle("-fx-background-color:mediumturquoise ; -fx-text-fill: white;"
  				+ "   -fx-background-radius: 100;\r\n" + 
  				"   -fx-font-weight: bold;");
		root.getChildren().add(tf);
		root.getChildren().add(b);
		
		b.setOnAction(e->{
		Main.MainPage(primaryStage,User);
		});
		
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		
	}
	
	static void winner(Stage primaryStage,String User) {
		
		FlowPane root = new FlowPane ();
		root.setStyle("-fx-background-color: linear-gradient(gray,aliceblue);");
		Scene scene = new Scene (root,700,700);
		root.setOrientation(Orientation.VERTICAL);
		root.setAlignment(Pos.CENTER);
		
		Label tf = new Label("You Won! :)");
		tf.setStyle("-fx-font-size: 20px;\r\n" + 
				"    -fx-font-weight: bold;\r\n" + 
				"    -fx-text-fill: white;\r\n");
		Button b = new Button ("Back");
		b.setStyle("-fx-background-color:mediumturquoise ; -fx-text-fill: white;"
  				+ "   -fx-background-radius: 100;\r\n" + 
  				"   -fx-font-weight: bold;");
		root.getChildren().add(tf);
		root.getChildren().add(b);
		
		b.setOnAction(e->{
			Main.MainPage(primaryStage,User);
		});
		
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		
	}
	
	public static Button hints=new Button("?");
	public static void accept(Stage p,Hint hint)
	{
		String h=null;
		h=hint.visit(new Match());
		Tooltip.install(hints,new Tooltip(h));
	}

}