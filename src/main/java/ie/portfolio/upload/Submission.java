package ie.portfolio.upload;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
public class Submission {

    @Id
    private String CAO;

    @Lob
    private byte[] portfolio;

    public Submission() {
    }

    public Submission(String CAO, MultipartFile portfolio) throws IOException, IOException {
        this.CAO = CAO;
        this.portfolio = portfolio.getBytes();
    }

    public String getCAO() {
        return CAO;
    }

    public void setCAO(String CAO) {
        this.CAO = CAO;
    }

    public byte[] getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(MultipartFile portfolio) throws IOException {
        this.portfolio = portfolio.getBytes();
    }
}

