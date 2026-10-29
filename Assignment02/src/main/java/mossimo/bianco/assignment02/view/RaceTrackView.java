package mossimo.bianco.assignment02.view;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 * Draws the race track with JavaFX shapes: grass, asphalt lanes, curbs, lane
 * dividers, the start line and a checkered finish line. Cars are placed on a
 * separate layer above the track
 *
 * @author Mossimo Bianco
 */
public class RaceTrackView extends Pane {
    
    /** Width of the track area in pixels. */
    public static final double TRACK_WIDTH = 960;

    private static final double SQUARE_SIZE = 9;
    private static final double KERB_WIDTH = 20;
    private static final double KERB_HEIGHT = 6;

    private final Group trackLayer = new Group();
    private final Group carLayer = new Group();
    
    /**
     * Creates an empty track view
     * Call drawTrack once the model is known
     */
    public RaceTrackView() {
        getStyleClass().add("race-track");
        getChildren().addAll(trackLayer, carLayer);
    }
}
