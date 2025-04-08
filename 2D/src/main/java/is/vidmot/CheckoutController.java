package is.vidmot;

import is.vinnsla.CheckoutData;
import is.vinnsla.Receit;
import is.vinnsla.ReceitData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

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
        fxCompleteButton.disableProperty().bind(
            Bindings.createBooleanBinding(
                () -> fxNameField.getText().trim().isEmpty() || fxEmailField.getText().trim().isEmpty(),
                fxNameField.textProperty(),
                fxEmailField.textProperty()
            )
        );
        // Retrieve the values from CheckoutData
        int numTickets = CheckoutData.getNumTickets();
        int totalCost = CheckoutData.getTotalCost();
        String nameOfTour = CheckoutData.getNameOfTour();
        LocalDate dateOfTour = CheckoutData.getDateOfTour();

        // Update the labels
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
        String tourName = fxNameOfTour.getText().replace("Name of Tour: ", "").trim();
        String dateOfTour = fxDateOfTour.getText().replace("Date of Tour: ", "").trim();
        int numTickets = Integer.parseInt(fxNumTickets.getText().replace("Number of Tickets: ", "").trim());
        int totalCost = Integer.parseInt(fxTotalCost.getText().replace("Total Cost: ", "").replace(" ISK", "").trim());

        // Assuming ticketPrice is totalCost divided by numTickets
        int ticketPrice = totalCost / numTickets;

        // Create a new Receit object
        Receit receit = new Receit(
                tourName, // Name of the tour
                TourController.getSelectedTour().getDuration(), // Duration (if not available, set to 0 or retrieve from another source)
                TourController.getSelectedTour().getLocation(), // Location (if not available, set to an empty string or retrieve from another source)
                TourController.getSelectedTour().getDescription(), // Description (if not available, set to an empty string or retrieve from another source)
                LocalDate.parse(dateOfTour), // Date of the tour
                TourController.getSelectedTour().getStartTime(), // Start time (if not available, set to null or retrieve from another source)
                ticketPrice, // Ticket price
                username, // Username
                email, // Email
                numTickets, // Number of tickets
                totalCost // Total cost
        );
        ReceitData.setReceit(receit);
        ViewSwitcher.switchTo(View.RECEIT, false);

        // Add logic to save the receit or process it further
        System.out.println("Purchase completed!");
    }
    @FXML
    protected void onForsida(javafx.event.ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.HEIMA, true);
    }
}
