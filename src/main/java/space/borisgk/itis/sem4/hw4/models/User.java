package space.borisgk.itis.sem4.hw4.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 6)
    private String name;
    @Column
    @NotNull
    @Pattern(regexp = ".+@.+")
    private String email;
    @Column
    private String country;
    @Column
    private Boolean gender;
    @Column
    private Boolean subscript;
}
