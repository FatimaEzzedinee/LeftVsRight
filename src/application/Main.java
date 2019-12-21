package application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {
	//Scene scene;
	public static View view=null;
	
    public static ObservableList<String> names2 = FXCollections.observableArrayList("Awarness","Adaptability","Reflex","Precision","Reasoning","Patience");
    public static  ListView<String> list2 =new ListView<String>(names2); 
	public static ObservableList<String> selected=null;
	
	public static void MainPage(Stage primaryStage,String User)
	{
		
		TableView<String> t = new TableView<String>(names2);
		
		t.getItems().add("mmm");
		
		for(int i=0;i<t.getItems().size();i++)
			System.out.println(t.getItems().get(i));
		
		
		for(int i=0;i<names2.size();i++)
			System.out.println(names2.get(i));
			
		view.root6.setOrientation(Orientation.VERTICAL);
		view.root6.setAlignment(Pos.CENTER);
		primaryStage.setTitle("Games Menu");
		
		view.root6.getChildren().removeAll(view.g1,view.g2,view.g3,view.g4,view.g6,view.stats);
		view.root6.getChildren().addAll(view.g1,view.g2,view.g3,view.g4,view.g6,view.stats);
		view.root6.setVgap(5);
		
		//view.root6.set
		view.g1.setOnAction(e->{
			Freeze.startGame(view.primaryStage,0,User);
			Freeze.wins=0;
			Freeze.loses=0;
		});
		view.g2.setOnAction(e->{
			FillShapes.startGame(view.primaryStage,0,User);
			FillShapes.wins=0;
			FillShapes.loses=0;
		});
		view.g3.setOnAction(e->{
			HowManyMoved.startGame(view.primaryStage,0,User);
			HowManyMoved.wins=0;
			HowManyMoved.loses=0;
			HowManyMoved.levell=0;
		});
		
		view.g4.setOnAction(e->{
		Match.startGame(view.primaryStage,User);
		});
		view.g6.setOnAction(e->{
		PayBill.startGame(view.primaryStage,2,User);
			 
			PayBill.lose=0;
			PayBill.wins=0;
		});
		
		view.stats.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
  				+ "   -fx-background-radius: 100;\r\n" + 
  				"   -fx-font-weight: bold;");
		
		view.stats.setOnAction(e->{
			System.out.println("Stats");
			FlowPane statistic=new FlowPane();
			
			statistic.setAlignment(Pos.CENTER);
			Scene ss=new Scene(statistic,700,700);
		      //list View for educational qualification 
			  statistic.setStyle("-fx-background-color: red;");
		 
				list2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				
				list2.getItems().add("MMM");
				
			//selected.add("MMM");
				
				list2.setStyle("-fx-background-color: blue; "
						+ "-fx-text-fill: black;);"
						+ "-fx-font-size: 24px;"
								+ " -fx-font-family: 'SketchFlow Print';");
				   Button  vi=new Button("See result ");
				   vi.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
			  				+ "   -fx-background-radius: 100;\r\n" + 
			  				"   -fx-font-weight: bold;");
				   
				vi.setOnAction(e2->{
					try {
						 FlowPane statistic2=new FlowPane();
						 //statistic2.setAlignment(Pos.CENTER);
						 //statistic2.setOrientation(Orientation.VERTICAL);
						 //statistic2.setMar	
						 
						 statistic2.setHgap(10);
						 statistic2.setHgap(40);
						 String query="Select * from stats where Username='"+User+"'";
					     ResultSet res;
						 res = view.stmt.executeQuery(query);
						 selected=list2.getSelectionModel().getSelectedItems();
						 
						 for(String m:selected){
							 System.out.println(m);
						 }
						 //System.out.println(res.getInt(1));
						  res.next();
						  if(selected.contains("Patience"))
						  {
							  Label patience=new Label("Patience");
							 
							  Label nbPl=new Label(" Total Games Played : ");
							  
							  Label wins=new Label("Total wins : ");
							  Label r=new Label("Rate : ");
							  Label pl=new Label(""+res.getInt("PatienceNb"));
							  Label w=new Label(""+res.getInt("PatienceWinRate"));
							  
							  patience.setStyle("-fx-font-size: 35px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  nbPl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  w.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  pl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  r.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  double winRate;
							  if (res.getInt("PatienceNb")==0)
								  winRate =0;
							  else winRate = ((double)res.getInt("PatienceWinRate")/res.getInt("PatienceNb"))*100;
							  
							  Label rr=new Label(""+ winRate +" %");
							  
							  wins.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  rr.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  FlowPane P1 = new FlowPane();
							  P1.setOrientation(Orientation.VERTICAL);
							  P1.getChildren().addAll(patience,nbPl,pl,wins,w,r,rr);
							  statistic2.getChildren().add(P1);
						  }
							  
						  
						  if(selected.contains("Reasoning"))
						  {
							  Label patience=new Label("Reasoning");
							  Label nbPl=new Label(" Total Games Played : ");
							  Label wins=new Label("Total wins : ");
							  Label r=new Label("Rate : ");
							  
							  Label pl=new Label(""+res.getInt("ReasoningNb"));
							  Label w=new Label(""+res.getInt("ReasoningWinRate"));
							  
							  patience.setStyle("-fx-font-size: 35px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  nbPl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  w.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  pl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  r.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  double winRate;
							  if (res.getInt("ReasoningNb")==0)
								  winRate =0;
							  else winRate = ((double)res.getInt("ReasoningWinRate")/res.getInt("ReasoningNb"))*100;
							  Label rr=new Label(""+ winRate +" %");
							  wins.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  rr.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  FlowPane P1 = new FlowPane();
							  P1.setOrientation(Orientation.VERTICAL);
							  P1.getChildren().addAll(patience,nbPl,pl,wins,w,r,rr);
							  statistic2.getChildren().addAll(P1);
						  }
						  
						  if(selected.contains("Reflex"))
						  {
							  Label patience=new Label("Reflex");
							  Label nbPl=new Label(" Total Games Played : ");
							  Label wins=new Label("Total wins : ");
							  Label r=new Label("Rate : ");
							  
							 
							  Label pl=new Label(""+res.getInt("ReflexNb"));
							  Label w=new Label(""+res.getInt("ReflexWinRate"));
							  patience.setStyle("-fx-font-size: 35px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  nbPl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  w.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  pl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  r.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  double winRate;
							  if (res.getInt("ReflexNb")==0)
								  winRate =0;
							  else winRate = ((double)res.getInt("ReflexWinRate")/res.getInt("ReflexNb"))*100;
							  Label rr=new Label(""+ winRate +" %");
							  wins.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  rr.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  FlowPane P1 = new FlowPane();
							  P1.setOrientation(Orientation.VERTICAL);
							  P1.getChildren().addAll(patience,nbPl,pl,wins,w,r,rr);
							  statistic2.getChildren().addAll(P1);
						  
						  }
						  
						  if(selected.contains("Awarness"))
						  {
							  Label patience=new Label("Awarness");
							  Label nbPl=new Label(" Total Games Played : ");
							  Label wins=new Label("Total wins : ");
							  Label r=new Label("Rate : ");
							  Label pl=new Label(""+res.getInt("AwarnessNb"));
							  Label w=new Label(""+res.getInt("AwarnessWinRate"));
							  
							  patience.setStyle("-fx-font-size: 35px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  nbPl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  w.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  pl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  r.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  double winRate;
							  if (res.getInt("AwarnessNb")==0)
								  winRate =0;
							  else winRate = ((double)res.getInt("AwarnessWinRate")/res.getInt("AwarnessNb"))*100;
							  Label rr=new Label(""+ winRate +" %");
							  wins.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  rr.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  FlowPane P1 = new FlowPane();
							  P1.setOrientation(Orientation.VERTICAL);
							  P1.getChildren().addAll(patience,nbPl,pl,wins,w,r,rr);
							  statistic2.getChildren().addAll(P1);
						  
						  }
						  
						  if(selected.contains("Adaptability"))
						  {
							  Label patience=new Label("Adaptability");
							  Label nbPl=new Label(" Total Games Played : ");
							  Label wins=new Label("Total wins : ");
							  Label r=new Label("Rate : ");
							  
							  Label pl=new Label(""+res.getInt("AdaptabilityNb"));
							  Label w=new Label(""+res.getInt("AdaptabilityWinRate"));
							  
							  patience.setStyle("-fx-font-size: 35px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  nbPl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  w.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  pl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  r.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  double winRate;
							  if (res.getInt("AdaptabilityNb")==0)
								  winRate =0;
							  else winRate = ((double)res.getInt("AdaptabilityWinRate")/res.getInt("AdaptabilityNb"))*100;
							  Label rr=new Label(""+ winRate +" %");
							  wins.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  rr.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  statistic2.getChildren().addAll(patience,nbPl,pl,wins,w,r,rr);
							  FlowPane P1 = new FlowPane();
							  P1.setOrientation(Orientation.VERTICAL);
							  P1.getChildren().addAll(patience,nbPl,pl,wins,w,r,rr);
							  statistic2.getChildren().addAll(P1);
						  }
						  if(selected.contains("Precision"))
						  {
							  Label patience=new Label("Precision");
							  Label nbPl=new Label(" Total Games Played : ");
							  Label wins=new Label("Total wins : ");
							  Label r=new Label("Rate : ");
							  Label pl=new Label(""+res.getInt("PrecisionNb"));
							  Label w=new Label(""+res.getInt("PrecisionWinRate"));
							  double winRate;
							  
							  patience.setStyle("-fx-font-size: 35px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  nbPl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  w.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  pl.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							  r.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  
							 
							  
							  if (res.getInt("PrecisionNb")==0)
								  winRate =0;
							  else winRate = ((double)res.getInt("PrecisionWinRate")/res.getInt("PrecisionNb"))*100;
							  Label rr=new Label(""+ winRate +" %");
							  wins.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  rr.setStyle("-fx-font-size: 25px;"
										+"-fx-font-family:\"Comic Sans ms\";"
										+"-fx-text-fill: White;;"
					                    +"-fx-font-weight: bold;"
					                    +"-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
							  FlowPane P1 = new FlowPane();
							  P1.setOrientation(Orientation.VERTICAL);
							  P1.getChildren().addAll(patience,nbPl,pl,wins,w,r,rr);
							  statistic2.getChildren().addAll(P1);
							
						  }
						  statistic2.setStyle("-fx-background-color: red;");
						  Scene p=new Scene(statistic2,700,700);
						 // p.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							view.primaryStage.setScene(p);
							Button goback=new Button("Go back ! ");
							statistic2.getChildren().add(goback);
							
							  goback.setStyle("-fx-background-color:yellow ; -fx-text-fill: white;"
						  				+ "   -fx-background-radius: 100;\r\n" + 
						  				"   -fx-font-weight: bold;"
						  				+"  -fx-font-size:30px;");
							goback.setOnAction(e5->{
								Main.MainPage(primaryStage, User);
							});
							
							
							
							view.primaryStage.setFullScreen(true);
							view.primaryStage.show();
						  
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					
					
				});
				

		     
		 statistic.getChildren().add(vi);
		statistic.getChildren().add(list2);
		view.primaryStage.setScene(ss);
		view.primaryStage.setFullScreen(true);
		view.primaryStage.show();
			
		});
	
		view.primaryStage.setScene(view.scene5);
		view.primaryStage.setFullScreen(true);
		view.primaryStage.show();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		view=new View(primaryStage);
		primaryStage.setFullScreen(true);
		view.getFonctionality();
		
		Model model=new Model(view);
		model.doStuff();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
