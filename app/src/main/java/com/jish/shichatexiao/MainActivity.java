package com.jish.shichatexiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //A.VIew
        final ParallaxListView plv = (ParallaxListView) findViewById(R.id.plv);

        //B.ListView添加一个头布局,ListView的上拉加载,下拉刷新
        View headerView = View.inflate(this, R.layout.layout_header, null);
        plv.addHeaderView(headerView);

        final ImageView iv_header = (ImageView) headerView.findViewById(R.id.iv_header);

        //等View界面全部绘制完毕的时候,去得到已经绘制完控件的宽和高,查一下这个方法,并做一个笔记
        //ListView添加头布局,及把图片控件绘制完的对象传到自定义控件中,以便的到高度
        iv_header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //宽和高已经测量完毕
                plv.setIv_header(iv_header);
                //释放资源
                iv_header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        //B.使用ListVIew的ArrayAfapter,添加文本的Item
        plv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Cheeses.NAMES));
    }
}
