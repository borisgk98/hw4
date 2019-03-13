package space.borisgk.itis.sem4.hw4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.borisgk.itis.sem4.hw4.models.MessageSourceItem;

import java.util.Locale;

@Repository
public interface MessageSourceItemRepository extends JpaRepository<MessageSourceItem, String> {

    MessageSourceItem findOneByNameAndLocale(String name, Locale locale);
}
