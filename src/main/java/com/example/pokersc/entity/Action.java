package com.example.pokersc.entity;


public class Action {
    public enum Act {
        RAISE,
        CHECK,
        FOLD,
        CALL,
    }
    private final Act act;
    private int amount = 0;

    public Action(Act act){
        this.act = act;

    }
    public Action(Act act, int amount){
        this.act = act;
        this.amount = amount;
    }

    public Act getAct() {
        return this.act;
    }

    public int getAmount() {
        return this.amount;
    }
}
