package br.com.devsburger.api;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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
}
