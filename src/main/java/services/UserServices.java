package services;

import models.User;
import repositories.UserDAO;
import repositories.UserDAOImpl;

public class UserServices {
    private UserDAO userDAO;

    public UserServices(){
        this.userDAO = new UserDAOImpl();
    }

    public UserServices(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Boolean Register(User user){
        User DB_user_username = userDAO.getUserGivenUsername(user.getUsername());
        User DB_user_email = userDAO.getUserGivenUsername(user.getEmail());

        if(DB_user_username != null){//checking if the username is being used
            return Boolean.FALSE;
        }
        if (DB_user_email != null){//checking if the email is being used
            return Boolean.FALSE;
        }

        this.userDAO.crateUser(user);
        return Boolean.TRUE;
    }

    public User Login(String username,String passsword){
        User user = this.userDAO.getUserGivenUsername(username);
        if(user == null){
            return null;
        }
        if (!passsword.equals(user.getPassword())){
            return null;
        }

        return user;
    }
}
