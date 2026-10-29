package mossimo.bianco.assignment02.model;

public enum CarType {
    SPORTS_COUPE("Sports Coupe"),
    SEDAN("Sedan"),
    MUSCLE_CAR("Muscle Car"),
    HATCHBACK("Hatchback"),
    ROADSTER("Roadster");
    
    private final String displayName;
    
    /**
     * Creates a car type with the name shown to the user
     * @param displayName  readable name of the car type
     */
    CarType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
