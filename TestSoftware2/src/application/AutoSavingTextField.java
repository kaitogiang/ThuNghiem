package application;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AutoSavingTextField extends Application{

	public void start(Stage primaryStage) {
		HBox container = new HBox();
		TextField tf = new TextField();
		container.getChildren().add(tf);
		container.setAlignment(Pos.CENTER);
		Scene scene = new Scene(container, 400, 400);
		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Đã nhập: "+observable);
			saveToDatabase(newValue);
			
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void saveToDatabase(String data) {
		System.out.println(data + " have been saved succesfully");
	}

}
