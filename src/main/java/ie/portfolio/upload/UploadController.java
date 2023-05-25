package ie.portfolio.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Controller
public class UploadController {

    private final SubmissionService SubmissionService;

    public UploadController(SubmissionService SubmissionService) {
        this.SubmissionService = SubmissionService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("CAO") String CAO, @RequestParam("file") MultipartFile file) throws IOException {

        boolean matchesPattern = CAO.matches("22\\d{6}");
        String fileType = file.getContentType();

        assert fileType != null;

        if((fileType.equals("application/pdf")) && matchesPattern){
            SubmissionService.savePortfolio(new Submission(CAO, file));
            return "success.html";
        }
        else
            //TODO: add error message to model
            return "upload.html";
    }
    @GetMapping("/upload")
    public String uploadFile() {
        return "upload.html";
    }
}
