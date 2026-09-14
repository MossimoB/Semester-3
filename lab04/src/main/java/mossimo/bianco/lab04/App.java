package mossimo.bianco.lab04;

// Git Repository: PASTE-YOUR-GITHUB-REPOSITORY-LINK-HERE

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Business Travel Expenses Calculator
 *
 * The application calculates:
 * 1. Total expenses incurred
 * 2. Total allowable expenses
 * 3. Excess paid by the businessperson
 * 4. Amount saved when expenses are under the allowance
 *
 * Company reimbursement policy:
 * Meals: $37.00 per day
 * Parking: up to $10.00 per day
 * Taxi: up to $20.00 per day
 * Lodging: up to $95.00 per day
 * Private vehicle: $0.27 per mile
 */
public class App extends Application {

    // ---------------------------------------------------------
    // COMPANY REIMBURSEMENT CONSTANTS
    // ---------------------------------------------------------

    private static final double MEAL_ALLOWANCE = 37.00;
    private static final double PARKING_ALLOWANCE = 10.00;
    private static final double TAXI_ALLOWANCE = 20.00;
    private static final double LODGING_ALLOWANCE = 95.00;
    private static final double PRIVATE_VEHICLE_RATE = 0.27;


    // ---------------------------------------------------------
    // INPUT FIELDS
    // ---------------------------------------------------------

    private TextField numberDaysField;
    private TextField amountAirfareField;
    private TextField carRentalFeesField;
    private TextField milesDrivenField;
    private TextField parkingFeesField;
    private TextField taxiChargesField;
    private TextField seminarFeesField;
    private TextField lodgingChargesField;


    // ---------------------------------------------------------
    // OUTPUT LABELS
    // ---------------------------------------------------------

    private Label totalExpensesLabel;
    private Label allowableExpensesLabel;
    private Label excessLabel;
    private Label amountSavedLabel;


    // ---------------------------------------------------------
    // START APPLICATION
    // ---------------------------------------------------------

    @Override
    public void start(Stage stage) {

        // Title
        Label title = new Label("Business Travel Expenses Calculator");
        title.getStyleClass().add("title");

        // Subtitle
        Label subtitle = new Label(
                "Enter the actual expenses incurred during the business trip."
        );
        subtitle.getStyleClass().add("subtitle");


        // ---------------------------------------------------------
        // CREATE INPUT FIELDS
        // ---------------------------------------------------------

        numberDaysField = createTextField("Number of days");
        amountAirfareField = createTextField("0.00");
        carRentalFeesField = createTextField("0.00");
        milesDrivenField = createTextField("0");
        parkingFeesField = createTextField("0.00");
        taxiChargesField = createTextField("0.00");
        seminarFeesField = createTextField("0.00");
        lodgingChargesField = createTextField("0.00");


        // ---------------------------------------------------------
        // INPUT GRID
        // ---------------------------------------------------------

        GridPane inputGrid = new GridPane();

        inputGrid.setHgap(15);
        inputGrid.setVgap(12);
        inputGrid.setPadding(new Insets(10));


        // Labels
        inputGrid.add(new Label("Number of days:"), 0, 0);
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


        // ---------------------------------------------------------
        // CALCULATE BUTTON
        // ---------------------------------------------------------

        Button calculateButton = new Button("Calculate Expenses");

        calculateButton.getStyleClass().add("calculate-button");

        calculateButton.setOnAction(event -> calculateExpenses());


        // ---------------------------------------------------------
        // OUTPUT LABELS
        // ---------------------------------------------------------

        Label resultsTitle = new Label("Results");
        resultsTitle.getStyleClass().add("results-title");


        totalExpensesLabel = new Label("Total expenses: $0.00");

        allowableExpensesLabel =
                new Label("Total allowable expenses: $0.00");

        excessLabel =
                new Label("Excess paid by businessperson: $0.00");

        amountSavedLabel =
                new Label("Amount saved: $0.00");


        // ---------------------------------------------------------
        // RESULTS CONTAINER
        // ---------------------------------------------------------

        VBox resultsBox = new VBox(
                8,
                resultsTitle,
                totalExpensesLabel,
                allowableExpensesLabel,
                excessLabel,
                amountSavedLabel
        );

        resultsBox.getStyleClass().add("results-box");


        // ---------------------------------------------------------
        // MAIN LAYOUT
        // ---------------------------------------------------------

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


        // ---------------------------------------------------------
        // SCENE
        // ---------------------------------------------------------

        Scene scene = new Scene(root, 600, 750);


        // ---------------------------------------------------------
        // CSS STYLING
        // ---------------------------------------------------------
        //
        // For now, the CSS is added directly to the scene.
        // This keeps the project to ONE Java file.
        //

        String css = """
                .root {
                    -fx-background-color: #f4f6f8;
                }

                .title {
                    -fx-font-size: 26px;
                    -fx-font-weight: bold;
                }

                .subtitle {
                    -fx-font-size: 14px;
                }

                .text-field {
                    -fx-pref-width: 180px;
                }

                .calculate-button {
                    -fx-font-size: 15px;
                    -fx-padding: 10px 25px;
                }

                .results-title {
                    -fx-font-size: 20px;
                    -fx-font-weight: bold;
                }

                .results-box {
                    -fx-padding: 15px;
                    -fx-border-width: 1px;
                    -fx-border-radius: 5px;
                    -fx-background-radius: 5px;
                }
                """;


        // ---------------------------------------------------------
        // ADD CSS
        // ---------------------------------------------------------

        scene.getStylesheets().add(
                "data:text/css," + css.replace("\n", "%0A")
        );


        // ---------------------------------------------------------
        // SHOW WINDOW
        // ---------------------------------------------------------

        stage.setTitle("Business Travel Expenses Calculator");
        stage.setScene(scene);
        stage.show();
    }


