package entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.OneToMany;
import java.util.List;

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

    // Um Pedido pode ter UMA LISTA de Muitos Itens
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
    // --- Construtor, Getters e Setters ---

    public Pedido() {
    }

    // Getters e Setters para todos os campos
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
