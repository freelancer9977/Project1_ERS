package repositories;

import models.Reimbursement;
import models.Status;
import models.User;

import java.util.List;

public interface ReimbursementDAO {
    Boolean createReimbursementWithADescription(Reimbursement reimbursement);
    Boolean createReimbursementWithoutADescription(Reimbursement reimbursement);
    void reimbursementApprovedORDenied(Integer reimbursementID,Integer userId, Status status);
    List<Reimbursement> getAllReimbursementGiveUserId(Integer userId);
    List<Reimbursement> getAllReimbursement();
    List<Reimbursement> getAllReimbursementFilterByStatus(Status status);
}
