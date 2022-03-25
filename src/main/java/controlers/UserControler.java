package controlers;

import io.javalin.http.Context;
import models.JsonResponses;
import models.User;
import services.UserServices;

public class UserControler {
    private UserServices userServices;

    public UserControler() {
        this.userServices = new UserServices();
    }

    public UserControler(UserServices userServices) {
        this.userServices = userServices;
    }

    public void register(Context context){
        JsonResponses response;

        User user = context.bodyAsClass(User.class);

        if (userServices.Register(user)){
            response = new JsonResponses(true, "new user created ",null);
        }
        else {
            response = new JsonResponses(false, "email or username is already in the database",null);
        }

        context.json(response);
    }
}
