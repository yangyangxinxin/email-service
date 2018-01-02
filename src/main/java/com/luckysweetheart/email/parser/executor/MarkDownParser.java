package com.luckysweetheart.email.parser.executor;

import com.luckysweetheart.email.parser.parsers.TextParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.io.File;
import java.util.List;

/**
 * MarkDown 文本解析器
 * Created by yangxin on 2017/12/26.
 */
public class MarkDownParser implements TextParser {

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
            e.printStackTrace();
        }
        return null;
    }
}
