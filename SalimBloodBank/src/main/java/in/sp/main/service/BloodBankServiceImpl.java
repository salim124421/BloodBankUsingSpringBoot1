package in.sp.main.service;

import in.sp.main.dao.BloodBankRepository;
import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.entities.BloodBankModel;
import in.sp.main.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BloodBankServiceImpl implements BloodBankService{

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Override
    public void bloodBankService(BloodBankDTO bloodBankDTO, RegisterDTO registerDTO) {

        BloodBankModel bloodBankModel=new BloodBankModel();

        bloodBankModel.setBloodGroup(bloodBankDTO.getBloodGroup());
        bloodBankModel.setStatus("pending");
        bloodBankModel.setCreatedBy(registerDTO.getCreatedBy());
        bloodBankModel.setQuantity(bloodBankDTO.getQuantity());
        bloodBankModel.setCreatedAt(String.valueOf(LocalDateTime.now()));
        bloodBankModel.setRequestType(bloodBankDTO.getRequestType());
        bloodBankModel.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        bloodBankModel.setUpdatedBy("admin");
        bloodBankModel.setId(bloodBankDTO.getId());

        bloodBankRepository.save(bloodBankModel);
    }
//
//    @Override
//    public List<BloodBankModel> getAllBloodRequest() {
//        return (List<BloodBankModel>) bloodBankRepository.findAll();
//    }

}
