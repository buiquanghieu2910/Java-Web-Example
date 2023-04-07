/**
 * BUI_QUANG_HIEU, 2023
 * User.java
 */
package domain_models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author BUI_QUANG_HIEU
 */

@Entity
@Table(name = "users")
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, length = 50)
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
