/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author Pontus
 */
public class Activity {
    
    private String description;
    private String duedate;
    private String done;
    private int id;

    public Activity(String description, String duedate, String done, int id) {
        this.description = description;
        this.duedate = duedate;
        this.done = done;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Description: " + description + ", duedate: " + duedate + ", done: " + done;
    }

    
    
    
}
