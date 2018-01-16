package com.luckysweetheart.email.sender.parser.parsers;

import com.luckysweetheart.email.sender.parser.Parser;
import com.luckysweetheart.email.sender.template.Template;
import com.luckysweetheart.email.sender.template.Template;

/**
 * 模板解析器
 * Created by yangxin on 2017/12/26.
 */
public interface TemplateParser extends Parser {

    /**
     * 解析
     *
     * @param template
     * @return
     */
    String parse(Template template);

}
