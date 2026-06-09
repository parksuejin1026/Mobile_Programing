package com.cookandroid.class4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 인텐트를 전역(멤버) 변수로 두어 onCreate 실행 시점과 버튼 클릭 시점에 동일하게 참조하도록 유지합니다.
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("서비스 테스트 예제");

        // 기존 인텐트 생성 코드 유지
        intent = new Intent(this, MusicService.class);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnPause = (Button) findViewById(R.id.btnPause);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
                android.util.Log.i("서비스 테스트", "startService()");
                finish(); // 요청하신 대로 finish()를 유지하여 액티비티를 닫습니다.
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish() 후 앱을 새로 켜서 이 버튼을 누르더라도,
                // 위에서 선언된 명시적 intent 덕분에 OS가 실행 중인 원래 서비스를 정확히 찾아가서 멈춥니다.
                stopService(intent);
                android.util.Log.i("서비스 테스트", "stopService()");
            }
        });
    }
}