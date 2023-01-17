package teste_;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    private boolean enderecoPrincipal;

    public Pessoa() {}

    public Pessoa(String nome, LocalDate dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(boolean enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }
}