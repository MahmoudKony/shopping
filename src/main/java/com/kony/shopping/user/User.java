package com.kony.shopping.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "shopping", name = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int userId;


    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String  password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Gender gender;
    @Column(name = "social_media_profiles")
    String socialMediaProfiles;
    @Column(name = "date_of_birth",
            nullable = false)
    LocalDate dateOfBirth = LocalDate.now();
    @Column(name = "created_at")
    LocalDate createdAt = LocalDate.now();
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL )
    private List<Address> address;


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
