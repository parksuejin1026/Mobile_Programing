package com.cookandroid.week13_assi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 선호도 투표");

        // 투표 결과 배열
        final int voteCount[] = new int[9];

        for (int i = 0 ; i < 9 ; i++)
            voteCount[i] = 0; // 전부 0표로 SET

        ImageView image[] = new ImageView[9];
        // 이미지뷰 아이디값 저장
        Integer imageId[] = {   R.id.iv1, R.id.iv2, R.id.iv3,
                                R.id.iv4, R.id.iv5, R.id.iv6,
                                R.id.iv7, R.id.iv8, R.id.iv9 };
        // 이미지 이름 저장
        final String imgName[] = {  "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                                    "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매",
                                    "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        for (int i = 0; i < imageId.length; i++) { // ID 개수만큼 반복
            final int index; // 인덱스라는 상수 변수 생성
            index = i; // 인덱스는 i의 값을 갖게 설정
            image[index] = (ImageView) findViewById(imageId[index]); // 이미지뷰의 아이디 삽입
            image[index].setOnClickListener(new View.OnClickListener() { // 이미지를 클릭하면
                @Override
                public void onClick(View view) {
                    voteCount[index]++; // 그 인덱스의 투표횟수가 1회 상승
                    Toast.makeText(getApplicationContext(), imgName[index] + ": 총 " + voteCount[index] + "표", Toast.LENGTH_SHORT).show();
                    // 토스트 메시지로 그 이미지의 이름의 표 개수
                }
            });
        }

        Button btnFinish = (Button) findViewById(R.id.btnResult);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);
            }
        });

    }
}