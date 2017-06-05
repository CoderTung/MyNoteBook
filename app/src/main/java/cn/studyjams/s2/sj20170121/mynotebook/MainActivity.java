package cn.studyjams.s2.sj20170121.mynotebook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

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

public class MainActivity extends BaseActivity {

    private ListView lv;
    private MyListViewAdapter mAdapter;
    private Note mNote;
    private List<Note> mNotes;

    @Override
    protected void initActivity() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("我的笔记本");

        lv = (ListView) findViewById(R.id.main_list);

        if (DataSupport.findAll(Note.class).isEmpty()){
            mNote = new Note();
            mNote.setContent("没有笔记");
            mNote.setDate("ooops");
            mNotes = new ArrayList<>();
            mNotes.add(mNote);
            mAdapter = new MyListViewAdapter(mContext , mNotes);
        } else {
            mNotes = new ArrayList<>();
            mNotes = DataSupport.findAll(Note.class);
            mAdapter = new MyListViewAdapter(mContext , mNotes);
        }

        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext , EditActivity.class);
                intent.putExtra("content" , mNotes.get(position).getContent());
                intent.putExtra("type" , "change");
                startActivityForResult(intent , 1);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataSupport.deleteAll(Note.class , "content = ?" , mNotes.get(position).getContent());
                mNotes.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext , EditActivity.class);
                intent.putExtra("type" , "add");
                startActivityForResult(intent , 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case 1:
                Toast.makeText(mContext, "修改", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(mContext, "新增", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
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
            Toast.makeText(this, "跳转到设置界面", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
