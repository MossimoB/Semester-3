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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 1. Number of days on the trip
 * 2. Amount of airfare, if any
 * 3. Amount of car rental fees, if any
 * 4. Number of miles driven, if a private vehicle was used
 * 5. Amount of parking fees, if any
 * 6. Amount of taxi charges, if any
 * 7. Conference or seminar registration fees, if any
 * 8. Lodging charges, per night
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
        inputGrid.getStyleClass().add("input-grid");
        
        inputGrid.setHgap(18);
        inputGrid.setVgap(12);
        inputGrid.setPadding(new Insets(22));
        
        // labels
        Label daysLabel = new Label("Number of days:");
        daysLabel.getStyleClass().add("input-label");
        inputGrid.add(daysLabel, 0, 0);
        inputGrid.add(numberDaysField, 1, 0);
        
        Label airfareLabel = new Label("Airfare ($):");
        airfareLabel.getStyleClass().add("input-label");
        inputGrid.add(airfareLabel, 0, 1);
        inputGrid.add(amountAirfareField, 1, 1);

        Label carRentalLabel = new Label("Car rental fees ($):");
        carRentalLabel.getStyleClass().add("input-label");
        inputGrid.add(carRentalLabel, 0, 2);
        inputGrid.add(carRentalFeesField, 1, 2);

        Label milesLabel = new Label("Miles driven:");
        milesLabel.getStyleClass().add("input-label");
        inputGrid.add(milesLabel, 0, 3);
        inputGrid.add(milesDrivenField, 1, 3);

        Label parkingLabel = new Label("Parking fees ($):");
        parkingLabel.getStyleClass().add("input-label");
        inputGrid.add(parkingLabel, 0, 4);
        inputGrid.add(parkingFeesField, 1, 4);

        Label taxiLabel = new Label("Taxi charges ($):");
        taxiLabel.getStyleClass().add("input-label");
        inputGrid.add(taxiLabel, 0, 5);
        inputGrid.add(taxiChargesField, 1, 5);

        Label seminarLabel = new Label("Seminar registration ($):");
        seminarLabel.getStyleClass().add("input-label");
        inputGrid.add(seminarLabel, 0, 6);
        inputGrid.add(seminarFeesField, 1, 6);

        Label lodgingLabel = new Label("Lodging per night ($):");
        lodgingLabel.getStyleClass().add("input-label");
        inputGrid.add(lodgingLabel, 0, 7);
        inputGrid.add(lodgingChargesField, 1, 7);
        
        // calculate button
        Button calculateButton = new Button("Calculate Expenses");
        calculateButton.getStyleClass().add("calculate-button");
        calculateButton.setOnAction(event -> calculateExpenses());
        
        // output labels
        Label resultsTitle = new Label("Results");
        resultsTitle.getStyleClass().add("results-title");
        
        totalExpensesLabel = new Label("Total expenses: $0.00");
        totalExpensesLabel.getStyleClass().add("result-total");

        allowableExpensesLabel = new Label("Total allowable expenses: $0.00");
        allowableExpensesLabel.getStyleClass().add("result-allowable");

        excessLabel = new Label("Excess paid by businessperson: $0.00");
        excessLabel.getStyleClass().add("result-excess");

        amountSavedLabel = new Label("Amount saved: $0.00");
        amountSavedLabel.getStyleClass().add("result-saved");
        
        // results box
        VBox resultsBox = new VBox(
                12,
                resultsTitle,
                totalExpensesLabel,
                allowableExpensesLabel,
                excessLabel,
                amountSavedLabel
        );
        
        resultsBox.getStyleClass().add("results-box");
        
        // main layout
        VBox root = new VBox(
                18,
                title,
                subtitle,
                inputGrid,
                calculateButton,
                resultsBox
        );
        
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root");
        
        // scene
        Scene scene = new Scene(root, 650, 790);
        
        
        // css styling
        String css =
           ".root {" +
           "    -fx-background-color: linear-gradient(to bottom right, #0b0f14, #111827, #0b0f14);" +
           "    -fx-font-family: \"Segoe UI\";" +
           "}" +

           ".title {" +
           "    -fx-text-fill: #f8fafc;" +
           "    -fx-font-size: 30px;" +
           "    -fx-font-weight: bold;" +
           "    -fx-letter-spacing: 0.5px;" +
           "}" +

           ".subtitle {" +
           "    -fx-text-fill: #8b98aa;" +
           "    -fx-font-size: 14px;" +
           "    -fx-padding: 0 0 8px 0;" +
           "}" +

           ".input-grid {" +
           "    -fx-background-color: rgba(255, 255, 255, 0.055);" +
           "    -fx-background-radius: 22px;" +
           "    -fx-border-radius: 22px;" +
           "    -fx-border-color: rgba(255, 255, 255, 0.10);" +
           "    -fx-border-width: 1px;" +
           "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.35), 25, 0.20, 0, 10);" +
           "}" +

           ".input-grid:hover {" +
           "    -fx-border-color: rgba(255, 255, 255, 0.15);" +
           "}" +

           ".input-label {" +
           "    -fx-text-fill: #cbd5e1;" +
           "    -fx-font-size: 13px;" +
           "    -fx-font-weight: bold;" +
           "}" +

           ".text-field {" +
           "    -fx-pref-width: 230px;" +
           "    -fx-pref-height: 40px;" +
           "    -fx-background-color: rgba(255, 255, 255, 0.075);" +
           "    -fx-background-radius: 12px;" +
           "    -fx-border-radius: 12px;" +
           "    -fx-border-color: rgba(255, 255, 255, 0.08);" +
           "    -fx-border-width: 1px;" +
           "    -fx-padding: 0px 14px;" +
           "    -fx-font-size: 14px;" +
           "    -fx-text-fill: #f8fafc;" +
           "    -fx-prompt-text-fill: #64748b;" +
           "    -fx-effect: innershadow(gaussian, rgba(0, 0, 0, 0.25), 7, 0.2, 0, 2);" +
           "}" +

           ".text-field:hover {" +
           "    -fx-background-color: rgba(255, 255, 255, 0.095);" +
           "    -fx-border-color: rgba(148, 163, 184, 0.25);" +
           "}" +

           ".text-field:focused {" +
           "    -fx-background-color: rgba(255, 255, 255, 0.11);" +
           "    -fx-border-color: #60a5fa;" +
           "    -fx-border-width: 1.5px;" +
           "    -fx-effect: innershadow(gaussian, rgba(0, 0, 0, 0.20), 6, 0.2, 0, 2)," +
           "                 dropshadow(gaussian, rgba(96, 165, 250, 0.22), 14, 0.25, 0, 0);" +
           "}" +

           ".calculate-button {" +
           "    -fx-background-color: linear-gradient(to right, #2563eb, #3b82f6);" +
           "    -fx-text-fill: #ffffff;" +
           "    -fx-font-size: 15px;" +
           "    -fx-font-weight: bold;" +
           "    -fx-padding: 13px 34px;" +
           "    -fx-background-radius: 13px;" +
           "    -fx-border-radius: 13px;" +
           "    -fx-border-color: rgba(255, 255, 255, 0.12);" +
           "    -fx-border-width: 1px;" +
           "    -fx-cursor: hand;" +
           "    -fx-effect: dropshadow(gaussian, rgba(37, 99, 235, 0.28), 16, 0.25, 0, 6);" +
           "}" +

           ".calculate-button:hover {" +
           "    -fx-background-color: linear-gradient(to right, #3b82f6, #60a5fa);" +
           "    -fx-effect: dropshadow(gaussian, rgba(59, 130, 246, 0.42), 22, 0.30, 0, 7);" +
           "    -fx-scale-x: 1.025;" +
           "    -fx-scale-y: 1.025;" +
           "}" +

           ".calculate-button:pressed {" +
           "    -fx-background-color: #1d4ed8;" +
           "    -fx-scale-x: 0.98;" +
           "    -fx-scale-y: 0.98;" +
           "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.35), 8, 0.20, 0, 3);" +
           "}" +

           ".results-box {" +
           "    -fx-background-color: rgba(255, 255, 255, 0.055);" +
           "    -fx-background-radius: 22px;" +
           "    -fx-border-radius: 22px;" +
           "    -fx-border-color: rgba(255, 255, 255, 0.10);" +
           "    -fx-border-width: 1px;" +
           "    -fx-padding: 22px;" +
           "    -fx-pref-width: 540px;" +
           "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.38), 28, 0.22, 0, 10);" +
           "}" +

           ".results-box:hover {" +
           "    -fx-border-color: rgba(255, 255, 255, 0.14);" +
           "}" +

           ".results-title {" +
           "    -fx-text-fill: #f8fafc;" +
           "    -fx-font-size: 20px;" +
           "    -fx-font-weight: bold;" +
           "    -fx-padding: 0 0 5px 0;" +
           "}" +

           ".result-total {" +
           "    -fx-text-fill: #f8fafc;" +
           "    -fx-font-size: 15px;" +
           "    -fx-font-weight: bold;" +
           "    -fx-padding: 8px 0;" +
           "}" +

           ".result-allowable {" +
           "    -fx-text-fill: #60a5fa;" +
           "    -fx-font-size: 15px;" +
           "    -fx-font-weight: bold;" +
           "    -fx-padding: 8px 0;" +
           "}" +

           ".result-excess {" +
           "    -fx-text-fill: #fb7185;" +
           "    -fx-font-size: 15px;" +
           "    -fx-font-weight: bold;" +
           "    -fx-padding: 8px 0;" +
           "}" +

           ".result-saved {" +
           "    -fx-text-fill: #4ade80;" +
           "    -fx-font-size: 15px;" +
           "    -fx-font-weight: bold;" +
           "    -fx-padding: 8px 0;" +
           "}" +

           ".error {" +
           "    -fx-text-fill: #fb7185;" +
           "    -fx-font-weight: bold;" +
           "    -fx-font-size: 14px;" +
           "}";
        
        
        try {
            File cssFile = File.createTempFile("travel-expenses-", ".css");
            cssFile.deleteOnExit();

            try (FileWriter writer = new FileWriter(cssFile)) {
                writer.write(css);
            }

            scene.getStylesheets().add(cssFile.toURI().toURL().toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
            if (numberDaysField.getText().trim().isEmpty()) {
                showError("Please enter the number of days.");
                return;
            }

            int numberOfDays =
                    Integer.parseInt(numberDaysField.getText().trim());

            double airfare =
                    getValue(amountAirfareField);

            double carRental =
                    getValue(carRentalFeesField);

            double milesDriven =
                    getValue(milesDrivenField);

            double parking =
                    getValue(parkingFeesField);

            double taxi =
                    getValue(taxiChargesField);

            double seminar =
                    getValue(seminarFeesField);

            double lodgingPerNight =
                    getValue(lodgingChargesField);

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
            totalExpensesLabel.setText(
                    String.format("Total expenses: $%.2f", totalExpenses)
            );

            allowableExpensesLabel.setText(
                    String.format(
                            "Total allowable expenses: $%.2f",
                            totalAllowableExpenses
                    )
            );

            excessLabel.setText(
                    String.format(
                            "Excess paid by businessperson: $%.2f",
                            excess
                    )
            );

            amountSavedLabel.setText(
                    String.format(
                            "Amount saved: $%.2f",
                            saved
                    )
            );

        } catch (NumberFormatException e) {
            showError("Please enter valid numbers in all fields.");
        }
    }

    private double getValue(TextField field) {
        String text = field.getText().trim();

        if (text.isEmpty()) {
            return 0.0;
        }

        return Double.parseDouble(text);
    }
            
    // error message
    private void showError(String message) {
        totalExpensesLabel.setText("Error: " + message);
        allowableExpensesLabel.setText("");
        excessLabel.setText("");
        amountSavedLabel.setText("");

        totalExpensesLabel.getStyleClass().add("error");
    }
        
    public static void main(String[] args) {
        launch();
    }
}
