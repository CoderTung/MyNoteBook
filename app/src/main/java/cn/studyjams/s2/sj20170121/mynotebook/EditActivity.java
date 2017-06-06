package cn.studyjams.s2.sj20170121.mynotebook;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends BaseActivity {

    private Note mNote;
    private EditText mEditText;
    private Button mButton;
    private Calendar mCalendar;
    private String type;
    private String olddate , content;

    @Override
    protected void initActivity() {
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        type = intent.getType();
        olddate = intent.getStringExtra("date");
        content = intent.getStringExtra("content");

        mEditText = (EditText) findViewById(R.id.edit_input);
        mButton = (Button) findViewById(R.id.edit_save);

        if (type.equalsIgnoreCase("change")){
            mEditText.setText(content);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type){
                    case "change":
                        /*ContentValues values = new ContentValues();
                        values.put("content" , mEditText.getText().toString().trim());
                        values.put("date" , millis2String(mCalendar.getTimeInMillis(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)));
                        DataSupport.updateAll(Note.class , values , "content = ?" , content);*/
                        mNote = new Note();
                        mCalendar = Calendar.getInstance();
                        mNote.setContent(mEditText.getText().toString().trim());
                        mNote.setDate(millis2String(mCalendar.getTimeInMillis(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)));
                        if (mNote.saveOrUpdate("content = ?", content)) {
                            Log.e("onActivityResult  1  ", "更新成功");
                        }
                        /*Intent mIntent = new Intent(mContext , MainActivity.class);
                        mIntent.putExtra("content" , mEditText.getText().toString().trim());
                        mIntent.putExtra("olddate" , olddate);
                        mIntent.putExtra("date" , millis2String(mCalendar.getTimeInMillis(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)));
                        setResult(RESULT_OK , mIntent);*/
                        overridePendingTransition(R.anim.move_in_left, R.anim.move_out_right);
                        finish();
                        break;
                    case "add":
                        mNote = new Note();
                        mCalendar = Calendar.getInstance();
                        mNote.setContent(mEditText.getText().toString().trim());
                        mNote.setDate(millis2String(mCalendar.getTimeInMillis(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)));
                        if (mNote.save()){
                            Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
                        }

                        /*Intent sIntent = new Intent(mContext , MainActivity.class);
                        sIntent.putExtra("content" , mEditText.getText().toString().trim());
                        sIntent.putExtra("date" , millis2String(mCalendar.getTimeInMillis(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)));
                        setResult(RESULT_OK , sIntent);*/
                        overridePendingTransition(R.anim.move_in_left, R.anim.move_out_right);
                        finish();
                        break;
                }

            }
        });
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为format</p>
     *
     * @param millis 毫秒时间戳
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String millis2String(long millis, DateFormat format) {
        return format.format(new Date(millis));
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.move_in_left, R.anim.move_out_right);
        super.onBackPressed();
    }
}
