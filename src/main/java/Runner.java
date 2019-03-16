import driver.MEFDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

//wordCount代码
public class Runner {
    public static void main(String[] args ) throws Exception {
        Configuration conf = new Configuration();
        //使用ToolRunner的run方法对自定义的类型进行处理
        ToolRunner.run(conf, new MEFDriver(), args);
    }
}
