package com.luckysweetheart.email.sender.demo;

import com.luckysweetheart.email.sender.template.TemplateManager;
import com.luckysweetheart.email.sender.template.TemplateManager;

/**
 * Created by yangxin on 2017/12/29.
 */
public enum DemoManger implements TemplateManager {

    TEST("test.ftl", "TEST"),
    VELOCITY("/META-INF/template/test.vm", "AAA");

    private String path;

    private String name;

    DemoManger(String path, String name) {
        this.path = path;
        this.name = name;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getName() {
        return name;
    }
}
