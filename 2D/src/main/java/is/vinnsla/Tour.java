package is.vinnsla;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tour {
    private String name;
    private int duration;
    private String picture;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private Image image;
    private Integer ticketPrice;
    private int numTickets;

    public Tour(String name, int duration, String picture, String location, String description, LocalDate date, LocalTime startTime, Integer ticketPrice, int numTickets) {
        this.name = name;
        this.duration = duration;
        this.picture = picture;
        this.location = location;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.ticketPrice = ticketPrice;
        this.numTickets = numTickets;

        // Image loading logic
        try {
            String imagePath = "/is/vidmot/media/" + picture;
            this.image = new Image(getClass().getResourceAsStream(imagePath));
            if (this.image.isError()) {
                throw new IllegalArgumentException("Error loading image: " + imagePath + " - " + this.image.getException());
            }
        } catch (Exception e) {
            System.err.println("Error loading image for tour: " + name + " - " + e.getMessage());
            try {
                this.image = new Image(getClass().getResourceAsStream("/is/vidmot/media/dots.jpg"));
                if (this.image == null || this.image.isError()){
                    System.err.println("FATAL: Placeholder image also failed to load.");
                }
            } catch (Exception p_ex){
                System.err.println("FATAL: Exception loading placeholder image: " + p_ex.getMessage());
            }
        }
    }

    public Tour getTour() { return this; }
    public Tour setTour(Tour tour) {

        this.image = tour.getImage();

        this.name = tour.getName();

        this.duration = tour.getDuration();

        this.location = tour.getLocation();

        this.picture = tour.getPicture();

        this.description = tour.getDescription();

        this.date = tour.getDate();

        this.startTime = tour.getStartTime();

        this.ticketPrice = tour.getTicketPrice();

        this.numTickets = tour.getNumTickets();

        return this;

    }
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public String getLocation() { return location; }
    public String getPicture() { return picture; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public Integer getTicketPrice() { return ticketPrice; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public int getNumTickets() { return numTickets; }

    public void setNumTickets(int numTickets) {
        if (numTickets < 0) {
            this.numTickets = 0;
        } else {
            this.numTickets = numTickets;
        }
    }


    public boolean decreaseTickets(int amountToDecrease) {
        if (amountToDecrease <= 0) {
            System.err.println("Error: Cannot decrease tickets by a non-positive amount (" + amountToDecrease + ") for tour " + this.name);
            return false; // getur ekki keypt < 1 miða
        }
        if (this.numTickets >= amountToDecrease) {
            this.numTickets -= amountToDecrease;
            System.out.println("Tickets for '" + this.name + "' decreased by " + amountToDecrease + ". Remaining: " + this.numTickets);
            return true;
        } else {
            System.err.println("Error: Not enough tickets available for '" + this.name + "'. Tried to decrease by " + amountToDecrease + ", but only " + this.numTickets + " remain.");
            return false; // á ekki að gerast
        }
    }
}
