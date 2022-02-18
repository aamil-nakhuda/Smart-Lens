package com.smartlens;

public class SearchRVModel {
    //Image Url in the search result
    private String imageUrl;

   //Title in the search result
    private String title;

    //Link in the search result
    private String link;


    public SearchRVModel(String imageUrl, String title, String link) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

}
