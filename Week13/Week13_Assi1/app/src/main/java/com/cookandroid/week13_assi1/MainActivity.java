package com.cookandroid.week13_assi1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button = (Button) findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemRed) {

            baseLayout.setBackgroundColor(Color.RED);
            return true;

        } else if(item.getItemId() == R.id.itemBlue) {

            baseLayout.setBackgroundColor(Color.BLUE);
            return true;

        } else if(item.getItemId() == R.id.itemGreen) {

            baseLayout.setBackgroundColor(Color.GREEN);
            return true;

        } else if(item.getItemId() == R.id.subRotate) {

            button.setRotation(45);
            return true;

        } else if(item.getItemId() == R.id.subSize) {

            button.setScaleX(2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}