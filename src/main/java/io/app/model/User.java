package io.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    @Column(columnDefinition = "boolean default true")
    private Boolean isActive=true;
    private Long parentId;
    @ManyToMany
    private List<Bot> bots;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void preCreate(){
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
        this.isActive=true;
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set set=Set.of(
                new SimpleGrantedAuthority(this.getRole().name())
        );
        return set;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
