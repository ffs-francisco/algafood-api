package com.ffs.algafood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author francisco
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class ApplicationWebContextIntegrationTest {

    @Autowired(required = false)
    private MockMvc contextMockMvc;

    protected ResultActions get(final String url) throws Exception {
        final var httpGet = MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON);

        return this.contextMockMvc.perform(httpGet);
    }
}
