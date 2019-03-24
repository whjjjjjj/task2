package com.eshanren.config;

import com.eshanren.engine.DateFormat;
import com.eshanren.model.Admin;
import com.eshanren.model.Record;
import com.eshanren.model.Robot;
import com.jfinal.config.*;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

/**
 * Created by Administrator on 2019/3/18.
 *
 * @author whj
 */
public class MainConfig extends JFinalConfig {

    // TODO: 2019-03-24 数据库建库sql 也要上传 
    // TODO: 2019-03-24 界面实在太难看了，任务中要求有后台模版 

    public static void main(String[] args){
        UndertowServer.start(MainConfig.class,80,true);

    }
    
    

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        PropKit.use("config.properties");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/robot", RobotController.class);
        routes.add("/admin", AdminController.class);
        routes.add("/record", RecordController.class);
    }

    @Override
    public void configEngine(Engine engine) {
        engine.addDirective("format",DateFormat.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {
        // TODO: 2019-03-24 尽量不要使用c3p0连接池 
        C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"),PropKit.get("user"),PropKit.get("password"));

        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setShowSql(true);

        // TODO: 2019-03-24 model 类应该用工具生成 
        arp.addMapping("admin", Admin.class);
        arp.addMapping("robot", Robot.class);
        arp.addMapping("record", Record.class);

        plugins.add(c3p0Plugin);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new SessionInViewInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
