package reducer;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class FriendReducer extends Reducer<ArrayWritable,ArrayWritable, Text,Text> {
    @Override
    protected void reduce(ArrayWritable key, Iterable<ArrayWritable> values, Context context) throws IOException, InterruptedException {
        Set<Integer> relationOne = new TreeSet<Integer>();
        Set<Integer> relationTwo = new TreeSet<Integer>();
        Iterator<ArrayWritable> iter = values.iterator();
        for (int i= 0 ;i < 2; i++ ){
            if(iter.hasNext()){
                Integer[] data = (Integer[]) iter.next().toArray();
                if(i==0) {
                    for (Integer v: data) {
                        relationOne.add(v);
                    }
                }else{
                    for (Integer v: data) {
                        relationOne.add(v);
                    }
                }
            }
        }
        Set<Integer> commonFriend = tool.CommonFriend.intersection(relationOne,relationTwo);
        Text resultKey = new Text();
        resultKey.set(key.toString());
        Text resultValue = new Text();
        resultValue.set( commonFriend.toString());
        context.write(resultKey,resultValue);
    }
}
