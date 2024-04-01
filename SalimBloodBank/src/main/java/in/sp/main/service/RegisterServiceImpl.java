package in.sp.main.service;

import in.sp.main.entities.UserModel;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RegisterServiceImpl implements RegisterService
{
	@Autowired
	UserRepository userRepository;



	@Override
	public void registerService(RegisterDTO registerDTO)
	{

		UserModel user = new UserModel();
		// check if user with same name exists
		Optional<UserModel> getUser = userRepository.findByUserEmail(registerDTO.getUserEmail());
		if(!getUser.isEmpty())
		{
			throw new RuntimeException("user by this email already exists");
		}

		// Check if user with the same phone number or username already exists
		Iterable<UserModel> fetchUsers = userRepository.findAll();
		for (UserModel fetchedUser : fetchUsers) {
			if ((registerDTO.getPhoneNumber() == fetchedUser.getPhoneNumber()) && registerDTO.getUsername().equals(fetchedUser.getUsername())) {
				throw new RuntimeException("User already exists");
			}
		}

		// Validate username
		if (registerDTO.getUsername().trim().isEmpty()) {
			throw new IllegalArgumentException("Username cannot be empty or contain only whitespace");
		}


		// Validate phone number length
		if (String.valueOf(registerDTO.getPhoneNumber()).length() != 10) {
			throw new IllegalArgumentException("Phone number must be of 10 digits");
		}

		// Validate address
		if (registerDTO.getCity().trim().isEmpty()) {
			throw new IllegalArgumentException("Address cannot be empty or contain only whitespace");
		}

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirthConverted = LocalDate.parse(registerDTO.getDateOfBirth(), formatter);

		user.setUsername(registerDTO.getUsername());
		user.setUserEmail(registerDTO.getUserEmail());
		user.setCity(registerDTO.getCity());

		if(registerDTO.getRole()==null) user.setRole("user");

		else user.setRole("agent");
		user.setDateOfBirth(dateOfBirthConverted);
		user.setGender(registerDTO.getGender());
		user.setPhoneNumber(registerDTO.getPhoneNumber());
		user.setGender(registerDTO.getGender());
		user.setPassword(registerDTO.getPassword());
        user.setBloodGroup(registerDTO.getBloodGroup());
        user.setCreatedOn(LocalDate.now());
        user.setCreatedBy("admin");
        user.setBlockStatus("unblocked");

		userRepository.save(user);

	}
}
