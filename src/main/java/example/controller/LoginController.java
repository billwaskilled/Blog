package example.controller;

import example.service.LoginCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String Login() {
        return "login";
    }

    @RequestMapping(value = "/login-action")
    public String Login(HttpServletRequest  request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isPassed = LoginCheck.check(username, password);
        if (isPassed) {
            return "login_success";
        } else {
            return "login_fail";
        }
    }
}
