package com.luckysweetheart.email.template.velocity;

import com.luckysweetheart.email.parser.executor.VelocityTemplateParser;
import com.luckysweetheart.email.template.BaseTemplate;
import com.luckysweetheart.email.template.Template;
import com.luckysweetheart.email.parser.parsers.TemplateParser;
import com.luckysweetheart.email.util.MapUtil;
import com.luckysweetheart.email.util.SpringUtil;

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
