package com.example.testt00000;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class HomeActivity extends AppCompatActivity {

    Button search, review;
    EditText word;
    String partOfSpeech;

    WordFragment wordFrag = new WordFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        search = findViewById(R.id.searchWord);
        word = findViewById(R.id.editText);
        review = findViewById(R.id.reviewList);

        FragmentManager fragmentManager = getSupportFragmentManager();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.id_frameLayout, wordFrag);
                fragmentTransaction.commit();
                new JsonClass1().execute();
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, WordRecycler.class));
            }
        });
    }//onCreate

    private class JsonClass1 extends AsyncTask<URL, Void, Void> {
        String name, definition, pronounce, origins, url;
        JSONArray jsonarr = new JSONArray();
        protected Void doInBackground(URL... urls) {
            String txt = "";
            String wordString = word.getText().toString();
            Log.d("TAG_WORD", wordString);

            StringBuilder stringB = new StringBuilder();
            URL urlDict = null;
            try{
                urlDict = new URL("https://www.dictionaryapi.com/api/v3/references/collegiate/json/"+word.getText().toString().toLowerCase()+"?key=f0dd951f-e385-4945-968d-f2b53094b568");
                Log.d("TAG_INFOurl", urlDict.toString());
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
            URLConnection connect = null;
            try{
                assert urlDict != null;
                connect = urlDict.openConnection();

            }catch(IOException e){
                e.printStackTrace();
            }
            try{
                assert connect != null;
                BufferedReader input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                while((txt = input.readLine()) != null){
                    stringB.append(txt);
                }
                jsonarr = new JSONArray(stringB.toString());
            }catch(Exception e){
                e.printStackTrace();
            }
            Log.d("TAG_INFO", jsonarr.toString());

            return null;
        }//doInBackground

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{
                name = word.getText().toString();
                JSONArray subArr = new JSONArray();
                subArr = jsonarr.getJSONObject(0).getJSONArray("shortdef");
                definition = subArr.get(0).toString();
                JSONArray subArr2 = new JSONArray();
                subArr2 = jsonarr.getJSONObject(0).getJSONArray("et");
                origins = subArr2.getJSONArray(0).get(1).toString();
                JSONObject subobj = new JSONObject();
                subobj = jsonarr.getJSONObject(0).getJSONObject("hwi");
                JSONObject subobj2 = new JSONObject();
                subobj2 = subobj.getJSONArray("prs").getJSONObject(0);
                pronounce = subobj2.getJSONObject("sound").get("audio").toString();
                String pronounceIndex = pronounce.substring(0,1);
                Log.d("TAG_name", name);
                Log.d("TAG_def", definition);
                Log.d("TAG_origins", origins);
                Log.d("TAG_pronounce", pronounce);
                Log.d("TAG_pronounceIndex", pronounceIndex);
                url = "https://media.merriam-webster.com/audio/prons/en/us/mp3/"+pronounceIndex+"/"+pronounce+".mp3";
            }catch(JSONException e){
                e.printStackTrace();
            }
            ((TextView)wordFrag.getView().findViewById(R.id.nameText)).setText(name);
            ((TextView)wordFrag.getView().findViewById(R.id.defText)).setText("Definition: "+definition);
            ((TextView)wordFrag.getView().findViewById(R.id.originsText)).setText("Origins: "+origins);
            ((TextView)wordFrag.getView().findViewById(R.id.urlText)).setText(url);
        }//onPostExecute
    }//AsyncTask: JsonClass1
}
