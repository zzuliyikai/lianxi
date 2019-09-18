package com.realm;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.realm.realmobject.Person;
import com.rxjava2.test.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private MyAdapter mAdapter;
    private List<Person> mPersons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("yikai","onCreate");
        setContentView(R.layout.activity_realm);
        mRecycleView = findViewById(R.id.rv_view);

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this,mPersons);
        mRecycleView.setAdapter(mAdapter);
    }

    public void add(View view) {

        Realm realm = Realm.getDefaultInstance();
        final Person person = new Person();
        person.setAge(18);
        person.setId(1);
        person.setName("易凯");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(person);
            }
        });
    }

    public void delete(View view) {

    }

    public void update(View view) {

    }

    public void query(View view) {

        mPersons.clear();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Person> persons = realm.where(Person.class)
                .equalTo("name", "易凯")
                .findAll();
        for (Person person: persons) {
            mPersons.add(person);
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("yikai","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("yikai","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("yikai","onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("yikai","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("yikai","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("yikai","onDestroy");
    }
}
