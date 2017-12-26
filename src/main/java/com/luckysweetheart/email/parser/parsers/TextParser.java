package com.luckysweetheart.email.parser.parsers;

import com.luckysweetheart.email.parser.Parser;

/**
 * 纯文本解析器，没有变量的概念。
 * Created by yangxin on 2017/12/26.
 */
public interface TextParser extends Parser {

    /**
     * 解析文本
     *
     * @param pathOrContent 待解析的文本路径或文本内容。视实现方法而定。
     * @return 解析后的文本
     */
    String parse(String pathOrContent);

}
