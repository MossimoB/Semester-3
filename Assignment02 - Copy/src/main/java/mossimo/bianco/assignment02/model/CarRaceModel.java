package mossimo.bianco.assignment02.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Model of the car race. Stores the cars, the track dimensions and the race
 * results, and provides methods to update each car's state and position.
 * It does not know anything about the user interface.
 *
 * @author Mossimo Bianco
 */
public class CarRaceModel {

    /** X coordinate of the start line. */
    public static final double START_LINE_X = 150;
    /** X coordinate of the finish line. */
    public static final double FINISH_LINE_X = 860;
    /** Y coordinate where the asphalt (first lane) begins. */
    public static final double TRACK_TOP = 30;
    /** Height of one lane in pixels. */
    public static final double LANE_HEIGHT = 72;
    /** Slowest possible speed in pixels per second (at 1x). */
    public static final double MIN_SPEED = 80;
    /** Fastest possible speed in pixels per second (at 1x). */
    public static final double MAX_SPEED = 130;

    private final List<Car> cars = new ArrayList<>();
    private final List<Car> finishOrder = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Creates the model with five different cars, each in its own lane, and
     * gives them random speeds.
     */
    public CarRaceModel() {
        addCar(CarType.SPORTS_COUPE, 7, Color.web("#e53935"), "Red");
        addCar(CarType.SEDAN, 12, Color.web("#1e88e5"), "Blue");
        addCar(CarType.MUSCLE_CAR, 23, Color.web("#43a047"), "Green");
        addCar(CarType.HATCHBACK, 44, Color.web("#fdd835"), "Yellow");
        addCar(CarType.ROADSTER, 88, Color.web("#8e24aa"), "Purple");
        randomizeSpeeds();
    }

    /**
     * Adds a car in the next free lane.
     */
    private void addCar(CarType type, int number, Color color, String colorName) {
        double laneTop = getLaneTop(cars.size());
        cars.add(new Car(type, number, color, colorName, START_LINE_X, laneTop));
    }

    /**
     * Gives every car a new random speed between MIN_SPEED and MAX_SPEED.
     */
    public final void randomizeSpeeds() {
        for (Car car : cars) {
            double speed = MIN_SPEED + random.nextDouble() * (MAX_SPEED - MIN_SPEED);
            car.setSpeed(speed);
        }
    }

    /**
     * Updates a car's position from the distance it has travelled since the
     * start line.
     *
     * @param car                the car to move
     * @param distanceTravelled  distance from the start line, in pixels
     */
    public void updatePosition(Car car, double distanceTravelled) {
        car.setX(START_LINE_X + distanceTravelled);
    }

    /**
     * Checks whether the front of the car has reached the finish line.
     *
     * @param car the car to check
     * @return true if the car is on or past the finish line
     */
    public boolean hasCrossedFinishLine(Car car) {
        return car.getX() >= FINISH_LINE_X;
    }

    /**
     * Records that a car crossed the finish line. The first car recorded is
     * the winner. Recording the same car twice has no effect.
     *
     * @param car the car that finished
     * @return the car's finishing place (1 = winner)
     */
    public int recordFinish(Car car) {
        if (!car.isFinished()) {
            finishOrder.add(car);
            car.finish(finishOrder.size());
            car.setX(FINISH_LINE_X);
        }
        return car.getFinishPlace();
    }

    /**
     * Returns the car that is furthest along the track.
     *
     * @return the current leader
     */
    public Car getLeader() {
        Car leader = cars.get(0);
        for (Car car : cars) {
            if (car.getX() > leader.getX()) {
                leader = car;
            }
        }
        return leader;
    }

    /**
     * Returns how much of the track a car has covered, from 0.0 to 1.0.
     *
     * @param car the car to check
     * @return the fraction of the race completed
     */
    public double getProgress(Car car) {
        double progress = (car.getX() - START_LINE_X) / getRaceDistance();
        return Math.max(0, Math.min(1, progress));
    }

    /**
     * Returns the time a car needs to finish the race at normal speed (1x).
     *
     * @param car the car
     * @return the race time in seconds
     */
    public double getRaceTime(Car car) {
        return getRaceDistance() / car.getSpeed();
    }

    /**
     * Returns the distance between the start line and the finish line.
     *
     * @return the race distance in pixels
     */
    public double getRaceDistance() {
        return FINISH_LINE_X - START_LINE_X;
    }

    /**
     * Returns the y coordinate of the top of a lane.
     *
     * @param lane the lane index, starting at 0
     * @return the y coordinate of the lane's top edge
     */
    public double getLaneTop(int lane) {
        return TRACK_TOP + lane * LANE_HEIGHT;
    }

    /**
     * Puts every car back on the start line, clears the results and picks new
     * random speeds for the next race.
     */
    public void reset() {
        finishOrder.clear();
        for (Car car : cars) {
            car.reset();
        }
        randomizeSpeeds();
    }

    /**
     * Returns the winner of the race.
     *
     * @return the first car to cross the finish line, or null if none has
     */
    public Car getWinner() {
        return finishOrder.isEmpty() ? null : finishOrder.get(0);
    }

    /**
     * Checks whether every car has crossed the finish line.
     *
     * @return true when the race is over
     */
    public boolean isRaceOver() {
        return finishOrder.size() == cars.size();
    }

    /**
     * Returns the cars in the order they crossed the finish line.
     *
     * @return a read-only list of finished cars
     */
    public List<Car> getFinishOrder() {
        return Collections.unmodifiableList(finishOrder);
    }

    /**
     * Returns all the cars in lane order.
     *
     * @return a read-only list of the cars
     */
    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    /**
     * Returns the number of lanes on the track (one per car).
     *
     * @return the number of lanes
     */
    public int getNumberOfLanes() {
        return cars.size();
    }
}
