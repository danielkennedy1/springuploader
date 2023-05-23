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

        System.out.println("CAO: " + CAO);
        System.out.println("File content type: " + file.getContentType());
        System.out.println("File name: " + file.getName());

        int CAOInt = Integer.parseInt(CAO);
        String fileName = file.getOriginalFilename();



        // TODO: check if file is a pdf
        // TODO: check if CAO is 8 digits

        SubmissionService.savePortfolio(new Submission(CAO, file));


        //TODO if works, return success, else return upload with error message
        //redirect to success page
        return "success.html";
        //ELSE
        //model error attribute
        //return "upload";
    }
    @GetMapping("/upload")
    public String uploadFile() {
        return "upload.html";
    }
}
