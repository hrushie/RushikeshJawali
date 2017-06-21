package com.hrushie.rushikeshjawali.model;

/**
 * Created by hrushie on 6/20/2017.
 */

public class NavDrawerItem {
    private boolean showNotify;
    private String title;

    public NavDrawerItem(){

    }
    public NavDrawerItem(Boolean showNotify, String title){
        this.showNotify = showNotify;
        this.title = title;
    }
    public boolean isShowNotify(){
        return showNotify;
    }
    public void setShowNotify(boolean showNotify){
        this.showNotify = showNotify
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}
