package com.luckysweetheart.email.sender.template.velocity;

import com.luckysweetheart.email.sender.parser.executor.VelocityTemplateParser;
import com.luckysweetheart.email.sender.template.BaseTemplate;
import com.luckysweetheart.email.sender.template.Template;
import com.luckysweetheart.email.sender.parser.parsers.TemplateParser;
import com.luckysweetheart.email.sender.util.MapUtil;
import com.luckysweetheart.email.sender.util.SpringUtil;

import java.util.Map;

/**
 * velocity模板
 * Created by yangxin on 2017/12/26.
 */
public class VelocityTemplate extends BaseTemplate {

    public VelocityTemplate(String path, Object... params) {
        this(path, MapUtil.strObj(params));
    }

    public VelocityTemplate(String path, Map<String, Object> parameters) {
        this.path = path;
        this.parameters = parameters;
    }

    @Override
    public TemplateParser getParser() {
        return SpringUtil.getBean(VelocityTemplateParser.class);
    }
}
