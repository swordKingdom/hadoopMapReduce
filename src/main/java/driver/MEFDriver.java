package driver;

import composite.MEFCompositeData;
import mapper.MEFMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import reducer.MEFReducer;

public class MEFDriver extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setMapperClass(MEFMapper.class);
        job.setReducerClass(MEFReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(MEFCompositeData.class);
        job.setOutputValueClass(Text.class);

        job.setJar("D:\\Users\\25873\\IdeaProjects\\hadoopMapReduce\\classes\\artifacts\\hadoopMapReduce_jar\\hadoopMapReduce.jar");

        FileInputFormat.addInputPath(job,new Path("/data/input1.txt"));
        FileSystem fs= FileSystem.get(conf);
        Path op1 = new Path("/data/output1");
        if(fs.exists(op1)){
            System.out.println("存在此输出路径，已删除！！！");
            fs.delete(op1, true);
        }
        FileOutputFormat.setOutputPath(job,op1);
        boolean result = job.waitForCompletion(true);
        System.out.println(result);
        return 0;
    }
}
