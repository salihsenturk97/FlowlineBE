package com.flowline.ws.core.exceptions;

import com.flowline.ws.core.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super(Messages.getMessageForLocale( "flowline.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
