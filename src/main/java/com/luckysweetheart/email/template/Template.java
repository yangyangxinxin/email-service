package com.luckysweetheart.email.template;

import com.luckysweetheart.email.parser.parsers.TemplateParser;

import java.util.Map;

/**
 * 模板
 * Created by yangxin on 2017/12/26.
 */
public interface Template {

    /**
     * 模板路径
     * @return
     */
    String getPath();

    /**
     * 模板变量
     * @return
     */
    Map<String,Object> getParameters();

    /**
     * 模板解析器
     * @return
     */
    TemplateParser getParser();

}
