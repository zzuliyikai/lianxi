package com.socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rxjava2.test.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerActivity extends AppCompatActivity {

    private ServerSocket mServerSocket = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_server);



    }



    public void startServer(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getServerSocket();

            }
        }).start();
    }


    private void getServerSocket() {
        PrintWriter printWriter = null;
        Socket accept = null;
        try {
            mServerSocket = new ServerSocket(10086);
            accept = mServerSocket.accept();
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter( accept.getOutputStream(), "UTF-8")), true);
            printWriter.println("成功连接服务器"+"（服务器发送）");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (printWriter != null){
                printWriter.close();
            }
            try {
                if (accept != null){
                    accept.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            mServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
