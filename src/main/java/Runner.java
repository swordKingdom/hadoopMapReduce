import driver.FriendDriver;
import driver.MEFDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

//wordCount代码
public class Runner {
    public static void main(String[] args ) throws Exception {
        Configuration conf = new Configuration();
        //使用ToolRunner的run方法对自定义的类型进行处理
        /*启动wordCount算法
       oolRunner.run(conf, new WordCountDriver(), args);
        */
        /*启动移动平均算法
        * ToolRunner.run(conf, new MEFDriver(), args);
        * */
        conf.set("mapreduce.framework.name","yarn");
        ToolRunner.run(conf, new FriendDriver(), args);
    }
}
