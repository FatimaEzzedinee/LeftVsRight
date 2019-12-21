package application;

import javafx.stage.Stage;

public interface  TheVisitable {

	public  void accept(Stage s,TheVisitor v);
	
}
