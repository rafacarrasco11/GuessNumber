package com.example.guessnumber.model;

import java.io.Serializable;

public class Message implements Serializable {
    int attemps;

    public Message(int attemps) {
        this.attemps = attemps;
    }

    public int getAttemps() {
        return attemps;
    }

    public void setAttemps(int attemps) {
        this.attemps = attemps;
    }

    @Override
    public String toString() {
        return "Message{" +
                "attemps=" + attemps +
                '}';
    }
}
