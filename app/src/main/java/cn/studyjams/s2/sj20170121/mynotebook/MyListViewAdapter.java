package cn.studyjams.s2.sj20170121.mynotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by iCursoft_Tung on 2017/6/5.
 */

public class MyListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Note> mNotes;

    public MyListViewAdapter(Context context, List<Note> notes) {
        this.mContext = context;
        this.mNotes = notes;
    }

    @Override
    public int getCount() {
        return mNotes.size();
    }

    @Override
    public Object getItem(int position) {
        return mNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_main , null);
            holder.content = (TextView) convertView.findViewById(R.id.lv_content);
            holder.date = (TextView) convertView.findViewById(R.id.lv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.content.setText(mNotes.get(position).getContent());
        holder.date.setText(mNotes.get(position).getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView content , date;
    }
}
