package composite;

import com.sun.jdi.IntegerType;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class FriendCompositeKey  implements Writable, WritableComparable<FriendCompositeKey> {

    private Integer personOneId;
    private Integer personTwoId;

    public FriendCompositeKey() {
    }

    public FriendCompositeKey(Integer personOneId, Integer personTwoId) {
        this.personOneId = personOneId;
        this.personTwoId = personTwoId;
    }

    public Integer getPersonOneId() {
        return personOneId;
    }

    public void setPersonOneId(Integer personOneId) {
        this.personOneId = personOneId;
    }

    public Integer getPersonTwoId() {
        return personTwoId;
    }

    public void setPersonTwoId(Integer personTwoId) {
        this.personTwoId = personTwoId;
    }

    public int compareTo(FriendCompositeKey o) {
        boolean eq = this.personOneId.equals(o.personOneId) && this.personTwoId .equals(o.personTwoId);
        return eq?0:1;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.personOneId);
        dataOutput.writeInt(this.personTwoId);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.personOneId = dataInput.readInt();
        this.personTwoId = dataInput.readInt();
    }

    @Override
    public String toString() {
        return this.personOneId+","+this.personTwoId;
    }
}
