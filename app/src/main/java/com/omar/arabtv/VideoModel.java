package com.omar.arabtv;


public class VideoModel {

    private String Title;
    private String imageURL;
    private String TrailerURL;
    private String Des;

    public VideoModel(){

    }

    public VideoModel(String title, String imageURL ,String trailerURL, String des) {
        Title = title;
        this.imageURL = imageURL;
        this.TrailerURL = trailerURL;
        this.Des = des;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTrailerURL() {
        return TrailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        TrailerURL = trailerURL;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }
}
