package is.vidmot;

import is.vinnsla.CheckoutData;
import is.vinnsla.Receit;
import is.vinnsla.ReceitData;
import is.vinnsla.Tour;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;


import java.time.LocalDate;

public class CheckoutController {
    @FXML
    private Label fxNumTickets;
    @FXML
    private Label fxTotalCost;
    @FXML
    private Label fxNameOfTour;
    @FXML
    private Label fxDateOfTour;
    @FXML
    private Button fxCompleteButton;
    @FXML
    private TextField fxNameField;
    @FXML
    private TextField fxEmailField;


    @FXML
    public void initialize() {
        // Button disable binding
        fxCompleteButton.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> fxNameField.getText().trim().isEmpty() || fxEmailField.getText().trim().isEmpty(),
                        fxNameField.textProperty(),
                        fxEmailField.textProperty()
                )
        );
        // Retrieve values from CheckoutData and update labels
        int numTickets = CheckoutData.getNumTickets();
        int totalCost = CheckoutData.getTotalCost();
        String nameOfTour = CheckoutData.getNameOfTour();
        LocalDate dateOfTour = CheckoutData.getDateOfTour();
        fxNumTickets.setText(String.valueOf(numTickets));
        fxTotalCost.setText(totalCost + " ISK");
        fxNameOfTour.setText(nameOfTour);
        fxDateOfTour.setText(String.valueOf(dateOfTour));
    }

    @FXML
    protected void onCompletePurchase(ActionEvent actionEvent){
        // Retrieve values from the fields
        String username = fxNameField.getText();
        String email = fxEmailField.getText();
        String tourName = fxNameOfTour.getText();
        String dateOfTourStr = fxDateOfTour.getText();
        int numTickets = CheckoutData.getNumTickets();
        int totalCost = CheckoutData.getTotalCost();

        // fækka miða
        Tour selectedTour = TourController.getSelectedTour();

        if (selectedTour != null) {
            boolean decreaseSuccess = selectedTour.decreaseTickets(numTickets);
            //error
            if (!decreaseSuccess) {
                System.err.println("Checkout failed: Could not decrease the number of tickets for " + selectedTour.getName());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Booking Error");
                alert.setHeaderText("Ticket Availability Problem");
                alert.setContentText("Unfortunately, the requested number of tickets ("+ numTickets +") are no longer available for the tour: " + selectedTour.getName() + ". Please go back and select fewer tickets or a different tour.");
                alert.showAndWait();
                return;
            }
        } else {
            System.err.println("Critical Error during checkout: Cannot find the selected tour to decrease tickets.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("System Error");
            alert.setHeaderText("Could not complete purchase");
            alert.setContentText("A system error occurred (selected tour not found). Please try again later.");
            alert.showAndWait();
            return;
        }


        // Create a new Receit object
        int ticketPrice = (numTickets > 0) ? totalCost / numTickets : 0;
        Receit receit = new Receit(
                selectedTour.getName(),
                selectedTour.getDuration(),
                selectedTour.getLocation(),
                selectedTour.getDescription(),
                selectedTour.getDate(),
                selectedTour.getStartTime(),
                ticketPrice,
                username,
                email,
                numTickets,
                totalCost
        );
        ReceitData.setReceit(receit);
        ViewSwitcher.switchTo(View.RECEIT, false);

        System.out.println("Purchase completed! Tickets decreased for: " + selectedTour.getName());
    }

    @FXML
    protected void onForsida(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.HEIMA, true);
    }
}
