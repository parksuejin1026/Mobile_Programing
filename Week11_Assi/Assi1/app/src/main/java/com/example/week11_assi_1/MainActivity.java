package com.example.week11_assi_1;

import android.os.Bundle;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SlidingDrawer slidingDrawer = findViewById(R.id.slidingDrawer);

        slidingDrawer.setOnDrawerOpenListener(() ->
                Toast.makeText(getApplicationContext(), "서랍이 열렸습니다", Toast.LENGTH_SHORT).show()
        );

        slidingDrawer.setOnDrawerCloseListener(() ->
                Toast.makeText(getApplicationContext(), "서랍이 닫혔습니다", Toast.LENGTH_SHORT).show()
        );
    }
}