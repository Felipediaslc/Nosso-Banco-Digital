package zup.bootcamp.banco.digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zup.bootcamp.banco.digital.model.ClienteModel;
import zup.bootcamp.banco.digital.repository.ClienteRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel salvar(@RequestBody @Valid ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    @GetMapping("/{id}")
    public ClienteModel acharPorId(@PathVariable Integer id){
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        clienteRepository
                .findById(id)
                .map(clienteModel -> {
                    clienteRepository.delete(clienteModel);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid ClienteModel clienteAtualizado){
        clienteRepository
                .findById(id)
                .map( clienteModel -> {
                    clienteModel.setNome(clienteAtualizado.getNome());
                    clienteModel.setCpf(clienteAtualizado.getCpf());
                    return clienteRepository.save(clienteModel);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado"));
    }
}