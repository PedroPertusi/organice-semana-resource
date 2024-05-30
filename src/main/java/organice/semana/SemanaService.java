package organice.semana;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import organice.lembrete.LembreteController;
import organice.lembrete.LembreteDateIn;
import organice.lembrete.LembreteOut;
import org.springframework.http.HttpStatus;


@Service
public class SemanaService {
    
    @Autowired
    private LembreteController lembreteController;

    @Autowired
    private SemanaRepository semanaRepository;


    // public SemanaService(SemanaRepository semanaRepository) {
    //     this.semanaRepository = semanaRepository;
    // }

    public Semana create(Semana in) {
        return semanaRepository.save(new SemanaModel(in)).to();
    }


    public ResponseEntity<List<LembreteOut>> getLembretes(String userId, String id_semana) {

        SemanaModel semana = semanaRepository.findById(id_semana).get();
    
        // Adjust the pattern to match the required format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    
        // Remove the time part from the string before parsing
        LocalDate startOfWeek = LocalDate.parse(semana.dia_inicio().toString().split(" ")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endOfWeek = LocalDate.parse(semana.dia_fim().toString().split(" ")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
        List<LembreteOut> allLembretesForWeek = new ArrayList<>();
    
        for (LocalDate currentDate = startOfWeek; !currentDate.isAfter(endOfWeek); currentDate = currentDate.plusDays(1)) {
            String currentDateString = currentDate.format(formatter);
            LembreteDateIn currentData = new LembreteDateIn(currentDateString);
            ResponseEntity<List<LembreteOut>> lembretesForDay = lembreteController.getByDate(userId, currentData);
            if (lembretesForDay.getBody() != null) {
                allLembretesForWeek.addAll(lembretesForDay.getBody());
            }
        }
    
        return new ResponseEntity<>(allLembretesForWeek, HttpStatus.OK);
    }

    public void delete(String id_semana) {
        semanaRepository.deleteById(id_semana);
    }

    // public Semana update(String id_dia, Semana dia) {
    //     throw new UnsupportedOperationException("Unimplemented method 'update'");
    // }


}
