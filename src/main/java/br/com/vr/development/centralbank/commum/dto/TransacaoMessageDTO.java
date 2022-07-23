package br.com.vr.development.centralbank.commum.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document("transacao_messages")
public class TransacaoMessageDTO {

    @Id
    private String id;

    private UUID correlationId;
    private Valor valor;
    private ContaOrigem contaOrigem;
    private ContaDestino contaDestino;
    private String tipoTransferencia;

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
    public static class Banco {
        private NomeFantasia nomeFantasia;
        private String codigo;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContaDestino {
        private Banco banco;
        private AgenciaBancaria agenciaBancaria;
        private int numero;
        private int digito;
        private Object nomeCorrentista;
        private Cpf cpf;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ContaOrigem {
        private NomeFantasia nomeFantasia;
        private String codigoBanco;
        private Banco banco;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cpf {
        private String numero;
        private boolean valido;
        private String tipoDocumento;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NomeFantasia {
        private String nome;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Valor {
        private BigDecimal value;

        public boolean forMenorOuIgualAZero() {
            return value.compareTo(BigDecimal.ZERO) <= 0;
        }
    }

}
