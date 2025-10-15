package br.com.devsburger.api.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import br.com.devsburger.api.entity.StatusPedido;
import br.com.devsburger.api.entity.ItemPedido;
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "dt_pedido")
    private LocalDateTime dtPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    // Construtor vazio (obrigatório pelo JPA)
    public Pedido() {
    }

    // Getters e Setters (pode usar Alt+Insert para gerar se faltar algum)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDateTime getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(LocalDateTime dtPedido) {
        this.dtPedido = dtPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}