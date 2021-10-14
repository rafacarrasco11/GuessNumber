package com.example.guessnumber.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guessnumber.GuessNumberApplication;
import com.example.guessnumber.R;
import com.example.guessnumber.databinding.ActivityEndPlayBinding;

/**
 * En esta Actividad se vuelcan los resultados del juego.
 * La informacion viene contenidaen un intent que se vuelca sobre unos TextView.
 *
 * @author Rafa CS
 * @version 1.0
 */
public class EndPlayActivity extends AppCompatActivity {

    ActivityEndPlayBinding binding;
    boolean acertado;
    int secretNum;
    int attempsDone;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEndPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userName = ((GuessNumberApplication)getApplication()).getUserName();

        getInfo();

        setInfoControles();
    }

    private void getInfo() {
        Intent intent = getIntent();

        acertado = (boolean) intent.getExtras().getSerializable("acertado");
        attempsDone = (int) intent.getExtras().getSerializable("attempsDone");
        secretNum = (int) intent.getExtras().getSerializable("secretNum");
    }

    @SuppressLint("ResourceAsColor")
    private void setInfoControles() {
        if (acertado) {
            String introWin = getText(R.string.tvEndPlayIntroWin) + " " + this.userName + ",";
            binding.tvIntro.setText(introWin);

            binding.tvGameResult.setTextColor(R.color.greenWin);
            binding.tvGameResult.setText(getText(R.string.tvEndPlayGameResultWin));

            String infoWin = getText(R.string.tvEndPlayInfoWin) + " " + this.attempsDone + " " + getText(R.string.attemps).toString();
            binding.tvFinalInfo.setText(infoWin);
            return;
        }

        String introLoose = getText(R.string.tvEndPlayIntroLoose) + " " + this.userName + ",";
        binding.tvIntro.setText(introLoose);

        binding.tvGameResult.setTextColor(R.color.redLoose);
        binding.tvGameResult.setText(getText(R.string.tvEndPlayGameResultLoose));

        String infoLoose = getText(R.string.tvEndPlayInfoLoose) + " " + this.secretNum;
        binding.tvFinalInfo.setText(infoLoose);
    }

    //--------------------------------------------------------------------------------------------------
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("acertado", this.acertado);
        outState.putInt("secretNum", this.secretNum);
        outState.putInt("attempsDone", this.attempsDone);
        outState.putString("userName", this.userName);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.acertado = savedInstanceState.getBoolean("acertado");
        this.secretNum = savedInstanceState.getInt("secretNum");
        this.attempsDone = savedInstanceState.getInt("attempsDone");
        this.userName = savedInstanceState.getString("userName");
    }
}