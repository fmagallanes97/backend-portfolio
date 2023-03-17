package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.AbstractIntegrationTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class ContactControllerIT extends AbstractIntegrationTest {

    MockMvc mockMvc;

    final Long RESUME_ID = 1L;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test()
    @DisplayName("Should verify that at least one resume can be retrieved")
    void exists_at_least_one_resume() throws Exception {
        mockMvc.perform(get("/resume/" + RESUME_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that POST method works correctly")
    void contact_creation_works() throws Exception {
        mockMvc.perform(post("/resume/" + RESUME_ID + "/contact")
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

        mockMvc.perform(get("/resume/" + RESUME_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contact[?(@.email == 'john.doe@example.com')]").exists())
                .andExpect(jsonPath("$.contact[?(@.githubProfileURL == 'https://github.com/johndoe')]").exists())
                .andExpect(jsonPath("$.contact[?(@.linkedinProfileURL == 'https://www.linkedin.com/in/johndoe/')]").exists());
    }

    @Test
    @DisplayName("Should verify that request body infringe the constraints for POST method")
    void contact_creation_does_not_work() throws Exception {
        mockMvc.perform(post("/resume/" + RESUME_ID + "/contact")
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
    @DisplayName("Should verify that GET method works correctly")
    void contact_retrieving_works() throws Exception {
        mockMvc.perform(get("/contact/" + RESUME_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that the path variable is not found for GET method")
    void contact_retrieving_does_not_work() throws Exception {
        mockMvc.perform(get("/contact/" + -1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Contact Not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("The contact with ID -1 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(-1));
    }

    @Test
    @DisplayName("Should verify that PUT method works correctly")
    void contact_updating_works() throws Exception {
        mockMvc.perform(put("/contact/" + RESUME_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "email": "JD@example.com",
                                  "githubProfileURL": "github.com/jd",
                                  "linkedinProfileURL": "www.linkedin.com/in/jd/"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that body request infringe the constrains for PUT method")
    void contact_updating_does_not_work() throws Exception {
        mockMvc.perform(put("/contact/" + RESUME_ID)
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
    @DisplayName("Should verify that DELETE method works correctly")
    void contact_deleting_works() throws Exception {
        mockMvc.perform(delete("/contact/" + RESUME_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should verify that path variable is not found for DELETE method")
    void contact_deleting_does_not_work() throws Exception {
        mockMvc.perform(delete("/contact/" + -1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Contact Not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("The contact with ID -1 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(-1));
    }
}