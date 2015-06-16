package com.baixd.app.framework.base;

/**
 * Created by Baixd on 2015/4/7.
 */
public interface IBaseActivity {

    /**
     * 初始化数据
     */
    void init();

    /**
     * 初始化界面组件
     */
    void initView();

    /**
     * 为组件注册监听事件
     */
    void registerListener();


}
