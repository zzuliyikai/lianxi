package com.socket;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rxjava2.test.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);

    }

    public void startClient(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Log.d("yikai","getClientSocket");
                    getClientSocket();
                }
            }
        }).start();
    }

    private void getClientSocket(){
        InputStream inputStream = null ;
        BufferedReader in = null;
        Socket socket = null;
        try {
            if (socket == null){
                socket = new Socket(ConnectionUtis.getServerAddress(this),10086);
            }
            while (true){
                Thread.sleep(2000);

                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                String readData = in.readLine();

                if (!TextUtils.isEmpty(readData)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SocketClientActivity.this, "收到消息", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("yikai","Exception = "+e.getMessage());

        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
