package br.com.vr.development.centralbank.inbound;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import br.com.vr.development.centralbank.events.Event;
import br.com.vr.development.centralbank.events.transacoes.TransacaoAprovadaEvent;
import br.com.vr.development.centralbank.events.transacoes.TransacaoReprovadaEvent;
import br.com.vr.development.centralbank.infrastructure.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Component
public class TransacoesConsumer {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("transacoesAprovadas")
    private TopicExchange transacoesAprovadas;

    @Autowired
    @Qualifier("transacoesReprovadas")
    private TopicExchange transacoesReprovadas;


    @RabbitListener(queues = {"${transacao.queue.name}"})
    public void receive(String message, @Header(name = "x-death", required = false) Map<?, ?> death) throws JsonProcessingException {
        log.info("Message {}", message);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        TransacaoMessageDTO transacaoMessageDTO =
                mapper.readValue(message, TransacaoMessageDTO.class);

        messageRepository.save(transacaoMessageDTO);

        Event event = buildTipoDeEvento(transacaoMessageDTO);
        TopicExchange topicEvent = getTopicEvent(transacaoMessageDTO);

        rabbitTemplate.convertAndSend(topicEvent.getName(), EMPTY, mapper.writeValueAsString(event));
    }

    private TopicExchange getTopicEvent(TransacaoMessageDTO transacaoMessageDTO) {
        return transacaoMessageDTO.getValor().ehMenorOuIgualAZero()
                ? transacoesReprovadas
                : transacoesAprovadas;
    }

    private Event buildTipoDeEvento(TransacaoMessageDTO transacaoMessageDTO) {
        return transacaoMessageDTO.getValor().ehMenorOuIgualAZero()
                ? new TransacaoReprovadaEvent(transacaoMessageDTO)
                : new TransacaoAprovadaEvent(transacaoMessageDTO);
    }

}
