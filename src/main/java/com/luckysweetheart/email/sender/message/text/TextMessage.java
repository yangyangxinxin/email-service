package com.luckysweetheart.email.sender.message.text;

import com.luckysweetheart.email.sender.message.EmailMessage;
import com.luckysweetheart.email.sender.parser.executor.TextOnlyParser;

/**
 * 纯文本邮件消息
 * Created by yangxin on 2017/12/26.
 */
public class TextMessage extends EmailMessage {

    /**
     * 文本内容
     */
    private String content;

    private TextMessage(){
    }

    public static TextMessage create() {
        return new TextMessage();
    }

    public TextMessage content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String getContent() {
        return new TextOnlyParser().parse(this.content);
    }


}
