package br.com.fiap.PzBurguer.producer;

public interface IProducer<T> {

    void publishMessage(T message);
}
