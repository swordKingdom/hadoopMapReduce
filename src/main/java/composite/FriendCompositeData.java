package composite;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collections;

public class FriendCompositeData implements Writable {

    private Integer[] value ;

    public FriendCompositeData() {
    }

    public FriendCompositeData(Integer[] value) {
        this.value = value;
    }

    public Integer[] getValue() {
        return value;
    }

    public void setValue(Integer[] value) {
        this.value = value;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeChars(this.toString());
    }

    public void readFields(DataInput dataInput) throws IOException {
       String strValue = dataInput.readLine();
       String[] strArr = strValue.split(":");
       this.value = new Integer[strArr.length];
       for (int i =0;i<strArr.length;i++) {
           this.value[i] = new Integer(Integer.parseInt(strArr[i].trim()));
       }
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int i=0;i<this.value.length; i++) {
            if (i!=0) {
                outputStr += ":";
            }
            outputStr += this.value[i].toString();
        }
        return outputStr;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
