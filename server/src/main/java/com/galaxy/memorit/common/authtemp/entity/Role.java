package com.galaxy.memorit.common.authtemp.entity;

import java.util.Arrays;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role {

    USER("USER", "일반 사용자 권한"),
    ADMIN("ADMIN", "관리자 권한"),
    GUEST("GUEST", "게스트 권한");

    private final String code;
    private final String dispalyName;

    public static Role of(String code) {
        return Arrays.stream(Role.values())
            .filter(r -> r.getCode().equals(code))
            .findAny()
            .orElse(GUEST);
    }

    public static boolean isOf(Collection<? extends GrantedAuthority> authorities, Role role) {
        if (authorities == null) {
            return false;
        }

        for (GrantedAuthority grantedAuthority : authorities) {
            if (role.getCode().equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }
}
