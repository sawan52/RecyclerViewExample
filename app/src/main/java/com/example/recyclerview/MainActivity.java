package com.example.recyclerview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=662c5bcc6a9f42f99de159ad6f53bad2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItemList;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressDialog = new ProgressDialog(this);
        // this means every item of a recyclerView has a fixed size
        recyclerView.setHasFixedSize(true);
        // set the Layout Manager of the Recycler View and pass the context as 'this'
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // inside this listItemList we need to put all the data which we want to display in our RecyclerView
        listItemList = new ArrayList<>();

//      This is the Dummy Data for testing...

//        for (int i = 0; i < 10; i++){
//
//            ListItem listItem = new ListItem("Heading " + (i+1), "Dummy Data");
//            // added all the data inside listItemList using listItem object
//            listItemList.add(listItem);
//        }


        loadRecyclerViewData();
    }


    private void loadRecyclerViewData() {
        progressDialog.setMessage("Loading data...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        // fetch the data from server
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("articles");

                            for (int i = 0; i < array.length(); i++){

                                JSONObject object = array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        object.getString("title"),
                                        object.getString("description"),
                                        object.getString("urlToImage"));

                                // add the data to the List
                                listItemList.add(item);
                            }

                            // create the adapter and set the adapter to the RecyclerView
                            adapter = new MyAdapter(listItemList, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "An Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
