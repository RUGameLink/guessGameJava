package com.example.guesstheumbergame.Game;

import android.util.Log;

import com.example.guesstheumbergame.R;

import java.util.Random;

public class GuessGame {
    //Набор переменных для класса
    private int maxCount;
    private int minCount;
    private int midCount;
    private int number;

    /*
    Идея режима Угадай число телефона:
    Мы задаем рамки и количество попыток
    Рандом генерирует число в заданных рамках
    Мы пытаемся угадать, в ответ получаем интовое значение, которое преобразуется в Активити в "Больше", "Меньше" или "Верно"
    Счетчик попыток отслеживается на Активити, классе Игры данного функционала нет
     */

    /*
    Идея режима Угадай мое число:
    Мы задаем рамки и количество попыток
    Игра посредством бинарного перебора пытается угадать число
    Если загаданное число меньше того, что выдало приложение, то выданное значение становится максимальным
    Если загаданное число больше того, что выдало приложение, то выданное значение становится минимальным
    (midCount заменяет minCount или maxCount)
    Попытки также отслеживаются только на Активити
    */

    public GuessGame(int maxCount, int minCount) { //Конструктор класса
        this.maxCount = maxCount;
        this.minCount = minCount;
    }

    public void generateNumber(){ //Генерация числа в заданном диапозоне
        if(maxCount == minCount +1){
            number = minCount;
        }
        else {
            Random random = new Random();
            number = random.nextInt(maxCount - minCount) + minCount;
            System.out.println("Результат генератора: " + number);
        }
    }

    public int checkNumber(int userNumber){ //Проверка числа
        if (number > userNumber) //Если введенное пользователем число меньше заданного
            return 1;
        else if(number < userNumber)//Если введенное пользователем число больше заданного
            return 2;
        else //Если введенное пользователем число равно заданному
            return 0;
    }

    public boolean checkNumberBigger(){ //Функция поиска числа в заданном диапозоне (бинарный перебор)
        midCount = (maxCount + minCount) / 2; //Вычисляем среднее значение
        String res = "";
        if (midCount == maxCount){ //Если среднее равно максимальному, то
            midCount = midCount - 1;
            return true;
        }
        else if (midCount == minCount){ //Если среднее равно минимальному, то
            midCount = midCount - 1;
            return true;
        }
        else {
            return false;
        }
    }

    public int getMidCount(){
        return midCount;
    } //Геттер среднего значения

    public void biggerNum(){
        minCount = midCount;
    } //Сеттер присвоения минимальному значению среднего

    public void lessNum(){
        maxCount = midCount;
    } //Сеттер приспоения максимальному значению среднего
}
