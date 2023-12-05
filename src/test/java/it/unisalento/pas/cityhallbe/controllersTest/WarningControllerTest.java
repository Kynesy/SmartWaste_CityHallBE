package it.unisalento.pas.cityhallbe.controllersTest;

import com.nimbusds.jose.shaded.gson.Gson;
import it.unisalento.pas.cityhallbe.domains.Warning;
import it.unisalento.pas.cityhallbe.dto.WarningDTO;
import it.unisalento.pas.cityhallbe.services.IWarningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WarningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IWarningService warningService;

    @Test
    void createWarningTest_Success() throws Exception {
        WarningDTO warningDTO = new WarningDTO();
        warningDTO.setId("mockID");
        Gson gson = new Gson();
        String json = gson.toJson(warningDTO);

        when(warningService.createWarning(any(Warning.class))).thenReturn(0);

        mockMvc.perform(post("/api/warning/create")
                        .contentType("application/json")
                        .content(json)
                        .with(user("admin").authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Warning created\"}"));
    }

    @Test
    void createWarningTest_Failure() throws Exception {
        WarningDTO warningDTO = new WarningDTO();
        warningDTO.setId("mockID");
        Gson gson = new Gson();
        String json = gson.toJson(warningDTO);

        when(warningService.createWarning(any(Warning.class))).thenReturn(1);

        mockMvc.perform(post("/api/warning/create")
                        .contentType("application/json")
                        .content(json)
                        .with(user("admin").authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteWarningTest_Success() throws Exception {
        String warningID = "mockID";
        when(warningService.deleteWarning(warningID)).thenReturn(0);

        mockMvc.perform(delete("/api/warning/delete/{warningID}", warningID)
                        .with(user("user").authorities(new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"Warning deleted\"}"));
    }

    @Test
    void deleteWarningTest_Failure() throws Exception {
        String warningID = "mockID";
        when(warningService.deleteWarning(warningID)).thenReturn(1);

        mockMvc.perform(delete("/api/warning/delete/{warningID}", warningID)
                        .with(user("admin").authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getAllUserWarningTest_Success() throws Exception {
        String userID = "mockID";
        ArrayList<Warning> warningList = new ArrayList<>();
        Warning warning = new Warning();
        warning.setId("1");
        warning.setUserId(userID);
        warning.setMessage("Test Warning");
        warningList.add(warning);

        when(warningService.getAllByUser(userID)).thenReturn(warningList);

        mockMvc.perform(get("/api/warning/get/user/{userID}", userID)
                        .with(user("user").authorities(new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].userId").value(userID))
                .andExpect(jsonPath("$[0].message").value("Test Warning"));
    }
}
