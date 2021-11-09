package com.andyadc.samples.test;

import com.andyadc.samples.UserAccountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * https://www.baeldung.com/spring-valid-vs-validated
 * https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-mvc-3
 */
@SpringBootTest
public class UserAccountControllerTests {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserAccountController()).build();
    }

    @Test
    public void testSave() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/user-account/save")
                        .accept(MediaType.TEXT_HTML)
//                        .param("name", "adc")
//                        .param("password", "pwddwp")
//                        .param("phone", "13855556666")
                        .param("age", "9")
//                        .param("userAddress.countryCode", "shanghai")
//                        .param("userAddress.provinceCode", "changning")

        )
                .andExpect(MockMvcResultMatchers.view().name("success"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    public void testSaveBasicInfo() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/user-account/saveBasicInfo")
                        .accept(MediaType.TEXT_HTML)
                        .param("name", "adc")
                        .param("password", "pwddwp")

        )
                .andExpect(MockMvcResultMatchers.view().name("success"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    public void testSaveAdvanceInfo() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/user-account/saveAdvanceInfo")
                        .accept(MediaType.TEXT_HTML)
                        .param("name", "adc")
                        .param("password", "pwddwp")

        )
                .andExpect(MockMvcResultMatchers.view().name("success"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
        ;
    }
}
