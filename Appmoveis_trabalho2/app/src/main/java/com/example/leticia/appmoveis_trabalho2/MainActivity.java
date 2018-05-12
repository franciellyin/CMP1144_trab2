package com.example.leticia.appmoveis_trabalho2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * @author Francielly Gomes
 * Activity Principal responsavel por controlar a interação com os componentes da tela
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvNumeroRand;
    private ImageButton btRandomizar;
    private Button btDefinirLimites;
    private Button btVoltarPadrao;
    private int inicio = 1;
    private int fim = 10;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.v(TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define a orientação da tela para portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inicio = 1;
        fim = 10;

        //inicialização das variaveis que controlam os componentes
        tvNumeroRand = findViewById(R.id.tvNumeroRand);
        btRandomizar = findViewById(R.id.btRandomizar);
        btDefinirLimites = findViewById(R.id.btDefinirLimites);

        btVoltarPadrao = findViewById(R.id.btVoltarPadrao);

        btDefinirLimites.setOnClickListener(clickListenerDefinirLimites());
        btVoltarPadrao.setOnClickListener(clickListenerVoltarPadrao());
        btRandomizar.setOnClickListener(clickListenerRandomizar());

        randomizar();
    }

    private View.OnClickListener clickListenerDefinirLimites(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), IntervalActivity.class);
                i.putExtra("inicio", String.valueOf(inicio));
                i.putExtra("fim", String.valueOf(fim));
                startActivityForResult(i, 1);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.v(TAG, "onactivity result");
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strIni = data.getStringExtra("inicio");
                String strFim = data.getStringExtra("fim");

                try {
                    inicio = Integer.parseInt(strIni);
                    fim = Integer.parseInt(strFim);
                    randomizar();
                }catch (Exception e){
                    inicio = 1;
                    fim = 10;
                    alertar("Intervalo redefinido para o padrão. Volte e informe o intervalo corretamente. ");
                }
            }
        }
    }


    private View.OnClickListener clickListenerVoltarPadrao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicio = 1;
                fim = 10;
            }
        };
    }

    private View.OnClickListener clickListenerRandomizar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomizar();
            }
        };
    }

    private void randomizar(){
        try{
            Random r = new Random();
            int num = r.nextInt((fim - inicio) + 1) + inicio;
            tvNumeroRand.setText(String.valueOf(num));
        }catch (NumberFormatException e) {
            alertar("Erro no cálculo do randômico");
        }
    }



    private void alertar(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
