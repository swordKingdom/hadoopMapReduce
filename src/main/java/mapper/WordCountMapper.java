package mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public  class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    Text k2 = new Text();
    LongWritable v2 = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println(value.toString());
        String[] strArr = value.toString().split("\n");
        for (String s :strArr) {
            k2.set(s);
            v2.set(1L);
            context.write(k2,v2);
        }
    }
}