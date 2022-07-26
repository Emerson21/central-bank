package br.com.vr.development.centralbank.domain;

import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaAprovadaEvent;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaEvent;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaRecebidaEvent;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaReprovadaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaTransferenciaMessageSender {

    @Value("${topico.transferencia.recebida}")
    private String transferenciaRecebida;

    @Value("${topico.transferencia.aprovada}")
    private String transferenciaAprovada;

    @Value("${topico.transferencia.reprovada}")
    private String transferenciaReprovada;


    @Autowired
    private KafkaTemplate<String, TransferenciaEvent> kafkaTemplate;

    public void publish(TransferenciaRecebidaEvent transferenciaEvent) {
        kafkaTemplate.send(transferenciaRecebida, transferenciaEvent.correlationId(), transferenciaEvent);
    }

    public void publish(TransferenciaReprovadaEvent transferenciaEvent) {
        kafkaTemplate.send(transferenciaReprovada, transferenciaEvent.correlationId(), transferenciaEvent);
    }

    public void publish(TransferenciaAprovadaEvent transferenciaAprovadaEvent) {
        kafkaTemplate.send(transferenciaAprovada, transferenciaAprovadaEvent.correlationId(), transferenciaAprovadaEvent);
    }

}
