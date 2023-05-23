package ie.portfolio.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    //post endpoint to upload file
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
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
    public String uploadFile() {
        return "upload";
    }
}
