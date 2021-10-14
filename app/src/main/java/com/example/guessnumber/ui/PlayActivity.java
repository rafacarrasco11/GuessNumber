package com.example.guessnumber.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.guessnumber.GuessNumberApplication;
import com.example.guessnumber.R;
import com.example.guessnumber.databinding.ActivityPlayBinding;

import java.util.Random;

/**
 * Esta Actividad es la que gestiona el jeugo.
 * Hay 2 botones que sirven para jugar y un EditText para introducir un intento de adivinar el numero
 * Tambien hay algunos TextView para volcar info sobre el juego, como una pista (tvInfoGame)
 *
 * Esta actividad recibe los intentos que queremos realizar y genera un Numero secreto. Con esto ya podemos jugar
 *
 *
 * @author Rafa CS
 * @version 1.0
 */
public class PlayActivity extends AppCompatActivity {

    private static final String TAG = "CONSTANTES ";
    boolean acertado;
    ActivityPlayBinding binding;
    int secretNum;
    Random r = new Random();
    int attemps;
    int attempsDone;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userName = ((GuessNumberApplication)getApplication()).getUserName().toString();
        acertado = false;
        attempsDone = 0;
        secretNum = r.nextInt(100) + 1;
        super.onCreate(savedInstanceState);

        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        attemps  = getAttempsFromConfig();


        binding.btnCheckNumber.setOnClickListener(View -> {
            Play();
        });

        binding.btnTryAgain.setOnClickListener(View -> {
            if (!canTryAgain()) {
                goEndPlayActivity();
            }
            binding.btnCheckNumber.setEnabled(true);
            binding.btnTryAgain.setEnabled(false);
            binding.edAttemp.setText("");
            binding.tvInfoGame.setText("");
        });
    }

    //---------------------------------------------------------------------------------------

    /**
     * Comprueba que hemos introducido un numero en el EditText edAttemp
     * @return
     */
    private boolean checkTvClickBtnCheckNumber() {
        if (!TextUtils.isEmpty(binding.edAttemp.getText())) {
            if (TextUtils.isDigitsOnly(binding.edAttemp.getText()) && (Integer.parseInt(binding.edAttemp.getText().toString())) <= 100 ) {
                return true;
            } else {
                showMessage(getText(R.string.tvErrorMessagePlayAttempInteger).toString());
            }
        }
        return false;
    }

    /**
     * Comprueba que no hayamos gastado los intentos
     * @return
     */
    private boolean canTryAgain() {
        if (attempsDone < attemps) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que comprueba si el intento que hemos probado es amyor o menor que el numero secreto.
     * Si es igual devuelve 0, si es mayor devuelve 1 y si es menir devuelve 2.
     *
     * @param attemp
     * @return
     */
    private int checkAttemp(int attemp) {
        if (attemp > secretNum) {
            return 1;
        }
        if (attemp < secretNum) {
            return 2;
        }
        return 0;
    }

    /**
     * Metodo donde se desarrolla el juego:
     * Mientras que no hayamos consumido nuestros intentos, entramos en el if y comprobamos
     * para escribir una pista sobre si el numero es maor o menor
     */
    private void Play() {
        if (checkTvClickBtnCheckNumber()) {
            int attemp = Integer.parseInt(binding.edAttemp.getText().toString());
            binding.btnCheckNumber.setEnabled(false);
            binding.btnTryAgain.setEnabled(true);

            if (checkAttemp(attemp) == 2) {
                String msgHigher = userName + ", " + getString(R.string.tvNumberIsHigher);
                binding.tvInfoGame.setText(msgHigher);
            }
            if (checkAttemp(attemp) == 1) {
                String msgLower = userName + ", " + getString(R.string.tvNumberIsLower);
                binding.tvInfoGame.setText(msgLower);
            }

            if (checkAttemp(attemp) == 0 ) {
                acertado = true;
                goEndPlayActivity();
            }

            attempsDone++;
        }
    }

    /**
     * Metodo para mostrar un mensaje de error por pantalla qque desparece
     * @param s
     */
    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    //---------METODOS PARA ENVIAR Y RECIBIR INFO ENTRE ACTIVITIES----------------------------------------------------
    private int getAttempsFromConfig() {
        Intent intent = getIntent();

        return (int) intent.getExtras().getSerializable("attemps");
    }

    private void goEndPlayActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt("attempsDone", attempsDone);
        bundle.putInt("secretNum", secretNum);
        bundle.putBoolean("acertado", acertado);

        Intent intent = new Intent(this, EndPlayActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    //----------METODOS PARA GUARDAR LAS VARIABLES -------------------------------------------------------------------
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("secretNum", this.secretNum);
        outState.putBoolean("acertado", this.acertado);
        outState.putInt("attemps", this.attemps);
        outState.putInt("attempsDone", this.attempsDone);
        outState.putString("userName", this.userName);
        outState.putBoolean("btCheckNumber", this.binding.btnCheckNumber.isEnabled());
        outState.putBoolean("btTryAgain", this.binding.btnTryAgain.isEnabled());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.secretNum = savedInstanceState.getInt("secretNum");
        this.acertado = savedInstanceState.getBoolean("acertado");
        this.attemps = savedInstanceState.getInt("attemps");
        this.attempsDone = savedInstanceState.getInt("attempsDone");
        this.userName = savedInstanceState.getString("userName");
        this.binding.btnCheckNumber.setEnabled(savedInstanceState.getBoolean("btCheckNumber"));
        this.binding.btnTryAgain.setEnabled(savedInstanceState.getBoolean("btTryAgain"));
    }

}