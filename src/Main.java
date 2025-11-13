import controller.CandidateController;
import repository.CandidateRepository;
import service.CandidateService;
import view.CandidateView;

public class Main {
    public static void main(String[] args) {
        CandidateRepository repository = new CandidateRepository();
        CandidateService service = new CandidateService(repository);
        CandidateView view = new CandidateView();
        CandidateController controller = new CandidateController(service, view);
        controller.start();
    }
}

