package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.GameDto;
import pl.coderslab.app.entities.Game;

import pl.coderslab.app.entities.Pitch;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.GameRepository;
import pl.coderslab.app.repositories.PitchRepository;

import javax.servlet.http.HttpSession;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;


@Controller
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PitchRepository pitchRepository;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createGame (HttpSession session, Model model){
        if(session.getAttribute("user") == null){
            return "needSignIn";
        }
        model.addAttribute("game", new GameDto());
        return "createGame";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String saveGame(@ModelAttribute(name = "game") GameDto gameDto,
                           HttpSession session){
        Game game = new Game();
        game.setCreator((User)session.getAttribute("user"));
        game.setDescription(gameDto.getDescription());
        game.setMaxPlayer(gameDto.getMaxPlayers());
        game.setPitch(pitchRepository.findFirstByName(gameDto.getPitch()));
        game.setPricePerPlayer(gameDto.getPricePerPlayer());
        game.setGameTime(gameDto.getGameTime());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:m");
        game.setGameDate(LocalDate.parse(gameDto.getGameDate(), dateFormatter));
        game.setStartTime(LocalTime.parse(gameDto.getStartTime(), timeFormatter));
        game.getPlayers().add((User)session.getAttribute("user"));
        game.setAvailable(true);
        gameRepository.save(game);
        return "redirect: /game/all";
    }

    @RequestMapping(path = "/join/{id}")
    public String joinTheGame(@PathVariable Long id, HttpSession session){
        if(session.getAttribute("user") == null){
            return "needSignIn";
        }
        Game game = gameRepository.findFirstById(id);
        User user = (User)session.getAttribute("user");
        game.getPlayers().add(user);
        game.setAvailable(true);
        gameRepository.save(game);
        return "joiningSuccess";
    }


    @RequestMapping(path = "/all")
    public String findAll(){
        return "allGame";
    }


    @ModelAttribute("games")
    public Collection<Game> getGames() {
           return gameRepository.findAll();
    }

    @ModelAttribute("pitches")
    public Collection<String> getPitches(){
        return pitchRepository.findAll().stream().map(x->x.getName()).collect(Collectors.toList());
    }




}
