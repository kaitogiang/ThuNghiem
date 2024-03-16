package application;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObservableListExample {
    public static void main(String[] args) {
        // Tạo một ObservableList
        ObservableList<String> observableList = FXCollections.observableArrayList();

        // Thêm một ListChangeListener để theo dõi sự thay đổi
        observableList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("Các phần tử đã được thêm: " + change.getAddedSubList());
                    }
                    if (change.wasRemoved()) {
                        System.out.println("Các phần tử đã bị xóa: " + change.getRemoved());
                    }
                    // Các sự kiện khác có thể được xử lý tương tự
                }
            }
        });

        // Gán giá trị cho ObservableList
        observableList.addAll("A", "B", "C");

        // Thay đổi giá trị của ObservableList
        observableList.set(1, "X");

        // In ra sự thay đổi
        // Kết quả mong đợi: Các phần tử đã được thêm: [X]

        // Tạo một ObservableList mới
        ObservableList<String> temp = FXCollections.observableArrayList("OMG", "Kamen Gaiba");

        // Thêm một ListChangeListener để theo dõi sự thay đổi trong ObservableList mới
        temp.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("Các phần tử mới đã được thêm: " + change.getAddedSubList());
                    }
                    if (change.wasRemoved()) {
                        System.out.println("Các phần tử mới đã bị xóa: " + change.getRemoved());
                    }
                    // Các sự kiện khác có thể được xử lý tương tự
                }
            }
        });

        // Gán giá trị mới cho ObservableList
        observableList = temp;

        // Thay đổi giá trị trong ObservableList mới
        observableList.add("New Element");

        // In ra sự thay đổi trong ObservableList mới
        // Kết quả mong đợi: Các phần tử mới đã được thêm: [New Element]
    }
}
