package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl.IUserServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.root.ServerService;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class UserServiceAPI {
    private IUserServiceAPI iUserServiceAPI;
    private final String baseUrl = "";

    public UserServiceAPI() {
        Retrofit retrofit = new ServerService(baseUrl).getRetrofit();
        iUserServiceAPI = retrofit.create(IUserServiceAPI.class);
    }

    public void login(String email, String passwd, Callback<UserTO> callback) {
        if (callback == null) {
            return;
        }
        Call<UserTO> call = iUserServiceAPI.login(email, passwd);
        call.enqueue(callback);
    }

    public void logout(String token, Callback<Void> callback) {
        if (callback == null) {
            return;
        }
        Call<Void> call = iUserServiceAPI.logout(token);
        call.enqueue(callback);
    }
}
