package controlers;

import io.javalin.http.Context;
import models.JsonResponses;
import models.Reimbursement;
import models.Status;
import models.User;
import services.ReimbursementServices;

import java.util.List;

public class ReimbursementControler {
    private ReimbursementServices reimbursementServices;

    public ReimbursementControler() {
        this.reimbursementServices = new ReimbursementServices();
    }

    public ReimbursementControler(ReimbursementServices reimbursementServices) {
        this.reimbursementServices = reimbursementServices;
    }

    public void createReimbursement(Context context){
        JsonResponses response;

        Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);

        Reimbursement reimbursement1 = reimbursementServices.CreateReimbursement(reimbursement);

        if (reimbursement1 == null){
            context.json(new JsonResponses(false, "reimbursement was not created", null));
        }else {
            context.json(new JsonResponses(true, "reimbursement has been made", reimbursement1));
        }
    }

    public void processApproved(Context context){
        Integer userId = Integer.parseInt(context.pathParam("userId"));
        Integer reimbursementId = Integer.parseInt(context.pathParam("reimbursementId"));

        reimbursementServices.process(reimbursementId,userId, Status.APPROVED);

        context.json(new JsonResponses(true, "reimbursement has be approved", null));
    }

    public void processDenied(Context context){
        Integer userId = Integer.parseInt(context.pathParam("userId"));
        Integer reimbursementId = Integer.parseInt(context.pathParam("reimbursementId"));

        reimbursementServices.process(reimbursementId,userId, Status.DENIED);

        context.json(new JsonResponses(true, "reimbursement has be Denied", null));
    }

    public void getAllReimbursementGiveUserId(Context context){
        Integer userId = Integer.parseInt(context.queryParam("userId"));

        List<Reimbursement> list = reimbursementServices.getAllReimbursementGiveUserId(userId);

        context.json(new JsonResponses(true,"List all reimbursements for user" + userId, list));
    }

    public void getAllReimbursement(Context context){
        List<Reimbursement> list = reimbursementServices.getAllReimbursement();

        context.json(new JsonResponses(true,"List all reimbursements", list));
    }

    //todo need to get more then one thing from the context
    public void getAllReimbursementByStatusPending(Context context){
        List<Reimbursement> list = reimbursementServices.getAllReimbursementByStatus(Status.PENDING);

        context.json(new JsonResponses(true, "show all reimbursement that are pending", list));
    }

    public void getAllReimbursementByStatusApproved(Context context){
        List<Reimbursement> list = reimbursementServices.getAllReimbursementByStatus(Status.APPROVED);

        context.json(new JsonResponses(true, "show all reimbursement that are approved", list));
    }

    public void getAllReimbursementByStatusDenied(Context context){
        List<Reimbursement> list = reimbursementServices.getAllReimbursementByStatus(Status.DENIED);

        context.json(new JsonResponses(true, "show all reimbursement that are denied", list));
    }
}
