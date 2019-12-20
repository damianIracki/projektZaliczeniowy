package pl.coderslab.app.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.app.dto.UserDto;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AccountController {

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
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return "redirect: /";
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
            if(user.getStatus()==9){
                return "redirect: /admin/desktop";
            } else if (user.getStatus() == 8){
                return "userBanned";
            }
            return "redirect: /user/desktop";
        }
        return "wrongPassword";
    }

    @RequestMapping(path= "/logout", method = RequestMethod.GET)
    public String userLogout(HttpSession session){
        session.removeAttribute("user");
        return "logout";
    }
}
