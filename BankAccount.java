import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
/**
 * Creates a class BankAccount that extends Application.
 * @author Parth Thakur
 * @version 1
 */
public class BankAccount extends Application {
    @Override
    /**
     * Creates a method to initiliaze and launch application.
     * @param primaryStage Primary Stage of the application window
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Account");
        primaryStage.setMinHeight(500);
        StackPane root = new StackPane();
        StackPane three = new StackPane();
        AnchorPane two = new AnchorPane();

        Label balance = new Label("$1738");

        Rectangle rectangle1 = new Rectangle(200, 100);
        rectangle1.setFill(Color.RED);

        Circle circle1 = new Circle(50);
        circle1.setFill(Color.GREEN);

        Image img = new Image("car.png");
        ImageView imgview = new ImageView(img);

        three.getChildren().add(circle1);
        three.getChildren().add(balance);

        two.getChildren().add(three);

        AnchorPane.setTopAnchor(three, 8.0);
        AnchorPane.setRightAnchor(three, 8.0);

        root.getChildren().add(imgview);
        root.getChildren().add(rectangle1);
        root.getChildren().add(two);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

