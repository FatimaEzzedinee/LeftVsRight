package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;

public class Model {
	View view=null;
	
	Model(View v)
	{
		this.view=v;
	}
	
    public void doStuff()
	{
    	view.primaryStage.setFullScreen(true);
    	view.primaryStage.setTitle("Home Pages");
    	view.su.setOnAction(e->{
			
    	     view.maleRadio.setToggleGroup(view.groupGender); 
		     view.femaleRadio.setToggleGroup(view.groupGender); 
		     
		     //System.out.println("su 1");
		     //Label for date of birth 
			view.root3.getChildren().add(view.n);
			view.root3.getChildren().add(view.na);
			
			 //System.out.println("su 2");
			view.root3.getChildren().add(view.l33);
			view.root3.getChildren().add(view.t1);
			view.root3.getChildren().add(view.le);
			
			 //System.out.println("su 3");
			view.root3.getChildren().add(view.t13);
			view.root3.getChildren().add(view.l43);
			view.root3.getChildren().add(view.t2);
			
			// System.out.println("su 4");
			view.root3.setOrientation(Orientation.VERTICAL);
			view.root3.setAlignment(Pos.CENTER);
			view.root3.getChildren().add(view.gender);
			view.root3.getChildren().add(view.maleRadio);
			
			 System.out.println("su 5");
			view.root3.getChildren().add(view.femaleRadio);
			view.root3.getChildren().add(view.dobLabel);
			view.root3.getChildren().add(view.datePicker);
			view.root3.getChildren().add(view.b2);
			
			// System.out.println("su 6");
			view.scene3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			view.primaryStage.setScene(view.scene3);
			view.primaryStage.setFullScreen(true);
			view.primaryStage.show();

		});

		view.b2.setOnAction(e->{
			
			String nam=view.na.getText();
			System.out.println(nam);
			String usr=view.t1.getText();
			System.out.println(usr);
			String em=view.t13.getText();
			System.out.println(em);
			String p=view.t2.getText();
			System.out.println(p);
			try {
				ResultSet res;
				res = view.stmt.executeQuery("Select * from users");
				
				while(res.next())
				{
					if (res.getString("Username").equals(view.t1.getText()))
					{
						System.out.println("Username already exist Choose another username");
						view.UserExist=true;
					}
			if(view.UserExist==true)
			{
			System.out.println("Sign up Not successful ");
				
			}
			else
				{
				 String sql = "INSERT INTO users(Name,Email,Username,Password,Gender,DateOfBirth) VALUES(?,?,?,?,?,?)";
				 
			                PreparedStatement pstmt = view.MyConn.prepareStatement(sql);
			            pstmt.setString(1,nam);
			            pstmt.setString(2,em);
			            pstmt.setString(3,usr);
			            pstmt.setString(4,p);
			            if(view.femaleRadio.isSelected())
			            pstmt.setString(5,"female");
			            else {
			            	 pstmt.setString(5,"male");
			            }
			            java.util.Date date = java.util.Date.from(view.datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			            pstmt.setDate(6,sqlDate);
			            pstmt.executeUpdate();
			            view.currLogedIn=usr;
			            
			            
			            String  sql2="Insert into stats values(?,0,0,0,0,0,0,0,0,0,0,0,0)";
			            pstmt=view.MyConn.prepareStatement(sql2);
			            pstmt.setString(1,usr);
			            pstmt.executeUpdate();
			            view.primaryStage.setFullScreen(true);
						Main.MainPage(view.primaryStage,view.currLogedIn);
			            break;    
				}
				}
				view.UserExist=false;
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		);


    
		view.si.setOnAction(e->{

			view.root2.getChildren().add(view.l3);
			view.root2.getChildren().add(view.t1);
		
			view.root2.getChildren().add(view.l4);
			view.root2.getChildren().add(view.t2);
		
			view.root2.setOrientation(Orientation.VERTICAL);
			view.root2.setAlignment(Pos.CENTER);
			view.root2.getChildren().add(view.b);
			view.scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			view.primaryStage.setScene(view.scene2);
			view.primaryStage.setFullScreen(true);
			view.primaryStage.show();
		});
	
	
		view.b.setOnAction(e->{
		ResultSet res;
		
		try {
			res = view.stmt.executeQuery("Select * from users");
			
			while(res.next())
			{

				if (res.getString("Username").equals(view.t1.getText()))
				{
					System.out.println("Username found");
				
				if(res.getString("Password").equals(view.t2.getText()))
						{
					        view.UserExist = true;
							System.out.println(view.UserExist);
							break;
						}
				else System.out.println("Wrong password");

			    }
				else System.out.println("Username not found");
			}
			
		if(view.UserExist==true)
		{
			System.out.println("Sign in successful");
			view.currLogedIn=res.getString("Username");
			view.primaryStage.setFullScreen(true);
			Main.MainPage(view.primaryStage,view.currLogedIn);
			view.UserExist=false;
		}
		
		else System.out.println("Not logged in");
		
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
	});

}
	
}
