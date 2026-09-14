package mossimo.bianco.lab04;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        inputGrid.add(numberDaysField, 1, 0);
        
        inputGrid.add(new Label("Airfare ($):"), 0, 1);
        inputGrid.add(amountAirfareField, 1, 1);

        inputGrid.add(new Label("Car rental fees ($):"), 0, 2);
        inputGrid.add(carRentalFeesField, 1, 2);

        inputGrid.add(new Label("Miles driven:"), 0, 3);
        inputGrid.add(milesDrivenField, 1, 3);

        inputGrid.add(new Label("Parking fees ($):"), 0, 4);
        inputGrid.add(parkingFeesField, 1, 4);

        inputGrid.add(new Label("Taxi charges ($):"), 0, 5);
        inputGrid.add(taxiChargesField, 1, 5);

        inputGrid.add(new Label("Seminar registration ($):"), 0, 6);
        inputGrid.add(seminarFeesField, 1, 6);

        inputGrid.add(new Label("Lodging per night ($):"), 0, 7);
        inputGrid.add(lodgingChargesField, 1, 7);
        
        // calculate button
        Button calculateButton = new Button("Calculate Expenses");
        calculateButton.getStyleClass().add("calculate-button");
        calculateButton.setOnAction(event -> calculateExpenses());
        
        // output labels
        Label resultsTitle = new Label("Results");
        resultsTitle.getStyleClass().add("results-title");
        
        totalExpensesLabel = new Label("Total expenses: $0.00");
        allowableExpensesLabel = new Label("Total allowable expenses: $0.00");
        excessLabel = new Label("Excess paid by businessperson: $0.00");
        amountSavedLabel = new Label("Amount saved: $0.00");
        
        // results box
        VBox resultsBox = new VBox(
                8,
                resultsTitle,
                totalExpensesLabel,
                allowableExpensesLabel,
                excessLabel,
                amountSavedLabel
        );
        
        resultsBox.getStyleClass().add("results-box");
        
        // main layout
        VBox root = new VBox( 
                15,
                title,
                subtitle,
                inputGrid,
                calculateButton,
                resultsBox
        );
        
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root");
        
        // scene
        Scene scene = new Scene(root, 600, 750);
        
        
        // css styling
        // AFTER
        
        
        
        stage.setTitle("Business Travel Expenses Calculator");
        stage.setScene(scene);
        stage.show();
    }
    
    private TextField createTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefWidth(180);
        field.getStyleClass().add("text-field");
        
        return field;
    }
    
    private void calculateExpenses() {
        try {
            int numberOfDays =
                    Integer.parseInt(numberDaysField.getText());
            
            double airfare =
                    Double.parseDouble(amountAirfareField.getText());
            
            double carRental =
                    Double.parseDouble(carRentalFeesField.getText());
            
            double milesDriven =
                    Double.parseDouble(milesDrivenField.getText());
            
            double parking =
                    Double.parseDouble(parkingFeesField.getText());
            
            double taxi = 
                    Double.parseDouble(taxiChargesField.getText());
            
            double seminar =
                    Double.parseDouble(seminarFeesField.getText());
            
            double lodgingPerNight = 
                    Double.parseDouble(lodgingChargesField.getText());
            
            if (numberOfDays <= 0) {
                showError("Number of days must be greater than 0.");
                return;
            }
            
            if (airfare < 0 ||
                    carRental < 0 ||
                    milesDriven < 0 ||
                    parking < 0 ||
                    taxi < 0 ||
                    seminar < 0 ||
                    lodgingPerNight < 0) {
                
                showError("Expenses cannot be negative.");
                return;
            }
            
            double mealsActual = numberOfDays * MEAL_ALLOWANCE;
            double vehicleExpense = milesDriven * PRIVATE_VEHICLE_RATE;
            double lodgingActual = numberOfDays * lodgingPerNight;
            double totalExpenses =
                    airfare
                    + carRental
                    + vehicleExpense
                    + parking
                    + taxi
                    + seminar
                    + lodgingActual
                    + mealsActual;
            
            // allowable expenses
            double allowableMeals = numberOfDays * MEAL_ALLOWANCE;
            double allowableParking = Math.min(parking, numberOfDays * PARKING_ALLOWANCE);
            double allowableTaxi = Math.min(taxi, numberOfDays * TAXI_ALLOWANCE);
            double allowableLodging = Math.min(lodgingActual, numberOfDays * LODGING_ALLOWANCE);
            double allowableVehicle = vehicleExpense;
            double totalAllowableExpenses = 
                    airfare
                    + carRental
                    + allowableVehicle
                    + allowableMeals
                    + allowableParking
                    + allowableTaxi
                    + seminar
                    + allowableLodging;
            
            // excess and savings
            double excess = Math.max(0, totalExpenses - totalAllowableExpenses);
            double saved = Math.max(0, totalAllowableExpenses - totalExpenses);
            
            // display results
            totalExpensesLabel.setText(String.format( "Total expenses: $%.2f", totalExpenses ) );
            allowableExpensesLabel.setText( String.format( "Total allowable expenses: $%.2f", 
                    totalAllowableExpenses ) ); 
            excessLabel.setText( String.format( "Excess paid by businessperson: $%.2f", excess ) );
            amountSavedLabel.setText( String.format( "Amount saved: $%.2f", saved ) );
        
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers in all fields.");
        }
    }
            
    // error message
    private showError() {
    
        return null;
    }
        
    public static void main(String[] args) {
        launch();
    }
}
