package com.luckysweetheart.email.sender.template.freemarker;

import com.luckysweetheart.email.sender.parser.executor.FreemarkerTemplateParser;
import com.luckysweetheart.email.sender.template.BaseTemplate;
import com.luckysweetheart.email.sender.template.Template;
import com.luckysweetheart.email.sender.parser.parsers.TemplateParser;
import com.luckysweetheart.email.sender.util.MapUtil;
import com.luckysweetheart.email.sender.util.SpringUtil;

import java.util.Map;

/**
 * Freemarker 模板
 * Created by yangxin on 2017/12/26.
 */
public class FreemarkerTemplate extends BaseTemplate {

    /**
     * 通过可变参的方式构造对象
     * 例如: new FreemarkerTemplate("test.ftl","a","b","c","d")
     * 解析的map为key=a,value=b
     * key=c value=d
     * @param path
     * @param param
     */
    public FreemarkerTemplate(String path,Object... param){
        this(path, MapUtil.strObj(param));
    }

    public FreemarkerTemplate(String path, Map<String, Object> parameters) {
        this.path = path;
        this.parameters = parameters;
    }

    @Override
    public TemplateParser getParser() {
        return SpringUtil.getBean(FreemarkerTemplateParser.class);
    }
}
