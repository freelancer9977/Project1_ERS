package services;

import models.Reimbursement;
import models.Status;
import models.User;
import repositories.ReimbursementDAO;
import repositories.ReimbursementDAOImpl;

import java.util.Collections;
import java.util.List;

public class ReimbursementServices {
    private ReimbursementDAO reimbursementDAO;

    public ReimbursementServices(){
        this.reimbursementDAO = new ReimbursementDAOImpl();
    }

    public ReimbursementServices(ReimbursementDAO reimbursementDAO){
        this.reimbursementDAO = reimbursementDAO;
    }

    public Reimbursement CreateReimbursement(Reimbursement reimbursement){
        if(reimbursement.getDescription() == null){
            if(this.reimbursementDAO.createReimbursementWithoutADescription(reimbursement)){
                List<Reimbursement> list = getAllReimbursementGiveUserId(reimbursement.getAuthor_id());

                Collections.sort(list, (a, b) -> a.getId().compareTo(b.getId()));
                return list.get(list.size() - 1);
            }
            return null;
        }
        else {
            if(this.reimbursementDAO.createReimbursementWithADescription(reimbursement)){
                List<Reimbursement> list = getAllReimbursementGiveUserId(reimbursement.getAuthor_id());

                Collections.sort(list, (a, b) -> a.getId().compareTo(b.getId()));
                return list.get(list.size() - 1);
            }
            return null;
        }
    }

    public void process(Integer reimbursementID, Integer userId, Status status){
        this.reimbursementDAO.reimbursementApprovedORDenied(reimbursementID,userId,status);
    }

    public List<Reimbursement> getAllReimbursementGiveUserId (Integer userId){
        return this.reimbursementDAO.getAllReimbursementGiveUserId(userId);
    }

    public List<Reimbursement> getAllReimbursement (){
        return this.reimbursementDAO.getAllReimbursement();
    }

    public List<Reimbursement> getAllReimbursementByStatus(Status status){
        return this.reimbursementDAO.getAllReimbursementFilterByStatus(status);
    }
}
