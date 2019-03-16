package mapper;

import composite.MEFCompositeData;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class MEFMapper extends Mapper<LongWritable, Text, Text,MEFCompositeData> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arr = value.toString().split("\n");
        for (String s : arr) {
            String[] tmpArr = s.split(",");
            if (tmpArr.length >= 3) {
                String name = tmpArr[0];
                Long ts = Long.parseLong(tmpArr[1]);
                Double v = Double.parseDouble(tmpArr[2]);
                MEFCompositeData outputValue = new MEFCompositeData(ts, v);
                Text outputKey = new Text();
                outputKey.set(name);
                context.write(outputKey,outputValue);
            }
        }
    }
}
