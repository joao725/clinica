package br.com.clinica.clinica.crontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.clinica.clinica.model.DadosProcedimentos;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentosController {
    List<DadosProcedimentos> procedimentosCadastrados = new ArrayList<>();

    @GetMapping
    private List<DadosProcedimentos> obterProcedimentos(){
        return procedimentosCadastrados;
    }

    @PostMapping
    private String cadastrarProcedimentos(@RequestBody DadosProcedimentos procedimento){
        if(procedimentosCadastrados != null){
            procedimentosCadastrados.add(procedimento);
            return "o procedimento : "+ procedimento.getDescricao() +
            ", com valor de : "+ procedimento.getValor() + ", foi cadastrado com sucesso";
        }
        return "o procedimento informado ja existe ou é nulo";
    }
}
