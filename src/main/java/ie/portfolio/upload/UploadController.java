package ie.portfolio.upload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class UploadController {

    private final SubmissionService SubmissionService;

    public UploadController(SubmissionService SubmissionService) {
        this.SubmissionService = SubmissionService;
    }

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/")
    public String homeagain() {
        return "home.html";
    }

    // post endpoint to upload file
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("CAO") String CAO, @RequestParam("file") MultipartFile file, Model model) throws IOException {

        ArrayList<String> errors = new ArrayList<>();
        String fileType = file.getContentType();

        assert fileType != null;

        if(CAO.isBlank()) {
            errors.add("CAO number is blank");
        }else if (!CAO.matches("22\\d{6}")) {
            errors.add("CAO number is invalid");
        }

        if(file.isEmpty()) {
            errors.add("File is empty");
        }else {
            if(!fileType.equals("application/pdf")) {
                errors.add("File is not a pdf");
            }
        }


        if((fileType.equals("application/pdf")) && CAO.matches("22\\d{6}")){
            SubmissionService.savePortfolio(new Submission(CAO, file));
            return "success.html";
        }
        model.addAttribute("errors", errors);
        return "upload.html";
    }
  
    @GetMapping("/upload")
    public String uploadFile() {
        return "upload.html";
    }

    @GetMapping(value = "/login")
    public String getMethodName() {
        return "login.html";
    }

}
