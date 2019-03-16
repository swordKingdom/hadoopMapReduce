package composite;

import com.sun.jdi.IntegerType;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class FriendCompositeData  implements Writable, WritableComparable<MEFCompositeData> {

    private Set<Integer> data = new TreeSet<Integer>();

    public int compareTo(MEFCompositeData o) {
        return 0;
    }

    public void write(DataOutput dataOutput) throws IOException {

    }

    public void readFields(DataInput dataInput) throws IOException {

    }
}
