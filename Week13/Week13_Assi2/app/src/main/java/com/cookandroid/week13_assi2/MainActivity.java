package com.cookandroid.week13_assi2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배경색 바꾸기(컨텍스트 메뉴)");
        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        registerForContextMenu(btn1);
        registerForContextMenu(btn2);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mif = getMenuInflater();
        if (v == btn1) {
            menu.setHeaderTitle("배경색 변경");
            mif.inflate(R.menu.menu, menu);
        }

        if (v == btn2) {
            menu.setHeaderTitle("버튼 변경");
            mif.inflate(R.menu.menu2, menu);
        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

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

            btn2.setRotation(45);
            return true;

        } else if(item.getItemId() == R.id.subSize) {

            btn2.setScaleX(2);
            return true;
        }

        return super.onContextItemSelected(item);

    }
}