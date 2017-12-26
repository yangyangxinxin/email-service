package com.luckysweetheart.email.parser.executor;

import com.luckysweetheart.email.template.Template;
import com.luckysweetheart.email.parser.parsers.TemplateParser;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * velocity模板解析器
 * Created by yangxin on 2017/12/26.
 */
public class VelocityTemplateParser implements TemplateParser {

    private VelocityEngine velocityEngine = new VelocityEngine();

    public VelocityTemplateParser(){
        Properties p = new Properties();
        p.put("resource.loader", "classpath");
        p.put("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init(p);
    }

    @Override
    public String parse(Template template) {
        VelocityContext context = new VelocityContext();
        Map<String, Object> parameters = template.getParameters();
        if (parameters != null) {
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
        }

        org.apache.velocity.Template t = velocityEngine.getTemplate(template.getPath(), "UTF-8");
        //输出流
        StringWriter writer = new StringWriter();

        //转换输出
        t.merge(context, writer);

        return writer.toString();
    }
}
