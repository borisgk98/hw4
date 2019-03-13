package space.borisgk.itis.sem4.hw4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.borisgk.itis.sem4.hw4.models.User;

@Repository
interface UserRepository extends JpaRepository<User, Long> {
}
