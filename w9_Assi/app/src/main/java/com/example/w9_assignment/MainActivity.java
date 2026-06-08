package com.example.w9_assignment;

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

    // 1. 멤버 변수 선언 (정상 선언 상태)
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView calendarView;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;
    int selectYear, selectMonth, selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. 위젯 ID 매핑
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        chrono = findViewById(R.id.chronometer);
        rdoCal = findViewById(R.id.rdoCal);
        rdoTime = findViewById(R.id.rdoTime);
        calendarView = findViewById(R.id.calendarView);
        tPicker = findViewById(R.id.timePicker);
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);
        tvHour = findViewById(R.id.tvHour);
        tvMinute = findViewById(R.id.tvMinute);

        // 초기 상태: 두 위젯을 숨김
        tPicker.setVisibility(View.INVISIBLE);
        calendarView.setVisibility(View.INVISIBLE);

        // 3. 날짜 설정 라디오 버튼 리스너
        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // [★교정] 이 내부에 가시성 제어 코드가 들어가야 합니다.
                tPicker.setVisibility(View.INVISIBLE);
                calendarView.setVisibility(View.VISIBLE);
            }
        }); // <-- 원래 스크린샷에서는 이 닫는 괄호가 엉뚱한 곳에 가 있었습니다.

        // 4. 시간 설정 라디오 버튼 리스너
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.INVISIBLE);
            }
        });

        // 5. 타이머 시작 버튼 리스너
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        // 6. 예약 완료 버튼 리스너
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

        // 7. 캘린더뷰 날짜 변경 리스너
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month + 1;
                selectDay = dayOfMonth;
            }
        });

    } // <-- onCreate() 메서드가 최종적으로 닫히는 중괄호
} // <-- MainActivity 클래스가 최종적으로 닫히는 중괄호