package model;

import java.util.ArrayList;

public class Twitter {
    private int id ;
    private String content ;
    private ArrayList<Comment> Comments ;

    public Twitter(int id, String content, ArrayList<Comment> comments) {
        this.id = id;
        this.content = content;
        Comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Comment> getComments() {
        return Comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }
}
