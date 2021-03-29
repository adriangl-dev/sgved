package com.tfm.sgved.controller;

import com.tfm.sgved.model.*;
import com.tfm.sgved.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    SurveyService surveyService;
    @Autowired
    ParticipantService participantService;
    @Autowired
    ResultService resultService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    //BLOQUE DE LOGIN
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

    //BLOQUE DE ADMIN
    @GetMapping("/admin")
    public String showAdmin(Model model){
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("surveys",surveyService.getAllSurveys());

        //PRUEBAS STATS
        HashMap<String,String> map = resultService.stats(34);
        AtomicInteger nTotal = new AtomicInteger(0);
        Survey surv = surveyService.getSurveyById(34);

        surv.getQuestions().forEach(q -> {
            q.getAnswers().forEach(a -> {
                if(map.containsKey(String.valueOf(a.getId()))) a.setTotal(map.get(String.valueOf(a.getId())));
                if(a.getTotal() != null) nTotal.set(Integer.parseInt(a.getTotal())+nTotal.get());
                System.out.println("Respuesta "+a.getText()+ " con id "+a.getId()+" tiene respuestas: "+a.getTotal());
            });
        });
        System.out.println("ntotal: "+nTotal.get());

        //FIN PRUEBA STATS

        return "admin";
    }

    //CREAR NUEVA ENCUESTA
    @GetMapping("/survey/add")
    public String createSurvey(Model model){
        Survey survey = new Survey();
        model.addAttribute("survey",survey);

        return "create_survey";
    }
    @PostMapping("/survey/add")
    public String saveSurvey(@ModelAttribute("survey") Survey survey, BindingResult br, Model model){
        System.out.println(("Survey final: ")+survey.getTitle());
        surveyService.saveOrUpdate(survey);
        return "create_survey";
    }

    //CREAR NUEVA PREGUNTA-RESPUESTA
    @GetMapping("/survey/{id}/add_question")
    public String createQuestion(@PathVariable("id") int id, Model model){
        Question question = new Question();
        Survey survey = surveyService.getSurveyById(id);
        model.addAttribute("survey",survey);
        model.addAttribute("question",question);
        return "create_question";
    }
    @PostMapping("/survey/{id}/add_question")
    public String saveQuestion(@PathVariable("id") int id, @ModelAttribute("question") Question question, Model model){
        question.setNsurvey(id);
        question.setType("TIPO 1");
        Survey survey = surveyService.getSurveyById(id);
        Question saved = questionService.saveOrUpdate(question);
        question.getAnswers().forEach(a -> {
            a.setNquestion(saved.getId());
            answerService.saveOrUpdate(a);
        });
        question = new Question();
        model.addAttribute("question",question);
        model.addAttribute("survey",survey);
        return "create_question";
    }

    //RELLENAR ENCUESTA (USUARIOS)
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