package om.example.android.androidproject.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserLikes.class}, version = 4, exportSchema = false)
public abstract class UserLikesDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static UserLikesDatabase instance;

    public static synchronized UserLikesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserLikesDatabase.class, "user_likes_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract UserLikesDAO userLikesDAO();
}
