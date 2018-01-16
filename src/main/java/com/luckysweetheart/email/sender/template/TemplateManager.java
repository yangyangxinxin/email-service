package com.luckysweetheart.email.sender.template;

import java.io.Serializable;

/**
 * 模板管理器。用于记录模板的所存放的路径和名称等信息。方便统一管理。
 * Created by yangxin on 2017/12/29.
 */
public interface TemplateManager extends Serializable{

    /**
     * 模板路径
     * @return
     */
    String getPath();

    /**
     * 模板名/模板描述/用途
     * @return
     */
    String getName();

}
