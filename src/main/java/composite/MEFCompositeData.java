package composite;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MEFCompositeData implements Writable,WritableComparable<MEFCompositeData> {

    private long ts;
    private  double value;

    public MEFCompositeData() {
    }

    public MEFCompositeData(long ts, double value) {
        this.ts = ts;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public int compareTo(MEFCompositeData o) {
        return this.ts > o.ts ? 1 : -1;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.ts);
        dataOutput.writeDouble(this.value);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.ts = dataInput.readLong();
        this.value = dataInput.readDouble();
    }


}
