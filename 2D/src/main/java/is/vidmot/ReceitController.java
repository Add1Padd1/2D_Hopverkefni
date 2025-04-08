package is.vidmot;

import is.vinnsla.Receit;
import is.vinnsla.ReceitData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReceitController {

    @FXML
    private Label fxTourName;

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
    private Label fxUsername;

    @FXML
    private Label fxEmail;

    @FXML
    private Label fxNumTickets;

    @FXML
    private Label fxTotalCost;
    @FXML
    public void initialize() {
        // Retrieve the Receit object from ReceitData
        Receit receit = ReceitData.getReceit();

        // Populate the labels with the Receit data
        fxTourName.setText(receit.getTourName());
        fxDuration.setText(receit.getDuration() + " minutes");
        fxLocation.setText(receit.getLocation());
        fxDescription.setText(receit.getDescription());
        fxDate.setText(receit.getDate().toString());
        fxStartTime.setText(receit.getStartTime().toString());
        fxTicketPrice.setText(receit.getTicketPrice() + " ISK");
        fxUsername.setText(receit.getUsername());
        fxEmail.setText(receit.getEmail());
        fxNumTickets.setText(String.valueOf(receit.getNumTickets()));
        fxTotalCost.setText(receit.getTotalCost() + " ISK");
    }
    @FXML
    protected void onBack(javafx.event.ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.HEIMA, true);
    }

}
