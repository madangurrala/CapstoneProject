package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl.IUserServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class UserServerApi {
    private IUserServiceAPI iUserServiceAPI;
    private String baseUrl = "";

    public UserServerApi(String token) {
        baseUrl=CoreServerApi.BASE_URL;
        Retrofit retrofit = new CoreServerApi(baseUrl,token).getRetrofit();
        iUserServiceAPI = retrofit.create(IUserServiceAPI.class);
    }

    public void login(UserTO userTO, Callback<UserTO> callback) {
        if (callback == null) {
            return;
        }
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty(UserTO.KEY.EMAIL_KEY,userTO.getEmail());
        jsonObject.addProperty(UserTO.KEY.PASSWD_KEY,userTO.getPasswd());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Call<UserTO> call = iUserServiceAPI.login(requestBody);
        call.enqueue(callback);
    }

    public void register(UserTO userTO, Callback<UserTO> callback) {
        if (callback == null) {
            return;
        }
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty(UserTO.KEY.NAME_KEY,userTO.getName());
        jsonObject.addProperty(UserTO.KEY.FAMILY_KEY,userTO.getFamily());
        jsonObject.addProperty(UserTO.KEY.EMAIL_KEY,userTO.getEmail());
        jsonObject.addProperty(UserTO.KEY.PASSWD_KEY,userTO.getPasswd());
        jsonObject.addProperty(UserTO.KEY.PHONE_KEY,userTO.getPhone());

        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        Call<UserTO> call = iUserServiceAPI.register(requestBody);
        call.enqueue(callback);
    }

    public void getUserById(long id, Callback<UserTO> callback) {
        if (callback == null) {
            return;
        }
        Call<UserTO> call = iUserServiceAPI.selectUserById((int)id);
        call.enqueue(callback);
    }


    public void update(UserTO userTO, Callback<UserTO> callback) {
        if (callback == null) {
            return;
        }
        Gson gson=new Gson();
        String userJson=gson.toJson(userTO);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),userJson);
        Call<UserTO> call = iUserServiceAPI.update(requestBody);
        call.enqueue(callback);
    }
}
