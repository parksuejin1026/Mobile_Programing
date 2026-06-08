package com.cookandroid.week_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    int selectYear, selectMonth, selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnEnd = findViewById(R.id.btnEnd);
        Chronometer chrono = findViewById(R.id.chronometer);

        RadioButton rdoCal = findViewById(R.id.rdoCal);
        RadioButton rdoTime = findViewById(R.id.rdoTime);

        CalendarView calendarView = findViewById(R.id.calendarView);
        TimePicker tPicker = findViewById(R.id.timePicker);

        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvMonth = findViewById(R.id.tvMonth);
        TextView tvDay = findViewById(R.id.tvDay);
        TextView tvHour = findViewById(R.id.tvHour);
        TextView tvMinute = findViewById(R.id.tvMinute);



        tPicker.setVisibility(View.INVISIBLE);
        calendarView.setVisibility(View.INVISIBLE);

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE); // 타임피커 보이게
                calendarView.setVisibility(View.VISIBLE); // 캘린더뷰 끄기
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.INVISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                tvYear.setText(String.valueOf(selectYear));
                tvMonth.setText(String.valueOf(selectMonth));
                tvDay.setText(String.valueOf(selectDay));

                tvHour.setText(String.valueOf(tPicker.getCurrentHour()));
                tvMinute.setText(String.valueOf(tPicker.getCurrentMinute()));
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                selectYear = year;
                selectMonth = month + 1;
                selectDay = day;
            }
        });
    }
}