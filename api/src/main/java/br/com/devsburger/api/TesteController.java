package br.com.devsburger.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping
    public String testar() {
        return "Se voce esta vendo isso, o Spring esta funcionando!";
    }
}