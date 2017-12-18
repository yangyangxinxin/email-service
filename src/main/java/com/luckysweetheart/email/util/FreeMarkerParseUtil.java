package com.luckysweetheart.email.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yangxin on 2017/12/18.
 */
public class FreeMarkerParseUtil {

    private static final Logger logger = LoggerFactory.getLogger(FreeMarkerParseUtil.class);

    private static FreeMarkerConfigurer freeMarkerConfigurer = SpringUtil.getBean(FreeMarkerConfigurer.class);

    public static String parse(String path, Map<String, Object> params) {
        String html = null;
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(path);
            html = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
        }
        return html;
    }

}
