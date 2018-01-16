package com.luckysweetheart.email.sender.parser.executor;

import com.luckysweetheart.email.sender.exception.ParseException;
import com.luckysweetheart.email.sender.parser.parsers.TemplateParser;
import com.luckysweetheart.email.sender.parser.parsers.TemplateParser;
import com.luckysweetheart.email.sender.template.Template;
import com.luckysweetheart.email.sender.template.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Map;

/**
 * velocity模板解析器
 * Created by yangxin on 2017/12/26.
 */
public class VelocityTemplateParser implements TemplateParser {

    private VelocityEngine velocityEngine;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String parse(Template template) {
        try {
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
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ParseException(e.getMessage());
        }
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
