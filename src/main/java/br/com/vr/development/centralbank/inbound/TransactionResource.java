package br.com.vr.development.centralbank.inbound;

import br.com.vr.development.centralbank.inbound.dto.TransacaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@Slf4j
public class TransactionResource {

    //TODO - transformar em um serviço idempotente, não realizar o processamento de requests iguais

    @PostMapping
    public ResponseEntity<?> depositar(@RequestBody TransacaoDTO transacaoDTO) {
      log.info("Recebendo requisicao de transacao {}", transacaoDTO);
      return ResponseEntity.ok().build();
    }

}
