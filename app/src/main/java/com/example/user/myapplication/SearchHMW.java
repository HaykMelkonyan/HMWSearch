package com.example.user.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SearchHMW extends AppCompatActivity {

    Button btn_search;
    EditText txt_search;
    String[] searchList;
    ArrayList<String> adapterList =new ArrayList<String>() ;
    ArrayAdapter<String> adapter;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hmw);
        btn_search= (Button ) findViewById(R.id.btnSearch);
        txt_search =(EditText) findViewById(R.id.txtSearch);
        searchList= new String[]{"Finland", "Russia", "Latvia", "Lithuania", "Poland","Armenia", "England", "Egypt", "Spain","Germany" ,"Italy"};
        listView= (ListView)findViewById(R.id.listView);




        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, adapterList);
        listView.setAdapter(adapter);




        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterList = new ArrayList<String>();
                AsyncTask<String, String, List<Object>> task = new AsyncTask<String, String, List<Object>>() {


                    @Override
                    protected void onProgressUpdate(String... values) {
                        super.onProgressUpdate(values);
                        adapterList.add(values[0]);//
                        //     adapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        adapterList = new ArrayList<String>();
                    }

                    @Override
                    protected List<Object> doInBackground(String... params) {
                        String searchItem = params[0];
                        for (String item : searchList) {
                            if (item.toLowerCase().contains(searchItem.toLowerCase()))
                                publishProgress(item);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(List<Object> objects) {
                        super.onPostExecute(objects);

                        //notifyDataSetChanged() չաշխատեց դրա համար սենց եմ գրել
                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_list_item_1, adapterList);

                        listView.setAdapter(adapter);


                    }
                };

                task.execute(txt_search.getText().toString());
            }
        });
    }
}
