package cn.studyjams.s2.sj20170121.mynotebook;

import org.litepal.crud.DataSupport;

/**
 * Created by iCursoft_Tung on 2017/6/5.
 */

public class Note extends DataSupport {

    private String content;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
