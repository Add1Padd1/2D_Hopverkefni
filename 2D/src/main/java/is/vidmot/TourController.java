package is.vidmot;

import is.vinnsla.CheckoutData;
import is.vinnsla.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;

public class TourController  {

    @FXML
    private Spinner<Integer> fxTicketSpinner;
    @FXML
    private ImageView fxImage;
    @FXML
    private Label fxName;
    @FXML
    private Label fxDuration;
    @FXML
    private Label fxLocation;
    @FXML
    private Label fxDescription;
    @FXML
    private Label fxDate;
    @FXML
    private Label fxStartTime;
    @FXML
    private Label fxTicketPrice;
    @FXML
    private Label fxTicketsLeft;
    @FXML
    private Button fxBuyButton;


    private static Tour selectedTour;

    public static void setSelectedTour(Tour tour) {
        selectedTour = tour;
    }
    public static Tour getSelectedTour() {
        return selectedTour;
    }

    @FXML
    public void initialize() {
        if (selectedTour != null) {
            displayTour(selectedTour);
        } else {
            clearDisplay();
        }
    }

    private void displayTour(Tour tour) {
        fxImage.setImage(tour.getImage());
        fxName.setText(tour.getName());
        fxLocation.setText(tour.getLocation());
        fxDuration.setText(tour.getDuration() + " mín.");
        fxDescription.setText(tour.getDescription());
        fxDate.setText(tour.getDate().toString());
        fxStartTime.setText(tour.getStartTime().toString());
        fxTicketPrice.setText(tour.getTicketPrice() + " kr.");
        fxTicketsLeft.setText(String.valueOf(tour.getNumTickets()));

        int ticketsAvailable = tour.getNumTickets();

        if (ticketsAvailable <= 0) {
            // engnir miðar eftir
            fxTicketsLeft.setText("Sold Out!");
            fxTicketsLeft.setStyle("-fx-text-fill: red;");
            fxTicketSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
            fxTicketSpinner.setDisable(true);
            // Disable the buy button
            fxBuyButton.setDisable(true);
        } else {
            // ef eru til miðar
            fxTicketsLeft.setStyle(""); // Reset style if previously red
            SpinnerValueFactory<Integer> valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, ticketsAvailable, 1);
            fxTicketSpinner.setValueFactory(valueFactory);
            fxTicketSpinner.setDisable(false);
            fxBuyButton.setDisable(false);
        }
    }

    private void clearDisplay() {
        fxImage.setImage(null);
        fxName.setText("No Tour Selected");
        fxLocation.setText("");
        fxDuration.setText("");
        fxDescription.setText("");
        fxDate.setText("");
        fxStartTime.setText("");
        fxTicketPrice.setText("");
        fxTicketsLeft.setText("");
        if (fxTicketSpinner != null) {
            fxTicketSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
            fxTicketSpinner.setDisable(true);
        }
        if (fxBuyButton != null) {
            fxBuyButton.setDisable(true);
        }
    }


    @FXML
    protected void onHeim(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.HEIMA, true);
    }

    @FXML
    protected void onBuy(ActionEvent actionEvent) {
        if (selectedTour == null) {
            System.err.println("Buy button clicked, but no tour is selected.");
            return;
        }

        int numberOfTickets = fxTicketSpinner.getValue();

        if (numberOfTickets > selectedTour.getNumTickets()) {
            System.err.println("Attempting to buy " + numberOfTickets + " tickets, but only " + selectedTour.getNumTickets() + " are available.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Availability Changed");
            alert.setHeaderText("Ticket count updated");
            alert.setContentText("The maximum number of tickets currently available for " + selectedTour.getName() + " is " + selectedTour.getNumTickets() + ".");
            alert.showAndWait();
            displayTour(selectedTour);
            return;
        }


        int totalCost = numberOfTickets * selectedTour.getTicketPrice();

        CheckoutData.setNumTickets(numberOfTickets);
        CheckoutData.setTotalCost(totalCost);
        CheckoutData.setNameOfTour(selectedTour.getName());
        CheckoutData.setDateOfTour(selectedTour.getDate());

        ViewSwitcher.switchTo(View.CHECKOUT, false);
    }
}
