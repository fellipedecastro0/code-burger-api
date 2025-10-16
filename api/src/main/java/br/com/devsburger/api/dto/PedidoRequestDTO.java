package br.com.devsburger.api.dto;
import java.util.List;

// Este é o DTO principal que representa o pedido completo enviado pelo cliente.
public record PedidoRequestDTO(
        String nomeCliente,
        List<ItemPedidoRequestDTO> itens // <-- Fica bem mais limpo e fácil de ler
) {
}