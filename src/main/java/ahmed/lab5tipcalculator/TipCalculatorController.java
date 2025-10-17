package ahmed.lab5tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15); // default 15%

    @FXML private TextField amountTextField;
    @FXML private Label tipPercentageLabel;
    @FXML private Slider tipPercentageSlider;
    @FXML private TextField tipTextField;
    @FXML private TextField totalTextField;

    @FXML
    private void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

  
        tipPercentageLabel.textProperty().bind(
                Bindings.format("%.0f%%", tipPercentageSlider.valueProperty())
        );

      
        amountTextField.textProperty().addListener((obs, oldVal, newVal) -> calculate());
        tipPercentageSlider.valueProperty().addListener((obs, oldVal, newVal) -> calculate());

    
        calculate();
    }

    private void calculate() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal sliderValue = BigDecimal.valueOf(tipPercentageSlider.getValue() / 100.0);
            tipPercentage = sliderValue;

            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            // Reset to zero if input is invalid
            tipTextField.setText(currency.format(0));
            totalTextField.setText(currency.format(0));
        }
    }
}
