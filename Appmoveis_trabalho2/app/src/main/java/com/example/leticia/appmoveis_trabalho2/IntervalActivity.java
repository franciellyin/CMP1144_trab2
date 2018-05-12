package com.example.leticia.appmoveis_trabalho2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Francielly Gomes
 * Activity responsavel por controlar o intervalo de rondomização
 */
public class IntervalActivity extends AppCompatActivity {

    private EditText etInicio;
    private EditText etFinal;
    private Button btSalvar;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.v(TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        //define a orientação da tela para portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etInicio = findViewById(R.id.etInicio);
        etFinal = findViewById(R.id.etFinal);
        btSalvar = findViewById(R.id.btSalvar);

        Bundle extras = getIntent().getExtras();
        String inicio = extras.getString("inicio");
        String fim = extras.getString("fim");

        try {
            if (inicio != null) {
                etInicio.setText(inicio);
            }
        }catch (Exception e){
            etInicio.setText("1");
        }

        try {
            if (fim != null) {
                etFinal.setText(fim);
            }
        }catch (Exception e){
            etFinal.setText("1");
        }

        btSalvar.setOnClickListener(clickListenerSalvar());
    }



    private View.OnClickListener clickListenerSalvar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarIntervalo(etInicio.getText().toString(), etFinal.getText().toString())){
                    Intent intent = new Intent();
                    intent.putExtra("inicio", etInicio.getText().toString());
                    intent.putExtra("fim", etFinal.getText().toString());
                    setResult(MainActivity.RESULT_OK, intent);
                    finish();
                }

            }
        };
    }

    private boolean verificarIntervalo(String strInicio, String strFim){
        try{
            int inicio = Integer.parseInt(strInicio);
            int fim = Integer.parseInt(strFim);
            if(inicio>=fim) {
                alertar("O valor do início do intervalo precisa ser menor que o fim");
                return false;
            }
            return true;
        }catch (NumberFormatException e) {
            alertar("Os valores informados para o intervalo precisam ser númericos");
            return false;
        }
    }

    private void alertar(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

}
