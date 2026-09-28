package mossimo.bianco.lab05;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Task 01 - Order Form
 * Task 02 - Restaurant Bill
 */
public class App extends Application {

    private static final double TAX_RATE = 0.05;

    private ComboBox<String> beverageComboBox;
    private ComboBox<String> appetizerComboBox;
    private ComboBox<String> mainCourseComboBox;
    private ComboBox<String> dessertComboBox;

    private Label subtotalLabel;
    private Label taxLabel;
    private Label tipLabel;
    private Label totalLabel;

    private Slider tipSlider;

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        showBagOrderScreen();
        stage.show();
    }

    private void showBagOrderScreen() {
        Label titleLabel = new Label("Bag Order Form");
        titleLabel.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e1b4b;"
        );

        Label subtitleLabel = new Label("Choose your bag, quantity, and size");
        subtitleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );

        ListView<String> bagList = new ListView<>();
        bagList.getItems().addAll(
                "Full Decorative",
                "Beaded",
                "Pirate Design",
                "Fringed",
                "Leather",
                "Plain"
        );

        bagList.setPrefHeight(180);
        bagList.setStyle(
                "-fx-background-color: rgba(255,255,255,0.88);" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #c7d2fe;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 18px;" +
                "-fx-effect: dropshadow(gaussian, rgba(79,70,229,0.16), 18, 0.25, 0, 7);"
        );

        Label quantityLabel = new Label("Quantity");
        quantityLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );

        ComboBox<Integer> quantityComboBox = new ComboBox<>();

        for (int i = 1; i <= 10; i++) {
            quantityComboBox.getItems().add(i);
        }

        quantityComboBox.setPromptText("Select quantity");
        quantityComboBox.setMaxWidth(Double.MAX_VALUE);
        quantityComboBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.92);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #c7d2fe;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 12px;" +
                "-fx-font-size: 14px;"
        );

        Label sizeLabel = new Label("Bag Size");
        sizeLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );

        RadioButton smallButton = new RadioButton("Small");
        RadioButton mediumButton = new RadioButton("Medium");
        RadioButton largeButton = new RadioButton("Large");

        ToggleGroup sizeGroup = new ToggleGroup();

        smallButton.setToggleGroup(sizeGroup);
        mediumButton.setToggleGroup(sizeGroup);
        largeButton.setToggleGroup(sizeGroup);

        smallButton.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #334155;"
        );

        mediumButton.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #334155;"
        );

        largeButton.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #334155;"
        );

        VBox sizeBox = new VBox(10);
        sizeBox.setPadding(new Insets(14));
        sizeBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.72);" +
                "-fx-background-radius: 15px;" +
                "-fx-border-color: #e0e7ff;" +
                "-fx-border-radius: 15px;"
        );

        sizeBox.getChildren().addAll(
                smallButton,
                mediumButton,
                largeButton
        );

        Label messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setMaxWidth(Double.MAX_VALUE);
        messageLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #15803d;" +
                "-fx-padding: 8px;"
        );

        Button orderButton = new Button("Place Order");

        orderButton.setMaxWidth(Double.MAX_VALUE);
        orderButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4f46e5, #6366f1);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 14px;" +
                "-fx-padding: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(79,70,229,0.35), 12, 0.25, 0, 5);"
        );

        orderButton.setOnAction(event -> {
            String bagType = bagList.getSelectionModel().getSelectedItem();
            Integer quantity = quantityComboBox.getValue();
            RadioButton selectedSize =
                    (RadioButton) sizeGroup.getSelectedToggle();

            if (bagType == null) {
                messageLabel.setText("Please select a bag type.");
                messageLabel.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #dc2626;" +
                        "-fx-padding: 8px;"
                );
                return;
            }

            if (quantity == null) {
                messageLabel.setText("Please select a quantity.");
                messageLabel.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #dc2626;" +
                        "-fx-padding: 8px;"
                );
                return;
            }

            if (selectedSize == null) {
                messageLabel.setText("Please select a bag size.");
                messageLabel.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #dc2626;" +
                        "-fx-padding: 8px;"
                );
                return;
            }

            String size = selectedSize.getText();

            messageLabel.setText(
                    "You ordered " +
                    quantity +
                    " " +
                    size +
                    " " +
                    bagType +
                    " Bags."
            );

            messageLabel.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #15803d;" +
                    "-fx-padding: 8px;"
            );
        });

        Button clearButton = new Button("Clear");

        clearButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setStyle(
                "-fx-background-color: rgba(255,255,255,0.85);" +
                "-fx-text-fill: #475569;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #cbd5e1;" +
                "-fx-border-radius: 14px;" +
                "-fx-padding: 12px;" +
                "-fx-cursor: hand;"
        );

        clearButton.setOnAction(event -> {
            bagList.getSelectionModel().clearSelection();
            quantityComboBox.setValue(null);

            if (sizeGroup.getSelectedToggle() != null) {
                sizeGroup.getSelectedToggle().setSelected(false);
            }

            messageLabel.setText("");
        });

        Button restaurantButton = new Button("Go to Restaurant Bill →");

        restaurantButton.setMaxWidth(Double.MAX_VALUE);
        restaurantButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #4f46e5;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        restaurantButton.setOnAction(event -> showRestaurantScreen());

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
                orderButton,
                clearButton,
                restaurantButton
        );

        VBox root = new VBox(13);

        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(440);

        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to bottom right, #f8fafc, #e0e7ff" +
                ");"
        );

        root.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                bagList,
                quantityLabel,
                quantityComboBox,
                sizeLabel,
                sizeBox,
                buttonBox,
                messageLabel
        );

        Scene scene = new Scene(root, 440, 720);

        stage.setTitle("Bag Order Form");
        stage.setScene(scene);
    }

    private void showRestaurantScreen() {
        Label titleLabel = new Label("Restaurant Bill");
        titleLabel.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e1b4b;"
        );

        Label subtitleLabel = new Label(
                "Select your items and choose your tip"
        );

        subtitleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );

        beverageComboBox = new ComboBox<>();
        beverageComboBox.getItems().addAll(
                "Coffee - $2.50",
                "Tea - $2.00",
                "Soft Drink - $1.75",
                "Water - $2.95",
                "Milk - $1.50",
                "Juice - $2.50"
        );

        appetizerComboBox = new ComboBox<>();
        appetizerComboBox.getItems().addAll(
                "Soup - $4.50",
                "Salad - $3.75",
                "Spring Rolls - $5.25",
                "Garlic Bread - $3.00",
                "Chips and Salsa - $6.95"
        );

        mainCourseComboBox = new ComboBox<>();
        mainCourseComboBox.getItems().addAll(
                "Steak - $15.00",
                "Grilled Chicken - $13.50",
                "Chicken Alfredo - $13.95",
                "Turkey Club - $11.90",
                "Shrimp Scampi - $18.99",
                "Pasta - $11.75",
                "Fish and Chips - $12.25"
        );

        dessertComboBox = new ComboBox<>();
        dessertComboBox.getItems().addAll(
                "Apple Pie - $5.95",
                "Carrot Cake - $4.50",
                "Mud Pie - $4.75",
                "Pudding - $3.25",
                "Apple Crisp - $5.98"
        );

        styleComboBox(beverageComboBox);
        styleComboBox(appetizerComboBox);
        styleComboBox(mainCourseComboBox);
        styleComboBox(dessertComboBox);

        Label beverageLabel = new Label("Beverage");
        Label appetizerLabel = new Label("Appetizer");
        Label mainCourseLabel = new Label("Main Course");
        Label dessertLabel = new Label("Dessert");

        styleSectionLabel(beverageLabel);
        styleSectionLabel(appetizerLabel);
        styleSectionLabel(mainCourseLabel);
        styleSectionLabel(dessertLabel);

        GridPane menuGrid = new GridPane();

        menuGrid.setHgap(14);
        menuGrid.setVgap(14);
        menuGrid.setPadding(new Insets(20));

        menuGrid.setStyle(
                "-fx-background-color: rgba(255,255,255,0.75);" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #c7d2fe;" +
                "-fx-border-radius: 18px;" +
                "-fx-effect: dropshadow(gaussian, rgba(79,70,229,0.15), 18, 0.25, 0, 7);"
        );

        menuGrid.add(beverageLabel, 0, 0);
        menuGrid.add(beverageComboBox, 1, 0);
        menuGrid.add(appetizerLabel, 0, 1);
        menuGrid.add(appetizerComboBox, 1, 1);
        menuGrid.add(mainCourseLabel, 0, 2);
        menuGrid.add(mainCourseComboBox, 1, 2);
        menuGrid.add(dessertLabel, 0, 3);
        menuGrid.add(dessertComboBox, 1, 3);

        tipSlider = new Slider(0, 20, 0);

        tipSlider.setShowTickLabels(true);
        tipSlider.setShowTickMarks(true);
        tipSlider.setMajorTickUnit(5);
        tipSlider.setMinorTickCount(4);
        tipSlider.setBlockIncrement(1);
        tipSlider.setMaxWidth(Double.MAX_VALUE);

        tipSlider.setStyle(
                "-fx-control-inner-background: #6366f1;" +
                "-fx-accent: #4f46e5;"
        );

        Label tipTitle = new Label("Tip");
        tipTitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );

        VBox tipBox = new VBox(8);
        tipBox.setPadding(new Insets(15));

        tipBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.72);" +
                "-fx-background-radius: 15px;" +
                "-fx-border-color: #e0e7ff;" +
                "-fx-border-radius: 15px;"
        );

        tipBox.getChildren().addAll(
                tipTitle,
                tipSlider
        );

        subtotalLabel = new Label("$0.00");
        taxLabel = new Label("$0.00");
        tipLabel = new Label("$0.00");
        totalLabel = new Label("$0.00");

        styleAmountLabel(subtotalLabel);
        styleAmountLabel(taxLabel);
        styleAmountLabel(tipLabel);

        totalLabel.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4f46e5;"
        );

        GridPane billGrid = new GridPane();

        billGrid.setHgap(30);
        billGrid.setVgap(10);
        billGrid.setPadding(new Insets(20));

        billGrid.setStyle(
                "-fx-background-color: rgba(255,255,255,0.88);" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #c7d2fe;" +
                "-fx-border-radius: 18px;"
        );

        Label subtotalText = new Label("Subtotal");
        Label taxText = new Label("Tax");
        Label tipText = new Label("Tip");
        Label totalText = new Label("Total");

        styleSectionLabel(subtotalText);
        styleSectionLabel(taxText);
        styleSectionLabel(tipText);
        styleSectionLabel(totalText);

        billGrid.add(subtotalText, 0, 0);
        billGrid.add(subtotalLabel, 1, 0);
        billGrid.add(taxText, 0, 1);
        billGrid.add(taxLabel, 1, 1);
        billGrid.add(tipText, 0, 2);
        billGrid.add(tipLabel, 1, 2);
        billGrid.add(totalText, 0, 3);
        billGrid.add(totalLabel, 1, 3);

        beverageComboBox.setOnAction(event -> calculateBill());
        appetizerComboBox.setOnAction(event -> calculateBill());
        mainCourseComboBox.setOnAction(event -> calculateBill());
        dessertComboBox.setOnAction(event -> calculateBill());
        tipSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> calculateBill()
        );

        Button clearButton = new Button("Clear Bill");

        clearButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setStyle(
                "-fx-background-color: rgba(255,255,255,0.85);" +
                "-fx-text-fill: #475569;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #cbd5e1;" +
                "-fx-border-radius: 14px;" +
                "-fx-padding: 12px;" +
                "-fx-cursor: hand;"
        );

        clearButton.setOnAction(event -> {
            beverageComboBox.setValue(null);
            appetizerComboBox.setValue(null);
            mainCourseComboBox.setValue(null);
            dessertComboBox.setValue(null);
            tipSlider.setValue(0);

            subtotalLabel.setText("$0.00");
            taxLabel.setText("$0.00");
            tipLabel.setText("$0.00");
            totalLabel.setText("$0.00");
        });

        Button backButton = new Button("← Back to Bag Order");

        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #4f46e5;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(event -> showBagOrderScreen());

        VBox root = new VBox(14);

        root.setPadding(new Insets(28));
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(600);

        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to bottom right, #f8fafc, #e0e7ff" +
                ");"
        );

        root.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                menuGrid,
                tipBox,
                billGrid,
                clearButton,
                backButton
        );

        Scene scene = new Scene(root, 600, 680);

        stage.setTitle("Restaurant Bill");
        stage.setScene(scene);
    }

    private void calculateBill() {
        double subtotal = 0;

        subtotal += getPrice(beverageComboBox.getValue());
        subtotal += getPrice(appetizerComboBox.getValue());
        subtotal += getPrice(mainCourseComboBox.getValue());
        subtotal += getPrice(dessertComboBox.getValue());

        double tax = subtotal * TAX_RATE;

        double tipPercentage = tipSlider.getValue();
        double tip = subtotal * (tipPercentage / 100);

        double total = subtotal + tax + tip;

        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        tipLabel.setText(String.format("$%.2f", tip));
        totalLabel.setText(String.format("$%.2f", total));
    }

    private double getPrice(String item) {
        if (item == null) {
            return 0;
        }

        int dollarSign = item.lastIndexOf("$");

        return Double.parseDouble(
                item.substring(dollarSign + 1)
        );
    }

    private void styleComboBox(ComboBox<String> comboBox) {
        comboBox.setMaxWidth(Double.MAX_VALUE);

        comboBox.setPromptText("Select an item");

        comboBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.92);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #c7d2fe;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 12px;" +
                "-fx-font-size: 13px;"
        );
    }

    private void styleSectionLabel(Label label) {
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );
    }

    private void styleAmountLabel(Label label) {
        label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #475569;"
        );
    }

    public static void main(String[] args) {
        launch();
    }
}