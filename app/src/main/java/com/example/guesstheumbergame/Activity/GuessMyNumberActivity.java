package com.example.guesstheumbergame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guesstheumbergame.Game.GuessGame;
import com.example.guesstheumbergame.R;

public class GuessMyNumberActivity extends AppCompatActivity  {
    //Иницаиализируем переменные для Активити
    private Button lessBtn;
    private Button bigBtn;
    private Button equalsBtn;
    private TextView phoneAttemptsText;
    private TextView limitPhoneText;
    private TextView numText;

    private GuessGame guessGame; //Инициализируем класс игры
    private int attempts; //счетчик

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);

        ActionBar actionBar = getSupportActionBar(); //Инициализируем Action Bar (фиолетовая полоска сверху)
        actionBar.setDisplayHomeAsUpEnabled(true); //Отображаем на ней стрелку, которая будет вести на главную Активити

        init(); //Функиця инициализации всех компонентов

        Bundle arguments = getIntent().getExtras(); //Получаем из главной Активити переданные данные
        //Распихиваем данные по переменным
        String min = arguments.get("minCount").toString();
        String max = arguments.get("maxCount").toString();
        attempts = Integer.parseInt(arguments.get("attempts").toString());
        phoneAttemptsText.setText("[" + min + "; " + max + "]"); //выводим диапозон значений чисел
        limitPhoneText.setText("" + attempts); //Выводим число попыток

        //Подвязка слушателей нажатий к кнопкам
        lessBtn.setOnClickListener(lessBtnListener);
        bigBtn.setOnClickListener(bigBtnListener);
        equalsBtn.setOnClickListener(equalsBtnListener);

        guessGame = new GuessGame(Integer.parseInt(max), Integer.parseInt(min)); //Создаем объект игры и пихаем в него минимальное и максимальное значение чисел
        guessGame.checkNumberBigger(); //Начинаем перебор значений
        numText.setText(guessGame.getMidCount() + "?"); //Получаем из класса-игры результат выбранного числа и выводим в текстовое поле
    }

    View.OnClickListener lessBtnListener = new View.OnClickListener(){ //Слушатель кнопки Меньше

        @Override
        public void onClick(View v) { //Переопределенная функция клика
            guessGame.lessNum();  //Дергаем в игре функцию Меньше
            boolean res = guessGame.checkNumberBigger();  //Получаем новое число
            checkToWin(res);  //Вызываем функцию проверки на выигрыш
        }
    };

    View.OnClickListener bigBtnListener = new View.OnClickListener(){  //Слушатель кнопки Больше

        @Override
        public void onClick(View v) {  //Переопределенная функиця клика
            guessGame.biggerNum();  //Дергаем в игре функцию Больше
            boolean res = guessGame.checkNumberBigger();  //Получаем новое число
            checkToWin(res);  //Вызываем функцию проверки на выигрыш
        }
    };

    View.OnClickListener equalsBtnListener = new View.OnClickListener(){  //Слушатель кнопки Равно

        @Override
        public void onClick(View v) {
            endGame();
        }  //Переопределяем функцию клика и вызываем метод конца игры
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  //Слушатель кнопок на верхней панели Action Bar
        switch (item.getItemId()) {  //Перебераем свичем нажатые кнопки по id
            case android.R.id.home:  //Если id == home (что есть одна наша кнопка со стрелкой, то)
                this.finish();  //Останавливаем эту Активити
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkToWin(boolean res){  //Функция проверки на выигрыш (данный метод нужен для того,
        // чтобы если пользователь намеренно не нажимает кнопку Равно, то выдать ему два результата, которые могут быть и закончить игру намеренно )

        //Собираем из ресурсов res -> string строку для тоста
        String partOne = getString(R.string.end_guess1);
        String partTwo = getString(R.string.end_guess2);
        if (res){ //Проверка результата на True
            checkAttempts(); //Вызываем функицю уменьшения количества попыток
            int temp = guessGame.getMidCount() + 1;  //Вычисляем второе число
            numText.setText(partOne + "\n" + guessGame.getMidCount() + "\n" + partTwo + "\n"  + temp);  //Выводим два числа на Активити
            endGame();  //Вызываем функицю конца игры
        }
        else{
            checkAttempts();  //Уменьшаем число попыток
            numText.setText(guessGame.getMidCount() + "?");  //Обновляем на Активити число попыток
        }
    }

    private void checkAttempts(){ //Функция уменьшения числа попыток
        attempts = attempts - 1;  //Уменьшаем попытки на 1
        limitPhoneText.setText("" + attempts);  //Выводим новое число попыток
        if(attempts == 0){  //Если попытки кончались, то
            Toast.makeText(this, "Я проиграл...", Toast.LENGTH_SHORT).show();  //Выводим тост с сообщением о конце игры
            this.finish();  //Закрываем эту Активити и возвращаемся на Главную
        }
    }

    private void endGame(){ //Функция конца игры
        Toast.makeText(this, getText(R.string.end_guess_game).toString(), Toast.LENGTH_SHORT).show();  //Выводим тост с поздравлением
        Handler handler = new Handler();  //Создаем объект класса для задержки
        Runnable r = new Runnable() {  //Создаем объект Рунейбл, чтобы вывести что-то после конца задержки
            public void run() {  //Переходим на Главную Активити после конца задержки
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(r, 1000);  //Ждем секунду и выполняем код из Рунейбл
    }

    private void init(){  //Функция привязки полей к объектам для связки графики и кода
        lessBtn = findViewById(R.id.lessBtn);
        bigBtn = findViewById(R.id.bigBtn);
        equalsBtn = findViewById(R.id.equalsBtn);
        phoneAttemptsText = findViewById(R.id.phoneAttemptsText);
        limitPhoneText = findViewById(R.id.limitPhoneText);
        numText = findViewById(R.id.gameText);
    }
}
