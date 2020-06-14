package om.example.android.androidproject.Room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_likes")
public class UserLikes {
    @PrimaryKey
    @NonNull
    private String userId;
    private int numberOfLikes;

    public UserLikes(@NonNull String userId, int numberOfLikes) {
        this.userId = userId;
        this.numberOfLikes = numberOfLikes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    @Override
    public String toString() {
        return "UserLikes{" +
                "userId='" + userId + '\'' +
                ", numberOfLikes=" + numberOfLikes +
                '}';
    }
}
