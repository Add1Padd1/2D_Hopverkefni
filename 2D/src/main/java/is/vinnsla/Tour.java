package is.vinnsla;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.jar.Attributes.Name;

import javafx.scene.image.Image;

public class Tour {
    private String name;
    private int duration;
    private String picture;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime startTime; // New field for start time
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
        this.startTime = startTime; // Initialize start time
        this.ticketPrice = ticketPrice;
        this.numTickets = numTickets;
        // Attempt to load the image
    try {
        String imagePath = "/is/vidmot/media/" + picture;
        System.out.println("Loading image: " + imagePath);
        System.out.println("Resource URL: " + getClass().getResource("/is/vidmot/media/" + picture));
        this.image = new Image(getClass().getResourceAsStream(imagePath));
        if (this.image.isError()) {
            throw new IllegalArgumentException("Error loading image: " + imagePath);
        }
    } catch (Exception e) {
        System.err.println("Error loading image for tour: " + name + " - " + e.getMessage());
        // Use a placeholder image if the specified image cannot be loaded
        this.image = new Image(getClass().getResourceAsStream("/media/dots.jpg"));
    }
    }

    public Tour getTour() {
        return this;
    }

    public Tour setTour(Tour tour) {
        this.image = tour.getImage();
        this.name = tour.getName();
        this.duration = tour.getDuration();
        this.location = tour.getLocation();
        this.picture = tour.getPicture();
        this.description = tour.getDescription();
        this.date = tour.getDate();
        this.startTime = tour.getStartTime(); // Set start time
        this.ticketPrice = tour.getTicketPrice();
        this.numTickets = tour.getNumTickets();
        return this;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
    public String getLocation() {
        return location;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public LocalTime getStartTime() { // Getter for start time
        return startTime;
    }

    public void setStartTime(LocalTime startTime) { // Setter for start time
        this.startTime = startTime;
    }

    public int getNumTickets() {
        return numTickets;
    }
    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }
}
