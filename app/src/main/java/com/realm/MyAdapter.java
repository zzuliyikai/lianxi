package com.realm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realm.realmobject.Person;
import com.rxjava2.test.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Person> mPersons;

    public MyAdapter(Context context, List<Person> persons) {
        this.mContext = context;
        this.mPersons = persons;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv_name.setText(mPersons.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mPersons != null ? mPersons.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
