package com.tfm.sgved.controller;

import com.tfm.sgved.model.*;
import com.tfm.sgved.service.ParticipantService;
import com.tfm.sgved.service.ResultService;
import com.tfm.sgved.service.SurveyService;
import com.tfm.sgved.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@SessionAttributes("survey")
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    SurveyService surveyService;
    @Autowired
    ParticipantService participantService;
    @Autowired
    ResultService resultService;

    @GetMapping({"/", "login"})
    public String showLogin(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping({"/", "login"})
    public String logUser(User user, BindingResult br, Model model){
        User logged = userService.findUserByEmail(user.getEmail());
        if(logged != null) {
            if(user.getPassword().equals(logged.getPassword())) return "redirect:/admin";
            else {
                model.addAttribute("mensaje","La contraseña no es correcta.");
                return "login";
            }
        }
        else {
            model.addAttribute("mensaje","La cuenta introducida no existe.");
            return "login";
        }
    }
    @GetMapping("/admin")
    public String showAdmin(Model model){
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("surveys",surveyService.getAllSurveys());
        return "admin";
    }
    @GetMapping("/survey/add")
    public String createSurvey(Model model){

        return "create_survey";
    }
    @GetMapping("/survey/add_question")
    public String createQuestion(Model model){
        QuestionWrapper question = new QuestionWrapper();
        model.addAttribute("question",question);
        return "create_question";
    }
    @PostMapping("/survey/add_question")
    public String saveQuestion(@ModelAttribute("question") QuestionWrapper question, Model model){
        System.out.println("Question final: "+question);
        return "create_question";
    }
    @GetMapping("/survey/{id}")
    public String doSurvey(@PathVariable("id") int id, Model model){
        Survey survey = surveyService.getSurveyById(id);
        ResultWrapper result = new ResultWrapper();

        model.addAttribute("survey",survey);
        model.addAttribute("result",result);
        return "survey";
    }
    @PostMapping("/survey/{id}")
    public String confirmSurvey(@PathVariable("id") int id, @ModelAttribute("result") ResultWrapper result, Model model){
        System.out.println("Survey: "+id);

        result.getResultados().forEach(results -> {
            results.setSurvey(id);
            resultService.save(results);
        });
        Participant data = participantService.findByDniAndSurvey(result.getDni(),id);
        data.setFilled(true);
        data.setDateFilled(new Date());
        System.out.println("Resultado despues: "+result);
        System.out.println("Data final para insertar: "+data);
        return "survey";
    }
}
