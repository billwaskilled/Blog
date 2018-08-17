package example.controller;

import example.service.RegisterCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "register-action")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");

        if(RegisterCheck.register(username, password, age)){
            return "register_success";
        }else{
            return "register_fail";
        }
    }
}
