package mapper;

import composite.FriendCompositeData;
import composite.FriendCompositeKey;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FriendMapper extends Mapper<LongWritable, Text, FriendCompositeKey, FriendCompositeData> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] lines = value.toString().split("\n");
        for (String line:lines) {
            String[] arr = line.split(":");
            if (arr.length == 2 ) {
                String personIdStr =  arr[0];
                String[] tmpStrArr = arr[1].split(",");
                Integer[] tmpArr = new Integer[tmpStrArr.length];
                for (int i=0;i<tmpStrArr.length;i++) {
                    Integer frinendId = Integer.parseInt(tmpStrArr[i]);
                    tmpArr[i] = new Integer(frinendId);
                }
                Integer personId =  Integer.parseInt(personIdStr);
                FriendCompositeData reduceValue = new FriendCompositeData(tmpArr);
                for (Integer v : tmpArr) {
                    FriendCompositeKey reduceKey =  buildRedduceKey(v,personId);
                    System.out.println(reduceKey);
                    System.out.println(reduceValue);
                    context.write(reduceKey,reduceValue);
                }
            }
        }
    }
    private static FriendCompositeKey buildRedduceKey(Integer personOneId , Integer personTwoId){
        if(personOneId > personTwoId){
            return  new FriendCompositeKey(personOneId,personTwoId);
        }else {
            return new FriendCompositeKey(personTwoId, personOneId);
        }
    }
}
