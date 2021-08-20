package com.example.iii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView activeView;
    private TextView recoveredView;
    private TextView deathView;
    private Button stateWiseButton;

    static final String REPORT_URL = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // For background opacity
        View mainLayout = findViewById(R.id.mainLayout);
        Drawable drawable = mainLayout.getBackground();
        drawable.setAlpha(20);

        activeView = findViewById(R.id.activeView);
        recoveredView = findViewById(R.id.recoveredView);
        deathView = findViewById(R.id.deathView);
        stateWiseButton = findViewById(R.id.stateReportBtn);

        stateWiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StateReportActivity.class);
                startActivity(intent);
            }
        });
        loadReport();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(MainActivity.this);
        inflater.inflate(R.menu.menu_all, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.stateReport_item) {
            Intent intent = new Intent(MainActivity.this, StateReportActivity.class);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.credit) {
            Intent intent = new Intent(MainActivity.this, CreditActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadReport () {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, REPORT_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String activeCases = response.getString("activeCases");
                            String recoveredCases = response.getString("recovered");
                            String deathCases = response.getString("deaths");

                            activeView.setText(activeCases);
                            recoveredView.setText(recoveredCases);
                            deathView.setText(deathCases);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { }
        });
        requestQueue.add(jsonObjectRequest);
    }
}