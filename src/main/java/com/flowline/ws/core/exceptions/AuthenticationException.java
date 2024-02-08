package com.flowline.ws.core.exceptions;

import com.flowline.ws.core.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class AuthenticationException  extends RuntimeException{
    public AuthenticationException() {
        super(Messages.getMessageForLocale("flowline.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
}
