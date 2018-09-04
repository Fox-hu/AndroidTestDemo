package com.umeng.soexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by fox.hu on 2018/9/3.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
            }
        });

        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShareActivity.class));
            }
        });

        final File file = new File(getExternalFilesDir(null).getPath() + "/" + "eeee.mp4");
        if(!file.exists()){
            //复制文件
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        InputStream inputStream = getAssets().open("eeee.mp4");
                        OutputStream outputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1444];
                        int readSize = 0;
                        while ((readSize = inputStream.read(buffer)) != 0){
                            outputStream.write(buffer,0,readSize);
                        }
                        inputStream.close();
                        outputStream.close();
                    }catch (Exception e){}

                }
            });
            thread.start();
        }
    }
}
