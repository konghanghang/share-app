package com.ysla.web.exception;

import com.ysla.web.controller.article.ArticleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class GlobalExceptionHandlerTest {

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new ArticleController()).build();
    }

    @Test
    public void noHandler() throws Exception {
        RequestBuilder requestBuilder = get("/api/article11")
                .contentType(MediaType.APPLICATION_JSON);
        String rs = mvc.perform(requestBuilder)
                .andExpect(status().is(404))
                .andReturn()
                .getResponse().getContentAsString();
        System.out.println(rs);
    }

}