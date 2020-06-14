package om.example.android.androidproject.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.service.autofill.UserData;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutionException;

import om.example.android.androidproject.Room.UserLikes;
import om.example.android.androidproject.Room.UserLikesDAO;
import om.example.android.androidproject.Room.UserLikesDatabase;

public class UserLikesRepository {
    private static final String TAG = "UserLikesRepository";
    private static UserLikesRepository instance;
    private UserLikesDAO userLikesDAO;

    private UserLikesRepository(Context context) {
        UserLikesDatabase userLikesDatabase = UserLikesDatabase.getInstance(context);
        userLikesDAO = userLikesDatabase.userLikesDAO();

    }

    public static UserLikesRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UserLikesRepository(context);
        }
        return instance;
    }

    public UserLikes getUserLikesLiveData(String userID) {
        Log.i(TAG, "getUserLikesLiveData: Getting data for user with ID: " + userID);
        UserLikes userLikes = null;
        try {
            userLikes = new GetUserLikesAsync(userLikesDAO, userID).execute().get();

            if (userLikes == null)
                UserLikesDatabase.databaseWriterExecutor.execute(() ->
                        userLikesDAO.insert(new UserLikes(userID, 0))
                );
        } catch (Exception e) {
            Log.e(TAG, "getUserLikesLiveData: Exception ", e.getCause());
        }
        Log.i(TAG, "getUserLikesLiveData: Data for current user null: " + String.valueOf(userLikes == null));
        return userLikes;
    }

    public void updateNewUserLike(UserLikes userLikes) {
        UserLikesDatabase.databaseWriterExecutor.execute(() ->
                userLikesDAO.update(userLikes));
        Log.i(TAG, "updateNewUserLike: Created new User likes with User Id: " + userLikes.getUserId());
        Log.i(TAG, "updateNewUserLike: Created new User likes with total likes: " + userLikes.getNumberOfLikes());
    }

    private class GetUserLikesAsync extends AsyncTask<Void, Void, UserLikes> {
        private UserLikesDAO userLikesDAO;
        private String userId;

        public GetUserLikesAsync(UserLikesDAO userLikesDAO, String userId) {
            this.userLikesDAO = userLikesDAO;
            this.userId = userId;
        }

        @Override
        protected UserLikes doInBackground(Void... Void) {
            return userLikesDAO.getAllUserLikes(userId);
        }
    }
}
