package om.example.android.androidproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import om.example.android.androidproject.R;
import om.example.android.androidproject.adapters.PoemsRecyclerAdapter;
import om.example.android.androidproject.model.Poem;

import om.example.android.androidproject.viewModel.AllPoemsFragmentViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllPoemsFragment extends Fragment implements PoemsRecyclerAdapter.OnPoemClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AllPoemsFragmentViewModel allPoemsFragmentViewModel;
    public static String POEM_INDEX = "Poem_Index";

    private PoemsRecyclerAdapter poemsRecyclerAdapter;


    public AllPoemsFragment() {
        // Required empty public constructor
    }
    public static AllPoemsFragment newInstance() {
        return new AllPoemsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root  = inflater.inflate(R.layout.fragment_all_poems, container, false);

        recyclerView = root.findViewById(R.id.recycler_PoemOverView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        allPoemsFragmentViewModel = new ViewModelProvider(this).get(AllPoemsFragmentViewModel.class);

        poemsRecyclerAdapter = new PoemsRecyclerAdapter(this);
        recyclerView.setAdapter(poemsRecyclerAdapter);

        allPoemsFragmentViewModel.getAllPoems().observe(getActivity(), new Observer<ArrayList<Poem>>() {
            @Override
            public void onChanged(ArrayList<Poem> poems) {
                poemsRecyclerAdapter.setPoems(poems);
                poemsRecyclerAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }

    @Override
    public void onPoemClick(int clickedPoemIndex) {
        Poem poem = allPoemsFragmentViewModel.getAllPoems().getValue().get(clickedPoemIndex);
        Toast.makeText(getContext(), " Clicked " + clickedPoemIndex, Toast.LENGTH_SHORT).show();
        Fragment fragment = new SinglePoemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POEM_INDEX, clickedPoemIndex);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.base_frame_layout, fragment).commit();
    }
}
