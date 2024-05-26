package organice.semana;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import organice.lembrete.LembreteController;
import organice.lembrete.LembreteDateIn;
import organice.lembrete.LembreteOut;

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

    // public ResponseEntity<List<LembreteOut>> getLembretes(String userId, LembreteDateIn data) {
    //     System.out.println(data.data());
    //     return lembreteController.getByDate(userId, data);
    // }

    // public void delete(String id_dia) {
    //     // TODO Auto-generated method stub
    //     diaRepository.deleteById(id_dia);
    // }

    // public Semana update(String id_dia, Semana dia) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'update'");
    // }


}
