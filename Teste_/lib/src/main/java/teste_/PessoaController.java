package teste_;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
        Pessoa newPessoa = service.create(pessoa);
        return ResponseEntity.ok(newPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa updatedPessoa = service.update(pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = service.findById(id);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = service.findAll();
        return ResponseEntity.ok(pessoas);
    }
}