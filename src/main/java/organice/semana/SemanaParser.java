package organice.semana;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SemanaParser {

    public static Semana toSemana(SemanaIn diaIn) {
        try {
            // SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            return Semana.builder()
                    .dia_inicio(diaIn.diaInicio())
                    .dia_fim(diaIn.diaFim())
                    .descricao(diaIn.descricao())
                    .id_usuario(null)
                    .build();
        } catch (Exception e) {
            System.out.println("Deu Pau no Dia In");
            return null;
        }
    }

    public static SemanaOut toSemanaOut(Semana semana) {
        return SemanaOut.builder()
                .data_inicio(semana.dia_inicio())
                .data_fim(semana.dia_fim())
                .descricao(semana.descricao())
                .build();
    }
}
