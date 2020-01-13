package com.example.parsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Images extends AppCompatActivity {

    RecyclerView recyclerView;

    private ArrayList<String> mImagesUrls = new ArrayList<>();
    private ArrayList<String> mUrl = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);


    }


    public void onStart() {

        Parsing mt = new Parsing();
        mt.execute();

        super.onStart();
    }

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.recyclerView);
        ImagesAdapter adapter = new ImagesAdapter(this, mImagesUrls, mUrl);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    class Parsing extends AsyncTask<Void, Void, Void> {

       // String url = editText.getText().toString();
         String url = getIntent().getExtras().getString("url");

        @Override
        protected Void doInBackground (Void... voids) {
            Document doc;
            try {

                mImagesUrls.clear();
                doc = Jsoup.connect(url).get();
                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
                for (Element image : images) {

                    mImagesUrls.add(image.attr("src"));
                    mUrl.add(image.attr("src"));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void list){
            initRecyclerView();
        }

    }
}
