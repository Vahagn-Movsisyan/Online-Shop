package com.example.onlineshope.controller;

import com.example.onlineshope.entity.User;
import com.example.onlineshope.entity.UserRole;
import com.example.onlineshope.exceptions.EmailIsPresentException;
import com.example.onlineshope.exceptions.PasswordNotMuchException;
import com.example.onlineshope.security.SpringUser;
import com.example.onlineshope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login/page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register/page")
    public String registerPage(@RequestParam(value = "msg", required = false) String msg, ModelMap modelMap) {
        if (msg != null && !msg.isEmpty()) {
            modelMap.addAttribute("msg", msg);
        }
        return "register";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal SpringUser springUser) {
        if (springUser.getUser().getUserRole() == UserRole.ADMIN) {
            return "admin/adminHome";
        } else if (springUser.getUser().getUserRole() == UserRole.USER) {
            return "user/userHome";
        }
        return "redirect:/login/page";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute User user,
            @RequestParam String confirmPassword,
            @RequestParam("picture") MultipartFile multipartFile) {

        try {
            userService.register(user, confirmPassword, multipartFile);
        } catch (IOException e) {
            return "redirect:/user/login?msg=Invalid picture please try again";
        } catch (EmailIsPresentException e) {
            return "redirect:/register/page?msg=Email is already in use";
        } catch (PasswordNotMuchException e) {
            return "redirect:/register/page?msg=Passwords do not match";
        }
        return "redirect:/";
    }
}
