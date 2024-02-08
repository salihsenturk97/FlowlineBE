package com.flowline.ws.core.exceptions;

import com.flowline.ws.core.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationException extends RuntimeException{
    public ActivationNotificationException() {
        super(Messages.getMessageForLocale("flowline.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
