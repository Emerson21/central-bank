package br.com.vr.development.centralbank.inbound.dto.events;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class TransferenciaRecebidaEvent extends TransferenciaEvent {

    public TransferenciaRecebidaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        super(correlationId, transacaoMessage);
    }

}
