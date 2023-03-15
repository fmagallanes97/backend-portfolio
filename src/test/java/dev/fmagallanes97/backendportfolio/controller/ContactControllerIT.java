package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.AbstractIntegrationTest;
import dev.fmagallanes97.backendportfolio.dto.request.ResumeRequest;
import dev.fmagallanes97.backendportfolio.service.ResumeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
class ContactControllerIT extends AbstractIntegrationTest {

    MockMvc mockMvc;

    @BeforeAll
    void setUpResume(@Autowired ResumeService service) {
        ResumeRequest request = new ResumeRequest("John", "Doe", "Experienced Software Developer", "I am a highly skilled software developer with 5 years of experience in Java development.");
        service.save(request);
    }

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test()
    @Order(1)
    @DisplayName("Should verify that at least one resume can be retrieved")
    void exists_at_least_one_resume() throws Exception {
        mockMvc.perform(get("/resume/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(2)
    @DisplayName("Should verify that POST method works correctly")
    void contact_creation_works() throws Exception {
        mockMvc.perform(post("/resume/1/contact")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "email": "john.doe@example.com",
                                  "githubProfileURL": "https://github.com/johndoe",
                                  "linkedinProfileURL": "https://www.linkedin.com/in/johndoe/"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(3)
    @DisplayName("Should verify that request body infringe the constraints for POST method")
    void contact_creation_does_not_work() throws Exception {
        mockMvc.perform(post("/resume/1/contact")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "email": "john.doe_gmail.com",
                                  "githubProfileURL": "",
                                  "linkedinProfileURL": ""
                                }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("email"))
                .andExpect(jsonPath("$.errors[0].reason").value("this value is not a valid email"));
    }

    @Test
    @Order(4)
    @DisplayName("Should verify that GET method works correctly")
    void contact_retrieving_works() throws Exception {
        mockMvc.perform(get("/contact/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(5)
    @DisplayName("Should verify that the path variable is not found for GET method")
    void contact_retrieving_does_not_work() throws Exception {
        mockMvc.perform(get("/contact/9999"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Contact Not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("The contact with ID 9999 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(9999));
    }

    @Test
    @Order(6)
    @DisplayName("Should verify that PUT method works correctly")
    void contact_updating_works() throws Exception {
        mockMvc.perform(put("/contact/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "email": "JD@example.com",
                                  "githubProfileURL": "github.com/jd",
                                  "linkedinProfileURL": "www.linkedin.com/in/jd/"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    @DisplayName("Should verify that body request infringe the constrains for PUT method")
    void contact_updating_does_not_work() throws Exception {
        mockMvc.perform(put("/contact/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "e-mail": "JD@example.com",
                                  "githubProfileURL": "",
                                  "linkedinProfileURL": ""
                                }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("email"))
                .andExpect(jsonPath("$.errors[0].reason").value("this attribute is mandatory"));
    }

    @Test
    @Order(8)
    @DisplayName("Should verify that DELETE method works correctly")
    void contact_deleting_works() throws Exception {
        mockMvc.perform(delete("/contact/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(9)
    @DisplayName("Should verify that path variable is not found for DELETE method")
    void contact_deleting_does_not_work() throws Exception {
        mockMvc.perform(delete("/contact/9999"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Contact Not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("The contact with ID 9999 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(9999));
    }
}