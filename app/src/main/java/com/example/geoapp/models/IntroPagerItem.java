package com.example.geoapp.models;

public class IntroPagerItem {
    private String introHeader,introDescription;
    private int  introImg;

    public IntroPagerItem(String introHeader, String introDescription, int introImg) {
        this.introHeader = introHeader;
        this.introDescription = introDescription;
        this.introImg = introImg;
    }

    public String getIntroHeader() {
        return introHeader;
    }

    public String getIntroDescription() {
        return introDescription;
    }

    public int getIntroImg() {
        return introImg;
    }
}
