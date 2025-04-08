package br.com.fiap.PzBurguer.service;

import br.com.fiap.PzBurguer.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;
}
