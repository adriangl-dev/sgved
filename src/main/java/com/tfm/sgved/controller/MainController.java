package com.tfm.sgved.controller;

import com.tfm.sgved.model.Result;
import com.tfm.sgved.model.ResultWrapper;
import com.tfm.sgved.model.Survey;
import com.tfm.sgved.model.User;
import com.tfm.sgved.service.QuestionService;
import com.tfm.sgved.service.SurveyService;
import com.tfm.sgved.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;

@Controller
@SessionAttributes("survey")
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    SurveyService surveyService;
    @Autowired
    QuestionService questionService;

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
        System.out.println("Usuario: "+userService.findUserByEmail("adriangl@outlook.com"));
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("surveys",surveyService.getAllSurveys());
        return "admin";
    }
    @GetMapping("/survey/create")
    public String createSurvey(Model model){

        return "create_survey";
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
        System.out.println("Resultado despues: "+result);

       /* for(Result results : result.getResultados()){
            results.setId_survey(id);
            resultService.save(results);
        }*/

        return "admin";
    }
}
