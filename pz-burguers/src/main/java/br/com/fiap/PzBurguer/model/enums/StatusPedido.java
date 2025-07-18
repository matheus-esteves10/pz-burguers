package br.com.fiap.PzBurguer.model.enums;

import br.com.fiap.PzBurguer.exceptions.StatusPedidoException;

public enum StatusPedido {
    SOLICITADO,
    EM_PREPARO,
    ENTREGUE,
    CANCELADO,
    RECUSADO;

    public StatusPedido transicionaPara(StatusPedido novoStatus) {
        if (this == CANCELADO || this == ENTREGUE || this == RECUSADO) {
            throw new IllegalStateException("Pedido já finalizado. Transição não permitida.");
        }

        if (this == SOLICITADO && novoStatus == CANCELADO) {
            return CANCELADO;
        }

        if (this == SOLICITADO && novoStatus == EM_PREPARO) {
            return EM_PREPARO;
        }

        if (this == EM_PREPARO && novoStatus == ENTREGUE) {
            return ENTREGUE;
        }

        if (this == SOLICITADO && novoStatus == RECUSADO) {
            return RECUSADO;
        }

        throw new StatusPedidoException("Transição de " + this + " para " + novoStatus + " não permitida.");
    }
}