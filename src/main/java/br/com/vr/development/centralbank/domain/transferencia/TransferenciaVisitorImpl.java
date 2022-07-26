package br.com.vr.development.centralbank.domain.transferencia;

import br.com.vr.development.centralbank.domain.KafkaTransferenciaMessageSender;
import br.com.vr.development.centralbank.inbound.dto.events.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransferenciaVisitorImpl implements TransferenciaVisitor {

    private KafkaTransferenciaMessageSender kafkaMessageSender;

    @Override
    public void publishTransferenciaEvent(ITransferenciaVisitor transferenciaVisitor) {
        transferenciaVisitor.publish(this);
    }

    @Override
    public void visit(TransferenciaReprovadaEvent transferenciaReprovadaEvent) {
        kafkaMessageSender.publish(transferenciaReprovadaEvent);
    }

    @Override
    public void visit(TransferenciaAprovadaEvent transferenciaAprovadaEvent) {
        kafkaMessageSender.publish(transferenciaAprovadaEvent);
    }

}
