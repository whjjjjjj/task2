package com.eshanren.engine;

import com.jfinal.template.Directive;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/20.
 *
 * @author whj
 */
public class DateFormatDirective extends Directive {

    // 2019-03-24 类名不太规范
    //  2019-03-24 代码逻辑不严谨，可以看下jfinal 中的源码

    @Override
    public void exec(Env env, Scope scope, Writer writer) {

        //获取参数
        Object rett = this.exprList.eval(scope);

        //定义日期格式化规则
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //转换日期类型
            Date date = new Date((long)rett);

            //输出格式化之后的结果
            write(writer,sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
