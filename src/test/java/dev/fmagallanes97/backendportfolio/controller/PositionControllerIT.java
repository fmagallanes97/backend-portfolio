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
class PositionControllerIT extends AbstractIntegrationTest {

    MockMvc mockMvc;

    private Long RESUME_ID = 1L;
    private Long POSITION_ID = 1L;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("Should verify that at least one resume can be retrieved")
    void exists_at_least_one_resume() throws Exception {
        mockMvc.perform(get("/resume/" + RESUME_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that POST method works correctly")
    void position_creation_works() throws Exception {
        mockMvc.perform(post("/resume/" + RESUME_ID + "/position")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "role": "Software Engineer",
                                  "companyName": "ABC Technologies",
                                  "startDate": "2022-01-01",
                                  "endDate": "2022-12-31"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        mockMvc.perform(get("/position/" + POSITION_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("Software Engineer"))
                .andExpect(jsonPath("$.companyName").value("ABC Technologies"))
                .andExpect(jsonPath("$.startDate").value("2022-01-01"))
                .andExpect(jsonPath("$.endDate").value("2022-12-31"));
    }

    @Test
    @DisplayName("Should verify that POST method does not work correctly")
    void position_creation_does_not_work() throws Exception {
        mockMvc.perform(post("/resume/" + RESUME_ID + "/position")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                 {
                                   "role": "SE",
                                   "companyName": "ABC Technologies",
                                   "startDate": "2022-01-01",
                                   "endDate": "2022-12-31"
                                 }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("role"))
                .andExpect(jsonPath("$.errors[0].reason").value("this value must be between 4 and 45 characters"));
    }

    @Test
    @DisplayName("Should verify that GET method retrieves the desired resource")
    void position_retrieving_one_works() throws Exception {
        mockMvc.perform(get("/position/" + POSITION_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that the path variable is not found for GET method")
    void position_retrieving_one_does_not_work() throws Exception {
        mockMvc.perform(get("/position/" + -1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Job position Not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("The job position with ID -1 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(-1));
    }

    @Test
    @DisplayName("Should verify that GET method retrieves all the desired resources")
    void position_retrieving_all_works() throws Exception {
        mockMvc.perform(get("/resume/" + RESUME_ID + "/position"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Should verify that PUT method updates the desired resource")
    void position_updating_works() throws Exception {
        mockMvc.perform(put("/position/" + POSITION_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "role": "Software Engineer",
                                  "companyName": "ABC Technologies",
                                  "startDate": "2022-01-01",
                                  "endDate": "2022-12-31"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that the request body infringe the constrains for PUT method")
    void position_updating_does_not_work() throws Exception {
        mockMvc.perform(put("/position/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "role": "Software Engineer",
                                  "companyName": "ABC Technologies",
                                  "startDate": "",
                                  "endDate": "2022-12-31"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("startDate"))
                .andExpect(jsonPath("$.errors[0].reason").value("this attribute is mandatory"));
    }

    @Test
    @DisplayName("Should verify that DELETE method works correctly")
    void position_deleting_works() throws Exception {
        mockMvc.perform(delete("/position/" + (POSITION_ID + 1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should verify that the path variable is not found for DELETE method")
    void position_deleting_does_not_work() throws Exception {
        mockMvc.perform(delete("/position/" + -1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Job position Not found"))
                .andExpect(jsonPath("$.detail").value("The job position with ID -1 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(-1));
    }
}