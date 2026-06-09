package com.cookandroid.class4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {

    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("서비스 테스트", "onDestroy()");

        // [필수 추가] 서비스가 종료될 때 미디어 플레이어를 정지하고 메모리를 해제합니다.
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null;
        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("서비스 테스트", "onStartCommand()");

        // [보안 추가] 이미 재생 중이라면 중복 재생 방지
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.song1);
            mp.setLooping(true);
            mp.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }
}