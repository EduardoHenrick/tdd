package mentoring.study.tdd.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarTaskComSucesso() throws Exception {

        String json = """
            {
                "title": "Estudar TDD"
            }
        """;

        mockMvc.perform(post("/tasks")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Estudar TDD"));
    }
}