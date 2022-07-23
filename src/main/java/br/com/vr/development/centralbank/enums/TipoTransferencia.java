package br.com.vr.development.centralbank.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoTransferencia {

    TED,
    TEF,
    DOC,
    PIX,
    DEPOSITO,
    CARTAO_DEBITO,
    CARTAO_CREDITO,
    PAGAMENTO_DE_FATURA;

}
