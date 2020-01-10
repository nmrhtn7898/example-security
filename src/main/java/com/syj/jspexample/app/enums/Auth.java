package com.syj.jspexample.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Auth {

    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

}
