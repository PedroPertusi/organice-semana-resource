package organice.semana;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "semana")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class SemanaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_semana")
    private String id;

    @Column(name = "dia_inicio")
    private Date dia_inicio; 

    @Column(name = "dia_fim")
    private Date dia_fim;

    @Column(name = "semana_descricao")
    private String descricao;

    @Column(name = "semana_id_usuario")
    private String id_usuario;

    public SemanaModel(Semana semana) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            this.dia_inicio = formatter.parse(semana.dia_inicio());
            this.dia_fim = formatter.parse(semana.dia_fim());
            this.descricao = semana.descricao();
            this.id_usuario = semana.id_usuario();
            
        } catch (Exception e) {
            System.out.println("deu pau no Semana Model");
            return;
        }
    }   

    public  Semana to(){
        return Semana.builder()
            .id(this.id)
            .dia_inicio(this.dia_inicio.toString())
            .dia_fim(this.dia_fim.toString())
            .descricao(this.descricao)
            .id_usuario(this.id_usuario)
            .build();
    }
}
