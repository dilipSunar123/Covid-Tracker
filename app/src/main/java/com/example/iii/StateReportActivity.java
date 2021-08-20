package com.example.iii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StateReportActivity extends AppCompatActivity {

    final String REPORT_URL = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

    ArrayList<Item> itemArrayList;
    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_report);
        View mainLayout = findViewById(R.id.mainLayout);
        Drawable drawable = mainLayout.getBackground();
        drawable.setAlpha(20);

        itemArrayList = new ArrayList<>();
        loadStateReport();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_filter_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    public void loadStateReport () {
        Log.d ("debugging", "entered the loadSateReport() Fn.");
        RequestQueue requestQueue = Volley.newRequestQueue(StateReportActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, REPORT_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("regionData");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

                                String region = object.getString("region");
                                Long activeCases = object.getLong("activeCases");
                                Long newInfected = object.getLong("newInfected");
                                Long recovered = object.getLong("recovered");
                                Long newRecovered = object.getLong("newRecovered");
                                Long deceased = object.getLong("deceased");
                                Long newDeceased = object.getLong("newDeceased");
                                Long total = object.getLong("totalInfected");

                                itemArrayList.add(new Item(region, activeCases, newInfected, recovered, newRecovered, deceased,
                                        newDeceased, total));
                            }

                            recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setLayoutManager(new LinearLayoutManager(StateReportActivity.this));
                            adapter = new CustomAdapter(StateReportActivity.this, itemArrayList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StateReportActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
        Log.d("debugging", "ObjectRequest added to the queue.");
    }
}