package com.javahunter.application.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Firstname is mandatory")
    @Size(max = 50, message = "Firstname should not be more than 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    @Size(max = 50, message = "Lastname should not be more than 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email should not be more than 100 characters")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "PhoneNumber is mandatory")
    @Size(max = 20, message = "PhoneNumber should not be more than 20 characters")
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 50, message = "Username should not be more than 50 characters")
    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Address> addresses = new HashSet<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
