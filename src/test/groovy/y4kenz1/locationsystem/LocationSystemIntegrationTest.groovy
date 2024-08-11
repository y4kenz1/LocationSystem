package y4kenz1.locationsystem

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification
import org.springframework.boot.test.context.SpringBootTest
import y4kenz1.locationsystem.model.LocationDTO
import y4kenz1.locationsystem.model.UserDTO
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationServiceIntegrationTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def "should create user, location, and share it"() {
        given:
        def user = new UserDTO(name: "Alice", email: "alice@example.com")
        def friend = new UserDTO(name: "Bob", email: "bob@example.com")

        when:
        def userResponse = restTemplate.postForEntity("/users", user, UserDTO)
        def friendResponse = restTemplate.postForEntity("/users", friend, UserDTO)
        def locationResponse = restTemplate.postForEntity("/locations", new LocationDTO(name: "Home", address: "123 Main St", ownerId: userResponse.body.id), LocationDTO)

        then:
        userResponse.statusCode == HttpStatus.CREATED
        friendResponse.statusCode == HttpStatus.CREATED
        locationResponse.statusCode == HttpStatus.CREATED

        when:
        def shareLocation = restTemplate.postForEntity("/locations/${locationResponse.body.id}/share?friendUserId=${friendResponse.body.id}&accessType=READ_ONLY", null, Void)

        then:
        shareLocation.statusCode == HttpStatus.OK

        when:
        def friendsOnLocation = restTemplate.getForEntity("/locations/${locationResponse.body.id}/friends", List)

        then:
        friendsOnLocation.statusCode == HttpStatus.OK
        friendsOnLocation.body.size() == 1
        friendsOnLocation.body[0].email == "bob@example.com"
    }
}