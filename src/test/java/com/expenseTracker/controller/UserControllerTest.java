package com.expenseTracker.controller;

import com.expenseTracker.domain.User;
import com.expenseTracker.service.UserService;
import org.mockito.Mock;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertTrue;

public class UserControllerTest {
    private UserController userController;
    @Mock
    private UserService userService;
    
    @BeforeMethod
    public void setup() {
        initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void shouldPopulateUserListInViewModel() {
        List<User> users = newArrayList();
        when(userService.getUsersOfExpenseSheet(1)).thenReturn(users);

        ModelAndView modelAndView = userController.userList(1);

        assertThat(modelAndView.getViewName(), is("user_list"));
        assertThat(modelAndView.getModel().get("users"), is(users));
    }

    @Test
    public void shouldHaveCorrectRequestUrlMapping() throws NoSuchMethodException {
        Method method = UserController.class.getMethod("userList", Integer.class);
        Annotation[] annotations = method.getAnnotations();

        assertThat(annotations.length, is(1));
        assertTrue(annotations[0] instanceof RequestMapping, "Annotation should be of type RequestMapping");
        RequestMapping annotation = (RequestMapping) annotations[0];
        assertThat(annotation.value().length, is(1));
        assertThat(annotation.value()[0], is("/{expenseSheetId}/users"));
    }
}