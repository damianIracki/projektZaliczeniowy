package pl.coderslab.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.app.dto.PitchDto;
import pl.coderslab.app.entities.Pitch;
import pl.coderslab.app.repositories.PitchRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/pitch")
public class PitchController {

    @Autowired
    PitchRepository pitchRepository;


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addPitch(HttpSession session, Model model){
        model.addAttribute("pitch", new PitchDto());
        return "addPitch";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String savePitch(HttpSession session, @ModelAttribute("pitch") PitchDto pitchDto){
        Pitch pitch = new Pitch();
        pitch.setAddress(pitchDto.getAddress());
        pitch.setName(pitchDto.getName());
        pitch.setType(pitchDto.getType());
        pitchRepository.save(pitch);
        return "redirect: /pitch/list";
    }

    @RequestMapping(path = "/list")
    public String pitchList(Model model){
        List<Pitch> pitches = pitchRepository.findAll();
        model.addAttribute("pitches", pitches);
        return "allPitch";
    }

    @RequestMapping(path = "/{id}")
    public String showPitch(@PathVariable Long id, Model model){
        Pitch pitch = pitchRepository.findFirstById(id);
        model.addAttribute("pitch", pitch);
        return "pitchDetails";
    }
}

