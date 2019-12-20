package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.entities.Game;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.GameRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(path = "/")
    public String homePage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if(user != null){
            if(user.getStatus() == 9){
                return "redirect: admin/desktop";
            }
            return "redirect: user/desktop";
        }
        List<Game> games = gameRepository.findFirst5ByGameDateAfterOrderByGameDateAsc(LocalDate.now());
        model.addAttribute("games", games);
        return "index";
    }
}
