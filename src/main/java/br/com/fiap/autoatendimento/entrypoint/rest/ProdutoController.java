package br.com.fiap.autoatendimento.entrypoint.rest;

import java.util.stream.Collectors;

import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.AtualizarProdutoResDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.CadastrarProdutoResDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.ListarProdutosResDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import br.com.fiap.autoatendimento.entrypoint.rest.dto.request.AtualizarProdutoReqDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.request.CadastrarProdutoReqDto;
import br.com.fiap.autoatendimento.core.usecase.produto.AtualizarProdutoUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.CadastrarProdutoUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.ListarProdutosUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.RemoverProdutoUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.AtualizarProdutoInputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.AtualizarProdutoOutputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoOutputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.ListarProdutosOutputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@Validated
@RequiredArgsConstructor
public class ProdutoController {

    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final RemoverProdutoUseCase removerProdutoUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastrarProdutoResDto cadastrar(@RequestBody @Valid CadastrarProdutoReqDto request) {

        final CadastrarProdutoInputDto input = CadastrarProdutoInputDto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .imagem(request.getImagem())
                .ativo(request.getAtivo())
                .categoria(request.getCategoria())
                .build();
            
        final CadastrarProdutoOutputDto output = cadastrarProdutoUseCase.executar(input);

        return CadastrarProdutoResDto.builder().idProduto(output.getIdProduto().toString()).build();
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListarProdutosResDto listar(@RequestParam(required = false) String categoria) {

        final ListarProdutosOutputDto output = listarProdutosUseCase.executar(categoria);

        return ListarProdutosResDto.builder()
                .produtos(output.getProdutos().stream()
                        .map(produto -> ListarProdutosResDto.Produto.builder()
                                .idProduto(produto.getIdProduto().toString())
                                .nome(produto.getNome())
                                .descricao(produto.getDescricao())
                                .preco(produto.getPreco().toString())
                                .imagem(produto.getImagem())
                                .ativo(produto.getAtivo().toString())
                                .categoria(ListarProdutosResDto.Categoria.builder()
                                        .idCategoria(produto.getCategoria().getIdCategoria().toString())
                                        .nome(produto.getCategoria().getNome())
                                        .build())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

	@PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AtualizarProdutoResDto atualizar(@RequestBody @Valid AtualizarProdutoReqDto request) {

        final AtualizarProdutoInputDto input = AtualizarProdutoInputDto.builder()
				.idProduto(request.getIdProduto())
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .imagem(request.getImagem())
                .ativo(request.getAtivo())
                .categoria(request.getCategoria())
                .build();

		final AtualizarProdutoOutputDto output = atualizarProdutoUseCase.executar(input);

		return AtualizarProdutoResDto.builder().idProduto(output.getIdProduto().toString()).build();
    }

	@DeleteMapping("/{idProduto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer idProduto) {

		removerProdutoUseCase.executar(idProduto);

	}

}


