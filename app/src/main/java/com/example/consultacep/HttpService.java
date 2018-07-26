package com.example.consultacep;


import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, CEP>{
    private final String cep;

    public HttpService(String cep){
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if(this.cep != null && this.cep.length() == 8){

            try {
                URL url = new URL("https://api.postmon.com.br/v1/cep/" + this.cep);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);

                connection.connect();



                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()){
                    resposta.append(scanner.next());
                    Log.i("INFO", "doInBackground: " + resposta.toString());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(resposta.toString(), CEP.class);
    }
}
