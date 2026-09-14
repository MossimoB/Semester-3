package mossimo.bianco.lab04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
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
    
    private TextField numberDaysField;
    private TextField amountAirfareField;
    private TextField carRentalFeesField;
    private TextField milesDrivenField;
    private TextField parkingFeesField;
    private TextField taxiChargesField;
    private TextField seminarFeesField;
    private TextField lodgingChargesField;
    
   private Label totalExpensesLabel;
   private Label allowableExpensesLabel;
   private Label excessLabel;
   private Label savingsLabel;
    

    @Override
    public void start(Stage stage) {
        
        
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
