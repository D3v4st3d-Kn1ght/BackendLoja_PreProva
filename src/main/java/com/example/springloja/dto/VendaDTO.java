package com.example.springloja.dto;

import com.example.springloja.entities.Cliente;
import com.example.springloja.entities.Venda;
import com.example.springloja.entities.Vendedor;

public record VendaDTO(
        Long id,
        String data,
        Double valor,
        Cliente cliente,
        Vendedor vendedor
) {
    public VendaDTO(Venda v){
        this(v.getId(), v.getData(), v.getValor(), v.getCliente(), v.getVendedor());
    }
}
