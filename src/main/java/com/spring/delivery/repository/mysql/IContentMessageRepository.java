/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:07 AM - 16/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.repository.mysql;

import com.spring.delivery.model.ContentMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContentMessageRepository extends JpaRepository<ContentMessage, Long> {
}
