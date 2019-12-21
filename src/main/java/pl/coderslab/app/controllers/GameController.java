package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dto.GameDto;
import pl.coderslab.app.entities.Candidate;
import pl.coderslab.app.entities.Game;

import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.CandidateRepository;
import pl.coderslab.app.repositories.GameRepository;
import pl.coderslab.app.repositories.PitchRepository;
import pl.coderslab.app.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createGame (HttpSession session, Model model){
        if(session.getAttribute("user") == null){
            return "needSignIn";
        }
        model.addAttribute("game", new GameDto());
        return "game/createGame";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String saveGame(@ModelAttribute(name = "game") @Valid GameDto gameDto,
                           HttpSession session, BindingResult result){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "needSignIn";
        }
        if(result.hasErrors()){
            return "game/createGame";
        }
        Game game = new Game();
        game.setCreator(user);
        game.setDescription(gameDto.getDescription());
        game.setMaxPlayer(gameDto.getMaxPlayers());
        game.setPitch(pitchRepository.findFirstByName(gameDto.getPitch()));
        game.setPricePerPlayer(gameDto.getPricePerPlayer());
        game.setGameTime(gameDto.getGameTime());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:m");
        game.setGameDate(LocalDate.parse(gameDto.getGameDate(), dateFormatter));
        game.setStartTime(LocalTime.parse(gameDto.getStartTime(), timeFormatter));
        User userForPlayers = userRepository.findFirstById(user.getId());
        game.getPlayers().add(userForPlayers);
        game.checkAvailable(true);
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

    @RequestMapping(path = "candidate/accept/{candidateId}")
    public String acceptCandidateToGame(@PathVariable Long candidateId, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "needSignIn";
        }
        Candidate candidate = candidateRepository.findFirstById(candidateId);
        if(candidate == null){
            return "uDontHavePermission";
        }
        User player = userRepository.findFirstById(candidate.getUser().getId());
        Game game = gameRepository.findFirstById(candidate.getGameId());
        if(user.getId()!= game.getCreator().getId()){
            return "uAreNotCreator";
        }
        game.getPlayers().add(player);
        game.checkAvailable(true);
        model.addAttribute("player", player);
        model.addAttribute("game", game);
        gameRepository.save(game);
        candidateRepository.delete(candidate);
        return "game/playerAccepted";
    }

    @RequestMapping(path = "/candidates/{gameId}")
    public String showCandidates(Model model, HttpSession session, @PathVariable Long gameId){
        Game game = gameRepository.findFirstById(gameId);
        List<Candidate> candidates = candidateRepository.findAllByGameOrderByJoinedDateTimeAsc(game);
        model.addAttribute("candidates", candidates);
        model.addAttribute("game", game);
        return "game/candidatesToGame";
    }

    @RequestMapping(path = "/players/{gameId}")
    public String showPlayers(Model model, HttpSession session, @PathVariable Long gameId){
        Game game = gameRepository.findFirstById(gameId);
        List<User> players = game.getPlayers();
        model.addAttribute("players", players);
        model.addAttribute("game", game);
        return "game/playersToGame";
    }
    @RequestMapping(path = "/myGames")
    public String showMyGames(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Game> myGames = gameRepository.findByCreatorOrderByGameDateAscStartTimeAsc(user);
        model.addAttribute("myGames", myGames);
        return "user/asCreatorGames";
    }

    @RequestMapping(path = "/gamesToJoin")
    public String showGamesToJoin(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "needSignIn";
        }
        List<Game> gamesToJoined = gameRepository.findByPlayersNotContainsAndGameDateAfterOrderByGameDateAscStartTimeAsc(user, LocalDate.now());
        List<Game> gamesAlreadyJoined = new ArrayList<>();
        List<Candidate> userCandidates = candidateRepository.findAllByUser(user);
        for (Candidate userCandidate : userCandidates) {
            Game userGame = gameRepository.findFirstById(userCandidate.getGameId());
            for (Game game : gamesToJoined) {
                if(game.getId() == userGame.getId()){
                    gamesToJoined.remove(game);
                    gamesAlreadyJoined.add(game);
                    break;
                }
            }
        }
        model.addAttribute("gamesToJoined", gamesToJoined);
        model.addAttribute("gamesAlreadyJoined", gamesAlreadyJoined);
        return "game/gamesToJoin";
    }

    @RequestMapping(path = "cancel/{gameId}")
    public String cancelGame (@PathVariable Long gameId, HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "needSignIn";
        }
        Game game = gameRepository.findFirstById(gameId);
        if(user.getId() != game.getCreator().getId()){
            return "uAreNotCreator";
        }
        model.addAttribute("game", game);
        List<Candidate> candidatesToDelete = candidateRepository.findAllByGame(game);
        for (Candidate candidate : candidatesToDelete) {
            candidateRepository.delete(candidate);
        }
        gameRepository.delete(game);
        return "successfullyCanceled";
    }

    @RequestMapping(path = "/acceptedGames")
    public String showAcceptedGames(HttpSession session, Model model){
        User user =(User) session.getAttribute("user");
        if(user == null){
            return "needSignIn";
        }
        List<Game> gamesWhereUserArePlayer = gameRepository.findByPlayersContainsAndGameDateAfterOrderByGameDateAscGameTimeAsc(user, LocalDate.now());
        model.addAttribute("games", gamesWhereUserArePlayer);
        return "game/gamesAccepted";
    }

/*    @RequestMapping(path = "/leaveGame/{gameId}")
    public String leaveGameByUser(HttpSession session, Model model, @PathVariable Long gameId){
        User user =(User) session.getAttribute("user");
        if(user == null){
            return "needSignIn";
        }
        Game game = gameRepository.findFirstById(gameId);
        List<User> players = game.getPlayers();
        for (User player: players){
            if(player.getId() == user.getId()){
                game.getPlayers().remove(player);
            }
        }
        model.addAttribute("game", game);
        gameRepository.save(game);
        return "game/leavingTheGame";
    }*/

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
