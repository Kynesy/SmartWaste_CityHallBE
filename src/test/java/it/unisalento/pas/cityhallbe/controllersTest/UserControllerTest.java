package it.unisalento.pas.cityhallbe.controllersTest;

import com.nimbusds.jose.shaded.gson.Gson;
import it.unisalento.pas.cityhallbe.configurations.SecurityConstants;
import it.unisalento.pas.cityhallbe.controllers.UserController;
import it.unisalento.pas.cityhallbe.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import it.unisalento.pas.cityhallbe.domains.User;
import it.unisalento.pas.cityhallbe.dto.UserDTO;
import it.unisalento.pas.cityhallbe.services.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    

    @Test
    void existUserTest_UserExists() throws Exception {
        String userID = "mockID";
        when(userService.existUser(userID)).thenReturn(1);

        mockMvc.perform(get("/api/user/exist/{userID}", userID)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


    @Test
    void existUserTest_UserDoesNotExist() throws Exception {
        String userID = "mockID";
        when(userService.existUser(userID)).thenReturn(0);

        mockMvc.perform(get("/api/user/exist/{userID}", userID)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isNotFound())
                .andExpect(content().string("false"));
    }

    @Test
    void createUserTest_Success() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("mockID");
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);

        when(userService.createUser(any(User.class))).thenReturn(1);

        mockMvc.perform(post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"User created successfully\"}"));
    }

    @Test
    void createUserTest_Failure() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("mockID");
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);

        when(userService.createUser(any(User.class))).thenReturn(0);

        mockMvc.perform(post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"message\": \"User creation failed\"}"));
    }

    @Test
    void updateUserTest_Success() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("mockID");
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);

        when(userService.updateUser(any(User.class))).thenReturn(1);

        mockMvc.perform(post("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"User updated successfully\"}"));
    }

    @Test
    void updateUserTest_Failure() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("mockID");
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);

        when(userService.updateUser(any(User.class))).thenReturn(0);

        mockMvc.perform(post("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"message\": \"User update failed\"}"));
    }

    @Test
    void deleteUserTest_Success() throws Exception {
        String userID = "mockID";
        when(userService.deleteUser(userID)).thenReturn(1);

        mockMvc.perform(delete("/api/user/delete/{userID}", userID)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\": \"User deleted successfully\"}"));
    }

    @Test
    void deleteUserTest_Failure() throws Exception {
        String userID = "mockID";
        when(userService.deleteUser(userID)).thenReturn(0);

        mockMvc.perform(delete("/api/user/delete/{userID}", userID)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"message\": \"User deletion failed\"}"));
    }

    @Test
    void getUserTest_UserExists() throws Exception {
        String userID = "mockID";
        User user = new User();
        user.setId(userID);
        when(userService.findByID(userID)).thenReturn(user);

        mockMvc.perform(get("/api/user/get/{userID}", userID)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isOk());
    }

    @Test
    void getUserTest_UserDoesNotExist() throws Exception {
        String userID = "mockID";
        when(userService.findByID(userID)).thenReturn(null);

        mockMvc.perform(get("/api/user/get/{userID}", userID)
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllIdTest() throws Exception {
        ArrayList<String> userIdList = new ArrayList<>();
        userIdList.add("mockID");
        when(userService.getAllIdList()).thenReturn(userIdList);

        mockMvc.perform(get("/api/user/id/all")
                        .with(user("admin").authorities(new SimpleGrantedAuthority(SecurityConstants.ADMIN_ROLE_ID))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("mockID"));
    }
}
