package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Саша on 27.08.2016.
 */
@Controller
public class UserController {
    private UserService userService;



    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users{filter}", method = RequestMethod.GET)
    public String listUsers(@RequestParam("filter") String filter, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers(filter));
        return "/users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers( Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers(""));
        return "/users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if (user.getId() == 0){
            userService.addUser(user);
        }else userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUsers(""));
        return "/users";
    }

    @RequestMapping("/fill")
    public String fillTasks() {
        userService.fillUsers();

        return "redirect:/users";
    }
}
