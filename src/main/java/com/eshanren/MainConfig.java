package com.eshanren;

import com.eshanren.config.AdminController;
import com.eshanren.config.RecordController;
import com.eshanren.config.RobotController;
import com.eshanren.engine.DateFormatDirective;
import com.eshanren.interceptor.LoginInterceptor;
import com.eshanren.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

/**
 * Created by Administrator on 2019/3/18.
 *
 * @author whj
 */
public class MainConfig extends JFinalConfig {

    //  2019-03-24 数据库建库sql 也要上传
    //  2019-03-24 界面实在太难看了，任务中要求有后台模版

    static Prop p;

    static void loadConfig(){
        if (p==null) {
            p = PropKit.use("config.properties").appendIfExists("jfinal-task2-config-dev.txt");
        }
    }

    public static void main(String[] args){
        UndertowServer.start(MainConfig.class);

    }

    @Override
    public void configConstant(Constants constants) {

        loadConfig();
        constants.setDevMode(true);
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

        engine.addDirective("format",DateFormatDirective.class);
        engine.setBaseTemplatePath("webapp");
        engine.setToClassPathSourceFactory();

    }

    @Override
    public void configPlugin(Plugins plugins) {
        //  2019-03-24 尽量不要使用c3p0连接池
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"),PropKit.get("user"),PropKit.get("password"),PropKit.get("driverClass"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(true);
        //设置了sql文件存放的基础路径
        arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
        arp.addSqlTemplate("all.sql");

        _MappingKit.mapping(arp);

        //  2019-03-24 model 类应该用工具生成
        plugins.add(druidPlugin);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new LoginInterceptor());

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
