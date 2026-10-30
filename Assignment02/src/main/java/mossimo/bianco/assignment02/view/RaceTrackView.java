package mossimo.bianco.assignment02.view;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import mossimo.bianco.assignment02.model.CarRaceModel;

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
    private static final double CURB_WIDTH = 20;
    private static final double CURB_HEIGHT = 6;

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
    
    /**
     * Draws the track for the given model (one lane per car)
     *
     * @param model the race model holding the track dimensions
     */
    public void drawTrack(CarRaceModel model) {
        trackLayer.getChildren().clear();

        int lanes = model.getNumberOfLanes();
        double asphaltTop = CarRaceModel.TRACK_TOP;
        double asphaltHeight = lanes * CarRaceModel.LANE_HEIGHT;
        double asphaltBottom = asphaltTop + asphaltHeight;
        double height = asphaltBottom + CarRaceModel.TRACK_TOP;

        setPrefSize(TRACK_WIDTH, height);
        setMinSize(TRACK_WIDTH, height);
        setMaxSize(TRACK_WIDTH, height);
        
        drawGrass(height);

        Rectangle asphalt = new Rectangle(0, asphaltTop, TRACK_WIDTH, asphaltHeight);
        asphalt.setFill(Color.web("#3d3d3d"));
        trackLayer.getChildren().add(asphalt);

        drawCurbs(asphaltTop - CURB_HEIGHT);
        drawCurbs(asphaltBottom);
        drawLaneDividers(model, lanes);
        drawLaneNumbers(model, lanes);
        drawStartLine(asphaltTop, asphaltHeight);
        drawFinishLine(asphaltTop, asphaltHeight);
    }
    
    /**
     * Fills the background with mowed-grass stripes
     */
    private void drawGrass(double height) {
        double stripeWidth = 60;
        for (int i = 0; i * stripeWidth < TRACK_WIDTH; i++) {
            Rectangle stripe = new Rectangle(i * stripeWidth, 0, stripeWidth, height);
            stripe.setFill(i % 2 == 0 ? Color.web("#2e7d32") : Color.web("#388e3c"));
            trackLayer.getChildren().add(stripe);
        }
    }
    
    /**
     * Draws a row of red and white curb blocks at the given y
     */
    private void drawCurbs(double y) {
        for (int i = 0; i * CURB_WIDTH < TRACK_WIDTH; i++) {
            Rectangle curb = new Rectangle(i * CURB_WIDTH, y, CURB_WIDTH, CURB_HEIGHT);
            curb.setFill(i % 2 == 0 ? Color.web("#d32f2f") : Color.WHITE);
            trackLayer.getChildren().add(curb);
        }
    }

    /**
     * Draws dashed white lines between the lanes.
     */
    private void drawLaneDividers(CarRaceModel model, int lanes) {
        for (int lane = 1; lane < lanes; lane++) {
            double y = model.getLaneTop(lane);
            Line divider = new Line(0, y, TRACK_WIDTH, y);
            divider.setStroke(Color.rgb(255, 255, 255, 0.7));
            divider.setStrokeWidth(2);
            divider.getStrokeDashArray().addAll(22.0, 16.0);
            trackLayer.getChildren().add(divider);
        }
    }
}
