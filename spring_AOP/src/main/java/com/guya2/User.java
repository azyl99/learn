package com.guya2;

public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say(){
        System.out.println("Say:" + this.name);
    }

    public void hello(){
        System.out.println("Hello! " + this.name);
    }

    public void hey(String guest) {
        System.out.println("Hey! " + this.name + " " + guest);
    }

    public void hehe() {
        System.out.println("Hehe! " + this.name);
    }

    public void heihei() {
        System.out.println("Heihei! " + this.name);
    }
}