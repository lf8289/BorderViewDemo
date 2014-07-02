package com.example.borderviewdemo;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.borderviewdemo.View.FocusBorderView;
import com.example.borderviewdemo.View.VerticalSmoothGridView;
import com.example.tvborderviewdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

/*    import com.bestv.setting.utils.BoxNotFoundException;  
 import com.bestv.setting.views.FocusBorderView;  */

public class MainActivity extends Activity implements OnFocusChangeListener {

    private static final String TAG_base = "BaseActivity";
    FocusBorderView mBorderView;
    Handler mHandler = new Handler();

    Button[] btns = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btns[0] = (Button) findViewById(R.id.button1);
        btns[1] = (Button) findViewById(R.id.button2);
        btns[2] = (Button) findViewById(R.id.button3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FocusBorderView view = (FocusBorderView) findViewById(R.id.box);
        if (view == null) {
            // throw new BoxNotFoundException();// 必须在父布局中焦点框控件，否则抛出异常
        }
        mBorderView = view;

        for (Button btn : btns) {
            btn.setOnFocusChangeListener(this);
        }
    }

    public FocusBorderView getBorderView() {
        return mBorderView;
    }

    public void setBorderView(FocusBorderView box) {
        this.mBorderView = box;
    }

    public void setFocusedView(final View view, int delay) {
        if (mBorderView == null) {
            mBorderView = (FocusBorderView) findViewById(R.id.box);
        }
////        mBorderView.runBorderAnimation();
//        mHandler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                if (view == null) {
//                    return;
//                }
//                view.requestFocus();
//                mBorderView.setLocation(view);
//            }
//        }, delay);
    }

    public void runClickAnim() {
//        this.getBorderView().notifyClickBoxAnim();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mBorderView.runTranslateAnimation(v);
        }
    }

    public void setClickListener(View v, OnClickListener listener) {
        v.setOnClickListener(listener);
        v.setOnFocusChangeListener(this);
    }

}

//public class MainActivity extends Activity {
//    private String[] item = { "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒",
//            "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚",
//            "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧",
//            "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ",
//            "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒",
//            "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚",
//            "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧", "孙悟空 ", "猪八戒", "沙和尚", "唐僧",
//            "孙悟空 ", "猪八戒", "沙和尚" };
//    private SimpleAdapter adapterSimple;
//    private VerticalSmoothGridView gridView;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        gridView = (VerticalSmoothGridView) findViewById(R.id.gridView1);
//        // 创建一个ArrayList列表,内部存的是HashMap列表
//        ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
//        // 将数组信息分别存入ArrayList中
//        int len = item.length;
//        for (int i = 0; i < len; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("image", R.drawable.ic_launcher);
//            map.put("item", item[i]);
//            listItems.add(map);
//        }
//
//        // HashMap中的Key信息,要与grid_item.xml中的信息作对应
//        String[] from = { "image", "item" };
//        // grid_item.xml中对应的ImageView控件和TextView控件
//        int[] to = { R.id.item_imageView, R.id.item_textView };
//        // 设定一个适配器
//        adapterSimple = new SimpleAdapter(this, listItems, R.layout.grid_item,
//                from, to);
//
//        // 对GridView进行适配
//        gridView.setAdapter(adapterSimple);
//    }
//}
