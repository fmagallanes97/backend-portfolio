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
class PosResponsibilityControllerIT extends AbstractIntegrationTest {

    MockMvc mockMvc;

    final Long POSITION_RESPONSIBILITY = 3L;
    final Long POSITION_ID = 2L;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("Should verify that exist at least one position")
    void exists_at_least_one_position() throws Exception {
        mockMvc.perform(get("/position/" + POSITION_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should verify that POST method works correctly")
    void position_responsibility_creation_works() throws Exception {
        mockMvc.perform(post("/position/" + POSITION_ID + "/position_responsibility")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "description": "Design and develop software applications using Java and Spring Framework."
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        mockMvc.perform(get("/position/" + POSITION_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responsibilities[?(@.description == 'Design and develop software applications using Java and Spring Framework.')]").exists());
    }

    @Test
    @DisplayName("Should verify that POST method does not work correctly")
    void position_responsibility_creation_does_not_work() throws Exception {
        mockMvc.perform(post("/position/" + POSITION_ID + "/position_responsibility")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                 {
                                  "description": "Design."
                                 }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("description"))
                .andExpect(jsonPath("$.errors[0].reason").value("this value must be between 12 and 255 characters"));
    }

    @Test
    @DisplayName("Should verify that GET method retrieves all the desired resources")
    void position_responsibility_retrieving_all_works() throws Exception {
        mockMvc.perform(get("/position/" + POSITION_ID + "/position_responsibility"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Should verify that PUT method updates the desired resource")
    void position_responsibility_updating_works() throws Exception {
        mockMvc.perform(put("/position_responsibility/" + POSITION_RESPONSIBILITY)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "description": "Create and implement software solutions using Python and Django Framework."
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that PUT method updates the desired resource")
    void position_responsibility_updating_does_not_work() throws Exception {
        mockMvc.perform(put("/position_responsibility/" + POSITION_RESPONSIBILITY)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "description": "Create"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("description"))
                .andExpect(jsonPath("$.errors[0].reason").value("this value must be between 12 and 255 characters"));

    }

    @Test
    @DisplayName("Should verify that DELETE method works correctly")
    void position_responsibility_deleting_works() throws Exception {
        mockMvc.perform(delete("/position_responsibility/" + POSITION_RESPONSIBILITY))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should verify that the path variable is not found for DELETE method")
    void position_responsibility_deleting_does_not_work() throws Exception {
        mockMvc.perform(delete("/position_responsibility/-1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Job position responsibility Not found"))
                .andExpect(jsonPath("$.detail").value("The job position responsibility with ID -1 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(-1));
    }
}