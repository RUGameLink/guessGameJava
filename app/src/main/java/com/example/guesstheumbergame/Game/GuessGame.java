package com.example.guesstheumbergame.Game;

import android.util.Log;

import com.example.guesstheumbergame.R;

import java.util.Random;

public class GuessGame {
    private int maxCount;
    private int minCount;
    private int midCount;
    private int number;



    public GuessGame(int maxCount, int minCount) {
        this.maxCount = maxCount;
        this.minCount = minCount;
    }

    public GuessGame(int maxCount, int minCount, boolean phone) {
        this.maxCount = maxCount;
        this.minCount = minCount;
    }

    public void generateNumber(){
        if(maxCount == minCount +1){
            number = minCount;
        }
        else {
            Random random = new Random();
            number = random.nextInt(maxCount - minCount) + minCount;
            System.out.println("Результат генератора: " + number);
        }
    }

    public int checkNumber(int userNumber){
        if (number > userNumber)
            return 1;
        else if(number < userNumber)
            return 2;
        else
            return 0;
    }

    public boolean checkNumberBigger(){
        midCount = (maxCount + minCount) / 2;
        String res = "";
        if (midCount == maxCount){
            midCount = midCount - 1;
            return true;
        }
        else if (midCount == minCount){
            midCount = midCount - 1;
            return true;
        }
        else {
            return false;
        }
    }

    public int getMidCount(){
        return midCount;
    }

    public void biggerNum(){
        minCount = midCount;
    }

    public void lessNum(){
        maxCount = midCount;
    }
}
