package com.luckysweetheart.email.template;

import com.luckysweetheart.email.parser.parsers.TemplateParser;
import com.luckysweetheart.email.template.freemarker.FreemarkerTemplate;
import com.luckysweetheart.email.template.velocity.VelocityTemplate;

import java.util.Map;

/**
 * 所有模板父类。发送邮件自定义模板需要继承此类。
 * 参考：{@link FreemarkerTemplate,VelocityTemplate}
 * Created by yangxin on 2017/12/29.
 */
public abstract class BaseTemplate implements Template {

    /**
     * 模板路径
     */
    protected String path;

    /**
     * 模板变量
     */
    protected Map<String, Object> parameters;

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

}
