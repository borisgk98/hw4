package space.borisgk.itis.sem4.hw4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import space.borisgk.itis.sem4.hw4.models.MessageSourceItem;
import space.borisgk.itis.sem4.hw4.repositories.MessageSourceItemRepository;

import java.util.Locale;

public class DatabaseMessageSource implements MessageSource {
    @Autowired
    private MessageSourceItemRepository repository;

    @Override
    public String getMessage(String s, Object[] objects, String s1, Locale locale) {
        return null;
    }

    @Override
    public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
        MessageSourceItem item = repository.findOneByNameAndLocale(s, locale);
        return item.getValue();
    }

    @Override
    public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }
}
