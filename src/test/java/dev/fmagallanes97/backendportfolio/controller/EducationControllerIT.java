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
class EducationControllerIT extends AbstractIntegrationTest {

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
    void education_creation_works() throws Exception {
        mockMvc.perform(post("/resume/1/education")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                          "degree": "Bachelor's degree",
                          "school": "Example University",
                          "academicField": "Computer Science",
                          "startDate": "2018-09-01",
                          "endDate": "2022-06-30"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(3)
    @DisplayName("Should verify that POST method does not work correctly")
    void education_creation_does_not_work() throws Exception {
        mockMvc.perform(post("/resume/1/education")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                    {
                      "degree": "Bachelor's degree",
                      "school": "Example University",
                      "academic_field": "Computer Science",
                      "startDate": "2018-09-01",
                      "endDate": "2022-06-30"
                    }
                    """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("academicField"))
                .andExpect(jsonPath("$.errors[0].reason").value("this attribute is mandatory"));
    }

    @Test
    @Order(4)
    @DisplayName("Should verify that GET method retrieves the desired resource")
    void education_retrieving_one_works() throws Exception {
        mockMvc.perform(get("/education/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(5)
    @DisplayName("Should verify that the path variable is not found for GET method")
    void education_retrieving_one_does_not_work() throws Exception {
        mockMvc.perform(get("/education/9999"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Education Not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("The education with ID 9999 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(9999));
    }

    @Test
    @Order(6)
    @DisplayName("Should verify that GET method retrieves all the desired resources")
    void education_retrieving_all_works() throws Exception {
        mockMvc.perform(get("/resume/1/education"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(7)
    @DisplayName("Should verify that PUT method updates the desired resource")
    void education_updating_works() throws Exception {
        mockMvc.perform(put("/education/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                          "degree": "Bachelor's degree",
                          "school": "University",
                          "academicField": "Computer Science",
                          "startDate": "2018-09-01",
                          "endDate": ""
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(8)
    @DisplayName("Should verify that the request body infringe the constrains for PUT method")
    void education_updating_does_not_work() throws Exception {
        mockMvc.perform(put("/education/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                          "degree": "Bachelor's degree",
                          "academicField": "Computer Science",
                          "startDate": "2018-09-01",
                          "endDate": ""
                        }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("school"))
                .andExpect(jsonPath("$.errors[0].reason").value("this attribute is mandatory"));
    }

    @Test
    @Order(9)
    @DisplayName("Should verify that DELETE method works correctly")
    void education_deleting_works() throws Exception {
        mockMvc.perform(delete("/education/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(10)
    @DisplayName("Should verify that the path variable is not found for DELETE method")
    void education_deleting_does_not_work() throws Exception {
        mockMvc.perform(delete("/education/9999"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Education Not found"))
                .andExpect(jsonPath("$.detail").value("The education with ID 9999 cannot be found in the database"))
                .andExpect(jsonPath("$.instance").value("/education/9999"))
                .andExpect(jsonPath("$.id").value(9999));
    }
}