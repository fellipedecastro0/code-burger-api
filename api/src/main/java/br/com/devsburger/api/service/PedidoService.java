package br.com.devsburger.api.service;
import br.com.devsburger.api.dto.ItemPedidoRequestDTO;
import br.com.devsburger.api.dto.PedidoRequestDTO;
import br.com.devsburger.api.entity.ItemPedido;
import br.com.devsburger.api.entity.Pedido;
import br.com.devsburger.api.entity.Produto;
import br.com.devsburger.api.entity.StatusPedido;
import br.com.devsburger.api.repository.ItemPedidoRepository;
import br.com.devsburger.api.repository.PedidoRepository;
import br.com.devsburger.api.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

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
            itemPedido.setPedido(pedidoSalvo); // Você parou aqui

            // --- INÍCIO DO CÓDIGO RESTAURADO ---

            // Preenche o resto dos dados do item
            itemPedido.setQuantidade(itemDTO.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco()); // Pega o preço do banco, não do cliente!

            // Salva o item no banco e o adiciona à nossa lista temporária
            itensSalvos.add(itemPedidoRepository.save(itemPedido));
        }

        // Associa a lista de itens já salvos ao pedido principal
        pedidoSalvo.setItens(itensSalvos);

        // Retorna o pedido completo para o controller
        return pedidoSalvo;
    } //

}