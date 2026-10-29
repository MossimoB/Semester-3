package mossimo.bianco.assignment02.model;

import javafx.scene.paint.Color;

/**
 * Stores the data of one race car: its identity (type, number, and color),
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
    
    /**
     * Creates a car waiting at the start line
     * 
     * @param type      the kind of car
     * @param number    the racing number painted on the car
     * @param color     the paint color of the car
     * @param colorName the readable name of the color
     * @param startX    x coordinate of the start line
     * @param y         y coordinate of the top of the car's lane
     */
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
    
    /**
     * Returns a short description such as "Red Sports Coupe #7"
     * @return the description of the car
     */
    public String getDescription() {
        return colorName + " " + type.getDisplayName() + " #" + number;
    }
    
    // GETTERS
        public CarType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public String getColorName() {
        return colorName;
    }

    public double getStartX() {
        return startX;
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public int getFinishPlace() {
        return finishPlace;
    }
    
    // SETTERS
    public void setX(double x) {
        this.x = x;
    }
        public void setY(double y) {
        this.y = y;
    }
            public void setSpeed(double speed) {
        this.speed = speed;
    }
            
    // BOOLEAN        
    public boolean isFinished() {
        return finished;
    }
    
    /**
     * Marks the car as finished in the given place
     *
     * @param place the finishing position (1 = winner)
     */
    public void finish(int place) {
        this.finished = true;
        this.finishPlace = place;
    }
}
