package cn.studyjams.s2.sj20170121.mynotebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * 一个记事本APP
 * 参加Google Study Jams Android学习毕业作品
 *
 * 要求：由本人主导、完全使用 Android Studio 开发
 *      包含可交互的功能
 *      使用两种及以上的布局，使用三种不同的 View
 *      使用 Material Design 配色
 *      使用一个及以上的 Firebase 功能
 *      成功上载到 Google Play
 *      包名统一使用 cn.studyjams.s2.sj小组编号.个人姓名或软件名字
 */

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayAdapter mArrayAdapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("我的笔记本");

        lv = (ListView) findViewById(R.id.main_list);

        data = new ArrayList<>();

        data.add("123");
        data.add("456");
        data.add("789");

        String[] data1 = {"123" , "1231231"};

        mArrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , data);

        lv.setAdapter(mArrayAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "添加日记", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                data.add("112233");
                                mArrayAdapter.notifyDataSetChanged();
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "跳转到设置界面", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
