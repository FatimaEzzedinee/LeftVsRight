package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PayBill extends Game {
	
	static String op[]= {"+","*","/","-"};
	static int wins=0;
	static int lose=0;
	static int l=0;
	static int lm=0;
	static ArrayList<Button> b=new ArrayList<Button>();
	public static Button hints=new Button("?");
	
	public static void startGame(Stage primaryStage,int k,String User) {
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root,700,700);
		primaryStage.setScene(scene);
		primaryStage.setTitle("PayBill");
		primaryStage.setFullScreen(true);
		accept(primaryStage,new Hint());
		hints.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
  				+ "   -fx-background-radius: 100;\r\n" + 
  				"   -fx-font-weight: bold;");
		
		root.setStyle("-fx-background-color: linear-gradient(thistle,mediumpurple);");
  		
  		hints.setLayoutX(550);
		  root.getChildren().add(hints);
	
		root.setOrientation(Orientation.VERTICAL);
		root.setAlignment(Pos.CENTER);
		
		FlowPane BtPane = new FlowPane();

		TextField t1=new TextField("");
		t1.setEditable(false);
		TextField t2=new TextField("???");
		t2.setEditable(false);
		TextField t3=new TextField("");
		t3.setEditable(false);
		
		t1.setStyle("-fx-font-family:\"Comic Sans ms\";\r\n" + 
					   "-fx-background-color: linear-gradient(lightskyblue,lightsteelblue);");
		t2.setStyle("-fx-font-family:\"Comic Sans ms\";\r\n" + 
				   "-fx-background-color: linear-gradient(lightskyblue,lightsteelblue);");
		t3.setStyle("-fx-font-family:\"Comic Sans ms\";\r\n" + 
				   "-fx-background-color: linear-gradient(lightskyblue,lightsteelblue);");

		
        Random r1 = new Random(), r2 = new Random();
        int rand1, rand4;
        int rand3=0;
		
		Label l1=new Label("Number 1");
		Label l2=new Label("Number 2 To Guess : ");
		Label l3=new Label("Resultat : ");
		
		l1.setStyle("-fx-font-size: 20px;"
					+"-fx-font-family:\"Comic Sans ms\";"
					+"-fx-font-color: fuchsia;"
                    +"-fx-font-weight: bold;"
                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		
		l2.setStyle("-fx-font-size: 20px;"
				+"-fx-font-family:\"Comic Sans ms\";"
				+"-fx-font-color: fuchsia;"
                +"-fx-font-weight: bold;"
                +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		
		l3.setStyle("-fx-font-size: 20px;"
				+"-fx-font-family:\"Comic Sans ms\";"
				+"-fx-font-color: fuchsia;"
                +"-fx-font-weight: bold;"
                +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		
		ArrayList<Button> bt = new ArrayList<Button>();
		
		rand4 = r2.nextInt(2);
		
		
		rand1= r1.nextInt(2000);  //le nb dans la premiere case fixe 
		t1.setText(""+rand1);
		
		
		int randop = r2.nextInt(3);  
		
		
	
		
		String O = op[randop];  //l'operation random a utiliser;
		
		int resultat=0;
		
		root.getChildren().add(l1);
		root.getChildren().add(t1);
		
		if(O.equals("+"))
		{
		rand3=r1.nextInt(4000);
		resultat =rand1+rand3;
		String res =""+resultat;
		t3.setText(res);
		t3.setEditable(false);
		}
		
		if(O.equals("*"))
		{
		rand3=r1.nextInt(10);
		resultat =rand1*rand3;
		String res =""+resultat;
		t3.setText(res);
		t3.setEditable(false);
		}
		
		if(O.equals("-"))
		{
		rand3=r1.nextInt(4000);
		resultat =rand1-rand3;
		String res =""+resultat;
		t3.setText(res);
		t3.setEditable(false);
		}
		
		if(O.equals("/"))
		{
		rand3=r1.nextInt(10);
		resultat =rand1/rand3;
		String res =""+resultat;
		t3.setText(res);
		t3.setEditable(false);
		}
		
		Label L=new Label(O);
		
		//hints.setLayoutX(678);
		
		L.setText("Hint : The operation used is "+op[randop]);
		L.setStyle("-fx-font-family:\"Comic Sans ms\";");
		
		root.getChildren().add(l2);  //??
		
		root.getChildren().add(t2);
		
		root.getChildren().add(l3); // res
		
		root.getChildren().add(t3);
		
		root.getChildren().add(L);
		
		root.getChildren().add(BtPane);
		
		if(k<5)
		{
			
		for (int i=0;i<k;i++) {
			bt.add(new Button());
			bt.get(bt.size()-1).setStyle("-fx-background-color: purple; -fx-text-fill: white;"
	  				+ "   -fx-background-radius: 100;\r\n" + 
	  				"   -fx-font-size:20px;\r\n" + 
	  				"   -fx-font-weight: bold;");
			BtPane.getChildren().add(bt.get(i));
			if (i==rand4)
			{
				bt.get(i).setText(""+ rand3);
				bt.get(i).setOnAction(e->{
					System.out.println("Right!");
					wins++;
					PayBill.startGame(primaryStage,k+1,User);
				});
			}
			else {
				bt.get(i).setText("" + r2.nextInt(100));
				bt.get(i).setOnAction(e->{
					System.out.println("False");
					lose++;
					PayBill.startGame(primaryStage,k+1,User);
				});
			}
		
		}
		}
		
		else if (k==5)
		{
			System.out.println(wins);
		System.out.println(lose);
		Connection MyConn=new DBconnect().getConn();
		
		
		try {
			Statement stmt = MyConn.createStatement();
		//	String sql = "update stats set ReasoningWinRate= ?"+" where Username='"+User+"'";
			String sql2="Select * from stats";
			String sql3 = "update stats set ReasoningNb= ?"+" where Username='"+User+"'";
            PreparedStatement pstmt = MyConn.prepareStatement(sql3);
            ResultSet res;
            res=stmt.executeQuery(sql2);
            while(res.next())
			{
            	if(res.getString("Username").equals(User))
          {
            		System.out.println(res.getString("Username"));
              l=res.getInt("ReasoningNb");
              l++;

              System.out.println(l+" "+lm);
            
            pstmt.setInt(1,l);
            
           pstmt.executeUpdate(); 
           
           if(wins>=2)
           {
        	   //pstmt = MyConn.prepareStatement(sql);
        	   
        	   lm=res.getInt("ReasoningWinRate");
               lm++;
               pstmt.setInt(1,lm);
               pstmt.executeUpdate();
               
               
               Button b=new Button("Go back");
               b.setStyle("-fx-background-color:mediumturquoise ; -fx-text-fill: white;"
         				+ "   -fx-background-radius: 100;\r\n" + 
         				"   -fx-font-weight: bold;");
				System.out.println("Wins : "+wins);
			//	System.out.println("Loss : "+loses);
				FlowPane root8 = new FlowPane();
				root8.setStyle("-fx-background-color: linear-gradient(thistle,mediumpurple);");
				Label ll1=new Label("You won !!!!!!!!!");
				ll1.setStyle("-fx-font-size: 20px;\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-text-fill: white;\r\n");
				Scene scenee = new Scene(root8,700,700);
				root.setOrientation(Orientation.VERTICAL);
				  root8.setAlignment(Pos.CENTER);
				  root8.getChildren().add(ll1);
				  root8.getChildren().add(b);
					b.setOnAction(e->{
						Main.MainPage(primaryStage,User);
					}
						);
					
					primaryStage.setScene(scenee);
					primaryStage.setFullScreen(true);
  					primaryStage.show();
           }
           else {
              	  // pstmt = MyConn.prepareStatement(sql);
              	   
              	   lm=res.getInt("ReasoningWinRate");
                     lm++;
                     pstmt.setInt(1,lm);
                     pstmt.executeUpdate();
                     
                     
                     Button b=new Button("Go back");
                     b.setStyle("-fx-background-color:mediumturquoise ; -fx-text-fill: white;"
     		  				+ "   -fx-background-radius: 100;\r\n" + 
     		  				"   -fx-font-weight: bold;");
      				System.out.println("Wins : "+wins);
      			//	System.out.println("Loss : "+loses);
      				FlowPane root8 = new FlowPane();
      				root8.setStyle("-fx-background-color: linear-gradient(thistle,mediumpurple);");
      				Label ll1=new Label("You Lose !!!!!!!!!");
      				ll1.setStyle("-fx-font-size: 20px;\r\n" + 
    						"    -fx-font-weight: bold;\r\n" + 
    						"    -fx-text-fill: white;\r\n");
      				Scene scenee = new Scene(root8,700,700);
      				root.setOrientation(Orientation.VERTICAL);
      				  root8.setAlignment(Pos.CENTER);
      				  root8.getChildren().add(ll1);
      				  root8.getChildren().add(b);
      					b.setOnAction(e->{
      						Main.MainPage(primaryStage,User);
      					}
      						);
      					primaryStage.setScene(scenee);
      					primaryStage.setFullScreen(true);
      					primaryStage.show();
      				
      					

                 
           }
            break;
            	}
            
			}
		}
            catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		else if(k>5)
		{
		return;
		}
		}
	
	
	public static void accept(Stage p,Hint hint)
	{
		String h=null;
		h=hint.visit(new PayBill());
		Tooltip.install(hints,new Tooltip(h));
	}


	
}
