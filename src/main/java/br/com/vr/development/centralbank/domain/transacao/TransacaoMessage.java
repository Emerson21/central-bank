package br.com.vr.development.centralbank.domain.transacao;


import br.com.vr.development.centralbank.commum.dto.TransacaoMessageDTO;
import br.com.vr.development.centralbank.enums.TipoTransferencia;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransacaoMessage {

    private UUID correlationId;
    private TransacaoMessageDTO.Valor valor;
    private TransacaoMessageDTO.ContaOrigem contaOrigem;
    private TransacaoMessageDTO.ContaDestino contaDestino;
    private TipoTransferencia tipoTransferencia;

}
