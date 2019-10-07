package com.example.yohan.translator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    EditText editText;
    TextView textView;
    Button btnTranslate;
    Spinner spinner1,spinner2;
    String language1,language2;
    int lang,lang1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.ETTranslator);
        textView = findViewById(R.id.TVTranslator);
        btnTranslate= findViewById(R.id.btnTranslator);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);



        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lang = spinner1.getSelectedItemPosition();

                if(lang == 0){
                    language1 = "en";
                }else if(lang == 1){
                    language1 = "si";

                }else if(lang == 2){
                    language1 = "ta";
                }

                lang1 = spinner2.getSelectedItemPosition();

                if(lang1 == 0){
                    language2 = "si";
                }else if(lang1 == 1){
                    language2 = "en";
                }else if(lang1 == 2){
                    language2 = "ta";
                }

                String textToBeTranslated = editText.getText().toString();
                String languagePair = language1+"-"+language2; //English to French ("<source_language>-<target_language>")
                //Executing the translation function
                try {
                    Translate(textToBeTranslated,languagePair);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });


//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                String textToBeTranslated = editText.getText().toString();
////                String languagePair = language1+"-"+language2; //English to French ("<source_language>-<target_language>")
////                //Executing the translation function
////                try {
////                    Translate(textToBeTranslated,languagePair);
////                } catch (ExecutionException e) {
////                    e.printStackTrace();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                };
//
//                lang = spinner1.getSelectedItemPosition();
//
//                if(lang == 0){
//                    language1 = "en";
//                }else if(lang == 1){
//                    language1 = "si";
//
//                }else if(lang == 2){
//                    language1 = "ta";
//                }
//
//                lang1 = spinner2.getSelectedItemPosition();
//
//                if(lang1 == 0){
//                    language2 = "si";
//                }else if(lang1 == 1){
//                    language2 = "en";
//                }else if(lang1 == 2){
//                    language2 = "ta";
//                }
//
//                String textToBeTranslated = editText.getText().toString();
//                String languagePair = language1+"-"+language2; //English to French ("<source_language>-<target_language>")
//                //Executing the translation function
//                try {
//                    Translate(textToBeTranslated,languagePair);
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
    }

    public void Translate(String textToBeTranslated,String languagePair) throws ExecutionException, InterruptedException {
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(getApplication());
        String translationResult = translatorBackgroundTask.execute(textToBeTranslated,languagePair).get(); // Returns the translated text as a String
        textView.setText(translationResult);// Logs the result in Android Monitor
    }
}
