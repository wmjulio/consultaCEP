package com.example.consultacep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cep = (EditText) findViewById(R.id.etMain_cep);
        final TextView resposta = (TextView) findViewById(R.id.etMain_resposta);


        Button buscar = (Button) findViewById(R.id.btnMain_buscarCep);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Buscar CEP
                try {
                    CEP retorno = new HttpService(cep.getText().toString()).execute().get();
                    resposta.setText(retorno.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
