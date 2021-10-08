package com.omar.arabtv.Category;

import com.omar.arabtv.ui.Models.VideoModel;

import java.util.List;

public class CategoryModel {

    private String Name;
    private List<VideoModel> videoModels;

    public CategoryModel(String name, List<VideoModel> videoModels) {
        this.Name = name;
        this.videoModels = videoModels;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public List<VideoModel> getVideoModels() {
        return videoModels;
    }

    public void setVideoModels(List<VideoModel> videoModels) {
        this.videoModels = videoModels;
    }
}
