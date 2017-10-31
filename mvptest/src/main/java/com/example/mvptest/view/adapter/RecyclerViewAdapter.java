package com.example.mvptest.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvptest.R;
import com.example.mvptest.model.bean.DataBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by xxsj on 2017/10/30.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context context;
    private List<DataBean.StoriesBean> list;

    public RecyclerViewAdapter(Context context, List<DataBean.StoriesBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate =  LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(inflate);
    }

    public interface OnItemClickListener{
      //  void OnItemClick(View view,String image,String text,int price);

        void OnItemClick(View v, List<String> images, String title);


    }


    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //图片加载过程中显示的图片
                .showStubImage(R.mipmap.ic_launcher)
                //图片加载失败时显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher_round)
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        ImageLoader.getInstance().displayImage(String.valueOf(list.get(position).getImages()),holder.imageView,options);

        holder.textView.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, ""+v, Toast.LENGTH_SHORT).show();
                if (mItemClickListener!=null){
                mItemClickListener.OnItemClick(v,list.get(position).getImages(),list.get(position).getTitle());
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        private final ImageView imageView;
        private final TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
