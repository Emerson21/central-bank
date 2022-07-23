package br.com.vr.development.centralbank.inbound.listener;

import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaSolicitadaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransferenciaSolicitadaListener {


    @KafkaListener(
            topics = "${topico.transferencia.solicitada}",
            groupId = "${spring.kafka.group_id}",
            containerFactory = "transferenciasSolicitadasKafkaListener"
    )
    public void listenerTransferenciaSolicitadaEvent(TransferenciaSolicitadaEvent event) {
        log.info("TransferenciaSolicitadaEvent {}", event);
        //TODO - implement idempotent consumer
        //TODO - create new event, transferencia recebida
        //TODO - create new event, transferencia sucesso or recusada

    }

}
