package om.example.android.androidproject.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import om.example.android.androidproject.R;
import om.example.android.androidproject.Room.UserLikes;
import om.example.android.androidproject.viewModel.ProfileFragmentViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ProfileFragmentViewModel profileFragmentViewModel;
    TextView profileName;
    TextView likes;
    FirebaseAuth mAuth;
    String username;
    TextView emailLabel;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        profileFragmentViewModel = new ViewModelProvider(this).get(ProfileFragmentViewModel.class);
        profileName = rootView.findViewById(R.id.textViewProfileName);
        likes = rootView.findViewById(R.id.textViewLikes);
        emailLabel = rootView.findViewById(R.id.textViewEmailLabel);
        mAuth = FirebaseAuth.getInstance();

//        SharedPreferences prefs = getActivity().getSharedPreferences("Poem_Likes", MODE_PRIVATE);
//        int numberOfLikes = prefs.getInt("likes", 0);
//        likes.setText(numberOfLikes+"");
        likes.setText(profileFragmentViewModel.getUserLikes() == null ?
                0 + "" :
                profileFragmentViewModel.getUserLikes().getNumberOfLikes() + "");
        username = mAuth.getCurrentUser().getEmail();
        profileName.setText(username);
        return rootView;
    }
}
