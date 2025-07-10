package br.com.pzBurguer.pagamento;

import br.com.pzBurguer.pagamento.dto.PagamentoStatusDto;
import br.com.pzBurguer.pagamento.model.Conta;
import br.com.pzBurguer.pagamento.model.Pagamento;
import br.com.pzBurguer.pagamento.model.enums.StatusPagamento;
import br.com.pzBurguer.pagamento.repository.ContaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PagamentoService {

    private final RabbitTemplate rabbitTemplate;
    private final ContaRepository contaRepository;

//    @Value("${broker.queue.payment.response}")
//    private String paymentResponseQueue;

    public PagamentoService(RabbitTemplate rabbitTemplate, ContaRepository contaRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.contaRepository = contaRepository;
    }

    public void sendPayment(Pagamento pagamento) {
        System.out.println("➡️ Tentando processar pagamento do pedido: " + pagamento.getIdUser());

        StatusPagamento status = processarPagamento(pagamento);

        System.out.println("Status do pagamento: " + status);

        //enviarStatusResposta(pagamento.getIdUser(), status);
    }

    // Responsável por processar o pagamento (verificar saldo, descontar, retornar status)
    private StatusPagamento processarPagamento(Pagamento pagamento) {
        Optional<Conta> contaOptional = contaRepository.findById(pagamento.getIdUser());

        if (contaOptional.isEmpty()) {
            System.out.println("❌ Conta não encontrada para ID " + pagamento.getIdUser());
            return StatusPagamento.ERRO;
        }

        Conta conta = contaOptional.get();

        if (conta.getSaldo().compareTo(pagamento.getValorTotal()) >= 0) {
            conta.setSaldo(conta.getSaldo().subtract(pagamento.getValorTotal()));
            contaRepository.atualizar(conta);
            System.out.println("✅ Pagamento aprovado! Novo saldo: " + conta.getSaldo());
            return StatusPagamento.PAGO;
        } else {
            System.out.println("❌ Pagamento recusado. Saldo insuficiente.");
            return StatusPagamento.ERRO;
        }
    }

    // Responsável por enviar a mensagem com o status do pagamento para a fila
//    private void enviarStatusResposta(Long idPedido, StatusPagamento status) {
//        PagamentoStatusDto statusDto = new PagamentoStatusDto(idPedido, status);
//        rabbitTemplate.convertAndSend(paymentResponseQueue, statusDto);
//        System.out.println("📤 Status de pagamento enviado para fila: " + status);
//    }
}

