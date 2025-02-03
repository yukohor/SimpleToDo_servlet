package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Todo implements Serializable {
    private int id;
    private String todos;
    private Timestamp created_at;
    
    public Todo() {

}
    public Todo(String todos,Timestamp created_at) {
        this.todos = todos;
        this.created_at = created_at;
    }
    public Todo (int id, String todo, Timestamp created_at) {
        this.id = id;
        this.todos = todos;
        this.created_at = created_at;
    }
    
    public int getId() {
        return id;
    }
    public String getTodos() {
        return todos;
    }
    public Timestamp created_at() {
        return created_at;
    }
    }
