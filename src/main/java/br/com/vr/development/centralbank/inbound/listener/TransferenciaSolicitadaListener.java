package br.com.vr.development.centralbank.inbound.listener;

import br.com.vr.development.centralbank.domain.KafkaTransferenciaMessageSender;
import br.com.vr.development.centralbank.domain.transferencia.TransferenciaService;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaRecebidaEvent;
import br.com.vr.development.centralbank.inbound.dto.events.TransferenciaSolicitadaEvent;
import br.com.vr.development.centralbank.infrastructure.TransferenciaMessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransferenciaSolicitadaListener {

    private TransferenciaMessageRepository transferenciaMessageRepository;
    private TransferenciaService transferenciaService;
    private KafkaTransferenciaMessageSender kafkaMessageSender;

    @KafkaListener(
            topics = "${topico.transferencia.solicitada}",
            groupId = "${spring.kafka.group_id}",
            containerFactory = "transferenciasSolicitadasKafkaListener"
    )
    public void receberEventoTransferenciaSolicitadaEvent(TransferenciaSolicitadaEvent event) {
        log.info("TransferenciaSolicitadaEvent {}", event);

        if ( !transferenciaMessageRepository.findById(event.correlationId()).isPresent() ) {
            transferenciaMessageRepository.save(event);
        }

        kafkaMessageSender.publish(new TransferenciaRecebidaEvent(event.getCorrelationId(), event.getTransacaoMessage()));

        transferenciaService.processar(event.getTransacaoMessage().toModel());
    }

}
