package com.luckysweetheart.email.message.text;

import com.luckysweetheart.email.message.EmailMessage;
import com.luckysweetheart.email.parser.executor.MarkDownParser;

/**
 * markdown 邮件消息
 * Created by yangxin on 2017/12/26.
 */
public class MarkDownMessage extends EmailMessage {

    /**
     * markdown 文件路径
     */
    private String path;

    private MarkDownMessage(){

    }

    public static MarkDownMessage create(){
        return new MarkDownMessage();
    }

    public MarkDownMessage path(String path){
        this.path = path;
        return this;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String getContent() {
        return new MarkDownParser().parse(this.path);
    }

}
