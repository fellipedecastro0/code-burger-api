package br.com.devsburger.api.controller;
import br.com.devsburger.api.dto.PedidoRequestDTO;
import br.com.devsburger.api.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.devsburger.api.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService; // Agora injetamos o Service, e não o Repository!

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody PedidoRequestDTO dto) {
        Pedido pedidoSalvo = pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }
}
