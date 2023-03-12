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
    //Иницаиализируем переменные для Активити
    private EditText minCountText;
    private EditText maxCountText;
    private EditText attemptsText;
    private Button startGameButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //Создаем объект конструктора диалогов

        LayoutInflater inflater = requireActivity().getLayoutInflater(); //Создаем инфлейтор для отображения диалога
        View view = inflater.inflate(R.layout.dialog_start_game, null); //Привязываем верстку к диалогу
        init(view); //Инициализируем элементы

        startGameButton.setOnClickListener(new View.OnClickListener() { //Слушатель кнопки начала игры
            public void onClick(View v) { //Переопределенный Клик

                //Получение данных с элементов
                String minCount = minCountText.getText().toString();
                String maxCount = maxCountText.getText().toString();
                String attempts = attemptsText.getText().toString();

                if(minCount.isEmpty() && maxCount.isEmpty() && attempts.isEmpty()){ //Проверка на пустоту заполняемых элементов
                    Toast toast = Toast.makeText(getContext(), getString(R.string.error_text),Toast.LENGTH_LONG); //Вывод сообщения о пустоте
                    toast.show();
                }
                else {
                    if(Integer.parseInt(minCount) < Integer.parseInt(maxCount)){ //Если минимальное значение меньше максимального
                        Intent intent = new Intent(getContext(), GuessMyNumberActivity.class); //Готовим переход на игровую Активити

                        // указываем первым параметром ключ, а второе значение
                        // по ключу мы будем получать значение с Intent
                        intent.putExtra("minCount", minCount);
                        intent.putExtra("maxCount", maxCount);
                        intent.putExtra("attempts", attempts);

                        // показываем новое Activity
                        startActivity(intent);
                    }
                    else{ //Иначе выводим тост с предупреждением
                        Toast toast = Toast.makeText(getContext(), getString(R.string.num_error_text),Toast.LENGTH_LONG);
                        toast.show();
                    }

                }

            }
        });

        builder.setView(view);
        return builder.create();
    }

    private void init(View view){ //Функция привязки полей к объектам для связки графики и кода
        minCountText = view.findViewById(R.id.minCountText);
        maxCountText = view.findViewById(R.id.maxCountText);
        attemptsText = view.findViewById(R.id.attemptsText);
        startGameButton = view.findViewById(R.id.startGameButton);
    }
}
