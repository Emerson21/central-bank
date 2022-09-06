package br.com.vr.development.centralbank.inbound.dto.events;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
public class TransferenciaSolicitadaEvent extends TransferenciaEvent {

    public TransferenciaSolicitadaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        super(correlationId, transacaoMessage);
    }

    @Override
    public boolean equals(Object object) {
        if ( object != null && object instanceof TransferenciaSolicitadaEvent ) {
            return ((TransferenciaEvent) object).correlationId().equals(correlationId.toString());
        }

        return false;
    }

}
