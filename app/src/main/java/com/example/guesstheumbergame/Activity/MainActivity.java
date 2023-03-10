package com.example.guesstheumbergame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.guesstheumbergame.Dialog.StartMyNumberDialog;
import com.example.guesstheumbergame.Dialog.StartPhoneNumberDialog;
import com.example.guesstheumbergame.R;

public class MainActivity extends AppCompatActivity {
    private Button guessMyNumBtn;
    private Button guessPhoneNumBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        guessMyNumBtn.setOnClickListener(guessMyNumBtnListener);
        guessPhoneNumBtn.setOnClickListener(guessPhoneNumBtnListener);
    }

    View.OnClickListener guessMyNumBtnListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            StartMyNumberDialog startGameDialog = new StartMyNumberDialog();
            startGameDialog.show(getSupportFragmentManager(), "my");
        }
    };

    View.OnClickListener guessPhoneNumBtnListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            StartPhoneNumberDialog startPhoneNumberDialog = new StartPhoneNumberDialog();
            startPhoneNumberDialog.show(getSupportFragmentManager(), "phone");
        }
    };

    private void init(){
        guessMyNumBtn = findViewById(R.id.guessMyNumBtn);
        guessPhoneNumBtn = findViewById(R.id.guessPhoneNumBtn);
    }
}