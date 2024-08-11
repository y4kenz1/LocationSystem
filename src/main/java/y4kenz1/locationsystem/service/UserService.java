package y4kenz1.locationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y4kenz1.locationsystem.model.*;
import y4kenz1.locationsystem.repository.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityMapper mapper;

    public UserDTO registerUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return mapper.toUserDTO(user);
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(mapper::toUserDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toUserDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}