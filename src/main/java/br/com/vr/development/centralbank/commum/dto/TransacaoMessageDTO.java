package br.com.vr.development.centralbank.commum.dto;

import br.com.vr.development.centralbank.domain.transacao.TransacaoMessage;
import br.com.vr.development.centralbank.enums.TipoTransferencia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document("transacao_messages")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
public class TransacaoMessageDTO {

    @Id
    private String id;

    private UUID correlationId;
    private Valor valor;
    private ContaOrigem contaOrigem;
    private ContaDestino contaDestino;
    private String tipoTransferencia;

    public TransacaoMessageDTO(TransacaoMessage transacaoMessage) {
        this.correlationId = transacaoMessage.getCorrelationId();
        this.valor = transacaoMessage.getValor();
        this.contaDestino = transacaoMessage.getContaDestino();
        this.contaOrigem = transacaoMessage.getContaOrigem();
        this.tipoTransferencia = transacaoMessage.getTipoTransferencia().name();
    }

    @JsonIgnore
    public TransacaoMessage toModel() {
        return new TransacaoMessage(
                correlationId,
                valor,
                contaOrigem,
                contaDestino,
                TipoTransferencia.valueOf(tipoTransferencia)
        );
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AgenciaBancaria {
        private Object banco;
        private int numero;
        private int digito;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Banco {
        private NomeFantasia nomeFantasia;
        private String codigo;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ContaDestino {
        private Banco banco;
        private AgenciaBancaria agenciaBancaria;
        private int numero;
        private int digito;
        private String nomeCorrentista;
        private Cpf cpf;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ContaOrigem {
        private NomeFantasia nomeFantasia;
        private String codigoBanco;
        private Banco banco;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Cpf {
        private String numero;
        private String tipoDocumento;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class NomeFantasia {
        private String nome;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Valor {
        private BigDecimal value;

        public boolean ehMenorOuIgualAZero() {
            return value.compareTo(BigDecimal.ZERO) <= 0;
        }
    }

}
