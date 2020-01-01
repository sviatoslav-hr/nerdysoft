package com.khrystyna.nerdysoft.models;

import com.khrystyna.nerdysoft.security.AbstractUserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractUserDetails {
    @Id
    private String id;
    private String username;
    private String password;
}
