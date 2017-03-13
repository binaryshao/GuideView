package com.sbingo.guideview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

import com.sbingo.guide.GuideView;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;
    private TextView text8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.text_1);
        text2 = (TextView) findViewById(R.id.text_2);
        text3 = (TextView) findViewById(R.id.text_3);
        text4 = (TextView) findViewById(R.id.text_4);
        text5 = (TextView) findViewById(R.id.text_5);
        text6 = (TextView) findViewById(R.id.text_6);
        text7 = (TextView) findViewById(R.id.text_7);
        text8 = (TextView) findViewById(R.id.text_8);
        showGuideView();
    }

    private void showGuideView() {
        TextView tv = new TextView(this);
        tv.setText("自定义提示view");
        tv.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);

        //测试时用这个方法
        final GuideView.Builder builder = new GuideView.Builder(this, true);
        //正式环境用这个方法，每个GuideView只会显示一次
//        final GuideView.Builder builder = new GuideView.Builder(this);
        builder.addHintView(text1, "测试1", GuideView.Direction.TOP, GuideView.MyShape.ELLIPSE, -50, -200)
                .setTextSize(20)
                .addHintView(text2, "测试2", GuideView.Direction.RIGHT_BOTTOM, GuideView.MyShape.RECTANGULAR)
                .addHintView(text3, "测试3", GuideView.Direction.LEFT_BOTTOM, GuideView.MyShape.CIRCULAR)
                .addHintView(text4, "测试4", GuideView.Direction.RIGHT_TOP, GuideView.MyShape.ELLIPSE)
                .addHintView(text5, "测试5", GuideView.Direction.LEFT_TOP, GuideView.MyShape.CIRCULAR)
                .addHintView(text6, "测试6", GuideView.Direction.RIGHT, GuideView.MyShape.CIRCULAR)
                .addHintView(text7, "测试7", GuideView.Direction.LEFT, GuideView.MyShape.CIRCULAR)
                .addHintView(text8, tv, GuideView.Direction.BOTTOM, GuideView.MyShape.RECTANGULAR, -100, 100, new GuideView.OnClickCallback() {
                    @Override
                    public void onGuideViewClicked() {
                        builder.showNext();
                    }
                });
        builder.show();
    }
}
