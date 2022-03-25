package controlers;

import io.javalin.http.Context;
import models.JsonResponses;
import models.User;
import services.UserServices;

public class SessionController {
    private UserServices userServices;

    public SessionController(){
        this.userServices = new UserServices();
    }

    public void login(Context context){
        JsonResponses response;

        User user_info = context.bodyAsClass(User.class);

        User user_login = userServices.Login(user_info.getUsername(),user_info.getPassword());

        if (user_login == null){
            response = new JsonResponses(false, "Username or password are invalid",null);
        }
        else {
            context.sessionAttribute("user", user_login);
            response = new JsonResponses(true, "Logged In!",user_login);
        }

        context.json(response);
    }

    public void checkSession(Context context){
        User user = context.sessionAttribute("user");

        if(user == null){
            context.json(new JsonResponses(false, "no session found", null));
        }else{
            context.json(new JsonResponses(true, "session found", user));
        }

    }

    public void logout(Context context){
        context.sessionAttribute("user", null);

        context.json(new JsonResponses(true, "session invalidated", null));
    }
}
