package SpringDataAccessPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.DBCon.model.*;

@Repository
public interface personRepository extends JpaRepository<mySpringBootDataModel, Long>{

}
