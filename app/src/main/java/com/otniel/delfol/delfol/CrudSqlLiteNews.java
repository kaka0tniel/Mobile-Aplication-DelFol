package com.otniel.delfol.delfol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrudSqlLiteNews extends AppCompatActivity {
private Button btnCreateNews;
private Button btnUpdateNews;
private Button btnDeleteNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_sql_lite_news);
        btnCreateNews=(Button) findViewById(R.id.btnCreateNews);

        btnCreateNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new
                        Intent(getApplicationContext(),CreateNews.class);
                startActivity(nextScreen);
            }
        });
    }
}
