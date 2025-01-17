package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.Address;
import com.spring.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
    int countByUser(User user);

    List<Address> findByUserId(Long userID);

    Optional<Address> findByIdAndUser_Id(long id, long userId);

    List<Address> findByUser_IdAndIsDefaultTrue(long userId);
}
