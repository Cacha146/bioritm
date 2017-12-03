/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bio_ritm_tomcat;

import java.util.Date;

/**
 *
 * @author sasha
 */
class Ritm {
    private double physical;
    private double emotional;
    private double intellectual;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPhysical() {
        return physical;
    }

    public void setPhysical(double physical) {
        this.physical = physical;
    }

    public double getEmotional() {
        return emotional;
    }

    public void setEmotional(double emotional) {
        this.emotional = emotional;
    }

    public double getIntellectual() {
        return intellectual;
    }

    public void setIntellectual(double intellectual) {
        this.intellectual = intellectual;
    }
    
}
