package com.omar.arabtv;


public class VideoModel {

    private String Title;
    private String imageURL;

    public VideoModel(){

    }

    public VideoModel(String title, String imageURL) {
        Title = title;
        this.imageURL = imageURL;
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

}
