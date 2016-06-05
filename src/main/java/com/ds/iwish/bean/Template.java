package com.ds.iwish.bean;


public class Template {

    private long templateId;
    private String title;
    private String mainColor;
    private String titleColor;
    private String textColor;
    private String borderColor;
    private String borderType;
    private String borderWidth;
    private String titleStyle;
    private String titleFont;
    private String textStyle;
    private String textFont;
    private String imagePosition;
    private long userId;


    public String getMainColorDP() {
        return "#" + getMainColor();
    }

    public String getTitleColorDP() {
        return "#" + getTitleColor();
    }

    public String getTextColorDP() {
        return "#" + getTextColor();
    }

    public String getBorderColorDP() {
        return "#" + getBorderColor();
    }

    public String getTitleBoldDP(){
        return getTitleStyle().contains("Bold") ? "bold" : "";
    }

    public String getTextBoldDP(){
        return getTextStyle().contains("Bold") ? "bold" : "";
    }

    public String getTitleItalicDP(){
        return getTitleStyle().contains("Italic") ? "italic" : "";
    }

    public String getTextItalicDP(){
        return getTitleStyle().contains("Italic") ? "italic" : "";
    }


    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorderType() {
        return borderType;
    }

    public void setBorderType(String borderType) {
        this.borderType = borderType;
    }

    public String getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(String borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(String titleStyle) {
        this.titleStyle = titleStyle;
    }

    public String getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(String titleFont) {
        this.titleFont = titleFont;
    }

    public String getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
    }

    public String getTextFont() {
        return textFont;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public String getImagePosition() {
        return imagePosition;
    }

    public void setImagePosition(String imagePosition) {
        this.imagePosition = imagePosition;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
