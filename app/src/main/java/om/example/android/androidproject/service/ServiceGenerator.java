package om.example.android.androidproject.service;

import om.example.android.androidproject.retrofit.PoemApi;
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
