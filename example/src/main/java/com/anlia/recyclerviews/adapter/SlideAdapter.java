package com.anlia.recyclerviews.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anlia.recyclerviews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljm
 */
public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{

        private List<String> mList = new ArrayList<>();
        private Context mContext;

        public SlideAdapter(Context context) {
            for (int i=1;i<=20;i++){
                mList.add("消息"+i);
            }
            mContext = context;
        }

        @NonNull
        @Override
        public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.recycle_item_slide_menu, viewGroup, false);
            return new SlideViewHolder(view);
        }


        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
        @Override
        public void onBindViewHolder(@NonNull final SlideViewHolder slideViewHolder, int i) {
            slideViewHolder.mMsgTv.setText(mList.get(i));
            if (!slideViewHolder.mDeleteTv.hasOnClickListeners()) {
                slideViewHolder.mDeleteTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mList.remove(slideViewHolder.getAdapterPosition());
                        notifyItemRemoved(slideViewHolder.getAdapterPosition());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class SlideViewHolder extends RecyclerView.ViewHolder {
            private TextView mDeleteTv;
            private TextView mMsgTv;
            private TextView mCollectTv;
            private SlideViewHolder(@NonNull View itemView) {
                super(itemView);
                mDeleteTv = (TextView) itemView.findViewById(R.id.tv_delete);
                mMsgTv =  (TextView) itemView.findViewById(R.id.tv_msg);
                mCollectTv = (TextView)  itemView.findViewById(R.id.tv_collect);
                mMsgTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                    }
                });
                mCollectTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }