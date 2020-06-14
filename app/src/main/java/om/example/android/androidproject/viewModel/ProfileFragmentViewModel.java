package om.example.android.androidproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;

import om.example.android.androidproject.Room.UserLikes;
import om.example.android.androidproject.repository.UserLikesRepository;

public class ProfileFragmentViewModel extends AndroidViewModel {
    private static final String TAG = "ProfileFragmentViewMode";

    private UserLikesRepository userLikesRepository;

    public ProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        userLikesRepository = UserLikesRepository.getInstance(application);
    }

    public UserLikes getUserLikes() {
        return userLikesRepository.getUserLikesLiveData(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}
