package com.luckysweetheart.email.parser.executor;

import com.luckysweetheart.email.exception.ParseException;
import com.luckysweetheart.email.parser.parsers.TextParser;
import org.apache.commons.lang3.StringUtils;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MarkDown 文本解析器
 * Created by yangxin on 2017/12/26.
 */
public class MarkDownParser implements TextParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private PegDownProcessor processor = new PegDownProcessor(Extensions.ALL);

    @Override
    public String parse(String content) {
        try {
            String html;

            if (StringUtils.isNotBlank(content)) {
                html = processor.markdownToHtml(content);
                return html;
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ParseException(e.getMessage());
        }
    }
}
