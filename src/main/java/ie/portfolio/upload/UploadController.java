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

    //post endpoint to upload file
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("CAO") String CAO, @RequestParam("file") MultipartFile file) {
        int CAOInt = Integer.parseInt(CAO);
        String fileName = file.getOriginalFilename();
        //save the file to DB
        //if works, return success, else return upload with error message
        return "success";
        //ELSE
        //model error attribute
        //return "upload";
    }
    //get endpoint to see upload dialog
    @GetMapping("/upload")
    public String uploadFile() throws IOException {
        Submission submission = new Submission();
        submission.setCAO("12345678");
        SubmissionService.savePortfolio(submission);
        return "upload";
    }
}
