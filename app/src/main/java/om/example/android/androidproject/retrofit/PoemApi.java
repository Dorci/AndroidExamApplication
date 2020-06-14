package om.example.android.androidproject.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PoemApi {
    @GET("/author,linecount/{author};14/lines,author")
    Call<List<PoemResponse>> getPoemByAuthor(@Path("author") String name);
}
