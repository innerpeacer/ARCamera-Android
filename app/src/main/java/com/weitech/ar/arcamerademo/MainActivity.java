package com.weitech.ar.arcamerademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    private void initButtons() {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.main);
        {
            Button button = new Button(this);
            button.setText("Show Unity");
            button.setX(200);
            button.setY(500);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startUnity();
                }
            });
            layout.addView(button);
        }
    }

    private void startUnity() {
        Intent intent = new Intent(this, TestUnityActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}