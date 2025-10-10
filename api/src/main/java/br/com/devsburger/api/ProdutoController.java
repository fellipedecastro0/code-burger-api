package br.com.devsburger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController // transforma uma classe Java normal em um ponto de atendimento de API.
@RequestMapping("/produtos") // Todos os endpoints aqui dentro começarão com /produtos

public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // métodos do CRUD virão aqui dentro

    // primeiro metodo para o "R" do CRUD (Read/Ler)

    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }
}
