package mossimo.bianco.lab04;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
1. Number of days on the trip
2. Amount of airfare, if any
3. Amount of car rental fees, if any
4. Number of miles driven, if a private vehicle was used
5. Amount of parking fees, if any
6. Amount of taxi charges, if any
7. Conference or seminar registration fees, if any
8. Lodging charges, per night
 */

public class App extends Application {
    
    // company reimbursements
    private static final double MEAL_ALLOWANCE = 37.00;
    private static final double PARKING_ALLOWANCE = 10.00;
    private static final double TAXI_ALLOWANCE = 20.00;
    private static final double LODGING_ALLOWANCE = 95.00;
    private static final double PRIVATE_VEHICLE_RATE = 0.27;
    
    // inputs
    private TextField numberDaysField;
    private TextField amountAirfareField;
    private TextField carRentalFeesField;
    private TextField milesDrivenField;
    private TextField parkingFeesField;
    private TextField taxiChargesField;
    private TextField seminarFeesField;
    private TextField lodgingChargesField;
    
    // outputs
    private Label totalExpensesLabel;
    private Label allowableExpensesLabel;
    private Label excessLabel;
    private Label amountSavedLabel;
    

    @Override
    public void start(Stage stage) {
        
        Label title = new Label("Business Travel Expenses Calculator");
        title.getStyleClass().add("title");
        
        Label subtitle = new Label("Enter the actual expenses incurred during the business trip.");
        subtitle.getStyleClass().add("subtitle");
        
        // Input fields
        numberDaysField = createTextField("Number of days");
        amountAirfareField = createTextField("0.00");
        carRentalFeesField = createTextField("0.00");
        milesDrivenField = createTextField("0");
        parkingFeesField = createTextField("0.00");
        taxiChargesField = createTextField("0.00");
        seminarFeesField = createTextField("0.00");
        lodgingChargesField = createTextField("0.00");
        
        // input grid
        GridPane inputGrid = new GridPane();
        
        inputGrid.setHgap(15);
        inputGrid.setVgap(12);
        inputGrid.setPadding(new Insets(10));
        
        // labels
        inputGrid.add(new Label("Number of days: "), 0, 0);
        input.add(numberDaysField, 1, 0);
        
        VBox root = new VBox(10);
        
        Scene scene = new Scene(root, 600, 750);
        stage.setScene(scene);
        stage.show();
    }
    
    public TextField createTextField(String prompt) {
    
        return null;
    }

    public static void main(String[] args) {
        launch();
    }

}
