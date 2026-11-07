package com.example.springloja.services;

import com.example.springloja.dto.VendaDTO;
import com.example.springloja.entities.Venda;
import com.example.springloja.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private StorageService storageService;

    public List<VendaDTO> listarTodos() {
        return repository.findAll().stream().map(VendaDTO::new).toList();
    }

    public void salvar(Venda venda) {
        repository.save(venda);
    }

    @Transactional
    public Venda atualizar(Long id, Venda dadosAtualizados) {
        Venda vendaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrado com id: " + id));

        // Atualiza os campos normais
        vendaExistente.setData(dadosAtualizados.getData());
        vendaExistente.setValor(dadosAtualizados.getValor());
        vendaExistente.setCliente(dadosAtualizados.getCliente());
        vendaExistente.setVendedor(dadosAtualizados.getVendedor());

        return repository.save(vendaExistente);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public List<VendaDTO> buscarPorValorMaximo(Double valor) {
        return repository.findByValorLessThanEqual(valor).stream().map(VendaDTO::new).toList();
    }

    public VendaDTO buscarPorId(Long id){
        return repository.findById(id).stream().map(VendaDTO::new).findFirst().orElse(null);
    }
}
