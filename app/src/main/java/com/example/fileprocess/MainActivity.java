package com.example.fileprocess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnRead;
    TextView tvContent;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave=findViewById(R.id.btnSave);
        btnRead=findViewById(R.id.btnRead);
        tvContent=findViewById(R.id.tvContent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MODE_PRIVATE>> memo라는 이름으로 파일을 저장.
                // MODE_APPEND>> memo라는 파일을 가져옴.
                //파일 처리할 경우 의무적으로 try-catch문을 사용.
                try {
                    FileOutputStream fileOutputStream=openFileOutput("memo.txt", MODE_PRIVATE);
                    str="안녕하세요. 어서오세요~~~";
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.close();
                    //파일을 열고 반드시 닫아줘야함.
                    fileOutputStream.close();
                    showToast("memo.txt 파일이 저장되었습니다.");
                } catch (IOException e) {
                    showToast("파일을 저장할 수 없습니다.");
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream=openFileInput("memo.txt");
                    byte txt[]=new byte[fileInputStream.available()];
                    fileInputStream .read(txt);
                    String str=new String(txt);
                    tvContent.setText(str);

                } catch (IOException e) {
                    showToast("파일을 읽을 수 없습니다.");
                }
            }
        });
    }
    void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}