package com.reference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.rxjava2.test.R;

import java.util.ArrayList;
import java.util.List;

public class ReferenceActivity extends AppCompatActivity {

    private List<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

    }
}
