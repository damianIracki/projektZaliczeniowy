package pl.coderslab.app.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.app.dto.UserDto;
import pl.coderslab.app.entities.Game;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.CandidateRepository;
import pl.coderslab.app.repositories.GameRepository;
import pl.coderslab.app.repositories.UserRepository;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping(path = "/test")
    @ResponseBody
    public String test(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Game> games = gameRepository.findFirst5ByPlayersNotContainsAndGameDateAfterOrderByGameDateAsc(user, LocalDate.now());
        return games.stream().map(Game::toString).collect(Collectors.joining("<br>"));
    }

    @RequestMapping(path = "/desktop", method = RequestMethod.GET)
    public String userDesktop(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "needSignIn";
        }
        model.addAttribute("user", user);
        //List<Game> games = gameRepository.findFirst5ByPlayersNotContainsOrderByGameDateAsc(user);
        List<Game> games = gameRepository.findFirst5ByPlayersNotContainsAndGameDateAfterOrderByGameDateAsc(user, LocalDate.now());
        model.addAttribute("games", games);
        return "userDesktop";
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
            return "/user/editUser";
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
        return "/user/editUser";
    }

    @RequestMapping(path = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model, HttpSession session){
        User userInSession = (User)session.getAttribute("user");
        if(userInSession!=null) {
            UserDto user = new UserDto();
            user.setUserName(userInSession.getUserName());
            user.setEmail(userInSession.getEmail());
            model.addAttribute("user", user);
            return "/user/changePasswordUser";
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

    @ModelAttribute("games")
    public Collection<Game> getFiveGames(){
        return gameRepository.findFirst5ByOrderByGameDateAsc();
    }


}
