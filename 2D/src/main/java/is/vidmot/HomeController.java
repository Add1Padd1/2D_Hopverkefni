package is.vidmot;

import is.vinnsla.Tour;
import is.vinnsla.Tourlisti;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

public class HomeController  {

    // fastar
    public static final String ASKRIFANDI = "Áskrifandi";
    private Tourlisti tourlisti = new Tourlisti();

    // viðmótshlutir
    @FXML
    private GridPane fxGrid;
    @FXML
    protected Button fxAskrifandi;


    @FXML
    public void initialize() {
        tourlisti.buaTilTours(); // Populate the tours list
        populateGrid();
    }

    private void populateGrid() {
        int row = 0;
        int col = 0;

        // Clear the GridPane to avoid duplicates
        fxGrid.getChildren().clear();
        for (Tour tour : tourlisti.getListi()) {
            // Create a VBox for each tour
            VBox tourBox = new VBox(10); // Add spacing between elements
            tourBox.setAlignment(javafx.geometry.Pos.CENTER);

            // Add an ImageView
            ImageView imageView = new ImageView();
            try {
                // Load the image dynamically from the Tour object
                imageView.setImage(new Image(getClass().getResourceAsStream("/is/vidmot/media/" + tour.getPicture())));
            } catch (Exception e) {
                System.err.println("Error loading image for tour: " + tour.getName() + " - " + e.getMessage());
                // Use a placeholder image if the image cannot be loaded
                imageView.setImage(new Image(getClass().getResourceAsStream("/is/vidmot/media/placeholder.jpg")));
            }
            imageView.setFitHeight(60);
            imageView.setFitWidth(80);

            // Add a Label for the name
            Label nameLabel = new Label(tour.getName());
            nameLabel.getStyleClass().add("tour-name");

            // Add a Label for the description
            Label descriptionLabel = new Label(tour.getDescription());
            descriptionLabel.getStyleClass().add("tour-description");
            descriptionLabel.setWrapText(true);

            // Add a Button below the tour
            Button selectButton = new Button("Select Tour");
            selectButton.setOnAction(event -> {
                // Get the selected Tour from the button's userData
                ViewSwitcher.switchTo(View.LISTI, false);
                Tour selectedTour = (Tour) selectButton.getUserData();
                String selectedTourName = selectedTour.getName();
                Tour tourFromList = Tourlisti.chooseTour(selectedTourName);
                if (tourFromList != null) {
                    selectedTour = tourFromList;
                } else {
                    System.out.println("Tour not found in the list.");
                }

                System.out.println("Selected Tour: " + selectedTour.getName());
                if (selectedTour != null) {
                    // Pass the selected Tour to ListiController
                    TourController.setSelectedTour(selectedTour);
                    System.out.println("Tour selected: " + selectedTour);
                    // Switch to the tour-view.fxml
                    ViewSwitcher.switchTo(View.LISTI, false);
                } else {
                    System.out.println("No tour selected.");
                }

            });


            selectButton.setUserData(tour); // Store the corresponding Tour in the button's userData

            // Add components to the VBox
            tourBox.getChildren().addAll(imageView, nameLabel, descriptionLabel, selectButton);

            VBox.setMargin(tourBox, new Insets(10, 10, 10, 10)); // Add margins around each VBox

            // Add the VBox to the GridPane
            fxGrid.add(tourBox, col, row);

            // Update column and row for the next tour
            col++;
            if (col > 1) { // Assuming 2 columns
                col = 0;
                row++;
            }
        }
    }







}
