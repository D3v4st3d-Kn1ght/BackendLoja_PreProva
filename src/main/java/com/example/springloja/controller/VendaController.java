package com.example.springloja.controller;

import com.example.springloja.dto.VendaDTO;
import com.example.springloja.entities.Venda;
import com.example.springloja.services.VendaService;
import com.example.springloja.services.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@CrossOrigin
@Tag(name="Vendas", description = "Gerenciamento de estoque de vendas")
public class VendaController {
    @Autowired
    private VendaService service;

    @Autowired
    private StorageService storageService;

    @GetMapping
    @Operation(summary = "Lista todos os vendas", description = "Retorna uma lista completa de vendas com suas respectivas categorias")
    public List<VendaDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearerAuth")
    public String criar(@Valid @RequestPart("venda") Venda venda,
                        @RequestPart(value = "foto", required = false) MultipartFile foto,
                        @RequestPart(value = "manual", required = false) MultipartFile manual) {

        service.salvar(venda);
        return "Venda criada com sucesso!";
    }


    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,@Valid @RequestBody Venda venda){
        service.atualizar(venda.getId(), venda);
        return "Venda atualizada com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        service.excluir(id);
        return "Venda deletado com sucesso!";
    }

    @GetMapping("/filtro-preco")
    public List<VendaDTO> filtrarPorPreco(@RequestParam(name="valor") Double preco) {
        return service.buscarPorValorMaximo(preco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarPorId(@PathVariable Long id){
        VendaDTO obj = service.buscarPorId(id);

        if (obj != null){
            return ResponseEntity.ok().body(obj);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
