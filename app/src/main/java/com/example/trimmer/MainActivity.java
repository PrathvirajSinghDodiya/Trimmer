package com.example.trimmer;

import static com.example.trimmer.R.drawable.back_color;
import static com.example.trimmer.R.drawable.back_color2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;

import android.view.View;
import android.view.WindowManager;

import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView button;
    Boolean t = true;
    static Boolean check = true;
    MediaPlayer mediaPlayer;
    LottieAnimationView lottieAnimationView,setting;
    ConstraintLayout constraintLayout;
    int pos=0;



    Vibrator vibrator ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        button= findViewById(R.id.button);
        int[] resID = {R.raw.tone,R.raw.sound1,R.raw.sound2,R.raw.button4};

        if(check ==false){

            Intent intent = getIntent();
            int str = intent.getIntExtra("one",0);
            String tag = String.valueOf(str);
            if(str==1){

                pos=0;
            }
            else if(str==2){

                pos=1;
            }
            else if(str==3){

                pos=2;
            }
            else{
                pos=3;
            }

            mediaPlayer = MediaPlayer.create(this,resID[pos]);
        }
        else{
            mediaPlayer =MediaPlayer.create(this,resID[pos]);

        }

        
        lottieAnimationView = findViewById(R.id.onoff);
        constraintLayout = findViewById(R.id.cons);
        setting = findViewById(R.id.settings);
        lottieAnimationView.playAnimation();


        lottieAnimationView.setOnClickListener(v -> {

            final VibrationEffect vibrationEffect ;
          if(t==true){
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                  lottieAnimationView.setMinAndMaxProgress(0.0f,0.2f);
                  constraintLayout.setBackground(getDrawable(back_color));
                  lottieAnimationView.playAnimation();
                  mediaPlayer.start();
                  vibrationEffect = VibrationEffect.createOneShot(15000,VibrationEffect.DEFAULT_AMPLITUDE);
                  vibrator.cancel();
                  vibrator.vibrate(vibrationEffect);
                  t=false;
              }
          }
          else{
             vibrator.cancel();

              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  constraintLayout.setBackground(getDrawable(back_color2));
              }
              lottieAnimationView.setMinAndMaxProgress(0.2f,0.7f);
              lottieAnimationView.playAnimation();
              mediaPlayer.pause();
              t=true;
          }
        });
        setting.setSpeed(2);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                vibrator.cancel();
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                check=false;
            }
        });

}


    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        try{
            vibrator.cancel();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            constraintLayout.setBackground(getDrawable(back_color2));
        }
        lottieAnimationView.setMinAndMaxProgress(0.2f,0.7f);
        lottieAnimationView.playAnimation();
        t=true;

    }
}