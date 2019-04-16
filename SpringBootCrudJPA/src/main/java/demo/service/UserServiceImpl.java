package demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.User;
import demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUser() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

}
