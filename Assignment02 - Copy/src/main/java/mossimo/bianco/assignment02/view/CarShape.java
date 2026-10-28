package mossimo.bianco.assignment02.view;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import mossimo.bianco.assignment02.model.Car;
import mossimo.bianco.assignment02.model.CarType;

/**
 * Draws one car using JavaFX shapes only: Polygon objects for the body and the
 * roof, Rectangle objects for the windows, two Circle objects for the wheels
 * (each with a spoke line so the rotation is visible) and a Text object for
 * the car number. All the shapes are children of this Group, so they always
 * move together.
 *
 * <p>The car faces right. Its local origin is the top-left corner, and its
 * front bumper is at x = LENGTH.</p>
 *
 * @author Mossimo Bianco
 */
public class CarShape extends Group {

    /** Length of the car from rear bumper to front bumper. */
    public static final double LENGTH = 120;
    /** Height of the car including the wheels. */
    public static final double HEIGHT = 52;
    /** Radius of each wheel. */
    public static final double WHEEL_RADIUS = 10;

    private static final double BODY_TOP = 18;
    private static final double WHEEL_Y = 40;
    private static final double REAR_WHEEL_X = 28;
    private static final double FRONT_WHEEL_X = 92;

    private final Car car;
    private final Group rearWheel;
    private final Group frontWheel;

    /**
     * Builds the shapes for the given car.
     *
     * @param car the model car this shape represents
     */
    public CarShape(Car car) {
        this.car = car;
        Color paint = car.getColor();

        // Roof: a different profile for each car type
        double[] roofPoints = getRoofPoints(car.getType());
        Polygon roof = new Polygon(roofPoints);
        roof.setFill(paint.darker());
        roof.setStroke(Color.BLACK);

        // Windows: two rectangles that fit under the flat top of the roof
        double roofLeft = roofPoints[2];
        double roofTop = roofPoints[3];
        double roofRight = roofPoints[4];
        double gap = 4;
        double windowY = roofTop + 2;
        double windowHeight = BODY_TOP - roofTop - 4;
        double windowWidth = (roofRight - roofLeft - gap) / 2;
        Rectangle rearWindow = createWindow(roofLeft, windowY, windowWidth, windowHeight);
        Rectangle frontWindow = createWindow(roofLeft + windowWidth + gap,
                windowY, windowWidth, windowHeight);

        // Body: lower part of the car, sloped hood at the front
        Polygon body = new Polygon(
                0, WHEEL_Y,
                0, 24,
                8, BODY_TOP,
                104, BODY_TOP,
                LENGTH, 27,
                LENGTH, WHEEL_Y);
        body.setFill(paint);
        body.setStroke(Color.BLACK);

        Circle headlight = new Circle(LENGTH - 4, 25, 2.5, Color.web("#fff59d"));
        Rectangle taillight = new Rectangle(0, 24, 3, 6);
        taillight.setFill(Color.web("#ff1744"));

        Text numberText = createNumberText(car.getNumber());

        rearWheel = createWheel(REAR_WHEEL_X, WHEEL_Y);
        frontWheel = createWheel(FRONT_WHEEL_X, WHEEL_Y);

        getChildren().addAll(roof, rearWindow, frontWindow, body,
                headlight, taillight, numberText, rearWheel, frontWheel);
    }

    /**
     * Returns the 4 roof corners (bottom-left, top-left, top-right,
     * bottom-right) for a car type.
     */
    private static double[] getRoofPoints(CarType type) {
        switch (type) {
            case SPORTS_COUPE:
                return new double[]{34, BODY_TOP, 48, 5, 80, 5, 100, BODY_TOP};
            case SEDAN:
                return new double[]{22, BODY_TOP, 34, 3, 82, 3, 96, BODY_TOP};
            case MUSCLE_CAR:
                return new double[]{28, BODY_TOP, 36, 4, 84, 4, 94, BODY_TOP};
            case HATCHBACK:
                return new double[]{8, BODY_TOP, 12, 3, 80, 3, 98, BODY_TOP};
            case ROADSTER:
            default:
                return new double[]{44, BODY_TOP, 54, 8, 74, 8, 86, BODY_TOP};
        }
    }

    /**
     * Creates one light blue window.
     */
    private static Rectangle createWindow(double x, double y, double width, double height) {
        Rectangle window = new Rectangle(x, y, width, height);
        window.setFill(Color.web("#b3e5fc"));
        window.setStroke(Color.web("#263238"));
        window.setStrokeWidth(0.8);
        return window;
    }

    /**
     * Creates the car number, centered on the side of the body.
     */
    private static Text createNumberText(int number) {
        Text text = new Text(String.valueOf(number));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        text.setFill(Color.WHITE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(0.6);
        double textWidth = text.getLayoutBounds().getWidth();
        text.setX(LENGTH / 2 - textWidth / 2);
        text.setY(35);
        return text;
    }

    /**
     * Creates a wheel: a tire circle, a spoke line from the center to the edge
     * and a small hub. Grouping them lets the whole wheel rotate around its
     * center.
     */
    private static Group createWheel(double centerX, double centerY) {
        Circle tire = new Circle(centerX, centerY, WHEEL_RADIUS);
        tire.setFill(Color.web("#212121"));
        tire.setStroke(Color.web("#9e9e9e"));
        tire.setStrokeWidth(2);

        Line spoke = new Line(centerX, centerY, centerX, centerY - WHEEL_RADIUS + 1);
        spoke.setStroke(Color.web("#f5f5f5"));
        spoke.setStrokeWidth(2);

        Circle hub = new Circle(centerX, centerY, 2.5, Color.web("#bdbdbd"));

        return new Group(tire, spoke, hub);
    }

    /**
     * Turns the gold winner glow on or off.
     *
     * @param highlighted true to show the glow
     */
    public void setHighlighted(boolean highlighted) {
        if (highlighted) {
            DropShadow glow = new DropShadow(22, Color.GOLD);
            glow.setSpread(0.45);
            setEffect(glow);
        } else {
            setEffect(null);
        }
    }

    /**
     * Returns the two wheel groups (rear, front) so they can be rotated.
     *
     * @return the wheels of this car
     */
    public List<Node> getWheels() {
        return List.of(rearWheel, frontWheel);
    }

    /**
     * Returns the model car drawn by this shape.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }
}
