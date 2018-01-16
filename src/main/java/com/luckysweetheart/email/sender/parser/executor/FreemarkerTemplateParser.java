package com.luckysweetheart.email.sender.parser.executor;

import com.luckysweetheart.email.sender.exception.ParseException;
import com.luckysweetheart.email.sender.parser.parsers.TemplateParser;
import com.luckysweetheart.email.sender.template.Template;
import com.luckysweetheart.email.sender.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * Freemarker模板解析器
 * Created by yangxin on 2017/12/26.
 */
public class FreemarkerTemplateParser implements TemplateParser {

    private static final Logger logger = LoggerFactory.getLogger(FreemarkerTemplateParser.class);

    private FreeMarkerConfigurer freeMarkerConfigurer;

    public FreemarkerTemplateParser(FreeMarkerConfigurer freeMarkerConfigurer){
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    @Override
    public String parse(Template template) {
        String html = null;
        try {
            freemarker.template.Template temp = freeMarkerConfigurer.getConfiguration().getTemplate(template.getPath());
            html = FreeMarkerTemplateUtils.processTemplateIntoString(temp, template.getParameters());
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            throw new ParseException(e.getMessage());
        }
        return html;
    }

    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }
}
