package www.painacafe.com.Retrofit;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyService {
    @POST("register")
    @FormUrlEncoded
    io.reactivex.Observable<String> registerUser(@Field("email") String email,
                                                 @Field("name") String name,
                                                 @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    io.reactivex.Observable<String> loginUser(@Field("email") String email,
                                                 @Field("password") String password);
}
