package com.luckysweetheart.email.sender.message.text;

import com.luckysweetheart.email.sender.message.EmailMessage;
import com.luckysweetheart.email.sender.parser.executor.MarkDownParser;

/**
 * markdown 邮件消息
 * Created by yangxin on 2017/12/26.
 */
public class MarkDownMessage extends EmailMessage {

    /**
     * markdown 文件内容
     */
    private String content;

    private MarkDownMessage(){

    }

    public static MarkDownMessage create(){
        return new MarkDownMessage();
    }

    public MarkDownMessage content(String content){
        this.content = content;
        return this;
    }

    @Override
    public String getContent() {
        return new MarkDownParser().parse(this.content);
    }

}
