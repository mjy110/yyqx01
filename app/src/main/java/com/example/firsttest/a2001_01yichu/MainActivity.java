package com.example.firsttest.a2001_01yichu;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    HandlerThread handlerThread=new HandlerThread("子线程");
   /* static Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            WeakReference<MainActivity> weakReference =new WeakReference<MainActivity>((MainActivity) msg.obj);
                weakReference.get().text.setText("Asfd");
        }
    };*/
    Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           Log.e("tag", (String) msg.obj);
       }
   };
    private TextView text;
    private MainActivity.myrunable myrunable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        handlerThread.start();
        myrunable = new myrunable();
        new Thread(myrunable).start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
      new Thread(myrunable).start();
    }
     class myrunable implements Runnable{

         @Override
         public void run() {
             Message message = Message.obtain();
             message.obj = "asd";
             handler.sendMessage(message);
         }
     }
}
