package com.example.leticia.appmoveis_trabalho2;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * @author Francielly Gomes
 * Activity Principal responsabel por controlar a interação com os componentes da tela
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvNumeroRand;
    private ImageButton btRandomizar;
    private Button btDefinirLimites;
    private EditText etInicio;
    private EditText etFinal;
    private Button btVoltarPadrao;
    private LinearLayout llIntervalo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define a orientação da tela para portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //inicialização das variaveis que controlam os componentes
        tvNumeroRand = findViewById(R.id.tvNumeroRand);
        btRandomizar = findViewById(R.id.btRandomizar);
        btDefinirLimites = findViewById(R.id.btDefinirLimites);
        etInicio = findViewById(R.id.etInicio);
        etFinal = findViewById(R.id.etFinal);
        btVoltarPadrao = findViewById(R.id.btVoltarPadrao);
        llIntervalo = findViewById(R.id.llIntervalo);

        llIntervalo.setVisibility(View.INVISIBLE);

        verificarIntervalo(etInicio.getText().toString(), etFinal.getText().toString());

        btDefinirLimites.setOnClickListener(clickListenerDefinirLimites());
        btVoltarPadrao.setOnClickListener(clickListenerVoltarPadrao());
        btRandomizar.setOnClickListener(clickListenerRandomizar());

        randomizar();
    }

    private View.OnClickListener clickListenerDefinirLimites(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llIntervalo.setVisibility(View.VISIBLE);
            }
        };
    }

    private View.OnClickListener clickListenerVoltarPadrao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInicio.setText("1");
                etFinal.setText("10");
                llIntervalo.setVisibility(View.INVISIBLE);
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
        if(verificarIntervalo(etInicio.getText().toString(), etFinal.getText().toString())){
            try{
                int inicio = Integer.parseInt(etInicio.getText().toString());
                int fim = Integer.parseInt(etFinal.getText().toString());
                Random r = new Random();
                int num = r.nextInt((fim - inicio) + 1) + inicio;
                tvNumeroRand.setText(String.valueOf(num));
            }catch (NumberFormatException e) {
                alertar("Erro no cálculo do randômico");
            }
        }
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
