package y4kenz1.locationsystem

import spock.lang.Specification
import y4kenz1.locationsystem.model.User
import y4kenz1.locationsystem.model.UserDTO
import y4kenz1.locationsystem.service.UserService
import y4kenz1.locationsystem.repository.UserRepository
import y4kenz1.locationsystem.model.EntityMapper

class UserServiceTest extends Specification {

    def userRepository = Mock(UserRepository)
    def mapper = Mock(EntityMapper)
    def userService = new UserService(userRepository: userRepository, mapper: mapper)

    def "should register a user"() {
        given:
        def userDTO = new UserDTO(name: "John Doe", email: "john@example.com")
        def user = new User(name: "John Doe", email: "john@example.com")
        user.id = 1L

        when:
        def result = userService.registerUser(userDTO.name, userDTO.email)

        then:
        1 * userRepository.save(_) >> { User u ->
            u.id = 1L
            return u
        }
        1 * mapper.toUserDTO(_) >> { User u ->
            new UserDTO(id: u.id, name: u.name, email: u.email)
        }
        result.id == 1L
        result.name == "John Doe"
        result.email == "john@example.com"
    }
}