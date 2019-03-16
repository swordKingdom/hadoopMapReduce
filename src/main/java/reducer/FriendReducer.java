package reducer;

import composite.FriendCompositeData;
import composite.FriendCompositeKey;
import org.apache.commons.collections.IteratorUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FriendReducer extends Reducer<FriendCompositeKey, FriendCompositeData, Text, Text> {
    private static Logger logger = Logger.getLogger(FriendReducer.class);

    @Override
    protected void reduce(FriendCompositeKey key, Iterable<FriendCompositeData> values, Context context) throws IOException, InterruptedException {
        Set<Integer> relationOne = new TreeSet<Integer>();
        Set<Integer> relationTwo = new TreeSet<Integer>();
        Iterator<FriendCompositeData> iterator = values.iterator();
        int count = 0;
        if (iterator.hasNext()) {
            Integer[] data = iterator.next().getValue();
            if (count == 0) {
                for (Integer v : data) {
                    relationOne.add(v);
                }
            }

            if (count == 1) {
                for (Integer v : data) {
                    relationTwo.add(v);
                }
            }
            count++;
        }
        System.out.println("get reduce data");
        System.out.println(key);
        System.out.println(relationOne);
        System.out.println(relationTwo);
        Set<Integer> commonFriend = tool.CommonFriend.intersection(relationOne, relationTwo);
        if (commonFriend != null) {
            Text resultKey = new Text();
            resultKey.set(key.toString());
            Text resultValue = new Text();
            resultValue.set(commonFriend.toString());

            context.write(resultKey, resultValue);
        }
    }


}
