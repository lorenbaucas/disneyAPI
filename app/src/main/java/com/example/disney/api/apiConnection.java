package com.example.disney.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class apiConnection {
    private static String URL_BASE = "https://api.disneyapi.dev/characters?page=1";

    //Metodo para sacar todos los datos del JSON
    public static String getRequest() {
        HttpURLConnection http = null;
        String content = null;
        try {
            //Establezco la conexion
            http = (HttpURLConnection) new URL(URL_BASE).openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");

            //Si es correcta vamos leyendo y escribiendo la respuesta
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(http.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (http != null) http.disconnect();
        }
        return content;
    }


    public static int post(String datos){
        HttpURLConnection http = null;
        int respuestaCodigo = -1;
        try{
            //Abrimos la conexion con la API
            http = (HttpURLConnection) new URL(URL_BASE).openConnection();
            //Establecemos la conexión
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type","application/json");
            http.setRequestProperty("Accept","application/json");
            http.setDoOutput(true);

            //Creamos el writer y mostramos los datos por pantalla
            PrintWriter pw = new PrintWriter(http.getOutputStream());
            pw.print(datos);
            pw.flush();
            //Comprobamos si los datos han sido leidos correctamente
            respuestaCodigo = http.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (http != null){
                http.disconnect();
            }
        }
        return respuestaCodigo;
    }
}
