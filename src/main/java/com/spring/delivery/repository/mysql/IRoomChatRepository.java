/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:04 AM - 16/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoomChatRepository extends JpaRepository<RoomChat, Long> {

    @Query("""
            select r from RoomChat as r
                        join UserInRomChat as u on u.room.id = r.id
            where r.id = :room_id
            and u.user.id = :user_id
            """)
    Optional<RoomChat> findByIdAndUser_Id(@Param("room_id") long roomId, @Param("user_id") long userId);
}
