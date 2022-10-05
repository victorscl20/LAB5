package com.pucp.lab5gtics.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping(value = {"/","/login"})
    public String login(){
        return "inicio";
    }

    @GetMapping(value = "/redirectCategoria")
    public String redirectByCategoria(Authentication auth){
        String categoria = "";
        for(GrantedAuthority role : auth.getAuthorities()){
            categoria = role.getAuthority();
            break;
        }
        if(categoria.equals("1")||categoria.equals("0")){
            categoria = "general";
        }

        switch (categoria){
            case "general":
                return "redirect:/empleado/lista";
            case "1":
                return "redirect:/empleado/";
            case "0":
                return "redirect:/empleado/guardar";
            default:
                return "redirect:/login/";

        }






    }
}
