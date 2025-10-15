package br.com.devsburger.api.service;
import dto.ItemPedidoRequestDTO;
import dto.PedidoRequestDTO;
import entity.ItemPedido;
import entity.Pedido;
import entity.Produto;
import entity.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ItemPedidoRepository;
import repository.PedidoRepository;
import repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service // Anotação que diz ao Spring: "Esta é uma classe de serviço, gerencie-a!"
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    //  lógica de criar o pedido:

    @Transactional
    public Pedido criarPedido(PedidoRequestDTO dto) {
        // 1. Criar e salvar a entidade Pedido principal
        Pedido pedido = new Pedido();
        pedido.setNomeCliente(dto.nomeCliente());
        pedido.setDtPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.EM_PREPARO);

        // Salvamos primeiro para obter o ID gerado, que será usado nos itens
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        List<ItemPedido> itensSalvos = new ArrayList<>();

        // 2. Iterar sobre os itens do DTO e salvá-los
        for (ItemPedidoRequestDTO itemDTO : dto.itens()) {
            // Para cada item, buscamos o produto correspondente no banco
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setPedido(pedidoSalvo); // Linka o item com o pedido que acabamos de salvar
            itemPedido.setQuantidade(itemDTO.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco()); // Pega o preço do banco, não do cliente!

            itensSalvos.add(itemPedidoRepository.save(itemPedido));

        }
        pedidoSalvo.setItens(itensSalvos);
        return pedidoSalvo;
    }
}


