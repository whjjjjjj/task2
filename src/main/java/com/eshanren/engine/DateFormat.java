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
public class DateFormat extends Directive {

    // TODO: 2019-03-24 类名不太规范 
    // TODO: 2019-03-24 代码逻辑不严谨，可以看下jfinal 中的源码 

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        Object[] ret = this.exprList.evalExprList(scope);
        long l = (long)ret[0];
        Date date = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            write(writer,sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
