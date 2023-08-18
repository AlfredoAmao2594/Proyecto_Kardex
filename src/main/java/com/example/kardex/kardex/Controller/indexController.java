package com.example.kardex.kardex.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// http://localhost:8080/

@Controller
public class indexController {
    
    @GetMapping("index")
    public String index(){
        return "index";
    }
}
