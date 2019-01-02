import java.util.Optional;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
* @author Gabriel Wang
* @version 2.0
* @since 11/28/18
**/
public class OfficeHourQueue extends Application {

    private LinkedQueue<String> studentQueue = new LinkedQueue();
    private int current = 0;
    private int max = 0;
    /**
    * Shows TextInputDialog once dequeue button is pressed
    **/
    private void showInputDialog() {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Privilege Check");
        td.setHeaderText("Confirmation");
        td.setContentText("Please enter password: ");
        Optional<String> result = td.showAndWait();

        result.ifPresent(name -> {
                if (name.equals("CS1321R0X")) {
                    studentQueue.remove(0);
                }
            });
    }
    /**
    * Starts the scene
    * @param stage Stage for javafx
    **/
    @Override
    public void start(Stage stage) {
        Text text = new Text();
        text.setText("Current number of students in queue: " + current
            + "\n Historical maximum students in queue: " + max);

        ListView<String> listView = new ListView<String>(studentQueue);

        Button addButton = new Button();
        addButton.setText("Add Student");

        Button dequeueButton = new Button();
        dequeueButton.setText("Dequeue Student");

        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
                if (inputField.getText().trim().length() > 0) {
                    studentQueue.add(inputField.getText());
                }
                inputField.setText("");

                current  = studentQueue.size();
                if (current > max) {
                    max = current;
                }
                text.setText("Current number of students in queue: " + current
                    + "\n Historical maximum students in queue: " + max);
                inputField.requestFocus();
            });

        dequeueButton.setOnAction(e -> {
                showInputDialog();
                current  = studentQueue.size();
                text.setText("Current number of students in queue: " + current
                    + "\n Historical maximum students in queue: " + max);
                inputField.requestFocus();
            });

        addButton.disableProperty()
            .bind(Bindings.isEmpty(inputField.textProperty()));

        HBox entryBox = new HBox();
        entryBox.getChildren().addAll(inputField, addButton, dequeueButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(text, listView, entryBox);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("CS 1321 Office Hours Queue");
        stage.show();
    }

    /**
    * Hi
    * @param args array
    **/
    public static void main(String[] args) {
        launch(args);
    }

}




