package is.vidmot;

import is.vinnsla.Tour;
import is.vinnsla.Tourlisti;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;

import java.time.LocalDate;

public class HomeController  {

    private Tourlisti tourlisti = new Tourlisti();

    @FXML
    private GridPane fxGrid;
    @FXML
    private ScrollPane fxScrollPane;
    @FXML
    private TextField fxLocationFilter;
    @FXML
    private DatePicker fxDateFilter;
    @FXML
    private Button fxSearchButton;
    @FXML
    private Button fxClearFilterButton;

    @FXML
    private TextField fxMinDurationFilter;
    @FXML
    private TextField fxMaxPriceFilter;


    @FXML
    public void initialize() {
        tourlisti.buaTilTours();
        populateGrid(tourlisti.getListiTilSynis());
        fxSearchButton.setOnAction(event -> applyFilter());
        fxClearFilterButton.setOnAction(event -> clearFilter());

        tourlisti.getListiTilSynis().addListener((ListChangeListener<Tour>) c -> {
            System.out.println("List changed, repopulating grid...");
            populateGrid(tourlisti.getListiTilSynis());
        });

        fxLocationFilter.setOnAction(event -> applyFilter());
        fxMinDurationFilter.setOnAction(event -> applyFilter());
        fxMaxPriceFilter.setOnAction(event -> applyFilter());
    }

    private void populateGrid(ObservableList<Tour> toursToDisplay) {
        int row = 0;
        int col = 0;

        fxGrid.getChildren().clear();

        if (toursToDisplay.isEmpty()) {
            fxGrid.add(new Label("Engar ferðir fundust með þessum skilyrðum."), 0, 0, 2, 1); // Span across 2 columns
            return; // heldur ekki áfram ef listinn er tómur
        }


        for (Tour tour : toursToDisplay) {
            VBox tourBox = new VBox(10);
            tourBox.setAlignment(javafx.geometry.Pos.CENTER);
            ImageView imageView = new ImageView();
            try {
                String imagePath = "/is/vidmot/media/" + tour.getPicture();
                Image img = new Image(getClass().getResourceAsStream(imagePath));
                if (img.isError()) {
                    System.err.println("Error loading image: " + imagePath + " - " + img.getException());
                    throw new NullPointerException("Image loading error");
                }
                imageView.setImage(img);

            } catch (Exception e) {
                System.err.println("Using placeholder for tour: " + tour.getName() + ". Error: " + e.getMessage());
                try {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/is/vidmot/media/placeholder.jpg")));
                } catch (Exception placeholderEx) {
                    System.err.println("FATAL: Could not load placeholder image!");
                }
            }

            imageView.setFitHeight(60);
            imageView.setFitWidth(80);
            Label nameLabel = new Label(tour.getName());
            nameLabel.getStyleClass().add("tour-name");
            Label descriptionLabel = new Label(tour.getDescription());
            descriptionLabel.getStyleClass().add("tour-description");
            descriptionLabel.setWrapText(true);
            Button selectButton = new Button("Select Tour");
            selectButton.setOnAction(event -> {
                Tour buttonTourData = (Tour) selectButton.getUserData();
                Tour selectedTour = Tourlisti.chooseTour(buttonTourData.getName());

                if (selectedTour != null) {
                    System.out.println("Selected Tour: " + selectedTour.getName());
                    TourController.setSelectedTour(selectedTour);
                    ViewSwitcher.switchTo(View.LISTI, false);
                } else {
                    System.out.println("Error: Could not find selected tour in master list.");
                }
            });
            selectButton.setUserData(tour);
            tourBox.getChildren().addAll(imageView, nameLabel, descriptionLabel, selectButton);
            VBox.setMargin(tourBox, new Insets(10, 10, 10, 10));
            fxGrid.add(tourBox, col, row);
            col++;
            if (col >= fxGrid.getColumnConstraints().size()) { // Gera þetta dynamic miðað við fjölda dálka
                col = 0;
                row++;
            }
        }
    }

    private void applyFilter() {
        String locationFilterText = fxLocationFilter.getText();
        LocalDate dateFilterValue = fxDateFilter.getValue();

        Integer minDurationValue = null;
        try {
            String durationText = fxMinDurationFilter.getText();
            if (durationText != null && !durationText.trim().isEmpty()) {
                minDurationValue = Integer.parseInt(durationText.trim());
                if (minDurationValue < 0) minDurationValue = null; // Ekki leyfa neikvæða tölu
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input for Min Duration: " + fxMinDurationFilter.getText() + ". Ignoring duration filter.");
            // minDurationValue helst null
        }

        Double maxPriceValue = null; // Byrja sem null
        try {
            String priceText = fxMaxPriceFilter.getText();
            if (priceText != null && !priceText.trim().isEmpty()) {
                maxPriceValue = Double.parseDouble(priceText.trim());
                if (maxPriceValue < 0) maxPriceValue = null; // Ekki leyfa neikvætt verð
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input for Max Price: " + fxMaxPriceFilter.getText() + ". Ignoring price filter.");
            // maxPriceValue helst null
        }

        System.out.println("Applying filter - Location: " + locationFilterText +
                ", Date: " + dateFilterValue +
                ", Min Duration: " + minDurationValue + " hrs" +
                ", Max Price: " + maxPriceValue + " ISK");

        // Kalla í filter aðferðina með ÖLLUM gildunum (eða null)
        tourlisti.filterTours(locationFilterText, dateFilterValue, minDurationValue, maxPriceValue);

    }

    private void clearFilter() {
        fxLocationFilter.clear();
        fxDateFilter.setValue(null);
        fxMinDurationFilter.clear();
        fxMaxPriceFilter.clear();

        System.out.println("Clearing filter...");
        tourlisti.clearFilter();

    }
}
