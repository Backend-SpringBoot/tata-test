package tata.test.common.messages;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSourceUtil {

  private final MessageSource messageSource;

  public String getMessage(String key) {
    Locale currentLocale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(key, null, currentLocale);
  }
}
