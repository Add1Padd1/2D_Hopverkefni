package is.vinnsla;

import is.repository.DatabaseMock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tourlisti {
    // listi af öllum tours
    protected static ObservableList<Tour> tours = FXCollections.observableArrayList();

    // núverandi tour
    private static int index = 0;

    /**
     * Bua til tour
     */
    public static void buaTilTours() {
        tours.clear();
        DatabaseMock.generateTours(tours);
    }
    /**
     * Finds and returns a Tour by its name.
     *
     * @param tourName The name of the tour to find.
     * @return The Tour object if found, otherwise null.
     */
    public static Tour chooseTour(String tourName) {
        for (Tour tour : tours) {
            if (tour.getName().equalsIgnoreCase(tourName)) {
                return tour;
            }
        }
        return null; // Return null if no matching tour is found
    }


    // get og set aðferðir

    public ObservableList<Tour> getListi() {
        return tours;
    }

    public static void setIndex(int selectedIndex) {
        index = selectedIndex;
    }

    public int getIndex() {
        return index;
    }
    public static Tour getTourFromList(){
        return tours.get(index);
    }
}
