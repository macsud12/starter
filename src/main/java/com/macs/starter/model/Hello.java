package com.macs.starter.model;

/**
 * Created by Maksim_Alipov on 1/30/14.
 */
public class Hello extends BasicResponse {
    private String greeting;

    public Hello() {
    }

    public Hello(String content) {
        this.greeting = content;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
