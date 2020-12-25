package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Controller {
    private enum size {
        ExtraSmall(1),
        SMALL(3),
        MEDIUM(5),
        LARGE(7),
        ExtraLarge(9);
        private final int radius;
        size(int radius) {this.radius = radius;}
        public int getRadius() {return radius;}
    };
    @FXML private RadioButton smalrb;
    @FXML private RadioButton extrasmallrb;
    @FXML private RadioButton mediumrb;
    @FXML private RadioButton largerb;
    @FXML private RadioButton extralargerb;
    @FXML private Pane drawingAreaPane;
    @FXML private ToggleGroup colorToggleGroup;
    @FXML private ToggleGroup sizeToggleGroup;
    @FXML private Slider redslider;
    @FXML private Slider greenslider;
    @FXML private Slider blueslider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private Rectangle colRectangle;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private size radius = size.MEDIUM;
    private Paint brushColor = Color.BLACK;
    public void initialize() {
        smalrb.setUserData(size.SMALL);
        mediumrb.setUserData(size.MEDIUM);
        largerb.setUserData(size.LARGE);
        extralargerb.setUserData(size.ExtraLarge);
        extrasmallrb.setUserData(size.ExtraSmall);
        redTextField.textProperty().bind(
                redslider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(
                greenslider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(
                blueslider.valueProperty().asString("%.0f"));
        redslider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        red = newValue.intValue();
                        colRectangle.setFill(Color.rgb(red, green, blue));
                        brushColor = Color.rgb(red, green, blue);
                    }
                }
        );
        greenslider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        green = newValue.intValue();
                        colRectangle.setFill(Color.rgb(red, green, blue));
                        brushColor = Color.rgb(red, green, blue);
                    }
                }
        );
        blueslider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        blue = newValue.intValue();
                        colRectangle.setFill(Color.rgb(red, green, blue));
                        brushColor = Color.rgb(red, green, blue);
                    }
                }
        );
    }
    @FXML
    private void drawing(MouseEvent e) {
        Circle newCircle = new Circle(e.getX(), e.getY(),
                radius.getRadius(), brushColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
    @FXML
    private void colorRadioButtonSelected(ActionEvent e) {
        brushColor =
                (Color) colorToggleGroup.getSelectedToggle().getUserData();
    }
    @FXML
    private void RBsize(ActionEvent e) {
        radius =
                (size) sizeToggleGroup.getSelectedToggle().getUserData();
    }
    @FXML
    private void undobutton(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();
        if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }
    @FXML
    private void clearbutton(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}