package com.syj.jspexample.users;

import com.syj.jspexample.app.entity.Boards;
import com.syj.jspexample.app.entity.Categories;
import com.syj.jspexample.app.entity.Users;
import com.syj.jspexample.app.repository.BoardsRepository;
import com.syj.jspexample.app.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardsTest {

    @Autowired
    private BoardsRepository boardsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception {
        Users users = new Users();
        users.setEmail("nmrhtn7777@naver.com");
        users.setPassword("1234");
        usersRepository.save(users);

        Categories categories = new Categories();
        categories.setName("freeboards");

        mockMvc
                .perform(get("/freeboards/list")
                .queryParam("category", "freeboards"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }


}
