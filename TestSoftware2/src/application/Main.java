package application;
	
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			//Tạo Label để hiện thị thời gian
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Label timeLabel = new Label();
			timeLabel.setStyle("-fx-font-size: 48px");
			root.setCenter(timeLabel);
			//Tạo timeline để cập nhật thời gian mỗi giây
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					Date now = new Date();
					timeLabel.setText(sdf.format(now));
				}
			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
