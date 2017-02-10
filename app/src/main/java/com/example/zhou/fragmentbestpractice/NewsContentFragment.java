package com.example.zhou.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhou on 2017/2/10.
 */

public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container,false);
        view.findViewById(R.id.news_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.replaceFragment(new TestFragment());
            }
        });
        return view;
    }

    //刷新新闻标题、内容
    public void refresh(String newsTitle, String newsContent){
        view.findViewById(R.id.visibility_layout).setVisibility(View.VISIBLE);
        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title);
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }
}
