package com.example.iii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreditActivity extends AppCompatActivity {

    private Button visitApifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        View mainLayout = findViewById(R.id.mainLayout);
        Drawable drawable = mainLayout.getBackground();
        drawable.setAlpha(20);

        visitApifyBtn = findViewById(R.id.visitApifyBtn);

        visitApifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://apify.com/"));

                startActivity(intent);
            }
        });
    }
}