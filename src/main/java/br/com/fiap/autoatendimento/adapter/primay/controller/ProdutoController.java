package br.com.fiap.autoatendimento.adapter.primay.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.CadastrarProdutoReqDto;
import br.com.fiap.autoatendimento.application.port.in.CadastrarProdutoPortIn;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarProdutoInputDto;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/produtos")
@Validated
@RequiredArgsConstructor
public class ProdutoController {

    private final CadastrarProdutoPortIn cadastrarProdutoPortIn;

    @PostMapping
    @ResponseStatus
    public void cadastrar(@Valid @RequestBody CadastrarProdutoReqDto request) {

        final CadastrarProdutoInputDto input = CadastrarProdutoInputDto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .imagem(request.getImagem())
                .ativo(request.getAtivo())
                .categoria(request.getCategoria())
                .build();
            cadastrarProdutoPortIn.executar(input);
    }
    
}
