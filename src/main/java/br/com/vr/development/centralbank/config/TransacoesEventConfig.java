package br.com.vr.development.centralbank.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TransacoesEventConfig {

    @Value("${transacao.exchange.events.aprovadas}")
    private String transacoesAprovadas;

    @Value("${transacao.exchange.events.reprovadas}")
    private String transacoesReprovadas;


    @Bean
    public TopicExchange transacoesAprovadas() {
        return new TopicExchange(transacoesAprovadas, true, false);
    }

    @Bean
    public TopicExchange transacoesReprovadas() {
        return new TopicExchange(transacoesReprovadas, true, false);
    }

    @Bean
    MessageConverter transacoesConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
