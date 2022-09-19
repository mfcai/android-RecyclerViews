package com.anlia.recyclerviews.group;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.anlia.library.base.SlideRecyclerView;
import com.anlia.recyclerviews.R;
import com.anlia.recyclerviews.adapter.SlideAdapter;

public class SlideMenuActivity extends AppCompatActivity {
    private RecyclerView mSlideRv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);
        mSlideRv = (SlideRecyclerView)findViewById(R.id.rv_slide);
        SlideAdapter adapter = new SlideAdapter(this);
        mSlideRv.setAdapter(adapter);
        mSlideRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mSlideRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
                outRect.bottom = 2;
            }
        });
    }
}
