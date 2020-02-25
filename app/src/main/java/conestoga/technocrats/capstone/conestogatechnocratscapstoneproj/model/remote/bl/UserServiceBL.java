package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.bl;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.UserServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceBL
{
    private UserServiceAPI userServiceAPI;
    public UserServiceBL() {
        userServiceAPI=new UserServiceAPI();
    }

    public void login(String email, String passwd) {
        userServiceAPI.login(email, passwd, new Callback<UserTO>() {
            @Override
            public void onResponse(Call<UserTO> call, Response<UserTO> response) {

            }

            @Override
            public void onFailure(Call<UserTO> call, Throwable t) {

            }
        });
    }

    public void logout(String token) {
        userServiceAPI.logout(token, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
