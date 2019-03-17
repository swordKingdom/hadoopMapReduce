package mapper;

import composite.FriendRecommendData;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FriendRecommendMapper extends Mapper<LongWritable,Text, Text, FriendRecommendData> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] lines = value.toString().split("\n");
        for (String line: lines) {
            String[] tmpArr = line.split(":");
            if (tmpArr.length == 2 ) {
                String userId =  tmpArr[0];
                String[] friendIdList = tmpArr[1].split(",");
                Text reduceKey = new Text();
                reduceKey.set(userId);
                for(String friendId:friendIdList){
                    FriendRecommendData  reduceValue = new FriendRecommendData();
                    reduceValue.setUserOneId(friendId);
                    reduceValue.setUserTwoId("-1");
                    context.write(reduceKey,reduceValue);
                }
                for (int i = 0 ;i < friendIdList.length;i++){
                    for(int j = i + 1;j < friendIdList.length;j++){
                        Text uId1 = new Text();
                        uId1.set(friendIdList[i]);
                        Text uId2 = new Text();
                        uId2.set(friendIdList[j]);
                        FriendRecommendData  possibleFriend1 = new FriendRecommendData(friendIdList[j],userId);
                        context.write(uId1,possibleFriend1);
                        FriendRecommendData  possibleFriend2 = new FriendRecommendData(friendIdList[i],userId);
                        context.write(uId2,possibleFriend2);
                    }
                }
            }
        }
    }
}
