package com.cookandroid.week13_assi3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;

    RadioGroup rbg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        rbg = (RadioGroup) findViewById(R.id.rbg);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rbg.getCheckedRadioButtonId() == R.id.rb1) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("제목입니다.");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setMessage("이곳이 내용입니다.");
                    dlg.show();
                }
                else if(rbg.getCheckedRadioButtonId() == R.id.rb2) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("제목입니다.");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setMessage("이곳이 내용입니다.");
                    dlg.setPositiveButton("확인", null);
                    dlg.show();
                }
                else if(rbg.getCheckedRadioButtonId() == R.id.rb3) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("제목입니다.");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setMessage("이곳이 내용입니다.");
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "확인 누름", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dlg.show();
                }
                else if(rbg.getCheckedRadioButtonId() == R.id.rb4) {

                    final String[] versionArray = new String[] {"쫄면", "떡볶이", "김밥"};
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("좋아하는 간식은?");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setItems(versionArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, versionArray[i], Toast.LENGTH_SHORT).show();
                        }
                    });
                    dlg.setPositiveButton("닫기", null);
                    dlg.show();
                }
                else if(rbg.getCheckedRadioButtonId() == R.id.rb5) {
                    final String[] versionArray = new String[] {"쫄면", "떡볶이", "김밥"};
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("좋아하는 간식은?");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setSingleChoiceItems(versionArray, 2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, versionArray[i], Toast.LENGTH_SHORT).show();
                        }
                    });
                    dlg.setPositiveButton("닫기", null);
                    dlg.show();
                }
                else if(rbg.getCheckedRadioButtonId() == R.id.rb6) {
                    final String[] versionArray = new String[] {"쫄면", "떡볶이", "김밥"};
                    final boolean[] checkArray = new boolean[] {true, false, false};
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("좋아하는 간식은?");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            Toast.makeText(MainActivity.this, versionArray[i] + "-" + b, Toast.LENGTH_SHORT).show();
                            btn1.setText(versionArray[i]);
                        }
                    });
                    dlg.setPositiveButton("닫기", null);
                    dlg.show();
                }
            }
        });
    }
}