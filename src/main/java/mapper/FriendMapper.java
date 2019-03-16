package mapper;

import org.apache.hadoop.io.ArrayWritable;
import  org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FriendMapper extends Mapper<LongWritable, Text, ArrayWritable, ArrayWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
         String[] arr = value.toString().split(":");
         if (arr.length == 2 ) {
             String personIdStr =  arr[0];
             String[] tmpStrArr = arr[1].split(",");
             IntWritable[] tmpArr = new IntWritable[tmpStrArr.length];
             for (int i=0;i<tmpStrArr.length;i++) {
                 Integer frinendId = Integer.parseInt(tmpStrArr[i]);
                 tmpArr[i].set(frinendId);
             }
             Integer personId =  Integer.parseInt(personIdStr);
             ArrayWritable reduceValue = new ArrayWritable(IntWritable.class,tmpArr);
             for (IntWritable v : tmpArr) {
                 ArrayWritable reduceKey =  buildRedduceKey(v.get(),personId);
                 context.write(reduceKey,reduceValue);
             }
         }
    }

    private static ArrayWritable buildRedduceKey(Integer personOneId , Integer personTwoId){
        IntWritable[] arr = new IntWritable[2];
        if(personOneId > personTwoId){
            arr[0].set(personOneId);
            arr[1].set(personTwoId);
        }else{
            arr[0].set(personTwoId);
            arr[1].set(personOneId);
        }
        ArrayWritable key = new ArrayWritable(IntWritable.class,arr);
        return key;
    }

}
