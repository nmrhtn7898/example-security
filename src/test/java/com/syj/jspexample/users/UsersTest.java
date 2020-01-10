package com.syj.jspexample.users;

import com.syj.jspexample.app.entity.Users;
import com.syj.jspexample.app.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void available() throws Exception {
        Users users = new Users();
        users.setEmail("nmrhtn7777@naver.com");
        users.setPassword("1234");
        usersRepository.save(users);
        mockMvc
                .perform(get("/api/users/available")
                .accept(MediaType.APPLICATION_JSON)
                .queryParam("email", "nmrhtn7777@naver.com"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().string("false"));
    }

}
