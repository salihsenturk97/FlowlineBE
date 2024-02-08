package com.flowline.ws.core.exceptions;

import com.flowline.ws.core.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class NotFoundException extends RuntimeException{

    public NotFoundException(long id) {
        super(Messages.getMessageForLocale("flowline.user.not.found",LocaleContextHolder.getLocale(),id));
    }
}
