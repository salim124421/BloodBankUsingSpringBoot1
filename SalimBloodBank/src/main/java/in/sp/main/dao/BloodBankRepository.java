package in.sp.main.dao;

import in.sp.main.entities.BloodBankModel;
import in.sp.main.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends CrudRepository<BloodBankModel,Long> {

//@Query("select ")
}
