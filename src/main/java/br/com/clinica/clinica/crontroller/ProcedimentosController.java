package br.com.clinica.clinica.crontroller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.clinica.clinica.model.DadosProcedimentos;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentosController {
    List<DadosProcedimentos> procedimentosCadastrados = new ArrayList<>();

    //CREATE
    @PostMapping
    private String cadastrarProcedimentos(@RequestBody DadosProcedimentos procedimento){
        if(procedimentosCadastrados != null){
            procedimentosCadastrados.add(procedimento);
            return "o procedimento : "+ procedimento.getDescricao() +
            ", com valor de : "+ procedimento.getValor() + ", foi cadastrado com sucesso";
        }
        return "o procedimento informado ja existe ou é nulo";
    }

    //READM
    @GetMapping
    private List<DadosProcedimentos> obterProcedimentos(){
        return procedimentosCadastrados;
    }

    @GetMapping("/{descricao}")
    private DadosProcedimentos obterProcedimentoPorDescricao(@PathVariable String descricao){
        DadosProcedimentos procedimento = procedimentosCadastrados.stream()
        .filter(p -> p.getDescricao().equalsIgnoreCase(descricao))
        .findFirst().orElse(null);
    
        return procedimento;
    }

    //UPDATE
    @PutMapping("/{descricao}")
    private DadosProcedimentos atualizarProcedimento(@PathVariable String descricao, @RequestBody 
                                                    DadosProcedimentos novoProcedimento){
        DadosProcedimentos procedimento = procedimentosCadastrados.stream()
        .filter(p -> p.getDescricao().equalsIgnoreCase(descricao))
        
        .findFirst().orElse(null);
        if (procedimento != null) {
            procedimento.setDescricao(novoProcedimento.getDescricao());
            procedimento.setValor(novoProcedimento.getValor());
        }
        
        return procedimento;
    }

    //DELETE
    @DeleteMapping("/{descricao}")
    private void deletarProcedimento(@PathVariable String descricao){
        procedimentosCadastrados.removeIf(p -> p.getDescricao().equalsIgnoreCase(descricao));
    }
}
