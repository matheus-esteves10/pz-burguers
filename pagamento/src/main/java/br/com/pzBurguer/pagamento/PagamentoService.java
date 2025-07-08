package br.com.pzBurguer.pagamento;

import br.com.pzBurguer.pagamento.model.Pagamento;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    public void sendPayment(Pagamento pagamento) {
        System.out.println("Enviando pagamento de: " + pagamento.getValorTotal());
    }
}
