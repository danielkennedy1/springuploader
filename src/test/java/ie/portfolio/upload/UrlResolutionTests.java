package ie.portfolio.upload;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class    UrlResolutionTests {

    @Autowired
    private MockMvc mockMvc;

    // No authentication testcases
    @ParameterizedTest
    @CsvSource({
            "/home, home.html",
            "/, home.html",
            "/login, login.html"
    })
    void testNoAuth(String path, String template) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(view().name(template));
    }

    // Test that the upload page redirects to the login page when not authenticated
    @Test
    void testAuthRedirect() throws Exception {
        mockMvc.perform(get("/upload"))
                .andExpect(status().is3xxRedirection());
    }

    // Authorised testcases
    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void testUploadPage() throws Exception {
        mockMvc.perform(get("/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload.html"));
    }

}
