package com.luckysweetheart.email.parser.executor;

import com.luckysweetheart.email.parser.parsers.TextParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.io.File;
import java.util.List;

/**
 * MarkDown 文本解析
 * Created by yangxin on 2017/12/26.
 */
public class MarkDownParser implements TextParser {

    private PegDownProcessor processor = new PegDownProcessor(Extensions.ALL);

    @Override
    public String parse(String path) {
        try {
            String html = null;
            List<String> strings = FileUtils.readLines(new File(path), "UTF-8");
            StringBuilder sb = new StringBuilder();
            for (String string : strings) {
                sb.append(string).append("\n");
            }

            if (StringUtils.isNotBlank(sb.toString())) {
                html = processor.markdownToHtml(sb.toString());
                return html;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {

    }
}
