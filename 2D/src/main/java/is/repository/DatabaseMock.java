package is.repository;

import is.vinnsla.Tour;
import javafx.collections.ObservableList; // Mikilvægt fyrir Tourlisti

import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseMock {

    /**
     * Populates the provided list with mock Tour data.
     * This method clears the list before adding new tours.
     * @param targetList The ObservableList to populate with Tour objects.
     */
    public static void generateTours(ObservableList<Tour> targetList) {
        targetList.clear(); // Hreinsa listann áður en nýjum er bætt við

        // Búa til 8 mock ferðir með gögnum sem passa við is.vinnsla.Tour constructor
        targetList.add(new Tour("Golden Circle Afternoon", 360, "cover1.jpg", "South Iceland",
                "Classic tour: Þingvellir, Geysir, Gullfoss.", LocalDate.now().plusDays(5),
                LocalTime.of(13, 0), 10500, 50));

        targetList.add(new Tour("South Coast Express", 600, "cover2.jpg", "South Iceland",
                "Waterfalls Seljalandsfoss & Skógafoss, Reynisfjara beach.", LocalDate.now().plusDays(7),
                LocalTime.of(9, 0), 15000, 45));

        targetList.add(new Tour("Blue Lagoon Transfer", 240, "cover3.jpg", "Reykjanes",
                "Comfortable transfer to/from the Blue Lagoon (entry not incl.).", LocalDate.now().plusDays(3),
                LocalTime.of(10, 0), 5500, 60));

        targetList.add(new Tour("Reykjavik Walking Tour", 180, "dots.jpg", "Reykjavik",
                "Explore the city center highlights with a local guide.", LocalDate.now().plusDays(2),
                LocalTime.of(14, 0), 6000, 25));

        targetList.add(new Tour("Whale Watching", 210, "fox.jpg", "Faxaflói Bay",
                "See whales and dolphins near Reykjavik.", LocalDate.now().plusDays(4),
                LocalTime.of(10, 30), 12000, 70));

        targetList.add(new Tour("Snaefellsnes Peninsula Day Trip", 720, "leopard.jpg", "West Iceland",
                "Explore Kirkjufell, black beaches, and dramatic cliffs.", LocalDate.now().plusDays(10),
                LocalTime.of(8, 0), 18000, 30));

        targetList.add(new Tour("Katla Ice Cave", 300, "random.jpg", "Near Vík",
                "Visit a natural ice cave under the Katla volcano (requires transfer).", LocalDate.now().plusDays(15),
                LocalTime.of(11, 0), 19900, 20));

        targetList.add(new Tour("Northern Lights Mystery", 240, "snow.jpg", "Reykjavik Area",
                "Hunt for the aurora borealis (winter season).", LocalDate.now().plusMonths(4),
                LocalTime.of(21, 0), 9500, 55));

        System.out.println("DatabaseMock generated " + targetList.size() + " tours.");
    }
}
