package ie.portfolio.upload;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubmissionService {

    private final SubmissionRepository SubmissionRepository;

    public SubmissionService(SubmissionRepository SubmissionRepository) {
        this.SubmissionRepository = SubmissionRepository;
    }

    @Transactional
    public Submission savePortfolio(Submission Submission) {
        return SubmissionRepository.save(Submission);
    }
}
