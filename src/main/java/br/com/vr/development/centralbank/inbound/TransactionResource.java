package br.com.vr.development.centralbank.inbound;

import br.com.vr.development.centralbank.inbound.dto.TransacaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/transacoes")
@Slf4j
public class TransactionResource {

    @PostMapping
    public ResponseEntity<?> depositar(@RequestBody TransacaoDTO transacaoDTO) throws InterruptedException {
      log.info("Recebendo requisicao de transacao {}", transacaoDTO);

      TimeUnit.SECONDS.sleep(15);

      return ResponseEntity.ok().build();
    }

}
