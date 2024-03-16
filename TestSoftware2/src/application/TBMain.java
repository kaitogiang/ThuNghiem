package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TBMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView<String> tableView = new TableView<>();

        TableColumn<String, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

        TableColumn<String, String> actionColumn = new TableColumn<>("Hành động");
        actionColumn.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override
            public TableCell<String, String> call(TableColumn<String, String> param) {
                return new TableCell<String, String>() {
                    final Button btn = new Button("Gửi");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> System.out.println("Gửi email tới: " + getTableView().getItems().get(getIndex())));
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        });

        tableView.getColumns().add(emailColumn);
        tableView.getColumns().add(actionColumn);

        // Thêm dữ liệu mô phỏng vào TableView
        tableView.getItems().add("example1@example.com");
        tableView.getItems().add("example2@example.com");
        tableView.getItems().add("example3@example.com");

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 200, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
