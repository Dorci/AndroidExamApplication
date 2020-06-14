package om.example.android.androidproject.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserLikesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserLikes userLikes);

    @Update
    void update(UserLikes userLikes);

    @Delete
    void delete(UserLikes userLikes);

    @Query("SELECT * FROM user_likes WHERE userId = :userID LIMIT 1")
    UserLikes getAllUserLikes(String userID);

    @Query("SELECT * FROM user_likes")
    LiveData<List<UserLikes>> getAll();

}
