package is.vinnsla;

import java.time.LocalDate;
import java.time.LocalTime;

public class Receit {
    private String tourName;
    private int duration;
    private String location;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private int ticketPrice;

    private String username;
    private String email;
    private int numTickets;
    private int totalCost;

    // Constructor
    public Receit(String tourName, int duration, String location, String description, LocalDate date, LocalTime startTime, int ticketPrice, String username, String email, int numTickets, int totalCost) {
        this.tourName = tourName;
        this.duration = duration;
        this.location = location;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.ticketPrice = ticketPrice;
        this.username = username;
        this.email = email;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
    }
    public Receit getReceit() {
        return this;
    }
    public void setReceit(Receit receit) {
        this.tourName = receit.getTourName();
        this.duration = receit.getDuration();
        this.location = receit.getLocation();
        this.description = receit.getDescription();
        this.date = receit.getDate();
        this.startTime = receit.getStartTime();
        this.ticketPrice = receit.getTicketPrice();
        this.username = receit.getUsername();
        this.email = receit.getEmail();
        this.numTickets = receit.getNumTickets();
        this.totalCost = receit.getTotalCost();
    }

    // Getters and Setters
    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Receit{" +
                "tourName='" + tourName + '\'' +
                ", duration=" + duration +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", ticketPrice=" + ticketPrice +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", numTickets=" + numTickets +
                ", totalCost=" + totalCost +
                '}';
    }
}
