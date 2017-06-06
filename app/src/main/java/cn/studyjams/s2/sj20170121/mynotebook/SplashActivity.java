package cn.studyjams.s2.sj20170121.mynotebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by iCursoft_Tung on 2017/6/5.
 * 闪屏界面
 */

public class SplashActivity extends Activity {

    private TextView tvDate , tvWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        tvDate = (TextView) findViewById(R.id.splash_date);
        tvWeek = (TextView) findViewById(R.id.splash_week);

        setDate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
                overridePendingTransition(R.anim.activity_down_in,
                        R.anim.activity_down_out);
            }
        },2000);
    }

    public void setDate() {
        Calendar mCalendar = Calendar.getInstance();
        String month = null;
        String day = null;
        if ((mCalendar.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + (mCalendar.get(Calendar.MONTH) + 1);
        } else {
            month = "" + (mCalendar.get(Calendar.MONTH) + 1);
        }
        if (mCalendar.get(Calendar.DATE) < 10) {
            day = "0" + mCalendar.get(Calendar.DATE);
        } else {
            day = "" + mCalendar.get(Calendar.DATE);
        }
        tvDate.setText(mCalendar.get(Calendar.YEAR) + "年" + month + "月" + day + "日");
        String[] weeks = {"星期六", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        tvWeek.setText(weeks[(mCalendar.get(Calendar.DAY_OF_WEEK))]);
    }
}