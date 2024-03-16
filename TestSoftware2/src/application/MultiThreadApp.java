package application;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiThreadApp extends Application{

	public void start(Stage primaryStage) {
		VBox root = new VBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		//Tạo thanh tiến trình
		ProgressBar progressBar = new ProgressBar(0);
		root.getChildren().add(progressBar);
		//Tạo task để thực hiện công viện
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {
				for (int i=0; i<=100; i++) {
					final int progressValue = i;
					//Cập nhật giá trị của thanh tiến trình trong luồng UI
					updateProgress(progressValue, 100);
					Thread.sleep(100); //Tạm dừng 100ms để mô phỏng công việc
				}
				return null;
			}
		};
		//Gán giá trị của thanh tiến trình với giá trị của task
		progressBar.progressProperty().bind(task.progressProperty());
		//Khởi chạy luồng phụ
		Thread thread = new Thread(task);
		thread.setDaemon(true); //Đặt luồng phụ thành daemon để tự động kết thúc khi chương trình chính kết thúc
		thread.start();
		//Thiết lập scene và hiện thị stage
		Scene scene = new Scene(root, 300, 200);
		primaryStage.setTitle("Real Time Multithreading App");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);

	}

}
