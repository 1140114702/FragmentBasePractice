package com.example.zhou.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhou on 2017/2/10.
 */

public class NewsTitleFtagment extends Fragment {

    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new NewsAdapter(setNews()));
        return view;
    }

    private List<NewsBean> setNews(){
        List<NewsBean> list=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            NewsBean bean=new NewsBean();
            bean.setTitle("标题"+(i+1));
            bean.setContent(getRandomLengthContent("这是内容！"+(i+1)));
            list.add(bean);
        }
        return list;
    }

    //随机生成的长度
    private String getRandomLengthContent(String content){
        Random random=new Random();
        int lenght=random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断单双页
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<NewsBean> list;

        public NewsAdapter(List<NewsBean> list) {
            this.list = list;
        }

        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_title_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewsBean bean = list.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        //双页模式
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(bean.getTitle(), bean.getContent());
                    } else {
                        //单页模式
                        NewsContentActivity.actionStart(getActivity(), bean.getTitle(), bean.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
            NewsBean bean = list.get(position);
            holder.newsTitle.setText(bean.getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView newsTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                newsTitle = (TextView) itemView.findViewById(R.id.news_title_item);
            }
        }
    }
}
