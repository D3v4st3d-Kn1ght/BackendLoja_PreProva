package com.example.springloja.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do venda gerado pelo banco", example = "1")
    private Long id;

    @NotBlank(message = "Data precisa ser informada!")
    @Schema(description = "Data de quando essa venda foi efetuada")
    private String data;

    @Min(value = 0, message = "O preço não pode ser negativo")
    @Schema(description = "Preço da venda", example = "250.00")
    private Double valor;

    @NotNull
    @Schema(description = "Cliente a quem essa venda se destina")
    @ManyToOne
    private Cliente cliente;

    @NotNull
    @Schema(description = "Vendedor a quem essa venda pertence")
    @ManyToOne
    private Vendedor vendedor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
