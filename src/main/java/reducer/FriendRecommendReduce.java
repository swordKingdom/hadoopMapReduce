package reducer;

import composite.FriendRecommendData;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendRecommendReduce extends Reducer<Text, FriendRecommendData,Text ,Text> {
    @Override
    protected void reduce(Text key, Iterable<FriendRecommendData> values, Context context) throws IOException, InterruptedException {
        Map<String, List<String>>  result = new HashMap<String, List<String>>();
        for (FriendRecommendData data : values) {
            String toUserId = data.getUserOneId();
            String otherFriend = data.getUserTwoId();
            boolean alreadyFriend = otherFriend.equals("-1");
            if (result.containsKey(toUserId)) {
                if (alreadyFriend) {

                } else if (result.get(toUserId) != null) {
                    result.get(toUserId).add(otherFriend);
                }
            } else {
                if (alreadyFriend) {
                    result.put(toUserId, null);
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(otherFriend);
                    result.put(toUserId, list);
                }
            }
        }

        Text outputValue = new Text();
        outputValue.set(getOutputString(result));
        context.write(key,outputValue);
    }

    private String getOutputString( Map<String, List<String>>  r){
        String outputStr = "";
        for(Map.Entry<String,List<String>> entry:r.entrySet()) {
            String key = entry.getKey();
            List<String> list = entry.getValue();
            outputStr += (key + "("+list.size()+":"+list+")");
        }
        return outputStr;
    }
}
