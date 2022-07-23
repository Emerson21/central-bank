package br.com.vr.development.centralbank.inbound.dto.events;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
public class TransferenciaSolicitadaEvent extends TransferenciaEvent {

    public TransferenciaSolicitadaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        super(correlationId, transacaoMessage);
    }

}
