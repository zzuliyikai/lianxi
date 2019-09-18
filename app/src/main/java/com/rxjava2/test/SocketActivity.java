package com.rxjava2.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketActivity extends AppCompatActivity {

    private EditText etSendMessage;
    private TextView tvServerToClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        etSendMessage = findViewById(R.id.et_send_message);
        tvServerToClient = findViewById(R.id.tv_server_to_client);

    }

    public void click(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("yikai","send message start");
                    Socket socket = new Socket("192.168.0.112", 12306);

                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter pw =new PrintWriter(outputStream);//将输出流包装成打印流
                    pw.write("用户名：admin；密码：123");
                    pw.flush();
                    socket.shutdownOutput();
                    Log.d("yikai","send message end");
                    InputStream inputStream = socket.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String str = null;
                    final StringBuffer stringBuffer = new StringBuffer();
                    while ((str = bufferedReader.readLine()) != null) {
                        stringBuffer.append(str);
                    }
                    Log.d("yikai",stringBuffer.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvServerToClient.setText(stringBuffer.toString());
                        }
                    });
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    socket.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
