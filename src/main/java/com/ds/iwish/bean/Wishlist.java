package com.ds.iwish.bean;


public class Wishlist {

    private long wishlistId;
    private String title;
    private int priority;
    private String background;
    private String color;

    private transient boolean giftlist;


    public long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(long wishlistId) {
        this.wishlistId = wishlistId;
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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isGiftlist() {
        return giftlist;
    }

    public void setGiftlist(boolean giftlist) {
        this.giftlist = giftlist;
    }
}
