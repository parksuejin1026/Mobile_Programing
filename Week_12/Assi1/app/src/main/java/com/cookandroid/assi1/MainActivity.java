package com.cookandroid.assi1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Chronometer : 학습 시간 측정 위젯
    Chronometer chronometerStudy;

    // SeekBar : 목표 학습 시간 선택 위젯
    SeekBar seekBarGoal;

    // ProgressBar : 학습 진행률 표시 위젯
    ProgressBar progressStudy;

    // TextView : 목표 시간, 진행률, 상태 메시지 출력
    TextView tvGoalTime, tvProgress, tvStatus;

    // Button : 시작, 정지, 초기화 버튼
    Button btnStart, btnStop, btnReset;

    // 목표 학습 시간, 기본값 10분
    int goalMinute = 10;

    // 목표 시간을 밀리초 단위로 저장
    long goalMillis;

    // 현재까지 흐른 시간을 저장
    long elapsedMillis = 0;

    // 타이머 실행 여부 확인
    boolean isRunning = false;

    /*
        수업 실습용 테스트 모드

        true  : 1분을 5초로 계산
                예) 목표 10분 → 실제 50초 후 완료

        false : 실제 시간 기준
                예) 목표 10분 → 실제 10분 후 완료
    */
    final boolean TEST_MODE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_main.xml과 연결
        setContentView(R.layout.activity_main);

        // XML 위젯과 Java 변수 연결
        chronometerStudy = (Chronometer) findViewById(R.id.chronometerStudy);
        seekBarGoal = (SeekBar) findViewById(R.id.seekBarGoal);
        progressStudy = (ProgressBar) findViewById(R.id.progressStudy);

        tvGoalTime = (TextView) findViewById(R.id.tvGoalTime);
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnReset = (Button) findViewById(R.id.btnReset);

        // ProgressBar 기본 설정
        progressStudy.setMax(100);
        progressStudy.setProgress(0);

        /*
            SeekBar 설정

            max가 5이면 값은 0부터 5까지 사용 가능

            0 → 10분
            1 → 20분
            2 → 30분
            3 → 40분
            4 → 50분
            5 → 60분
        */
        seekBarGoal.setMax(5);
        seekBarGoal.setProgress(0);

        // 초기 목표 시간 설정
        setGoalTime(10);

        // Chronometer 초기 기준 시간 설정
        chronometerStudy.setBase(SystemClock.elapsedRealtime());

        /*
            Chronometer 시간 변화 이벤트

            Thread, Handler, Runnable을 사용하지 않고
            Chronometer가 1초마다 변화할 때 실행되는 이벤트를 사용함
        */
        chronometerStudy.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                // 타이머가 실행 중일 때만 진행률 계산
                if (isRunning) {

                    // 현재까지 흐른 시간 계산
                    elapsedMillis = SystemClock.elapsedRealtime() - chronometerStudy.getBase();

                    // 목표 시간 대비 진행률 계산
                    int progress = (int) ((elapsedMillis * 100) / goalMillis);

                    // 진행률이 100% 이상이면 목표 달성 처리
                    if (progress >= 100) {
                        progress = 100;

                        progressStudy.setProgress(progress);
                        tvProgress.setText("진행률 : " + progress + "%");
                        tvStatus.setText("목표 학습 시간을 달성했습니다!");

                        chronometerStudy.stop();
                        isRunning = false;

                        Toast.makeText(MainActivity.this,
                                "목표 학습 시간을 달성했습니다!",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        // 진행 중이면 ProgressBar와 TextView 갱신
                        progressStudy.setProgress(progress);
                        tvProgress.setText("진행률 : " + progress + "%");
                        tvStatus.setText("학습 중입니다.");
                    }
                }
            }
        });

        /*
            SeekBar 값 변경 이벤트

            사용자가 SeekBar를 움직이면 목표 학습 시간이 변경됨
        */
        seekBarGoal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // SeekBar 값이 변경될 때 실행
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // progress 값 0~5를 10분~60분으로 변환
                goalMinute = (progress + 1) * 10;

                // 목표 시간 설정
                setGoalTime(goalMinute);

                /*
                    타이머가 실행 중이 아닐 때만 화면 초기화

                    실행 중일 때 목표 시간을 바꾸면
                    다음 Tick에서 변경된 목표 시간 기준으로 진행률이 계산됨
                */
                if (!isRunning) {
                    elapsedMillis = 0;

                    chronometerStudy.setBase(SystemClock.elapsedRealtime());

                    progressStudy.setProgress(0);
                    tvProgress.setText("진행률 : 0%");
                    tvStatus.setText("목표 시간을 선택한 후 시작 버튼을 누르세요.");
                }
            }

            // SeekBar를 터치하기 시작할 때 실행
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 이 실습에서는 사용하지 않음
            }

            // SeekBar에서 손을 뗐을 때 실행
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 이 실습에서는 사용하지 않음
            }
        });

        /*
            시작 버튼 클릭 이벤트
        */
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });

        /*
            정지 버튼 클릭 이벤트
        */
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });

        /*
            초기화 버튼 클릭 이벤트
        */
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
    }

    /*
        목표 시간을 설정하는 메소드
    */
    private void setGoalTime(int minute) {

        if (TEST_MODE) {

            // 실습용 : 1분을 5초로 계산
            goalMillis = minute * 5 * 1000L;

            tvGoalTime.setText("목표 학습 시간 : " + minute + "분"
                    + " (실습용 " + (minute * 5) + "초)");

        } else {

            // 실제 시간 기준 : 1분을 60초로 계산
            goalMillis = minute * 60 * 1000L;

            tvGoalTime.setText("목표 학습 시간 : " + minute + "분");
        }
    }

    /*
        타이머 시작 메소드
    */
    private void startTimer() {

        // 이미 실행 중이면 다시 시작하지 않음
        if (!isRunning) {

            /*
                Chronometer 기준 시간 설정

                elapsedMillis를 빼는 이유:
                정지 후 다시 시작할 때 이전에 흐른 시간부터 이어서 시작하기 위함
            */
            chronometerStudy.setBase(SystemClock.elapsedRealtime() - elapsedMillis);

            // Chronometer 시작
            chronometerStudy.start();

            // 실행 상태 변경
            isRunning = true;

            // 상태 메시지 출력
            tvStatus.setText("학습 중입니다.");
        }
    }

    /*
        타이머 정지 메소드
    */
    private void stopTimer() {

        // 실행 중일 때만 정지
        if (isRunning) {

            // 현재까지 흐른 시간 저장
            elapsedMillis = SystemClock.elapsedRealtime() - chronometerStudy.getBase();

            // Chronometer 정지
            chronometerStudy.stop();

            // 실행 상태 변경
            isRunning = false;

            // 상태 메시지 출력
            tvStatus.setText("학습이 일시정지되었습니다.");
        }
    }

    /*
        타이머 초기화 메소드
    */
    private void resetTimer() {

        // Chronometer 정지
        chronometerStudy.stop();

        // Chronometer 기준 시간을 현재 시간으로 초기화
        chronometerStudy.setBase(SystemClock.elapsedRealtime());

        // 경과 시간 초기화
        elapsedMillis = 0;

        // 실행 상태 초기화
        isRunning = false;

        // ProgressBar 초기화
        progressStudy.setProgress(0);

        // TextView 초기화
        tvProgress.setText("진행률 : 0%");
        tvStatus.setText("초기화되었습니다. 시작 버튼을 누르세요.");
    }
}