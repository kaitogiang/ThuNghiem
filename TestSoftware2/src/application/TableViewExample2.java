package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewExample2 extends Application {

	private ObservableList<Person> data = FXCollections.observableArrayList(
            new Person("example@email.com", "Chưa gửi"),
            new Person("another@example.com", "Chưa gửi"),
            new Person("test@email.com", "Chưa gửi")
    );
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage stage) {
		stage.setTitle("TableView Example");
		
		TableView<Person> tableView = new TableView<>();
		tableView.setItems(data);
		
		TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		
		TableColumn<Person, String> statusColumn = new TableColumn<>("Trạng thái");
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		
		TableColumn<Person, Void> actionColumn = new TableColumn<>("Hành động");
		actionColumn.setCellFactory(param -> new ButtonCell());
		
		tableView.getColumns().addAll(emailColumn, statusColumn, actionColumn);
		
		VBox vbox = new VBox(tableView);
		Scene scene = new Scene(vbox, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

}

