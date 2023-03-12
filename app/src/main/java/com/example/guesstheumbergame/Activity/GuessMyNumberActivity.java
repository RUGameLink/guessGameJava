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
    private Button lessBtn;
    private Button bigBtn;
    private Button equalsBtn;
    private TextView phoneAttemptsText;
    private TextView limitPhoneText;
    private TextView numText;

    private GuessGame guessGame;
    private int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();

        Bundle arguments = getIntent().getExtras();
        String min = arguments.get("minCount").toString();
        String max = arguments.get("maxCount").toString();
        attempts = Integer.parseInt(arguments.get("attempts").toString());
        phoneAttemptsText.setText("[" + min + "; " + max + "]");
        limitPhoneText.setText("" + attempts);

        lessBtn.setOnClickListener(lessBtnListener);
        bigBtn.setOnClickListener(bigBtnListener);
        equalsBtn.setOnClickListener(equalsBtnListener);

        guessGame = new GuessGame(Integer.parseInt(max), Integer.parseInt(min));
        guessGame.checkNumberBigger();
        numText.setText(guessGame.getMidCount() + "?");
    }

    View.OnClickListener lessBtnListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            guessGame.lessNum();
            boolean res = guessGame.checkNumberBigger();
            checkToWin(res);
        }
    };

    View.OnClickListener bigBtnListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            guessGame.biggerNum();
            boolean res = guessGame.checkNumberBigger();
            checkToWin(res);
        }
    };

    View.OnClickListener equalsBtnListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            endGame();
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkToWin(boolean res){
        String partOne = getString(R.string.end_guess1);
        String partTwo = getString(R.string.end_guess2);
        if (res){
            checkAttempts();
            numText.setText(partOne + "\n" + guessGame.getMidCount() +  partTwo  + guessGame.getMidCount() + 1);
            endGame();
        }

        else{
            checkAttempts();
            numText.setText(guessGame.getMidCount() + "?");
        }
    }

    private void checkAttempts(){
        attempts = attempts - 1;
        limitPhoneText.setText("" + attempts);
        if(attempts == 0){
            Toast.makeText(this, "Я проиграл...", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private void endGame(){
        Toast.makeText(this, getText(R.string.end_guess_game).toString(), Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(r, 1000);
    }

    private void init(){
        lessBtn = findViewById(R.id.lessBtn);
        bigBtn = findViewById(R.id.bigBtn);
        equalsBtn = findViewById(R.id.equalsBtn);
        phoneAttemptsText = findViewById(R.id.phoneAttemptsText);
        limitPhoneText = findViewById(R.id.limitPhoneText);
        numText = findViewById(R.id.gameText);
    }
}
