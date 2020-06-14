package om.example.android.androidproject.dao;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import om.example.android.androidproject.model.Poem;
import om.example.android.androidproject.retrofit.PoemApi;
import om.example.android.androidproject.retrofit.PoemResponse;
import om.example.android.androidproject.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoemDAO {
    private static final String TAG = "PoemDAO";
    private static PoemDAO instance;
    private MutableLiveData<ArrayList<Poem>> allPoems;

    private PoemDAO() {

        allPoems = new MutableLiveData<>();
    }

    public static PoemDAO getInstance() {
        if (instance == null) {
            instance = new PoemDAO();
        }
        return instance;
    }

    public LiveData<ArrayList<Poem>> getAllPoems() {
        requestForPoem("Shakespeare");
        return allPoems;
    }

    public void insert(Poem poem) {
        ArrayList<Poem> currentPoems = allPoems.getValue();
        currentPoems.add(poem);
        allPoems.setValue(currentPoems);
    }


    public Poem getPoemByIndex(int index) {
        return allPoems.getValue().get(index);
    }

    public void requestForPoem(String name) {
        PoemApi poemApi = ServiceGenerator.getPoemApi();
        Call<List<PoemResponse>> call = poemApi.getPoemByAuthor(name);
        call.enqueue(new Callback<List<PoemResponse>>() {
            @Override
            public void onResponse(Call<List<PoemResponse>> call, Response<List<PoemResponse>> response) {
                Log.i(TAG, "onResponse: Response received");
                Log.i(TAG, "onResponse: " + response.body());
                // Create empty arraylist of poems
                ArrayList<Poem> poems = new ArrayList<>();

                //loop through all poems from response
                //extract poem from poem response
                //add poem to empty arraylist

                for (PoemResponse poemResponse : response.body()) {
                    poems.add(poemResponse.getPoem());
                }

                allPoems.setValue(poems);

            }

            @Override
            public void onFailure(Call<List<PoemResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });


    }
}
