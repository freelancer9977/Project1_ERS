package repositories;

import models.Reimbursement;
import models.Status;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    String url = "jdbc:postgresql://"+ System.getenv("AWS_RDS_ENDPOINT")+"/Project1-db";
    String username = System.getenv("RDS_USERNAME");
    String passwaord = System.getenv("RDS_PASSWORD");

    @Override
    public Boolean createReimbursementWithADescription(Reimbursement reimbursement) {
        try(Connection con = DriverManager.getConnection(url, this.username,passwaord)){
            String sql = "insert into ers_reimbursement (Reimb_amount, Reimb_author, Reimb_type_id,Reimb_description) " +
                    "values (?,?,?,?);";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1,reimbursement.getAmount());
            ps.setInt(2,reimbursement.getAuthor_id());
            ps.setInt(3,reimbursement.getType_id());
            ps.setString(4,reimbursement.getDescription());

            return ps.executeUpdate() != 0;

        }catch  (SQLException sqle){
            sqle.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean createReimbursementWithoutADescription(Reimbursement reimbursement) {
        try(Connection con = DriverManager.getConnection(url, this.username,passwaord)){
            String sql = "insert into ers_reimbursement (Reimb_amount, Reimb_author, Reimb_type_id) " +
                    "values (?,?,?);";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1,reimbursement.getAmount());
            ps.setInt(2,reimbursement.getAuthor_id());
            ps.setInt(3,reimbursement.getType_id());

            return ps.executeUpdate() !=0;

        }catch  (SQLException sqle){
            sqle.printStackTrace();
        }
        return false;
    }

    @Override
    public void reimbursementApprovedORDenied(Integer reimbursementID,Integer userId,Status status) {
        Integer status_id = 1;
        if (status == Status.APPROVED){
            status_id = 2;
        }
        else if (status == Status.DENIED){
            status_id = 3;
        }
        try (Connection con = DriverManager.getConnection(url, this.username, passwaord)) {
            String sql = "update ers_reimbursement set Reimb_resolver = ?, Reimb_status_id = ?, Reimb_resolved = now() where Reimb_id = ?;";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, status_id);
            ps.setInt(3, reimbursementID);

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public List<Reimbursement> getAllReimbursementGiveUserId(Integer userId) {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(url, this.username,passwaord)){
            String sql = "select * from ers_reimbursement where reimb_author = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getBlob(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10)));
            }

        }catch  (SQLException sqle){
            sqle.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbursement() {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(url, this.username,passwaord)){
            String sql = "select * from ers_reimbursement;";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getBlob(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10)));
            }

        }catch  (SQLException sqle){
            sqle.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbursementFilterByStatus(Status status) {
        Integer status_id = 1;
        if (status == Status.APPROVED){
            status_id = 2;
        }
        else if (status == Status.DENIED){
            status_id = 3;
        }
        List<Reimbursement> reimbursements = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(url, this.username,passwaord)){
            String sql = "select * from ers_reimbursement where reimb_status_id = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,status_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                    reimbursements.add(new Reimbursement(rs.getInt(1),
                            rs.getDouble(2),
                            rs.getDate(3),
                            rs.getDate(4),
                            rs.getString(5),
                            rs.getBlob(6),
                            rs.getInt(7),
                            rs.getInt(8),
                            rs.getInt(9),
                            rs.getInt(10)));
            }

        }catch  (SQLException sqle){
            sqle.printStackTrace();
        }
        return reimbursements;
    }
}
