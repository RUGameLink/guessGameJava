package com.example.guesstheumbergame.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guesstheumbergame.R;

public class GuessPhoneNumberActivity extends AppCompatActivity  {
    private TextView myAttemptsText;
    private TextView limitText;
    private EditText numEditText;
    private Button enterBtn;

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
        String attempts = arguments.get("attempts").toString();
        myAttemptsText.setText("[" + min + "; " + max + "]");
        limitText.setText(attempts);
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
    }
}
