package om.example.android.androidproject.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import om.example.android.androidproject.model.Poem;
import om.example.android.androidproject.repository.PoemsRepository;

public class AllPoemsFragmentViewModel extends ViewModel {

    private PoemsRepository repository;

    public AllPoemsFragmentViewModel() {
        repository = PoemsRepository.getInstance();

    }


    public LiveData<ArrayList<Poem>> getAllPoems() {
        return repository.getAllPoems();
    }


    public void insert(final Poem poem) {
        repository.insert(poem);
    }
}
