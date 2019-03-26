import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;


/**
 * Created by Administrator on 2019/3/25.
 *
 * @author whj
 */
public class Generator {

    public static DataSource getDataSource() {
        Prop p = PropKit.use("config.properties");
        DruidPlugin druidPlugin = new DruidPlugin(
                p.get("jdbcUrl"), p.get("user"), p.get("password"));
        druidPlugin.setDriverClass("com.mysql.jdbc.Driver");
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {

        // base model 所使用的包名
        String baseModelPackageName = "com.eshanren.model.base";
        // base model 文件保存路径
        //String baseModelOutputDir = "F:\\Workspaces\\coin\\bepal-discover\\src\\main\\java\\com\\eshanren\\model\\base";
        String baseModelOutputDir = "C:\\Users\\Administrator\\IdeaProjects\\task2\\src\\main\\java\\com\\eshanren\\model\\base";

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.eshanren.model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = "C:\\Users\\Administrator\\IdeaProjects\\task2\\src\\main\\java\\com\\eshanren\\model";

        // MappingKit文件默认保存路径
        String kitOutputDir = baseModelOutputDir + "/..";

        // 创建生成器
        com.jfinal.plugin.activerecord.generator.Generator gernerator = new com.jfinal.plugin.activerecord.generator.Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 设置数据库方言
        gernerator.setDialect(new MysqlDialect());
        // 设置是否在 Model 中生成 dao 对象
        gernerator.setGenerateDaoInModel(true);
        gernerator.setMappingKitOutputDir(kitOutputDir);
        // 设置是否生成字典文件
        gernerator.setGenerateDataDictionary(false);
        gernerator.addExcludedTable("task_list", "task_run_log");
        // 生成
        gernerator.generate();
    }

}
