package com.cookandroid.week13_assi_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second);

        Button btnReturn = (Button) findViewById(R.id.btnReturn2);
        Button btnNew = (Button) findViewById(R.id.btnNew);

        RadioButton rdoThird = (RadioButton) findViewById(R.id.rdoThird);
        RadioButton rdoFourth = (RadioButton) findViewById(R.id.rdoFourth);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;

                if(rdoThird.isChecked()) {
                    // third 액티비티 이동
                    intent = new Intent(SecondActivity.this, ThirdActivity.class);

                } else if(rdoFourth.isChecked()) {
                    // fourth 액티비티 이동
                    intent = new Intent(SecondActivity.this, FourthActivity.class);
                } else {
                    return;
                }
                startActivity(intent);


            }
        });
    }
}