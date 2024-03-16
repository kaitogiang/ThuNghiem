package application;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;

public class ButtonCell extends TableCell<Person, Void>{
	private final Button btn = new Button("Gửi");
	
	public ButtonCell() {
		btn.setOnAction(event -> {
			Person person = getTableView().getItems().get(getIndex());
			person.statusProperty().set("Đã gửi");
		});
	}
	
	protected void updateItem(Void item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
		} else {
			setGraphic(btn);
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		}
	}
}
