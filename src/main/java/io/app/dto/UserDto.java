package io.app.dto;

import io.app.model.BillingType;
import io.app.model.Bot;
import io.app.model.Role;
import io.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Role role;
    private BillingType billingType;
    private Long parentId;
    private Boolean isActive=true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Bot> bots;

    public User mapToUser(){
        return User.builder()
                .id(this.id)
                .userName(this.userName)
                .password(this.password)
                .role(this.role)
                .billingType(this.billingType)
                .isActive(this.isActive)
                .bots(this.bots)
                .email(this.email)
                .parentId(this.parentId)
                .updatedAt(this.updatedAt)
                .createdAt(this.createdAt)
                .build();
    }
}
