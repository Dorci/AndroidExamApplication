package om.example.android.androidproject.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;

import om.example.android.androidproject.Room.UserLikes;
import om.example.android.androidproject.model.Poem;
import om.example.android.androidproject.repository.PoemsRepository;
import om.example.android.androidproject.repository.UserLikesRepository;

public class SinglePoemFragmentViewModel extends AndroidViewModel {
    private static final String TAG = "SinglePoemFragmentViewM";
    private PoemsRepository repository;
    private UserLikesRepository userLikesRepository;

    public SinglePoemFragmentViewModel(Application application) {
        super(application);
        repository = PoemsRepository.getInstance();
        userLikesRepository = UserLikesRepository.getInstance(application);

    }

    public Poem getPoemByIndex(int index) {
        return repository.getPoemByIndex(index);
    }


    public void updateNewUserLikes() {
        // User ID from firebase
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        UserLikes userLikes = userLikesRepository.getUserLikesLiveData(userId);

        // If object is null, create new UserLikes with value 0 and insert to database
        if (userLikes == null){
            userLikes = new UserLikes(userId,0 );
        }

        // Get current amount of likes
        int currentLikes = userLikes.getNumberOfLikes();
        Log.i(TAG, "updateNewUserLikes: current likes ->>" + currentLikes);
        currentLikes++;
        userLikes.setNumberOfLikes(currentLikes);
        Log.i(TAG, "updateNewUserLikes: current likes after increment ->>" + currentLikes);
        userLikesRepository.updateNewUserLike(userLikes);
    }

}
