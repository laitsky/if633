package id.ac.umn.week11_27047_retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface netInterface {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();

    @GET("")
    Call<ArrayList<PostModel>> getSomething();
}
