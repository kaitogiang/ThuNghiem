package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewExample extends Application{

	public void start(Stage primaryStage) {
		TableView<Person> tableView = new TableView<>();
		ObservableList<Person> data = FXCollections.observableArrayList(
			new Person("john.doe@example.com", "Chưa gửi"),
			new Person("jane.doe@example.com", "Chưa gửi"),
			new Person("bob.doe@example.com", "Chưa gửi")
		);
		
		TableColumn<Person, String> emailCol = new TableColumn<>("Email");
		emailCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEmail()));
		
		TableColumn<Person, String> statusCol = new TableColumn<>("Trạng thái");
		statusCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStatus()));
		
		TableColumn<Person, Void> actionCol = new TableColumn<>("Hành động");
		actionCol.setCellFactory(param -> new TableCell<>() {
			final Button sendButton = new Button("Gửi");
			{
				sendButton.setOnAction(event -> {
					Person person = getTableView().getItems().get(getIndex());
					person.setStatus("Đã gửi");
					getTableView().refresh();
				});
			}
			
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(sendButton);
				}
			}
		});
		
		tableView.getColumns().addAll(emailCol, statusCol, actionCol);
		tableView.setItems(data);
		
		StackPane root = new StackPane();
		root.getChildren().add(tableView);
		
		Scene scene = new Scene(root, 400, 300);
		
		primaryStage.setTitle("TableView Example");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}


