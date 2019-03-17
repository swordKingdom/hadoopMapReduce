package composite;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FriendRecommendData implements Writable {
    private String userOneId;
    private String userTwoId;

    public FriendRecommendData() {
    }

    public FriendRecommendData(String userOneId, String userTwoId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
    }

    public String getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(String userOneId) {
        this.userOneId = userOneId;
    }

    public String getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(String userTwoId) {
        this.userTwoId = userTwoId;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeChars(userOneId);
        dataOutput.writeChars(userTwoId);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.userOneId = dataInput.readLine();
        this.userTwoId = dataInput.readLine();
    }
}
