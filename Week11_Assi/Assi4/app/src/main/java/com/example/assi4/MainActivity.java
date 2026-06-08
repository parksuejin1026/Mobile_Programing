package com.example.assi4;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity: 간단한 웹 브라우저 기능을 수행하는 액티비티입니다.
 * URL을 입력받아 페이지를 이동하거나, 이전 페이지로 돌아가는 기능을 제공합니다.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // [기능] EdgeToEdge.enable(this): 앱의 UI를 상태바와 내비게이션 바 영역까지 확장합니다.
        // [결과] 화면 전체를 사용하는 몰입형 UI가 구성됩니다.
        EdgeToEdge.enable(this);
        
        // [기능] setContentView: activity_main.xml 레이아웃 파일을 이 액티비티의 화면으로 설정합니다.
        setContentView(R.layout.activity_main);
        
        // [기능] ViewCompat.setOnApplyWindowInsetsListener: 시스템 바(상태바, 내비게이션 바)의 크기를 계산합니다.
        // [결과] 시스템 바와 UI 요소(입력창 등)가 겹치지 않도록 적절한 여백(Padding)을 자동으로 부여합니다.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // [기능] findViewById: XML 레이아웃에 정의된 UI 요소들을 자바 코드의 변수와 연결(Binding)합니다.
        EditText edtUrl = (EditText) findViewById(R.id.edtUrl);
        Button btnMove = (Button) findViewById(R.id.btnMove);
        Button btnPrev = (Button) findViewById(R.id.btnPrev);
        WebView webView = (WebView) findViewById(R.id.webView);

        // [기능] webView.setWebViewClient: 사용자가 링크를 클릭했을 때의 동작을 정의합니다.
        // [결과] 새로운 브라우저 창(크롬 등)이 뜨지 않고, 현재 앱 안의 WebView에서 페이지가 계속 로드됩니다.
        webView.setWebViewClient(new CookWebViewClient());

        // [기능] WebSettings 설정: 웹뷰의 상세 기능을 제어합니다.
        WebSettings webSet = webView.getSettings();
        webSet.setBuiltInZoomControls(true); // [결과] 화면 확대/축소 컨트롤(줌 버튼)이 화면에 나타납니다.
        webSet.setJavaScriptEnabled(true);    // [결과] 웹 페이지 내의 자바스크립트가 정상적으로 동작합니다.

        // [기능] 초기값 설정
        // [결과] 앱 실행 시 바로 구글 홈페이지가 나타나며 주소창에 주소가 표시됩니다.
        webView.loadUrl("https://www.google.com");
        edtUrl.setText("www.google.com");

        // [기능] [이동] 버튼 클릭 리스너 설정
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtUrl.getText().toString();
                
                // [기능] URL 유효성 체크 및 보정
                // [결과] 사용자가 "naver.com"만 입력해도 "https://naver.com"으로 변환하여 정상 접속되게 합니다.
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url;
                }
                
                // [기능] 입력된 URL로 웹뷰 페이지 이동
                webView.loadUrl(url);
            }
        });

        // [기능] [이전] 버튼 클릭 리스너 설정
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // [기능] webView.canGoBack(): 뒤로 돌아갈 수 있는 히스토리가 있는지 확인합니다.
                // [결과] 히스토리가 있을 때만 webView.goBack()을 호출하여 이전 페이지로 이동합니다.
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });

    }

    /**
     * CookWebViewClient: WebViewClient를 상속받아 커스텀 동작을 정의합니다.
     * 웹뷰 내에서 일어나는 각종 이벤트(로딩 시작, 종료, 에러 등)를 제어할 수 있습니다.
     */
    class CookWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            // [기능] URL 로딩 가로채기
            // [결과] false를 반환하면 웹뷰가 직접 URL을 처리하게 되어 앱 내부 로딩이 유지됩니다.
            return false;
        }
    }
}