    // ---------------------------------------------------------
    // CREATE TEXT FIELD
    // ---------------------------------------------------------

    private TextField createTextField(String prompt) {

        TextField field = new TextField();

        field.setPromptText(prompt);

        field.setPrefWidth(180);

        field.getStyleClass().add("text-field");

        return field;
    }


    // ---------------------------------------------------------
    // CALCULATE EXPENSES
    // ---------------------------------------------------------

    private void calculateExpenses() {

        try {

            // -----------------------------------------------------
            // GET VALUES FROM INPUT FIELDS
            // -----------------------------------------------------

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


            // -----------------------------------------------------
            // VALIDATION
            // -----------------------------------------------------

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


            // -----------------------------------------------------
            // TOTAL ACTUAL EXPENSES
            // -----------------------------------------------------

            double mealsActual =
                    numberOfDays * MEAL_ALLOWANCE;

            double vehicleExpense =
                    milesDriven * PRIVATE_VEHICLE_RATE;

            double lodgingActual =
                    numberOfDays * lodgingPerNight;


            double totalExpenses =
                    airfare
                    + carRental
                    + vehicleExpense
                    + parking
                    + taxi
                    + seminar
                    + lodgingActual
                    + mealsActual;


            // -----------------------------------------------------
            // ALLOWABLE EXPENSES
            // -----------------------------------------------------

            double allowableMeals =
                    numberOfDays * MEAL_ALLOWANCE;

            double allowableParking =
                    Math.min(parking,
                            numberOfDays * PARKING_ALLOWANCE);

            double allowableTaxi =
                    Math.min(taxi,
                            numberOfDays * TAXI_ALLOWANCE);

            double allowableLodging =
                    Math.min(lodgingActual,
                            numberOfDays * LODGING_ALLOWANCE);

            double allowableVehicle =
                    vehicleExpense;


            double totalAllowableExpenses =
                    airfare
                    + carRental
                    + allowableVehicle
                    + allowableMeals
                    + allowableParking
                    + allowableTaxi
                    + seminar
                    + allowableLodging;


            // -----------------------------------------------------
            // EXCESS AND SAVINGS
            // -----------------------------------------------------

            double excess =
                    Math.max(0,
                            totalExpenses - totalAllowableExpenses);

            double saved =
                    Math.max(0,
                            totalAllowableExpenses - totalExpenses);


            // -----------------------------------------------------
            // DISPLAY RESULTS
            // -----------------------------------------------------

            totalExpensesLabel.setText(
                    String.format(
                            "Total expenses: $%.2f",
                            totalExpenses
                    )
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

            showError(
                    "Please enter valid numbers in all fields."
            );
        }
    }


    // ---------------------------------------------------------
    // ERROR MESSAGE
    // ---------------------------------------------------------

    private void showError(String message) {

        totalExpensesLabel.setText("Error: " + message);

        allowableExpensesLabel.setText("");
        excessLabel.setText("");
        amountSavedLabel.setText("");
    }


    // ---------------------------------------------------------
    // MAIN
    // ---------------------------------------------------------

    public static void main(String[] args) {

        launch();
    }
}
