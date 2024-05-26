package organice.semana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import organice.lembrete.LembreteDateIn;
import organice.lembrete.LembreteOut;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Semana", description = "API de Semana")
public class SemanaResource implements SemanaController {

    @Autowired
    private SemanaService semanaService;

    // @GetMapping("/semana/info")
    // public ResponseEntity<Map<String, String>> info() {
    //     return new ResponseEntity<>(
    //         Map.ofEntries(
    //             Map.entry("microservice.name", SemanaApplication.class.getSimpleName())
    //         ), HttpStatus.OK
    //     );
    // }

    @Override
    @Operation(summary = "Criar uma nova semana", description = "Cria uma nova semana e retorna o objeto criado com seu ID.")
    public ResponseEntity<SemanaOut> create(@RequestHeader(required = true, name = "id-user") String idUser, @RequestBody SemanaIn semanaIn) {
        System.out.println("Entrou no create");
        System.out.println(semanaIn.toString());
        Semana semana = SemanaParser.toSemana(semanaIn);
        semana.id_usuario(idUser);
        semana = semanaService.create(semana);
        System.out.println("teste2: " + semana.id() + " " + semana.dia_inicio() + " " + semana.dia_fim() + " " + semana.descricao() + " " + semana.id_usuario());
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(semana.id())
                .toUri())
            .body(SemanaParser.toSemanaOut(semana));
    }

    // @Override
    // @Operation(summary = "Retorna todos os lembretes de uma semana para um usuário", description = "Retorna todos os lembretes de uma semana para um usuário.")
    // public ResponseEntity<List<LembreteOut>> read_lembretes_semana(@RequestHeader(required = true, name = "id-user") String UserId, @RequestBody LembreteDateIn data) {
    //     List<LembreteOut> lembretes = semanaService.getLembretes(UserId, data);
    //     return ResponseEntity.ok(lembretes);
    // }

    // @Override
    // @Operation(summary = "Atualiza os valores da semana", description = "Atualiza os valores da semana.")
    // public ResponseEntity<SemanaOut> update(@PathVariable("id_semana") String id_semana, @RequestBody SemanaIn semanaIn) {
    //     Semana semana = SemanaParser.toSemana(semanaIn);
    //     semana = semanaService.update(id_semana, semana);
    //     return ResponseEntity.ok(SemanaParser.toSemanaOut(semana));
    // }

    // @Override
    // @Operation(summary = "Deleta a semana", description = "Deleta a semana no banco de dados.")
    // public ResponseEntity<Void> delete(@PathVariable("id_semana") String id_semana) {
    //     semanaService.delete(id_semana);
    //     return ResponseEntity.noContent().build();
    // }

    // @Override
    // @Operation(summary = "Retorna os valores da semana", description = "Retorna os valores da semana.")
    // public ResponseEntity<SemanaOut> read(@PathVariable("id_semana") String id_semana) {
    //     SemanaOut semanaOut = semanaService.read(id_semana);
    //     return ResponseEntity.ok(semanaOut);
    // }
}
