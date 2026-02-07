package io.app.dto;

import io.app.model.BillingType;
import io.app.model.Role;
import io.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private Role role;
    private BillingType billingType;
    private Boolean isActive=true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User mapToUser(){
        return User.builder()
                .id(this.id)
                .userName(this.userName)
                .password(this.password)
                .role(this.role)
                .billingType(this.billingType)
                .isActive(this.isActive)
                .updatedAt(this.updatedAt)
                .createdAt(this.createdAt)
                .build();
    }
}
