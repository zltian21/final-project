package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.ss.formula.functions.FinanceLib;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

	private RetirementApp mainApp = null;
	@FXML
	private TextField txtSaveEachMonth;
	@FXML
	private TextField txtYearsToWork;
	@FXML
	private TextField txtAnnualReturnWorking;
	@FXML
	private TextField txtWhatYouNeedToSave;
	@FXML
	private TextField txtYearsRetired;
	@FXML
	private TextField txtAnnualReturnRetired;
	@FXML
	private TextField txtRequiredIncome;
	@FXML
	private TextField txtMonthlySSI;

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");
		txtSaveEachMonth.clear();
		txtYearsToWork.clear();
		txtAnnualReturnWorking.clear();
		txtWhatYouNeedToSave.clear();
		txtYearsRetired.clear();
		txtAnnualReturnRetired.clear();
		txtRequiredIncome.clear();
		txtMonthlySSI.clear();
		txtSaveEachMonth.setDisable(true);
		txtWhatYouNeedToSave.setDisable(true);
		txtYearsToWork.setDisable(false);
		txtAnnualReturnWorking.setDisable(false);
		txtYearsRetired.setDisable(false);
		txtAnnualReturnRetired.setDisable(false);
		txtRequiredIncome.setDisable(false);
		txtMonthlySSI.setDisable(false);
	}

	@FXML
	public void btnCalculate(ActionEvent event) {

		if (isInputValid2()) {

			txtSaveEachMonth.setDisable(false);
			txtWhatYouNeedToSave.setDisable(false);

			double dAnnualReturnRetired = Double.parseDouble(txtAnnualReturnRetired.getText());
			int iYearsRetired = Integer.parseInt(txtYearsRetired.getText());
			double dRequiredIncome = Double.parseDouble(txtRequiredIncome.getText());
			double dMonthlySSI = Double.parseDouble(txtMonthlySSI.getText());
			double dAnnualReturnWorking = Double.parseDouble(txtAnnualReturnWorking.getText());
			int iYearsToWork = Integer.parseInt(txtYearsToWork.getText());

			Retirement retirement = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired,
					dAnnualReturnRetired, dRequiredIncome, dMonthlySSI);

			double pv = retirement.TotalAmountSaved();

			double pmt = retirement.AmountToSave();

			txtWhatYouNeedToSave.setText(Double.toString(pv));
			txtSaveEachMonth.setText(Double.toString(pmt));
			txtYearsToWork.setDisable(true);
			txtAnnualReturnWorking.setDisable(true);
			txtYearsRetired.setDisable(true);
			txtAnnualReturnRetired.setDisable(true);
			txtRequiredIncome.setDisable(true);
			txtMonthlySSI.setDisable(true);
		}
	}

	private boolean isInputValid2() {
		String errorMessage = "";
		try {
			if (txtYearsToWork.getText() == null || txtYearsToWork.getText().length() == 0
					|| Integer.parseInt(txtYearsToWork.getText()) < 0) {
				errorMessage += "The number of years to work must be a whole number (an integer greater than or equal to 0).  "
						+ "Only numbers are accepted as input. \n\n";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The number of years to work must be a whole number (an integer greater than or equal to 0).  "
					+ "Only numbers are accepted as input. \n\n";
		}

		// I believe this assignment wanted us to check to make sure the annual working
		// return
		// rate is between 0 and 20. The assignment said "Use a range of 0-20% for
		// annual return when in investment mode.
		try {
			if (txtAnnualReturnWorking.getText() == null || txtAnnualReturnWorking.getText().length() == 0
					|| Double.parseDouble(txtAnnualReturnWorking.getText()) < 0
					|| Double.parseDouble(txtAnnualReturnWorking.getText()) > 20) {
				errorMessage += "The working annual return must be a number greater than or equal to 0"
						+ " and less than or equal to 20.  "
						+ "Only numbers and one decimal point are accepted as input. \n\n";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The working annual return must be a number greater than or equal to 0"
					+ " and less than or equal to 20.  "
					+ "Only numbers and one decimal point are accepted as input. \n\n";
		}

		try {
			if (txtYearsRetired.getText() == null || txtYearsRetired.getText().length() == 0
					|| Integer.parseInt(txtYearsRetired.getText()) < 0) {
				errorMessage += "The number of years retired must be a whole number (an integer greater than or equal to 0).  "
						+ "Only numbers are accepted as input. \n\n";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The number of years retired must be a whole number (an integer greater than or equal to 0).  "
					+ "Only numbers are accepted as input. \n\n";
		}

		// I believe this assignment wanted us to check to make sure the annual retired
		// return
		// rate is between 0% and 3%. The assignment said "Use a range of 0-3% for
		// annual return when in payback mode."
		try {
			if (txtAnnualReturnRetired.getText() == null || txtAnnualReturnRetired.getText().length() == 0
					|| Double.parseDouble(txtAnnualReturnRetired.getText()) < 0
					|| Double.parseDouble(txtAnnualReturnRetired.getText()) > 3)

			{
				errorMessage += "The retired annual return must be a number greater than or equal to 0 "
						+ "and less than or equal to 3.  "
						+ "Only numbers and one decimal point are accepted as input. \n\n";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The retired annual return must be a number greater than or equal to 0 "
					+ "and less than or equal to 3.  "
					+ "Only numbers and one decimal point are accepted as input. \n\n";
		}

		try {
			if (txtRequiredIncome.getText() == null || txtRequiredIncome.getText().length() == 0
					|| Double.parseDouble(txtRequiredIncome.getText()) < 0) {
				errorMessage += "The required income must be a number greater than or equal to 0.  "
						+ "Only numbers and one decimal point are accepted as input. \n\n";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The required income must be a number greater than or equal to 0.  "
					+ "Only numbers and one decimal point are accepted as input. \n\n";
		}

		try {
			if (txtMonthlySSI.getText() == null || txtMonthlySSI.getText().length() == 0
					|| Double.parseDouble(txtMonthlySSI.getText()) < 0) {
				errorMessage += "The monthly SSI must be a number greater than or equal to 0.  "
						+ "Only numbers and one decimal point are accepted as input. \n\n";
			}
		} catch (NumberFormatException e) {
			errorMessage += "The monthly SSI must be a number greater than or equal to 0.  "
					+ "Only numbers and one decimal point are accepted as input. \n\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct the invalid field(s).");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
