package mossimo.bianco.assignment02.model;

import javafx.scene.paint.Color;

/**
 * Stores the data of one race car: its identity (type, number, and colour),
 * its position on the track, its speed, and its result in the race
 * 
 * The x coordinate is the position of the car's front bumper on the track
 * The y coordinate is the top of the car's lane
 * 
 * @author Mossimo Bianco
 */
public class Car {
    private final CarType type;
    private final int number;
    private final Color color;
    private final String colorName;
    private final double startX;
    
    private double x;
    private double y;
    private double speed;
    private boolean finished;
    private int finishPlace;
    
    public Car(CarType type, int number, Color color, String colorName,
            double startX, double y) {
        this.type = type;
        this.number = number;
        this.color = color;
        this.colorName = colorName;
        this.startX = startX;
        this.x = startX;
        this.y = y;
    }
}
