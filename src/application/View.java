package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class View {
	FlowPane root = new FlowPane();
	Scene scene = new Scene(root,700,700);
	
	public Button g1,g2,g3,g4,g6,b,b2,su,si,stats;
	public boolean UserExist =false;
	public String currLogedIn=null;
	public PasswordField t2= null;
	public TextField t1=null,na=null,t13=null;
	
	 public DatePicker datePicker=null;
	 public ToggleGroup groupGender = null; 
     public RadioButton femaleRadio = null;
     Connection MyConn=null;
	 Statement stmt =null;
	 
	 FlowPane root3=null;
	 Scene scene3 = null;

		Label n=null;
		Label le=null;
		Label l33=null;
		RadioButton maleRadio =null;
		Label l43= null;
		Text dobLabel =null;
		Label gender=null;
		
		FlowPane root2=null;
		Scene scene2 = null;
		Label l3= null;
		Label l4= null;
		
        Label l1,l2;
     
        FlowPane root6 = null;
		Scene scene5 =null;
     
        Stage primaryStage=null;
        
     
     View(Stage p)
     {
    	 
    	 System.out.println("View");
    	this.primaryStage=p; 
     }
    	 
    	public void getFonctionality()
    	{
    		stats=new Button("View records");
    		
    	//	System.out.println("Fonctionality 1");
    	 root6 = new FlowPane();
    	 scene5 = new Scene(root6,700,700);
    	 scene5.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	 l3= new Label("Username :");
 		 l4= new Label("Password :");
 		 
 		 root2=new FlowPane();
 		 scene2 = new Scene(root2,700,700);
 		scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 		//System.out.println("Fonctionality 2");
    	
    	 dobLabel = new Text("Date of birth"); 
    	 maleRadio = new RadioButton("Male"); 
    	 le=new Label("Email");
    	 n=new Label("Name : ");
    	 l33= new Label("Username :");
    	 l43= new Label("Password :");
    	 gender=new Label("Gender :");
 		
    	 
    	// System.out.println("Fonctionality 3");
    	 root3=new FlowPane();
    	 scene3 = new Scene(root3,700,700);
    	 scene3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	     t1= new TextField("");
    	     t2=new PasswordField();
    		 na= new TextField("");
    		 t13= new TextField("");
	
    		 b = new Button("Sign in");
    		 b2=new Button("Sign up");
	
    		 datePicker = new DatePicker(); 
	  
    		 groupGender = new ToggleGroup(); 
    		// femaleRadio=(RadioButton) groupGender.getSelectedToggle();
    		 femaleRadio = new RadioButton("Female"); 
	
    		 g1=new Button("Freeze");
    		 g2=new Button("FillShapes");
    		 g3=new Button("HowManyMoved");
    		 g4=new Button("Match");
    		 g6=new Button("PayBill");
    		 
    		// System.out.println("Fonctionality 4");
	  
    		 l1= new Label(" Already Have an account ?");
    		 l2=new Label("Don't have an account ?");
		
	   si=new Button("Sign in");
	   su=new Button("Sign up");
	   
	 //  System.out.println("Fonctionality 5");
	    MyConn=new DBconnect().getConn();
		 try {
			stmt = MyConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		// System.out.println("Fonctionality 6");
		root.getChildren().addAll(l1,si,l2,su);
		root.setOrientation(Orientation.VERTICAL);
		root.setAlignment(Pos.CENTER);
		//System.out.println("Fonctionality 7");
		
		//root.setStyle("-fx-background-image: url(\"teacher.jpg\")");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		
		//scene.setUserAgentStylesheet("-fx-background-image: url ('C:\\Users\\SILICIUM\\eclipse-workspace\\GUIPROJECT\\src\\application\\unnamed.png' )");
		//System.out.println("Fonctionality 8");
		primaryStage.show();
		
     }
    	
   
}
