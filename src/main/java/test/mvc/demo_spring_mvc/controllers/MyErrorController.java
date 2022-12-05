package test.mvc.demo_spring_mvc.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController {
    @GetMapping("/error")
    ModelAndView getErrorPage(HttpServletRequest request, HttpServletResponse response){
        Object errorCode= request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println("error code "+errorCode.getClass().getName());
        return new ModelAndView("error");
    }
}
