package om.example.android.androidproject.repository;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import om.example.android.androidproject.dao.PoemDAO;
import om.example.android.androidproject.model.Poem;

public class PoemsRepository {
    private static PoemsRepository instance;
    private PoemDAO poemDAO;

    public PoemsRepository() {
        poemDAO = PoemDAO.getInstance();
    }

    public static PoemsRepository getInstance() {
        if (instance == null) {
            instance = new PoemsRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Poem>> getAllPoems() {
        return poemDAO.getAllPoems();
    }

    public void insert(Poem poem) {
        poemDAO.insert(poem);
    }

    public Poem getPoemByIndex(int index) {
        return poemDAO.getPoemByIndex(index);
    }

}
