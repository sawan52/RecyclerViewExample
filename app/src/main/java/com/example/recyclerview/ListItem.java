package com.example.recyclerview;


// created this class to store and retrieve the data in ListItem form and show it in the CardView using RecyclerView...
public class ListItem {

    private String heading, description, imageUrl;

    public ListItem(String heading, String description, String imageUrl) {
        this.heading = heading;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
