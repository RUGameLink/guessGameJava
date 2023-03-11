package com.example.guesstheumbergame.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guesstheumbergame.R;

public class GuessMyNumberActivity extends AppCompatActivity  {
    private Button lessBtn;
    private Button bigBtn;
    private Button equalsBtn;
    private TextView phoneAttemptsText;
    private TextView limitPhoneText;

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
        String attempts = arguments.get("attempts").toString();
        phoneAttemptsText.setText("[" + min + "; " + max + "]");
        limitPhoneText.setText(attempts);
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
        lessBtn = findViewById(R.id.lessBtn);
        bigBtn = findViewById(R.id.bigBtn);
        equalsBtn = findViewById(R.id.equalsBtn);
        phoneAttemptsText = findViewById(R.id.phoneAttemptsText);
        limitPhoneText = findViewById(R.id.limitPhoneText);
    }
}
