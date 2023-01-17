package teste_;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.AccessDeniedException;
import com.teste_.Pessoa;
import com.teste_.PessoaDTO;
import com.teste_.PessoaRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        pessoaRepository.deleteAll();
    }

    @After
    public void tearDown() {
        pessoaRepository.deleteAll();
    }

    @Test
    public void testCreatePessoa() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("John Doe");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setLogradouro("Rua Teste");
        endereco.setCep("12345-678");
        endereco.setNumero("123");
        endereco.setCidade("Test City");
        pessoaDTO.setEnderecos(Collections.singletonList(endereco));
        pessoaDTO.setEnderecoPrincipalId(1L);
        String json = objectMapper.writeValueAsString(pessoaDTO);

        mvc.perform(post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testGetAllPessoas() throws Exception {
        createPessoa();

        mvc.perform(get("/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void testGetPessoaById() throws Exception {
        Pessoa pessoa = createPessoa();

        mvc.perform(get("/pessoas/{id}", pessoa.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pessoa.getId().intValue())));
    }

    @Test
    public void testUpdatePessoa() throws Exception {
        Pessoa pessoa = createPessoa();
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Jane Doe");
        pessoaDTO.setDataNascimento(pessoa.getDataNascimento());
        String json = objectMapper.writeValueAsString(pessoaDTO);

        mvc.perform(put("/pessoas/{id}", pessoa.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Jane Doe")));
    }
}       