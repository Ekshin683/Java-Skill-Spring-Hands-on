//Mockito: in unit testing it is used to mock the object or methods which are not yet implemetnted.But in mockito we can mock objects and methods without implementing the code.
//Moclito:
// UserRepository.java
public interface UserRepository {
    User findById(int id);
    void save(User user);
    boolean existsByEmail(String email);
}

// User.java
public class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

// UserService.java
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
    }

    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Email already registered: " + user.getEmail());
        }
        userRepository.save(user);
    }
};

//Insted of mocking the arguments we can use ArgumentMatching which helps to match arguments with it's datatype, pattern and custom.
//Argument Matchers:
import static org.mockito.ArgumentMatchers.*;

@Test
void shouldUseArgumentMatchers() {
    // anyInt() matches any integer argument
    when(userRepository.findById(anyInt())).thenReturn(sampleUser);

    User result = userService.getUserById(42);
    assertNotNull(result);

    // anyString() matches any String
    when(userRepository.existsByEmail(anyString())).thenReturn(false);
    userService.registerUser(sampleUser);

    // verify with argument capture
    verify(userRepository).save(argThat(user ->
            user.getEmail().contains("@")  // custom condition
    ));
}

ArgumentCaptor helps to capture the arguments passed to method excatly instead of mocking the arguments.
//Argument Captor:
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

@ExtendWith(MockitoExtension.class)
class ArgumentCaptorTest {

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void shouldCaptureUserPassedToSave() {
        UserService service = new UserService(userRepository);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);

        service.registerUser(new User(5, "Bob", "bob@example.com"));

        verify(userRepository).save(userCaptor.capture());
        User captured = userCaptor.getValue();

        assertEquals("Bob", captured.getName());
        assertEquals("bob@example.com", captured.getEmail());
    }
}
public class Mockito {
    
}
