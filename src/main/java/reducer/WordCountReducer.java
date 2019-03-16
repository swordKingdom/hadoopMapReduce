package reducer;

import compositekey.MEFCompositeData;
import org.apache.commons.collections.IteratorUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public  class WordCountReducer extends Reducer<Text, LongWritable,Text, LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0L;
        for (LongWritable v : values) {
            count += v.get();
        }
        LongWritable v3 = new LongWritable(count);
        context.write(key, v3);
    }
}