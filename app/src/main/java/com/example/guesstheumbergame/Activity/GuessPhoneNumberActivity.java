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
    private TextView myAttemptsText;
    private TextView limitText;
    private EditText numEditText;
    private Button enterBtn;
    private TextView resText;

    private GuessGame guessTheGame;
    private int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_game);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();

        Bundle arguments = getIntent().getExtras();
        String min = arguments.get("minCount").toString();
        String max = arguments.get("maxCount").toString();
        attempts = Integer.parseInt(arguments.get("attempts").toString());
        myAttemptsText.setText("[" + min + "; " + max + "]");
        limitText.setText("" + attempts);

        guessTheGame = new GuessGame(Integer.parseInt(max), Integer.parseInt(min), true);
        guessTheGame.generateNumber();

        enterBtn.setOnClickListener(enterBtnListener);
    }

    private void checkAttempts(){
        attempts = attempts - 1;
        limitText.setText("" + attempts);
        if(attempts == 0){
            Toast.makeText(this, "Вы проиграли...", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    View.OnClickListener enterBtnListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(numEditText.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.num_error), Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            else{
                checkEqual();
                numEditText .setText("");
            }
        }
    };

    private void checkEqual() {
        int res = guessTheGame.checkNumber(Integer.parseInt(numEditText.getText().toString()));
        switch (res){
            case  1 :
                    resText.setText(R.string.num_is_big);
                    checkAttempts();
                    break;
            case 2 :
                    resText.setText(R.string.num_is_small);
                    checkAttempts();
                    break;
            case 0 :
                    resText.setText(R.string.num_is_true);
                    endGame();
                    break;
        }
    }

    private void endGame(){
        Toast toast = Toast.makeText(getApplicationContext(), getText(R.string.end_guess_game).toString(), Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();

        Runnable r = new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(r, 1000);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        myAttemptsText = findViewById(R.id.myAttemptsText);
        limitText = findViewById(R.id.limitText);
        numEditText = findViewById(R.id.numEditText);
        enterBtn = findViewById(R.id.enterBtn);
        resText = findViewById(R.id.gamePhoneText);
    }
}
