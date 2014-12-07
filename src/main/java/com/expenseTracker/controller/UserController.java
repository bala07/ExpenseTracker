package com.expenseTracker.controller;

import com.expenseTracker.domain.User;
import com.expenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/{expenseSheetId}/users")
    public ModelAndView userList(@PathVariable final int expenseSheetId) {
        List<User> users = userService.getUsersOfExpenseSheet(expenseSheetId);
        Map<String, List<User>> userListModel = new HashMap<String, List<User>>();
        userListModel.put("users", users);

        return new ModelAndView("user_list", userListModel);
    }
}
