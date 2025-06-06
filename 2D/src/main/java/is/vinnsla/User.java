package is.vinnsla;

public class User {
    private String name;
    private String password;
    private String email;

    // Constructor
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public User getUser() {
        return this;
    }
    public void setUser(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

