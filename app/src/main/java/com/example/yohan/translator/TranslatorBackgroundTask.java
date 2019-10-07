package com.example.yohan.translator;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TranslatorBackgroundTask extends AsyncTask<String, Void, String> {

    //Declare Context
    Context ctx;
    //Set Context
    TranslatorBackgroundTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        //String variables
        String textToBeTranslated = params[0];
        String languagePair = params[1];

        String jsonString;

        try {
            //Set up the translation call URL
            String yandexKey = "";
            String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                    + "&text=" + textToBeTranslated + "&lang=" + languagePair;
            String s = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20191006T125811Z.4097f8efb295c0b8.78634a22ec40dae9329546d7513682c137eb87ca&text=Hello&lang=en-si";
            URL yandexTranslateURL = new URL(yandexUrl);

            //Set Http Conncection, Input Stream, and Buffered Reader
            HttpURLConnection httpJsonConnection = (HttpURLConnection) yandexTranslateURL.openConnection();
            InputStream inputStream = httpJsonConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            //Set string builder and insert retrieved JSON result into it
            StringBuilder jsonStringBuilder = new StringBuilder();
            while ((jsonString = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(jsonString + "\n");



            }

            //Close and disconnect
            bufferedReader.close();
            inputStream.close();
            httpJsonConnection.disconnect();

            //Making result human readable
            String resultString = jsonStringBuilder.toString().trim();

            JSONObject jsonObject = new JSONObject(resultString);
            String result = jsonObject.getString("text");
            int length = result.length();
            String result1 = result.substring(2,length-2);

//            //Getting the characters between [ and ]
//            resultString = resultString.substring(resultString.indexOf('[')+1);
//            resultString = resultString.substring(0,resultString.indexOf("]"));
//            //Getting the characters between " and "
//            resultString = resultString.substring(resultString.indexOf("\"")+1);
//            resultString = resultString.substring(0,resultString.indexOf("\""));

            Log.d("Translation Result:", resultString);
            return result1;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void translate(){

    }
}
