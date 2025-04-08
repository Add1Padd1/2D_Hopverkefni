package is.vinnsla;

import is.repository.DatabaseMock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Tourlisti {
    protected static ObservableList<Tour> allTours = FXCollections.observableArrayList();

    private ObservableList<Tour> filteredTours = FXCollections.observableArrayList();



    public void buaTilTours() {
        DatabaseMock.generateTours(allTours);
        filteredTours.setAll(allTours);
    }


    public static Tour chooseTour(String tourName) {
        for (Tour tour : allTours) {
            if (tour.getName().equalsIgnoreCase(tourName)) {
                return tour;
            }
        }
        return null;
    }


    public ObservableList<Tour> getListiTilSynis() {
        return filteredTours;
    }


    public ObservableList<Tour> getAllToursListi() {
        return allTours;
    }



    public void filterTours(String location, LocalDate date, Integer minDuration, Double maxPrice ) {

        List<Tour> result = allTours.stream()
                .filter(tour -> {
                    // Staðsetningar filter
                    boolean locationMatch = (location == null || location.trim().isEmpty() ||
                            tour.getLocation().toLowerCase().contains(location.trim().toLowerCase()));
                    return locationMatch;
                })
                .filter(tour -> {
                    // Dagsetningar filter
                    boolean dateMatch = (date == null || tour.getDate().equals(date));
                    return dateMatch;
                })
                .filter(tour -> minDuration == null || tour.getDuration() >= (minDuration * 60)) // Ef duration er í min
                .filter(tour -> maxPrice == null || tour.getTicketPrice() <= maxPrice)
                .collect(Collectors.toList());

        filteredTours.setAll(result);
        System.out.println("Filtering applied. Showing " + filteredTours.size() + " tours.");
    }

    /**
     * Hreinsar filterinn og sýnir allar ferðir aftur.
     */
    public void clearFilter() {
        filteredTours.setAll(allTours);
        System.out.println("Filter cleared. Showing all " + filteredTours.size() + " tours.");
    }

}
