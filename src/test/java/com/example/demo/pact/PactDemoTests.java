package com.example.demo.pact;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("spring-boot-demo-app-provider")
@Consumer("spring-boot-demo-app-consumer")
@PactBroker(host = "pact-broker", port = "9292")  // optional
public class PactDemoTests {
    @TestTarget
    public SpringBootHttpTarget target = new SpringBootHttpTarget();

    @MockBean
    private UserService userService;

    @BeforeClass
    public static void enablePublishingPact() {
        System.setProperty("pact.verifier.publishResults", "true");
    }

    @State("there is a user with a given id")
    public void pactGetUser() throws Exception {
        User user = new User();
        user.setAge(18);
        user.setId(18);
        user.setEmail("heldfslo@world.com");
        user.setUsername("loremipsum");

        Mockito.when(userService.getUserById(18)).thenReturn(user);
    }
}
