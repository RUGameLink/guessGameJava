package com.example.guesstheumbergame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guesstheumbergame.Game.GuessGame;
import com.example.guesstheumbergame.R;

public class GuessPhoneNumberActivity extends AppCompatActivity  {
    //Иницаиализируем переменные для Активити
    private TextView myAttemptsText;
    private TextView limitText;
    private EditText numEditText;
    private Button enterBtn;
    private TextView resText;

    private GuessGame guessTheGame; //Инициализируем класс игры
    private int attempts; //счетчик

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_game);

        ActionBar actionBar = getSupportActionBar(); //Инициализируем Action Bar (фиолетовая полоска сверху)
        actionBar.setDisplayHomeAsUpEnabled(true); //Отображаем на ней стрелку, которая будет вести на главную Активити

        init(); //Функиця инициализации всех компонентов

        Bundle arguments = getIntent().getExtras(); //Получаем из главной Активити переданные данные
        //Распихиваем данные по переменным
        String min = arguments.get("minCount").toString();
        String max = arguments.get("maxCount").toString();
        attempts = Integer.parseInt(arguments.get("attempts").toString());
        myAttemptsText.setText("[" + min + "; " + max + "]"); //выводим диапозон значений чисел
        limitText.setText("" + attempts); //Выводим число попыток

        guessTheGame = new GuessGame(Integer.parseInt(max), Integer.parseInt(min)); //Создаем объект игры и пихаем в него минимальное и максимальное значение чисел
        guessTheGame.generateNumber(); //Начинаем перебор значений

        enterBtn.setOnClickListener(enterBtnListener);//Подвязка слушателя нажатий к кнопке
    }

    private void checkAttempts(){ //Функция уменьшения числа попыток
        attempts = attempts - 1;  //Уменьшаем попытки на 1
        limitText.setText("" + attempts); //Выводим новое число попыток
        if(attempts == 0){ //Если попытки кончались, то
            Toast.makeText(this, "Вы проиграли...", Toast.LENGTH_SHORT).show(); //Выводим тост с сообщением о конце игры
            this.finish();  //Закрываем эту Активити и возвращаемся на Главную
        }
    }

    View.OnClickListener enterBtnListener = new View.OnClickListener(){ //Слушатель кнопки Ввода

        @Override
        public void onClick(View v) { //Переопределенная функиця клика
            if(numEditText.getText().toString().isEmpty()){ //Проверка строки на пустоту
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.num_error), Toast.LENGTH_SHORT); //Вывод тоста с сообщением о пустоте
                toast.show();
                return;
            }
            else{
                checkEqual(); //Вызов функции проверки введенного значения
                numEditText .setText(""); //Очистка поля ввода
            }
        }
    };

    private void checkEqual() { //Функиця проверки введенного значения
        int res = guessTheGame.checkNumber(Integer.parseInt(numEditText.getText().toString())); //Вызов из класса-игры функции проверки введенного числа
        switch (res){  //Проверка результата
            case  1 :  //Если res = 1
                    resText.setText(R.string.num_is_big);  //Установка текста
                    checkAttempts();  //Вызов функции
                    break;
            case 2 : //Если res = 2
                    resText.setText(R.string.num_is_small); //Установка текста
                    checkAttempts(); //Вызов функции
                    break;
            case 0 : //Если res = 0
                    resText.setText(R.string.num_is_true); //Установка текста
                    endGame(); //Функция завершения игры
                    break;
        }
    }

    private void endGame(){ //Функция конца игры
        Toast toast = Toast.makeText(getApplicationContext(), getText(R.string.end_guess_game).toString(), Toast.LENGTH_SHORT); //Выводим тост с поздравлением
        toast.show();

        Handler handler = new Handler(); //Создаем объект класса для задержки

        Runnable r = new Runnable() { //Создаем объект Рунейбл, чтобы вывести что-то после конца задержки
            public void run() { //Переходим на Главную Активити после конца задержки
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(r, 1000); //Ждем секунду и выполняем код из Рунейбл
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Слушатель кнопок на верхней панели Action Bar
        switch (item.getItemId()) { //Перебераем свичем нажатые кнопки по id
            case android.R.id.home: //Если id == home (что есть одна наша кнопка со стрелкой, то)
                this.finish(); //Останавливаем эту Активити
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){ //Функция привязки полей к объектам для связки графики и кода
        myAttemptsText = findViewById(R.id.myAttemptsText);
        limitText = findViewById(R.id.limitText);
        numEditText = findViewById(R.id.numEditText);
        enterBtn = findViewById(R.id.enterBtn);
        resText = findViewById(R.id.gamePhoneText);
    }
}
