package com.example.joseph.kendrickjsonexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    private ProgressDialog pd;
    private static final String url_data = "https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById (R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        //Load the recycler Data
        fetchingData();
    }

    private void fetchingData()
    {
        //Fetching all data from the website listed
        pd = new ProgressDialog(this);
        pd.setMessage("Currently fetching Marvel data information...");
        pd.show();

        //----String Request
        //1st Parameter: HTTP REQUEST.METHOD
        //2nd Parameter: URL
        //3rd Parameter: Response Listener -- listens for response
        //4th Parameter: Error Listener -- execute if error
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();

                        try {
                            //Fetch the JSON Array  using the response from the stringRequest
                            JSONArray jsonArray = new JSONArray(response);

                            for(int items = 0; items < jsonArray.length(); items++)
                            {
                                JSONObject o = jsonArray.getJSONObject(items);

                                ListItem item = new ListItem(o.getString("name"),
                                                        o.getString("realname"),
                                        o.getString("team"),
                                        o.getString("firstappearance"),
                                        o.getString("createdby"),
                                        o.getString("publisher"),
                                        o.getString("imageurl"),
                                        o.getString("bio"));

                                        listItems.add(item);
                            }

                            adapter = new MyAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);
    }

}
