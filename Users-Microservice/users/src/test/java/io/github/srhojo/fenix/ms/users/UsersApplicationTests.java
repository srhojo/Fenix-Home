package io.github.srhojo.fenix.ms.users;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class UsersApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextInit() {
        assertTrue(Boolean.TRUE);
    }

}
