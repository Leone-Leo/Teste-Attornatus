package teste_;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa create(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Pessoa> findById(Long id) {
        return repository.findById(id);
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }
}