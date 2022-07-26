package br.com.vr.development.centralbank.inbound.dto.events;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import br.com.vr.development.centralbank.domain.transferencia.ITransferenciaVisitor;
import br.com.vr.development.centralbank.domain.transferencia.TransferenciaVisitor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class TransferenciaReprovadaEvent extends TransferenciaEvent implements ITransferenciaVisitor {

    public TransferenciaReprovadaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        super(correlationId, transacaoMessage);
    }

    @Override
    public void publish(TransferenciaVisitor transferenciaVisitor) {
        transferenciaVisitor.visit(this);
    }
}
