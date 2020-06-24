package br.eti.gabrieljuliobs.contatos.resource;

import br.eti.gabrieljuliobs.contatos.domain.Contato;
import br.eti.gabrieljuliobs.contatos.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/contatos")
public class ConsultaResource {

    @Autowired
    ContatoService service;

    @GetMapping
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Contato contato = service.buscar(id);
        map.put("Contato", contato);
        return ResponseEntity.ok().body(map);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid Contato contato) {
        Map<String, Object> map = new HashMap<>();
        contato = service.insert(contato);
        map.put("Contato criado status", "Sucesso!");
        map.put("Contato", contato);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(uri).body(contato);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Contato contato, @PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        contato.setId(id);
        contato = service.update(contato);
        map.put("Contato atualizado status", "Sucesso!");
        map.put("Contato", contato);
        return ResponseEntity.ok().body(contato);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        service.delete(id);
        map.put("Contato deletado id", id);
        return ResponseEntity.ok().body(map);
    }
}
