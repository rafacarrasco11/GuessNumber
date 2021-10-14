package com.example.guessnumber.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.guessnumber.GuessNumberApplication;
import com.example.guessnumber.R;
import com.example.guessnumber.databinding.ActivityConfigBinding;
import com.example.guessnumber.model.Message;

/**
 * Esta clase recoge el nombre de el usuario y el numero de intentos que quiere para el juego
 * No funcionara si no has introducido un Integer en el edAttemps y una cadena cualquiera
 * en el nombre de usuario.
 *
 * @author Rafa CS
 * @version 1.0
 */
public class ConfigActivity extends AppCompatActivity {

    ActivityConfigBinding binding;
    int attemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPlay.setOnClickListener(view -> {
            try {
                attemps = Integer.parseInt(binding.edAttemps.getText().toString());
                if (!(attemps < 1)) {
                    ((GuessNumberApplication) getApplication()).setUserName(binding.edUserName.getText().toString());
                    sendInfo();
                }
            } catch (NumberFormatException nfex ) {
                showMessage(getText(R.string.tvErrorMessagePlayConfigAttemps).toString());
            }
        });
    }

    /**
     * Este metodo envia un intent con los datos recogidos en los EditText
     * a la Activity PlayActivity
     */
    private void sendInfo() {
        Bundle bundle = new Bundle();

        bundle.putSerializable("attemps", attemps);

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    //--------------------------------------------------------------------------------
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("attemps", this.attemps);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.attemps = savedInstanceState.getInt("attemps");
    }
}