package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.entities.Game;
import pl.coderslab.app.entities.Pitch;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.GameRepository;
import pl.coderslab.app.repositories.PitchRepository;
import pl.coderslab.app.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PitchRepository pitchRepository;

    @RequestMapping(path = "/desktop")
    public String adminDesktop(HttpSession session){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        return "admin/desktop";
    }

    @RequestMapping(path = "/allGames")
    public String showAllGames(Model model, HttpSession session){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        List<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "admin/gameList";
    }

    @RequestMapping(path = "/deleteGame/{id}")
    public String deleteGame(@PathVariable long id, HttpSession session){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        Game game = gameRepository.findFirstById(id);
        gameRepository.delete(game);
        return "redirect: /admin/allGames";
    }

    @RequestMapping(path = "/allPitches")
    public String showAllPitches(Model model, HttpSession session){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        List<Pitch> pitches = pitchRepository.findAll();
        model.addAttribute("pitches", pitches);
        return "admin/pitchList";
    }

    @RequestMapping(path = "/deletePitch/{id}")
    public String deletePitch(@PathVariable long id, HttpSession session){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        Pitch pitch = pitchRepository.findFirstById(id);
        pitchRepository.delete(pitch);
        return "redirect: /admin/allPitches";
    }

    @RequestMapping(path = "/allUsers")
    public String showAllUsers(Model model, HttpSession session){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/userList";
    }

    @RequestMapping(path = "/ban/{id}")
    public String banUser (Model model, HttpSession session, @PathVariable Long id){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        User user = userRepository.findFirstById(id);
        user.setStatus(8);
        userRepository.save(user);
        model.addAttribute("user", user);
        return "admin/switchedUserStatus";
    }

    @RequestMapping(path = "/unban/{id}")
    public String unbanUser (Model model, HttpSession session, @PathVariable Long id){
        if(!checkAdmin(session)){
            return "uDontHavePermission";
        }
        User user = userRepository.findFirstById(id);
        user.setStatus(0);
        userRepository.save(user);
        model.addAttribute("user", user);
        return "admin/switchedUserStatus";
    }

    public boolean checkAdmin(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null || user.getStatus() != 9){
            return false;
        }
        return true;
    }
}
