package com.health.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Constants {

    private Constants() {
            // restrict instantiation
    }

    public static final Long ACTIVE=1L;
    public static final Long IN_ACTIVE=0L;


}
