package pl.coderslab.app.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.mindrot.jbcrypt.BCrypt;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User  {

    @Id
    @GeneratedValue
    private long id;

    @Size(min = 5, max = 30)
    @Column(unique = true)
    private String userName;

    @NotEmpty
    @Size(min = 5)
    private String password;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    private int status;


    public void setPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashed;
    }

    public String getPassword() {
        return password;
    }



}
