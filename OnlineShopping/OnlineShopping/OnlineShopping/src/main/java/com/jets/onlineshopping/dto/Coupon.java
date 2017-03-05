/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dto;

/**
 *
 * @author Eslam
 */
public class Coupon {
    private long randomNum;
    private int credit;

    public Coupon() {
    }

    public Coupon(long randomNum, int credit) {
        this.randomNum = randomNum;
        this.credit = credit;
    }

    public long getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(long randomNum) {
        this.randomNum = randomNum;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
