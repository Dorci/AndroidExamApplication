package om.example.android.androidproject.service;

import android.util.Log;

import java.util.List;

import om.example.android.androidproject.model.Poem;
import om.example.android.androidproject.retrofit.PoemApi;
import om.example.android.androidproject.retrofit.PoemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://poetrydb.org")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static PoemApi poemApi = retrofit.create(PoemApi.class);

    public static PoemApi getPoemApi() {
        return poemApi;

    }


}
