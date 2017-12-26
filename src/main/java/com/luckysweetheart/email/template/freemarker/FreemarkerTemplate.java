package com.luckysweetheart.email.template.freemarker;

import com.luckysweetheart.email.parser.executor.FreemarkerTemplateParser;
import com.luckysweetheart.email.template.Template;
import com.luckysweetheart.email.parser.parsers.TemplateParser;
import com.luckysweetheart.email.util.SpringUtil;

import java.util.Map;

/**
 * Created by yangxin on 2017/12/26.
 */
public class FreemarkerTemplate implements Template {

    private String path;

    private Map<String,Object> parameters;

    public FreemarkerTemplate() {
    }

    public FreemarkerTemplate(String path, Map<String, Object> parameters) {
        this.path = path;
        this.parameters = parameters;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public TemplateParser getParser() {
        return SpringUtil.getBean(FreemarkerTemplateParser.class);
    }
}
