package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class RealTimeUpdateApp extends Application {

    private ScheduledExecutorService executorService;
    private Label dataLabel;

    @Override
    public void start(Stage primaryStage) {
        dataLabel = new Label("Data: Loading...");

        StackPane root = new StackPane();
        root.getChildren().add(dataLabel);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Real-time Update App");
        primaryStage.setScene(scene);
        primaryStage.show();

        executorService = Executors.newScheduledThreadPool(1);

        // Schedule a task to update data every 5 seconds
        executorService.scheduleAtFixedRate(this::updateDataFromDatabase, 0, 5, TimeUnit.SECONDS);
    }

    private void updateDataFromDatabase() {
    	
        try {
        	
            // Kết nối đến cơ sở dữ liệu MySQL (sử dụng XAMPP)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/real_time_update_db", "root", "");

            // Thực hiện truy vấn để lấy dữ liệu từ bảng mô phỏng
            String query = "SELECT data FROM sample_table";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Lấy dữ liệu từ cơ sở dữ liệu
                String newData = resultSet.getString("data");

                // Update JavaFX UI on the JavaFX Application Thread
                Platform.runLater(() -> dataLabel.setText("Data: " + newData));
            }

            // Đóng các resource
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Dừng ScheduledExecutorService khi ứng dụng đóng
        executorService.shutdown();
    }

    public static void main(String[] args) {
        // Đăng ký driver JDBC
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Chạy ứng dụng
        launch(args);
    }
}

