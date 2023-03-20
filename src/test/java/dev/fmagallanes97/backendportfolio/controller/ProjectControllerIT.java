package dev.fmagallanes97.backendportfolio.controller;

import com.jayway.jsonpath.JsonPath;
import dev.fmagallanes97.backendportfolio.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class ProjectControllerIT extends AbstractIntegrationTest {

    MockMvc mockMvc;

    final int RESUME_ID = 1;
    final int PROJECT_ID = 1;

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
    void project_creation_works() throws Exception {
        String request = """
                {
                  "title": "My Project Title",
                  "description": "This is a description of my project",
                  "startDate": "2022-01-01",
                  "website": "https://example.com",
                  "repositoryURL": "https://github.com/my-username/my-repo",
                  "previewImageURL": "https://example.com/my-image.png"
                }
                """;

        MvcResult result = mockMvc.perform(post("/resume/" + RESUME_ID + "/project")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        int projectId = JsonPath.parse(response).read("$.id");

        mockMvc.perform(get("/resume/" + RESUME_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projects[?(@.id == '" + projectId + "')]").exists());
    }

    @Test
    @DisplayName("Should verify that POST method does not work correctly")
    void project_creation_does_not_work() throws Exception {
        String request = """
                {
                  "title": "My Project Title",
                  "startDate": "2022-01-01",
                  "website": "https://example.com",
                  "repositoryURL": "https://github.com/my-username/my-repo",
                  "previewImageURL": "https://example.com/my-image.png"
                }
                """;

        mockMvc.perform(post("/resume/" + RESUME_ID + "/project")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("description"))
                .andExpect(jsonPath("$.errors[0].reason").value("this attribute is mandatory"));
    }

    @Test
    @DisplayName("Should verify that GET method retrieves the desired resource")
    void project_retrieving_one_works() throws Exception {
        mockMvc.perform(get("/project/" + PROJECT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Should verify that the path variable is not found for GET method")
    void project_retrieving_one_does_not_work() throws Exception {
        mockMvc.perform(get("/project/" + -1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Project Not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("The project with ID -1 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(-1));
    }

    @Test
    @DisplayName("Should verify that GET method retrieves all the desired resources")
    void projects_retrieving_works() throws Exception {
        mockMvc.perform(get("/resume/" + RESUME_ID + "/project"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Should verify that PUT method updates the desired resource")
    void project_updating_works() throws Exception {
        String request = """
                {
                  "title": "My Project Title",
                  "description": "The description is not available yet",
                  "startDate": "2022-01-01",
                  "website": "https://example.com",
                  "repositoryURL": "https://github.com/my-username/my-repo",
                  "previewImageURL": "https://example.com/my-image.png"
                }
                """;

        mockMvc.perform(put("/project/" + PROJECT_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @DisplayName("Should verify that the request body infringe the constrains for PUT method")
    void project_updating_does_not_work() throws Exception {
        String request = """
                {
                  "title": "My",
                  "description": "The description is not available yet",
                  "startDate": "2022-01-01",
                  "website": "https://example.com",
                  "repositoryURL": "https://github.com/my-username/my-repo",
                  "previewImageURL": "https://example.com/my-image.png"
                }
                """;

        mockMvc.perform(put("/project/" + PROJECT_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/validation-error"))
                .andExpect(jsonPath("$.title").value("Invalid Request Body"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"))
                .andExpect(jsonPath("$.errors[0].name").value("title"))
                .andExpect(jsonPath("$.errors[0].reason").value("this value must be between 4 and 45 characters"));
    }

    @Test
    @DisplayName("Should verify that DELETE method works correctly")
    void project_deleting_works() throws Exception {
        mockMvc.perform(delete("/project/" + (PROJECT_ID + 1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should verify that the path variable is not found for DELETE method")
    void project_deleting_does_not_work() throws Exception {
        mockMvc.perform(delete("/project/" + -1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("https://example.com/error/resource-not-found"))
                .andExpect(jsonPath("$.title").value("Project Not found"))
                .andExpect(jsonPath("$.detail").value("The project with ID -1 cannot be found in the database"))
                .andExpect(jsonPath("$.id").value(-1));
    }
}