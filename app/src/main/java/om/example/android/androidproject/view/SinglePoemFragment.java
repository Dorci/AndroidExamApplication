package om.example.android.androidproject.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import om.example.android.androidproject.R;
import om.example.android.androidproject.utility.RandomColourGenerator;
import om.example.android.androidproject.viewModel.SinglePoemFragmentViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SinglePoemFragment extends Fragment {
    public static String POEM_LIKES = "Poem_Likes";
    TextView singlePoemText;
    TextView singlePoemAuthor;
    SinglePoemFragmentViewModel singlePoemFragmentViewModel;
    RandomColourGenerator randomColourGenerator = new RandomColourGenerator();
    ConstraintLayout singlePoemConstraintLayout;
    Button likeButton;
    int likes = 0;

    public SinglePoemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_single_poem, container, false);
        singlePoemFragmentViewModel = new ViewModelProvider(this).get(SinglePoemFragmentViewModel.class);

        likeButton = root.findViewById(R.id.likeButton);
        singlePoemConstraintLayout = root.findViewById(R.id.singlePoem_constraintLayout);
        singlePoemText = root.findViewById(R.id.SinglePoemTextView);
        singlePoemAuthor = root.findViewById(R.id.SinglePoemAuthorTextView);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt(AllPoemsFragment.POEM_INDEX, -1);
            singlePoemText.setText(singlePoemFragmentViewModel.getPoemByIndex(myInt).getText());
            singlePoemText.setTextSize(20);
            singlePoemAuthor.setText(singlePoemFragmentViewModel.getPoemByIndex(myInt).getAuthor());
            singlePoemAuthor.setTextSize(17);
            singlePoemConstraintLayout.setBackgroundColor(randomColourGenerator.getColor());
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    singlePoemFragmentViewModel.updateNewUserLikes();
                }
            });

        }

        // Inflate the layout for this fragment
        return root;
    }


}
