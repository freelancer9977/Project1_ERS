package repositories;

import models.Role;
import models.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO{
    String url = "jdbc:postgresql://"+ System.getenv("AWS_RDS_ENDPOINT")+"/Project1-db";
    String username = System.getenv("RDS_USERNAME");
    String passwaord = System.getenv("RDS_PASSWORD");

    @Override
    public User getUserGivenUsername(String username) {
        User user = null;
        try(Connection con = DriverManager.getConnection(url, this.username,passwaord)){
            String sql = "select * from ers_users where ers_username = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
            }

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }


        return user;
    }

    @Override
    public void crateUser(User user) {
        try(Connection con = DriverManager.getConnection(url, this.username,passwaord)){
            String sql = "insert into ers_users (ERS_username,ers_password,user_first_name,user_last_name,user_email,user_role_id) " +
                    "values (?,?,?,?,?,?);";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirst_name());
            ps.setString(4,user.getLast_name());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getRole());

            ps.executeUpdate();

        }catch  (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
