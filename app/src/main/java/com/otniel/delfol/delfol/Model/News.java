package com.otniel.delfol.delfol.Model;

/**
 * Created by Otniel on 5/20/2018.
 */

public class News {
    private int id;
    private String title;
    private String content;
    private byte[] image;

    public News(String title, String content, byte[] image,int id){
        this.title = title;
        this.content = content;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
