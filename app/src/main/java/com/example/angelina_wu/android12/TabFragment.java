package com.example.angelina_wu.android12;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        Button b = (Button) view.findViewById(R.id.tabButton);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabOnclick();
            }
        });
        return view;
    }

    public void tabOnclick (){
        //Intent intent = new Intent(getActivity(), Tab.class);
        //startActivity(intent);
    }
}