package mossimo.bianco.assignment02.view;

import javafx.scene.Group;

/**
 * Draws one car using JavaFX shapes only: Polygon objects for the body and the
 * roof, Rectangle objects for the windows, two Circle objects for the wheels
 * (each with a spoke line so the rotation is visible) and a Text object for
 * the car number. All the shapes are children of this Group, so they always
 * move together
 *
 * The car faces right
 * Its local origin is the top-left corner, and its front bumper is at x = LENGTH
 *
 * @author Mossimo Bianco
 */
public class CarShape extends Group {
    
    /** Length of the car from rear bumper to front bumper */
    public static final double LENGTH = 120;
    /** Height of the car including the wheels */
    public static final double HEIGHT = 52;
    /** Radius of each wheel. */
    public static final double WHEEL_RADIUS = 10;
    
    private static final double BODY_TOP = 18;
}
