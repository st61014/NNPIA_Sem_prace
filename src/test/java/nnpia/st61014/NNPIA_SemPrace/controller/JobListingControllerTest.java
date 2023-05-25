package nnpia.st61014.NNPIA_SemPrace.controller;

import nnpia.st61014.NNPIA_SemPrace.NnpiaSemPraceApplication;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import nnpia.st61014.NNPIA_SemPrace.service.JobListingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = NnpiaSemPraceApplication.class)
@AutoConfigureMockMvc
class JobListingControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private JobListingService service;

    @Test
    void findAll() throws Exception {
        List<JobListing> dataList = new ArrayList<>();

        dataList.add(new JobListing(1L, "field", "position", 2000.0, new AppUser(1L, "username", "password", "name", "surname", "field")));
        given(service.findAll(0, "pay")).willReturn(dataList);
        var performResult = mvc.perform(get("/job-listing/all?page=0&sort=pay")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("[{\"listingID\":1,\"jobField\":\"field\",\"position\":\"position\",\"pay\":2000.0}]"));


    }
}