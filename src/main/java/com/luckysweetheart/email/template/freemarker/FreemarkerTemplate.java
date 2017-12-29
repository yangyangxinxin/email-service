package com.luckysweetheart.email.template.freemarker;

import com.luckysweetheart.email.parser.executor.FreemarkerTemplateParser;
import com.luckysweetheart.email.template.BaseTemplate;
import com.luckysweetheart.email.template.Template;
import com.luckysweetheart.email.parser.parsers.TemplateParser;
import com.luckysweetheart.email.util.SpringUtil;

import java.util.Map;

/**
 * Freemarker 模板
 * Created by yangxin on 2017/12/26.
 */
public class FreemarkerTemplate extends BaseTemplate {

    public FreemarkerTemplate() {
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
