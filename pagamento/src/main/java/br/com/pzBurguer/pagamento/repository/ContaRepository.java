package br.com.pzBurguer.pagamento.repository;

import br.com.pzBurguer.pagamento.model.Conta;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ContaRepository {

    private final Map<Long, Conta> contas = new HashMap<>();

    @PostConstruct
    public void init() {
        // Contas Teste
        contas.put(1L, new Conta(1L, new BigDecimal("1000.00")));
        contas.put(2L, new Conta(2L, new BigDecimal("30.00")));
    }

    public Optional<Conta> findById(Long id) {
        return Optional.ofNullable(contas.get(id));
    }

    public void atualizar(Conta conta) {
        contas.put(conta.getIdPedido(), conta);
    }
}
