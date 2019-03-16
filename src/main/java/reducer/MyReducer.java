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

public  class MyReducer extends Reducer<Text, MEFCompositeData,Text, Text> {

}