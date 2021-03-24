package com.tfm.sgved.controller;

import com.tfm.sgved.model.Survey;
import com.tfm.sgved.model.User;
import com.tfm.sgved.service.QuestionService;
import com.tfm.sgved.service.SurveyService;
import com.tfm.sgved.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("surveys",surveyService.getAllSurveys());
        return "admin";
    }
    @GetMapping("/survey/create")
    public String createSurvey(Model model){

        return "create_survey";
    }
    @GetMapping("/survey/do")
    public String doSurvey(Model model){
        Survey survey = surveyService.getSurveyById(34);
        model.addAttribute("survey",survey);
        return "survey";
    }
    @PostMapping("/survey/do")
    public String confirmSurvey(Survey survey, BindingResult br, Model model,SessionStatus status){
        System.out.println("Resultado survey: "+survey);
        status.setComplete();
        return "survey";
    }
}
