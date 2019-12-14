package pl.coderslab.app.controllers;

import com.sun.org.apache.regexp.internal.RE;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.webflow.context.servlet.HttpServletContextMap;
import pl.coderslab.app.dto.UserDto;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.UserRepository;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("user", new UserDto());
        return "addUser";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String saveUSer(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result){
        if(result.hasErrors()) {
            return "addUser";
        }
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setStatus(1);
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return "redirect: /";
    }

    @RequestMapping(path = "/all")
    public String showAll(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "allusers";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "userLogin";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(name = "userName") String userName, @RequestParam String userPassword, HttpSession session) {
        User user = userRepository.findFirstByUserName(userName);
        if(user != null && BCrypt.checkpw(userPassword, user.getPassword())){
            session.setAttribute("user", user);
            return "redirect: /user/desktop";
        }
        return "redirect: /user/login";
    }

    @RequestMapping(path = "/desktop", method = RequestMethod.GET)
    public String userDesktop(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "needSignIn";
        }
        model.addAttribute("user", user);
        return "userDesktop";
    }

    @RequestMapping(path= "/logout", method = RequestMethod.GET)
    public String userLogout(HttpSession session){
        session.removeAttribute("user");
        return "redirect: /";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editUser(HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        if(user != null) {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setUserName(user.getUserName());
            userDto.setPassword("123456");
            userDto.setRepeatedPassword("123456");
            model.addAttribute("user", userDto);
            return "editUser";
        }
        return "needSignIn";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute @Valid UserDto user, BindingResult result, @RequestParam String passwordTest, HttpSession session,
                             Model model) {
        User userInSession = (User) session.getAttribute("user");
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "redirect: /user/edit";
        }
        if (userInSession != null && BCrypt.checkpw(passwordTest, userInSession.getPassword())) {
            userInSession.setEmail(user.getEmail());
            userInSession.setUserName(user.getUserName());
            userRepository.save(userInSession);
            return "redirect: /user/desktop";
        }
        return "editUser";
    }

    @RequestMapping(path = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model, HttpSession session){
        User userInSession = (User)session.getAttribute("user");
        if(userInSession!=null) {
            UserDto user = new UserDto();
            user.setUserName(userInSession.getUserName());
            user.setEmail(userInSession.getEmail());
            model.addAttribute("user", user);
            return "changePasswordUser";
        }
        return "needSignIn";
    }

    @RequestMapping (path = "/changePassword", method = RequestMethod.POST)
    public String saveChangedPassword(@ModelAttribute("user") @Valid UserDto user, BindingResult result, @RequestParam String oldPassword, HttpSession session,
                                      Model model){
        User userInSession = (User) session.getAttribute("user");
        if(userInSession != null && BCrypt.checkpw(oldPassword, userInSession.getPassword())){
            if(result.hasErrors()){
                return "changePasswordUser";
            }
            userInSession.setPassword(user.getPassword());
            userRepository.save(userInSession);
            return "redirect: /user/desktop";
        }
        return "wrongPassword";
    }
}
