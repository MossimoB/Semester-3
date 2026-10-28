package mossimo.bianco.assignment02.model;

/**
 * The different kinds of cars that can take part in the race. Each type has a
 * readable name used in messages and a slightly different roof shape in the
 * view.
 *
 * @author Mossimo Bianco
 */
public enum CarType {
    SPORTS_COUPE("Sports Coupe"),
    SEDAN("Sedan"),
    MUSCLE_CAR("Muscle Car"),
    HATCHBACK("Hatchback"),
    ROADSTER("Roadster");

    private final String displayName;

    /**
     * Creates a car type with the name shown to the user.
     *
     * @param displayName readable name of the car type
     */
    CarType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the readable name of this car type.
     *
     * @return the display name (for example "Sports Coupe")
     */
    public String getDisplayName() {
        return displayName;
    }
}
