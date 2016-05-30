package com.ds.iwish.bean;


import java.util.ArrayList;
import java.util.List;

public class Giftlist extends Wishlist {

    private List<Profile> friends;


    public Giftlist(){
        setFriends(new ArrayList<>());
    }

    public Giftlist(Wishlist wishlist) {
        setWishlistId(wishlist.getWishlistId());
        setTitle(wishlist.getTitle());
        setPriority(wishlist.getPriority());
        setBackground(wishlist.getBackground());
        setColor(wishlist.getColor());
        setLayoutId(wishlist.getLayoutId());
        setUserId(wishlist.getUserId());
        setFriends(new ArrayList<>());
    }


    public List<Profile> getFriends() {
        return friends;
    }

    public void setFriends(List<Profile> friends) {
        this.friends = friends;
    }
}
