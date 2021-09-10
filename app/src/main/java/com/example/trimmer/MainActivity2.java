package com.example.trimmer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView textView,sOne,sTwo,sThree,sFour,cancel;
    Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.sound);
        Toast.makeText(MainActivity2.this,"tap on sound to change",Toast.LENGTH_LONG).show();
        ok = findViewById(R.id.ok);
        Intent i2 = new Intent(MainActivity2.this,MainActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref",MODE_PRIVATE);
        String name = sharedPreferences.getString("key","Sound_default");
        textView.setText(name);



        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.choose_sound);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sOne = dialog.findViewById(R.id.sone);
        sTwo = dialog.findViewById(R.id.stwo);
        sThree = dialog.findViewById(R.id.sthree);
        sFour = dialog.findViewById(R.id.sFour);
        cancel = dialog.findViewById(R.id.cancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        sOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = sOne.getText().toString();
               textView.setText( str);
                dialog.dismiss();
            }
        });
        sTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = sTwo.getText().toString();
                textView.setText( str);
                int tag = Integer.parseInt(sTwo.getTag().toString());
                i2.putExtra("one",tag);
                dialog.dismiss();



            }
        });
        sThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = sThree.getText().toString();
                int tag = Integer.parseInt(sThree.getTag().toString());

                i2.putExtra("one",tag);
                textView.setText( str);
                dialog.dismiss();



            }
        });
        sFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = sFour.getText().toString();
                int tag = Integer.parseInt(sFour.getTag().toString());

                i2.putExtra("one",tag);
                textView.setText(str);
                dialog.dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("key",textView.getText().toString());
                editor.commit();
                startActivity(i2);
                Toast.makeText(MainActivity2.this, "set Successfully", Toast.LENGTH_SHORT).show();
            }
        });




    }
}