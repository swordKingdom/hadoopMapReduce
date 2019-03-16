package mapper;

import composite.MEFCompositeData;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MBAMapper extends Mapper<LongWritable, Text, Text, MEFCompositeData> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        super.map(key, value, context);
    }
}
