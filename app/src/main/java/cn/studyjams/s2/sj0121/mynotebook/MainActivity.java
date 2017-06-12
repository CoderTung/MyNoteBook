package cn.studyjams.s2.sj0121.mynotebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
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

public class MainActivity extends BaseActivity {

    private ListView lv;
    private MyListViewAdapter mAdapter;
    private Note mNote;
    private List<Note> mNotes;
    private Calendar mCalendar;
    private TextView tv;

    @Override
    protected void initActivity() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv = (TextView) findViewById(R.id.main_tv);
        lv = (ListView) findViewById(R.id.main_list);

        if (DataSupport.findAll(Note.class).isEmpty()){
            lv.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
        } else {
            lv.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
            mNotes = new ArrayList<>();
            mNotes = DataSupport.findAll(Note.class);
            mAdapter = new MyListViewAdapter(mContext , mNotes);
        }
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext , EditActivity.class);
                intent.setType("change");
                intent.putExtra("content" , mNotes.get(position).getContent());
                intent.putExtra("date" , mNotes.get(position).getDate());
                startActivityForResult(intent , 1);
                overridePendingTransition(R.anim.move_in_right, R.anim.move_out_left);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataSupport.deleteAll(Note.class , "content = ?" , mNotes.get(position).getContent());
                mNotes.remove(position);
                mAdapter.notifyDataSetChanged();
                if (mNotes.isEmpty()){
                    lv.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                }else {
                    lv.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.GONE);
                }
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext , EditActivity.class);
                intent.setType("add");
                startActivityForResult(intent , 2);
                overridePendingTransition(R.anim.move_in_right, R.anim.move_out_left);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mNotes = new ArrayList<>();
        mNotes = DataSupport.findAll(Note.class);
        mAdapter = new MyListViewAdapter(mContext , mNotes);
        lv.setAdapter(mAdapter);
        if (mNotes.isEmpty()){
            lv.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
        }else {
            lv.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            showDialogTip();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialogTip() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("MyNoteBook")
                .setIcon(R.mipmap.logo)
                .setMessage("By Tung" + "\n"  + "如果你有任何建议或疑问，请联系我" + "\n" + "wutong0213@sina.cn")
                .setPositiveButton("已了解", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //确定操作
                    }
                })
                .show();
    }
}
