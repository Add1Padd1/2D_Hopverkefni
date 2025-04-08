package is.vinnsla;

import java.time.LocalDate;

public class CheckoutData {
    private static int numTickets;
    private static int totalCost;
    private static String nameOfTour;
    private static LocalDate dateOfTour;

    public static int getNumTickets() {
        return numTickets;
    }

    public static void setNumTickets(int numTickets) {
        CheckoutData.numTickets = numTickets;
    }

    public static int getTotalCost() {
        return totalCost;
    }

    public static void setTotalCost(int totalCost) {
        CheckoutData.totalCost = totalCost;
    }
    public static String getNameOfTour() {
        return nameOfTour;
    }
    public static void setNameOfTour(String nameOfTour) {
        CheckoutData.nameOfTour = nameOfTour;
    }
    public static LocalDate getDateOfTour() {
        return dateOfTour;
    }
    public static void setDateOfTour(LocalDate dateOfTour) {
        CheckoutData.dateOfTour = dateOfTour;
    }
}
