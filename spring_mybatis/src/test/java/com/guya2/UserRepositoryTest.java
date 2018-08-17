package com.guya2;

import com.guya2.generate.common.domain.User;
import com.guya2.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsertUser() {
        User user = new User();
        String text = "123";
        user.setAge(20);
        user.setId(1L);
        user.setUsername("zyl");
        user.setPassword("zzz");
        user.setUsername(text);
        userRepository.insert(user);
        User shouldBeEqual = userRepository.selectByPrimaryKey(user.getId());
        Assert.assertEquals(user.getAge(), shouldBeEqual.getAge());
        Assert.assertEquals(user.getUsername(), shouldBeEqual.getUsername());
        Assert.assertEquals(user.getPassword(), shouldBeEqual.getPassword());
    }
}
