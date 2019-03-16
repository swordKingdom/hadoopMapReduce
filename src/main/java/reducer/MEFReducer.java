package reducer;

import compositekey.MEFCompositeData;
import org.apache.commons.collections.IteratorUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MEFReducer extends Reducer<Text, MEFCompositeData,Text, Text> {

    private int windowSize = 4;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        this. windowSize = conf.getInt("window.Size",4);
    }

    private List<MEFCompositeData> sort(Iterable<MEFCompositeData>  data ){
        List<MEFCompositeData> list =  IteratorUtils.toList(data.iterator());
        Collections.sort(list);
        return list;
    }

    @Override
    protected void reduce(Text key, Iterable<MEFCompositeData> values, Context context) throws IOException, InterruptedException {
        List<MEFCompositeData> list = sort(values);
        double sum = 0.0;
        if (list.size() < windowSize){
            Text value = new Text();
            value.set("");
            context.write(key,value);
        }else{
            for (int i = 0 ;i < windowSize - 1; i ++ ){
                sum += list.get(i).getValue();
            }
            for (int i= windowSize -1 ;i<windowSize; i ++) {
                sum += list.get(i).getValue();
                double MA = sum / windowSize;
                Text value = new Text();
                long ts = list.get(i).getTs();
                value.set(ts + ","+ MA);
                context.write(key,value);
                sum -= list.get(i- windowSize +1).getValue();
            }
        }
    }
}
