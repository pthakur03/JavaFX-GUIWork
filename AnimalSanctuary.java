import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
/**
 * Animal Sanctuary to hold all the cute animals.
 * @author Parth Thakur
 * @version 1.0
 */
public class AnimalSanctuary extends Application {
    private TilePane enclosure;
    /**
     * Method to arrange the sanctuary as desired.
     * @param animalName The name of the animal
     * @param animalHealth The health, ranging from 1-5
     * @param animal Animal object
     */
    public void enclosureArrangement(String animalName, String animalHealth, Animal animal) {
        for (int i = 0; i < 6; i++) {
            StackPane inside = (StackPane) enclosure.getChildren().get(i);
            Label contain;
            if (inside.getChildren().get(1) instanceof Label) {
                contain = (Label) inside.getChildren().get(1);
            } else {
                VBox rectBox = (VBox) inside.getChildren().get(1);
                contain = (Label) rectBox.getChildren().get(0);
            }
            if (contain.getText().equals("Empty")) {
                Label name = new Label(animalName);
                Label health = new Label(animalHealth);
                Label anim = new Label(animal.toString());
                VBox container = new VBox();
                container.setAlignment(Pos.CENTER);
                container.getChildren().addAll(name, health, anim);
                StackPane z = new StackPane();
                Rectangle rect = new Rectangle(90, 90);
                rect.setFill(Color.LIGHTGRAY);
                z.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        Rectangle r = new Rectangle(90, 90);
                        r.setFill(Color.WHITE);
                        z.getChildren().clear();
                        z.getChildren().addAll(r, new Label("Empty"));
                    }
                });
                z.getChildren().addAll(rect, container);
                enclosure.getChildren().set(i, z);
                return;
            }
        }
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("There is no more room!");
        alert.show();
    }
    /**
     * Starts the scene.
     * @param primaryStage start main stage in application.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Animal Sanctuary");
        StackPane wall = new StackPane();
        BorderPane mainpane = new BorderPane();
        enclosure = new TilePane();
        enclosure.setVgap(8);
        enclosure.setHgap(8);
        enclosure.setAlignment(Pos.CENTER);
        enclosure.setMaxWidth(300);
        for (int i = 0; i < 6; i++) {
            StackPane gridStack = new StackPane();
            Rectangle rect = new Rectangle(90, 90);
            rect.setFill(Color.WHITE);
            Label label = new Label("Empty");
            gridStack.getChildren().addAll(rect, label);
            enclosure.getChildren().add(gridStack);
        }
        StackPane barcontainer = new StackPane();
        Rectangle holdingRectangle = new Rectangle(barcontainer.getWidth() * .75, 60);
        holdingRectangle.setFill(Color.WHITE);
        HBox bottomBar = new HBox();
        bottomBar.setSpacing(8);
        VBox left = new VBox();
        TextField inputName = new TextField();
        left.getChildren().add(new Label("Name"));
        left.getChildren().add(inputName);
        VBox middle = new VBox();
        TextField inputHealth = new TextField();
        middle.getChildren().add(new Label("Health"));
        middle.getChildren().add(inputHealth);
        VBox right = new VBox();
        ChoiceBox<Animal> choices = new ChoiceBox(FXCollections.observableArrayList(Animal.DOG, Animal.CAT,
                        Animal.BIRD, Animal.LION, Animal.SQUIRREL, Animal.TIGER));
        right.getChildren().add(new Label("Pick an Animal"));
        right.getChildren().add(choices);
        Button submit = new Button("Insert Animal");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent a) {
                String n = inputName.getText();
                String h = inputHealth.getText();
                if (n == null || n.isEmpty()) {
                    n = "No Name Yet";
                }
                try {
                    if (Integer.parseInt(h) < 1 || Integer.parseInt(h) > 5 || h.isEmpty()) {
                        h = "5";
                    }
                } catch (NumberFormatException z) {
                    h = "5";
                }
                Animal listSelected = choices.getSelectionModel().getSelectedItem();
                enclosureArrangement(n, h, listSelected);
                inputHealth.clear();
                inputName.clear();
                choices.getSelectionModel().clearSelection();
            }
        });
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.getChildren().addAll(left, middle, right, submit);
        barcontainer.getChildren().addAll(holdingRectangle, bottomBar);
        primaryStage.setMinHeight(550);
        primaryStage.setMaxHeight(550);
        primaryStage.setMinWidth(700);
        primaryStage.setMaxWidth(700);
        Image background = new Image("animalImage.jpg");
        ImageView bgContainer = new ImageView(background);
        wall.getChildren().add(bgContainer);
        mainpane.setCenter(enclosure);
        mainpane.setBottom(barcontainer);
        wall.getChildren().add(mainpane);
        //configure
        primaryStage.setScene(new Scene(wall));
        primaryStage.show();
    }
    /**
     * Main method.
     * @param args Args.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
