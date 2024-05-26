package organice.semana;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemanaRepository extends CrudRepository<SemanaModel, String> {
    
    
}
