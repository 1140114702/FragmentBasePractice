package com.example.zhou.fragmentbestpractice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhou on 2017/2/10.
 */

public class TestFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("这是另一个Fragment");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setBackgroundColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getFragmentManager().popBackStack();
            }
        });
        return textView;
    }
}
