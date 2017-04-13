package com.underthejava.igottagogo;

import java.io.Serializable;

/**
 * Created by vincentdelacruz on 2017-03-18.
 */

public class User implements Serializable{

    private static User instance;

    private int id;
    private int credits;
    private String apiKey;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int radius;

    /* A private Constructor prevents any other
 * class from instantiating.
 */
    private User() { }

    /* Static 'instance' method */
    public static User getInstance( ) {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    public void buildUser(int id, String username, String password, String email, int credits, String firstName, String lastName)
    {
        setID(id);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setCredits(credits);
        setFirstName(firstName);
        setLastName(lastName);

        // @TODO(Llewellin):
        // this shouldn't be here but for the sake of getting it done by the demo on Friday
        radius = 10;
    }

    /* Getters */

    public int getId()
    {
        return id;
    }

    public int getCredits()
    {
        return credits;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getFirst_name()
    {
        return firstName;
    }

    public String getLast_name()
    {
        return lastName;
    }

    public int getRadius() { return radius; }

    /* Setters */

    protected void setID(int id) { this.id = id; }

    protected void setUsername(String username) { this.username = username; }

    protected void setPassword(String password) { this.password = password; }

    protected void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setCredits(int credits)
    {
        this.credits = credits;
    }

    public void setRadius(int radius) { this.radius = radius; }
}
