package com.expenseTracker.controller;

import com.expenseTracker.domain.User;
import com.expenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public ModelAndView userList() {
        List<User> users = userService.findAllUsers();
        Map<String, List<User>> userListModel = new HashMap<String, List<User>>();
        userListModel.put("users", users);

        return new ModelAndView("users", userListModel);
    }
}
