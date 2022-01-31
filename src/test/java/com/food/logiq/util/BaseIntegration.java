package com.food.logiq.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


public class BaseIntegration {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    protected ResultActions jsonGet(String url) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print());
    }

    protected ResultActions jsonPost(String url, Object entity) throws Exception {
        return
                this.mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity)))
                        .andDo(MockMvcResultHandlers.print());
    }
    protected ResultActions jsonDelete(String url, Object entity) throws Exception {
        return
                this.mockMvc.perform(MockMvcRequestBuilders
                        .delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity)))
                        .andDo(MockMvcResultHandlers.print());
    }
}
