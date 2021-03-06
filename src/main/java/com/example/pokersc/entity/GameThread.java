package com.example.pokersc.entity;

import java.util.Arrays;

public class GameThread extends Thread{

    public Game game;
    public Hand hand;
    public int[] emojis;
    public long[] emojiTimes;

    public GameThread(){
        this.game = new Game();
        this.hand = null;
        this.emojis = new int[8];
        this.emojiTimes = new long[8];
        for(int i = 0; i < 8; i++){
            this.emojis[i] = -1;
        }
    }

    public void run(){
        try {
            while (true) {
                if (game.numPlayers < 3) {
                    game.ongoing = false;
                    Thread.sleep(1000);
                } else {
                    game.ongoing = true;
                    game.handend = false;
                    game.updatePos();
                    hand = new Hand(game.userArr, game.remainingChips, game.dealerPos, game.numPlayers);
                    game.hand = hand;
                    hand.startHand();
                    System.out.println(Arrays.toString(hand.getRemainingStack()));
                    // function call to end hand
                    //game.remainingChips = hand.getRemainingStack();
                    // update user stats
                    for(int i = 0; i < 8; i++) {
                        if(game.userArr[i]!=null && hand.getStartingStack()[i] != 0) {
                            game.userArr[i].setTotal_round(game.userArr[i].getTotal_round() + 1);
                            game.userArr[i].setTotal_profit(game.userArr[i].getTotal_profit() + (game.remainingChips[i] - hand.getStartingStack()[i]));
                        }
                    }
                    

                    game.handend = true;
                    game.hand = null;
                    Thread.sleep(10000);
                    for(int i = 0; i < 8; i++){
                        if(game.remainingChips[i] < 1 && game.userArr[i] != null){
                            game.deleteUser(game.userArr[i].getUsername());
                        }
                    }
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
