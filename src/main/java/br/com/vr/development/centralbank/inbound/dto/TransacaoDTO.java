package br.com.vr.development.centralbank.inbound.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransacaoDTO {

    private Banco banco;

    private AgenciaBancaria agenciaBancaria;

    private Integer numero;

    private Integer digito;

    private String nomeCorrentista;

    private Cpf cpf;

    @Getter
    @Setter
    public class Banco {
        private String codigo;
    }

    @Getter
    @Setter
    public class AgenciaBancaria {

        private Integer numero;

        private Integer digito;

    }

    @Getter
    @Setter
    public class Cpf {
        @Getter
        @Setter
        private String numero;
    }
}
