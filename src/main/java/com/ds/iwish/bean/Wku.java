package com.ds.iwish.bean;


import java.util.ArrayList;
import java.util.List;

public class Wku {

    private long wkuId;
    private String title;
    private int priority;
    private String largeImageUrl;
    private String smallImageUrl;
    private String description;
    private long templateId;
    private long wishlistId;
    private long giftlistId;

    private List<Remote> remotes;
    private List<Category> categories;

    private transient Template template;
    private transient String priorityRaw;


    public Wku() {
        remotes = new ArrayList<>();
        categories = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wku wku = (Wku) o;

        return wkuId == wku.wkuId;

    }

    @Override
    public int hashCode() {
        return (int) (wkuId ^ (wkuId >>> 32));
    }

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

    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public long getGiftlistId() {
        return giftlistId;
    }

    public void setGiftlistId(long giftlistId) {
        this.giftlistId = giftlistId;
    }

    public List<Remote> getRemotes() {
        return remotes;
    }

    public void setRemotes(List<Remote> remotes) {
        this.remotes = remotes;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getPriorityRaw() {
        return priorityRaw;
    }

    public void setPriorityRaw(String priorityRaw) {
        this.priorityRaw = priorityRaw;
    }
}
