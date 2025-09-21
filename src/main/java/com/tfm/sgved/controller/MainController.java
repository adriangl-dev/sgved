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
        model.addAttribute("surveys",surveyService.getAllSurveys());
        return "adminV2";
    }

    //MOSTRAR RESULTADOS
    @GetMapping("/admin/stats/{id}")
    @PostMapping("/admin/stats/{id}")
    public String showStats(@PathVariable("id") int id, Model model){
        HashMap<String,String> map = resultService.stats(id);
        Survey survey = surveyService.getSurveyById(id);

        survey.getQuestions().forEach(q -> {
            q.getAnswers().forEach(a -> {
                if(map.containsKey(String.valueOf(a.getId()))) a.setTotal(Integer.parseInt(map.get(String.valueOf(a.getId()))));
                if(a.getTotal() != 0) q.setRespuestasTotales(q.getRespuestasTotales()+a.getTotal());
            });
        });

        model.addAttribute("survey",survey);
        return "show_stats";
    }

    //DETALLE ENCUESTA
    @GetMapping("/admin/details/{id}")
    public String showDetails(@PathVariable("id") int id, Model model){
        model.addAttribute("survey",surveyService.getSurveyById(id));
        model.addAttribute("participants",participantService.findBySurvey(id));
        return "show_details";
    }

    //CREAR NUEVA ENCUESTA
    @GetMapping({"/admin/add_survey", "/sgved/add_survey"})
    public String createSurvey(Model model){
        Survey survey = new Survey();
        model.addAttribute("survey",survey);
        return "create_survey";
    }
    @PostMapping({"/admin/add_survey", "/sgved/add_survey"})
    public String saveSurvey(@ModelAttribute("survey") Survey survey, BindingResult br, Model model){
        surveyService.saveOrUpdate(survey);
        return "redirect:/admin";
    }

    //CREAR NUEVA PREGUNTA-RESPUESTA
    @GetMapping("/admin/add_question/{id}")
    public String createQuestion(@PathVariable("id") int id, Model model){
        Question question = new Question();
        Survey survey = surveyService.getSurveyById(id);
        model.addAttribute("survey",survey);
        model.addAttribute("question",question);
        model.addAttribute("nQuestions",survey.getQuestions().size());
        return "create_question";
    }
    @PostMapping("/admin/add_question/{id}")
    public String saveQuestion(@PathVariable("id") int id, @ModelAttribute("question") Question question, Model model){
        question.setNsurvey(id);
        question.setType("TIPO 1");

        Question saved = questionService.saveOrUpdate(question);
        Survey survey = surveyService.getSurveyById(id);

        question.getAnswers().forEach(a -> {
            a.setNquestion(saved.getId());
            answerService.saveOrUpdate(a);
        });
        question = new Question();
        model.addAttribute("question",question);
        model.addAttribute("survey",survey);
        model.addAttribute("nQuestions",survey.getQuestions().size());
        model.addAttribute("mensajeOK","Pregunta registrada correctamente!");
        return "create_question";
    }

    //AÑADIR PARTICIPANTE
    @GetMapping("/admin/add_participant/{id}")
    public String createParticipant(@PathVariable("id") int id, Model model){
        Participant participant = new Participant();
        Survey survey = surveyService.getSurveyById(id);
        model.addAttribute("survey",survey);
        model.addAttribute("participant",participant);

        return "create_participant";
    }
    @PostMapping("/admin/add_participant/{id}")
    public String saveParticipant(@PathVariable("id") int id, @ModelAttribute("participant") Participant participant, Model model){
        Survey survey = surveyService.getSurveyById(id);
        Participant handler = participantService.findByDniAndSurvey(participant.getDni(),id);

        if(handler == null){
            participant.setKey(participant.generateSafeToken());
            participant.setNsurvey(id);
            participantService.saveParticipant(participant);
            model.addAttribute("mensajeOK","Participante registrado correctamente.");
            participant = new Participant();
        }
        else model.addAttribute("mensajeKO","ERROR: El DNI ya está dado de alta para esta encuesta.");

        model.addAttribute("survey",survey);
        model.addAttribute("participant",participant);

        return "create_participant";
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

        result.getResultados().forEach(results -> {
            results.setSurvey(id);
            resultService.save(results);
        });
        Participant data = participantService.findByDniAndSurvey(result.getDni(),id);
        data.setFilled(true);
        data.setDateFilled(new Date());
        participantService.saveParticipant(data);
        return "thanks";
    }
}