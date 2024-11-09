DROP TABLE IF EXISTS `role_permissions`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `permissions`;

-- Dumping structure for table delivery.permissions
CREATE TABLE IF NOT EXISTS `permissions`
(
    `id`
        bigint
        NOT
            NULL
        AUTO_INCREMENT,
    `name`
        varchar(255) DEFAULT NULL,
    PRIMARY KEY
        (
         `id`
            )
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table delivery.permissions: ~14 rows (approximately)
INSERT INTO `permissions` (`id`, `name`)
VALUES (1, 'USER_CHANGE_PASSWORD'),
       (3, 'PROMOTION_GET'),
       (4, 'ORDER_DELETE'),
       (5, 'ORDER_SUBMIT'),
       (6, 'ORDER_CANCEL'),
       (7, 'ORDER_UPDATE'),
       (8, 'ORDER_GET'),
       (9, 'RATING_CREATE'),
       (10, 'PROMOTION_USE'),
       (11, 'USER_READ_INFO'),
       (12, 'USER_CHANGE_INFO'),
       (13, 'ORDER_CREATE'),
       (14, 'CRUD_ADDRESS');

-- Dumping structure for table delivery.roles
CREATE TABLE IF NOT EXISTS `roles`
(
    `id`
        bigint
        NOT
            NULL
        AUTO_INCREMENT,
    `name`
        enum
            (
                'ADMIN',
                'SHIPPER',
                'USER'
                ) DEFAULT NULL,
    PRIMARY KEY
        (
         `id`
            )
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table delivery.roles: ~3 rows (approximately)
INSERT INTO `roles` (`id`, `name`)
VALUES (1, 'USER'),
       (2, 'ADMIN'),
       (3, 'SHIPPER');

-- Dumping structure for table delivery.user_permissions
CREATE TABLE IF NOT EXISTS `user_permissions`
(
    `user_id`
        bigint
        NOT
            NULL,
    `permission_id`
        bigint
        NOT
            NULL,
    PRIMARY
        KEY
        (
         `user_id`,
         `permission_id`
            ),
    KEY `fk_authority`
        (
         `permission_id`
            ),
    CONSTRAINT `FK6y0u41do0gynbgvlwnqngjudf` FOREIGN KEY
        (
         `permission_id`
            ) REFERENCES `permissions`
            (
             `id`
                ),
    CONSTRAINT `fk_authority` FOREIGN KEY
        (
         `permission_id`
            ) REFERENCES `permissions`
            (
             `id`
                ),
    CONSTRAINT `fk_user` FOREIGN KEY
        (
         `user_id`
            ) REFERENCES `users`
            (
             `id`
                ),
    CONSTRAINT `FKhiiib540jf74gksgb87oofni` FOREIGN KEY
        (
         `user_id`
            ) REFERENCES `users`
            (
             `id`
                )
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table delivery.user_permissions: ~12 rows (approximately)
INSERT INTO `user_permissions` (`user_id`, `permission_id`)
VALUES (1, 1),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (1, 11),
       (1, 12),
       (1, 13),
       (1, 14);

/*!40103 SET TIME_ZONE = IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES = IFNULL(@OLD_SQL_NOTES, 1) */;
