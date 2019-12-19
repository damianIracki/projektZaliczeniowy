package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.GameDto;
import pl.coderslab.app.entities.Candidate;
import pl.coderslab.app.entities.Game;

import pl.coderslab.app.entities.Pitch;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.CandidateRepository;
import pl.coderslab.app.repositories.GameRepository;
import pl.coderslab.app.repositories.PitchRepository;

import javax.servlet.http.HttpSession;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PitchRepository pitchRepository;

    @Autowired
    private CandidateRepository candidateRepository;

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
        return "redirect: /game/myGames";
    }

    @RequestMapping(path = "/join/{id}")
    public String joinTheGame(@PathVariable Long id, HttpSession session){
        if(session.getAttribute("user") == null){
            return "needSignIn";
        }

        Game game = gameRepository.findFirstById(id);
        User user = (User)session.getAttribute("user");
        if(candidateRepository.findFirstByUserAndGame(user, game) != null){
            return "alreadyJoined";
        }

        Candidate candidate = new Candidate();
        candidate.setUser(user);
        candidate.setJoinedDateTime(LocalDateTime.now());
        candidate.setGame(game);
        candidateRepository.save(candidate);
        return "joiningSuccess";
    }

    @RequestMapping(path = "/acceptCandidate/{id}")
    public String acceptCandidateToGame(@PathVariable Long id){

        return "ToDo";
    }

    @RequestMapping(path = "/candidates/{gameId}")
    public String showCandidates(Model model, HttpSession session, @PathVariable Long gameId){
        Game game = gameRepository.findFirstById(gameId);
        List<Candidate> candidates = candidateRepository.findAllByGameOrderByJoinedDateTimeAsc(game);
        model.addAttribute("candidates", candidates);
        model.addAttribute("game", game);
        return "game/candidatesToGame";


    }

    @RequestMapping(path = "/myGames")
    public String showMyGames(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Game> myGames = gameRepository.findByCreatorOrderByGameDateAscStartTimeAsc(user);
        model.addAttribute("myGames", myGames);
        return "user/asCreatorGames";
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
