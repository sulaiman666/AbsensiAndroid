package id.solo.absensi.services;

import id.solo.absensi.entity.Status;
import id.solo.absensi.entity.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("/user/add")
    Call<ResponseBody> addUser(@Body User user);

    @POST("/user/login")
    Call<ResponseBody> loginUser(@Body User user);

}
