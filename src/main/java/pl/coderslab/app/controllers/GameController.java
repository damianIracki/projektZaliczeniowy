package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.GameDto;
import pl.coderslab.app.entities.Game;

import pl.coderslab.app.repositories.GameRepository;

import javax.servlet.http.HttpSession;

import java.util.Collection;


@Controller
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createGame (HttpSession session, Model model){
        //User user = (User) session.getAttribute("user");
//        if(user == null){
//            return "needSignIn";
//        }
       model.addAttribute("game", new GameDto());
       return "createGame";
       }


       @RequestMapping(path = "/create", method = RequestMethod.POST)
       @ResponseBody
       public String saveGame(@RequestParam String gameDate,
                              @ModelAttribute(name = "game") GameDto game){
        return gameDate;
       }


    @RequestMapping(path = "/all")
    public String findAll(){
        return "allGame";
    }


    @ModelAttribute("games")
    public Collection<Game> getGames() {
           return gameRepository.findAll();
       }
    
}
