package com.example.guesstheumbergame.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.guesstheumbergame.Activity.GuessMyNumberActivity;
import com.example.guesstheumbergame.R;

public class StartMyNumberDialog extends DialogFragment{
    private EditText minCountText;
    private EditText maxCountText;
    private EditText attemptsText;
    private Button startGameButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_start_game, null);
        init(view);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String minCount = minCountText.getText().toString();
                String maxCount = maxCountText.getText().toString();
                String attempts = attemptsText.getText().toString();
                if(minCount.isEmpty() && maxCount.isEmpty() && attempts.isEmpty()){
                    Toast toast = Toast.makeText(getContext(), getString(R.string.error_text),Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    if(Integer.parseInt(minCount) < Integer.parseInt(maxCount)){
                        Intent intent = new Intent(getContext(), GuessMyNumberActivity.class);

                        // указываем первым параметром ключ, а второе значение
                        // по ключу мы будем получать значение с Intent
                        intent.putExtra("minCount", minCount);
                        intent.putExtra("maxCount", maxCount);
                        intent.putExtra("attempts", attempts);

                        // показываем новое Activity
                        startActivity(intent);
                    }
                    else{
                        Toast toast = Toast.makeText(getContext(), getString(R.string.num_error_text),Toast.LENGTH_LONG);
                        toast.show();
                    }

                }

            }
        });

        builder.setView(view);
        return builder.create();
    }

    private void init(View view){
        minCountText = view.findViewById(R.id.minCountText);
        maxCountText = view.findViewById(R.id.maxCountText);
        attemptsText = view.findViewById(R.id.attemptsText);
        startGameButton = view.findViewById(R.id.startGameButton);
    }
}
