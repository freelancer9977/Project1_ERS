import controlers.ReimbursementControler;
import controlers.SessionController;
import controlers.UserControler;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import models.Role;
import models.Status;

public class MainDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
        }).start(9001);

        UserControler userControler = new UserControler();
        ReimbursementControler reimbursementControler = new ReimbursementControler();
        SessionController sessionController = new SessionController();

        app.post("/register", userControler::register);

        app.post("/session", sessionController::login);
        app.get("/session",sessionController::checkSession);
        app.delete("/session", sessionController::logout);

        app.get("/user",reimbursementControler::getAllReimbursementGiveUserId);
        app.get("/user/FM",reimbursementControler::getAllReimbursement);
        app.get("/user/reimbursement/approved", reimbursementControler::getAllReimbursementByStatusApproved);
        app.get("/user/reimbursement/denied", reimbursementControler::getAllReimbursementByStatusDenied);
        app.get("/user/reimbursement/pending", reimbursementControler::getAllReimbursementByStatusPending);

        app.post("/user/reimbursement",reimbursementControler::createReimbursement);

        app.patch("/user/{userId}/reimbursement/{reimbursementId}/approved",reimbursementControler::processApproved);
        app.patch("/user/{userId}/reimbursement/{reimbursementId}/denied",reimbursementControler::processDenied);

    }
}
