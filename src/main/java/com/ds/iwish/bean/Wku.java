package com.ds.iwish.bean;


import java.util.List;

public class Wku {

    private long wkuId;
    private String title;
    private int priority;
    private String largeImageUrl;
    private String smallImageUrl;
    private String description;

    private List<String> remotes;
    private List<Category> categories;

    private transient String priorityRaw;


    public long getWkuId() {
        return wkuId;
    }

    public void setWkuId(long wkuId) {
        this.wkuId = wkuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<String> getRemotes() {
        return remotes;
    }

    public void setRemotes(List<String> remotes) {
        this.remotes = remotes;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    public String getPriorityRaw() {
        return priorityRaw;
    }

    public void setPriorityRaw(String priorityRaw) {
        this.priorityRaw = priorityRaw;
    }
}
