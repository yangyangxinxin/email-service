package com.luckysweetheart.email.template.velocity;

import com.luckysweetheart.email.parser.executor.VelocityTemplateParser;
import com.luckysweetheart.email.template.Template;
import com.luckysweetheart.email.parser.parsers.TemplateParser;

import java.util.Map;

/**
 * velocity模板
 * Created by yangxin on 2017/12/26.
 */
public class VelocityTemplate implements Template{

    private String path;

    private Map<String,Object> parameters;

    public VelocityTemplate() {
    }

    public VelocityTemplate(String path, Map<String, Object> parameters) {
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
        return new VelocityTemplateParser();
    }
}
