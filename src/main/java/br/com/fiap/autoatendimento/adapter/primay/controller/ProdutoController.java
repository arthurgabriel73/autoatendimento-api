package br.com.fiap.autoatendimento.adapter.primay.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.CadastrarProdutoReqDto;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.CadastrarProdutoResDto;
import br.com.fiap.autoatendimento.application.port.in.CadastrarProdutoPortIn;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarProdutoOutputDto;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/produtos")
@Validated
@RequiredArgsConstructor
public class ProdutoController {

    private final CadastrarProdutoPortIn cadastrarProdutoPortIn;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastrarProdutoResDto cadastrar(@Valid @RequestBody CadastrarProdutoReqDto request) {

        final CadastrarProdutoInputDto input = CadastrarProdutoInputDto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .imagem(request.getImagem())
                .ativo(request.getAtivo())
                .categoria(request.getCategoria())
                .build();
            
        final CadastrarProdutoOutputDto output = cadastrarProdutoPortIn.executar(input);

        return CadastrarProdutoResDto.builder().idProduto(output.getIdProduto().toString()).build();
    }
    
}
