package is.vidmot;

import is.vinnsla.CheckoutData;
import is.vinnsla.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;



public class TourController  {

    @FXML
    private Spinner<Integer> fxTicketSpinner;

    @FXML
    private ListView<Tour> fxListView;

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


    private static Tour selectedTour;


    /**
     * Sets the selected tour to be displayed.
     *
     * @param tour The selected tour.
     */
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
        }
    }

    /**
     * Displays the details of the given Tour in the UI.
     *
     * @param tour The Tour to display.
     */
    private void displayTour(Tour tour) {
        fxImage.setImage(tour.getImage());
        fxName.setText(tour.getName());
        fxLocation.setText(tour.getLocation());
        fxDuration.setText(tour.getDuration() + " mín.");
        fxDescription.setText(tour.getDescription());
        fxDate.setText(tour.getDate().toString());
        fxStartTime.setText(tour.getStartTime().toString());
        fxTicketPrice.setText(tour.getTicketPrice() + " kr.");
    }





    /**
     * Fara aftur í heima view. Ef spilari er til stöðva spilarann
     *
     * @param actionEvent ónotað
     */

    @FXML
    protected void onHeim(ActionEvent actionEvent) {
        // farðu í HEIMA senuna með ViewSwitcher
        ViewSwitcher.switchTo(View.HEIMA, true);
    }
    @FXML
    protected void onBuy(ActionEvent actionEvent) {
        int numberOfTickets = fxTicketSpinner.getValue();
        int totalCost = numberOfTickets * selectedTour.getTicketPrice();

        CheckoutData.setNumTickets(numberOfTickets);
        CheckoutData.setTotalCost(totalCost);
        CheckoutData.setNameOfTour(selectedTour.getName());
        CheckoutData.setDateOfTour(selectedTour.getDate());

        // Fara í checkout-view.fxml
        ViewSwitcher.switchTo(View.CHECKOUT, false);

    }







}


