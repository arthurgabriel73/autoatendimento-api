package br.com.fiap.autoatendimento.adapter.primay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testes")
public class TesteController {

    @GetMapping
    public String testes() {

        return "Hello World!";
    }

}
