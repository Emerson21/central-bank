package br.com.vr.development.centralbank.events.transacoes;

import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import br.com.vr.development.centralbank.events.Event;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class TransacaoAprovadaEvent extends Event {

    private TransacaoMessageDTO messageDTO;

    public TransacaoAprovadaEvent(TransacaoMessageDTO transacaoMessageDTO) {
        super(transacaoMessageDTO.getCorrelationId());
        this.messageDTO = transacaoMessageDTO;
    }

}
