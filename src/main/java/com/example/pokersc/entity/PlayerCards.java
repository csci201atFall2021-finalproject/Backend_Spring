package com.example.pokersc.entity;

public class PlayerCards {

    public Card[] getPlayerHand() {
        return playerHand;
    }

    private Card[] playerHand;
    private int numCard;

    public PlayerCards(){
        this.playerHand = new Card[2];
        this.numCard = 0;
    }

    public void receiveCard(Card c){
        this.playerHand[numCard] = c;
        numCard ++;
    }
}
