package com.example.guesstheumbergame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.guesstheumbergame.Dialog.StartMyNumberDialog;
import com.example.guesstheumbergame.Dialog.StartPhoneNumberDialog;
import com.example.guesstheumbergame.R;

public class MainActivity extends AppCompatActivity {
    //Иницаиализируем переменные для Активити
    private Button guessMyNumBtn;
    private Button guessPhoneNumBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); //Функиця инициализации всех компонентов

        //Подвязка слушателей нажатий к кнопкам
        guessMyNumBtn.setOnClickListener(guessMyNumBtnListener);
        guessPhoneNumBtn.setOnClickListener(guessPhoneNumBtnListener);
    }

    View.OnClickListener guessMyNumBtnListener = new View.OnClickListener(){ //Слушатель кнопки запуска игры Угадай мое число

        @Override
        public void onClick(View v) {
            //Вызов диалога
            StartMyNumberDialog startGameDialog = new StartMyNumberDialog();
            startGameDialog.show(getSupportFragmentManager(), "my");
        }
    };

    View.OnClickListener guessPhoneNumBtnListener = new View.OnClickListener(){ //Слушатель кнопки запуска игры Угадай число телефона

        @Override
        public void onClick(View v) {
            //Вызов диалога
            StartPhoneNumberDialog startPhoneNumberDialog = new StartPhoneNumberDialog();
            startPhoneNumberDialog.show(getSupportFragmentManager(), "phone");
        }
    };

    private void init(){ //Функция привязки полей к объектам для связки графики и кода
        guessMyNumBtn = findViewById(R.id.guessMyNumBtn);
        guessPhoneNumBtn = findViewById(R.id.guessPhoneNumBtn);
    }
}