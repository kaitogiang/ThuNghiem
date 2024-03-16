package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXDatabaseExample extends Application {

    private StringProperty dataProperty = new SimpleStringProperty();

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        primaryStage.setTitle("JavaFX Database Example");

        // Kết nối với cơ sở dữ liệu MySQL
        connectToDatabase();

        // Tạo giao diện người dùng
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        Label dataLabel = new Label("Data from Database:");
        TextField dataTextField = new TextField();

        // Binding giữa TextField và StringProperty
        dataTextField.textProperty().bindBidirectional(dataProperty);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> fetchDataFromDatabase());

        root.getChildren().addAll(dataLabel, dataTextField, refreshButton);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);

        primaryStage.show();

        // Mô phỏng việc thay đổi dữ liệu trong cơ sở dữ liệu
        fetchDataFromDatabase();
    }

    private void connectToDatabase() throws ClassNotFoundException {
        // Thay đổi các thông số kết nối tương ứng với cơ sở dữ liệu của bạn
        String url = "jdbc:mysql://localhost:3306/testgame";
        String username = "root";
        String password = "";

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            // Thực hiện các thao tác khác với cơ sở dữ liệu nếu cần
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchDataFromDatabase() {
        String query = "SELECT name FROM data_table WHERE status = 'Active'";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testgame", "root", "");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            // Lấy dữ liệu từ cơ sở dữ liệu và cập nhật StringProperty
            StringBuilder newData = new StringBuilder();
            while (resultSet.next()) {
                newData.append(resultSet.getString("name")).append("\n");
            }

            dataProperty.set(newData.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
