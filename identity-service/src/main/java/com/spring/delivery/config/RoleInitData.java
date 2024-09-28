/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:42 PM - 10/08/2024
 * User: lam-nguyen
 **/
package com.spring.delivery.config;

import java.util.*;

import com.spring.delivery.model.Permission;
import com.spring.delivery.repository.PermissionRepository;
import com.spring.delivery.util.enums.RoleEnum;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import com.spring.delivery.model.Role;
import com.spring.delivery.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Slf4j
//@Component
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoleInitData {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    //    Identity
    Permission userChangeInfo = Permission.builder().name("USER_CHANGE_INFO").build();
    Permission userChangePassword = Permission.builder().name("USER_CHANGE_PASSWORD").build();

    //    Profile
    Permission userReadInfo = Permission.builder().name("USER_READ_INFO").build();

    //Promotion
    Permission promotionGet = Permission.builder().name("PROMOTION_GET").build();
    Permission promotionUse = Permission.builder().name("PROMOTION_USE").build();

    //    Order
    Permission orderGet = Permission.builder().name("ORDER_GET").build();
    Permission orderCreate = Permission.builder().name("ORDER_CREATE").build();
    Permission orderUpdate = Permission.builder().name("ORDER_UPDATE").build();
    Permission orderSubmit = Permission.builder().name("ORDER_SUBMIT").build();
    Permission orderCancel = Permission.builder().name("ORDER_CANCEL").build();
    Permission orderDelete = Permission.builder().name("ORDER_DELETE").build();
    Permission orderGetDetail = Permission.builder().name("ORDER_GET_DETAIL").build();

    //    Rating
    Permission ratingCreate = Permission.builder().name("RATING_CREATE").build();

    @PostConstruct
    public void initRole() {
        log.info("Init roles and permissions");

        // Create and save permissions
        Map<String, Permission> permissionMap = new HashMap<>();
        // Store permissions in a map for easy access
        permissionMap.put("USER_CHANGE_INFO", userChangeInfo);
        permissionMap.put("USER_CHANGE_PASSWORD", userChangePassword);
        permissionMap.put("USER_READ_INFO", userReadInfo);
        permissionMap.put("PROMOTION_GET", promotionGet);
        permissionMap.put("PROMOTION_USE", promotionUse);
        permissionMap.put("ORDER_GET", orderGet);
        permissionMap.put("ORDER_CREATE", orderCreate);
        permissionMap.put("ORDER_UPDATE", orderUpdate);
        permissionMap.put("ORDER_SUBMIT", orderSubmit);
        permissionMap.put("ORDER_CANCEL", orderCancel);
        permissionMap.put("ORDER_DELETE", orderDelete);
        permissionMap.put("ORDER_GET_DETAIL", orderGetDetail);
        permissionMap.put("RATING_CREATE", ratingCreate);
        permissionRepository.saveAll(permissionMap.values());

        // Create roles using the permission map
        Role userRole = Role.builder()
                .name(RoleEnum.USER)
                .permissions(new HashSet<>(Arrays.asList(
                        permissionMap.get("USER_CHANGE_INFO"),
                        permissionMap.get("USER_CHANGE_PASSWORD"),
                        permissionMap.get("USER_READ_INFO"),
                        permissionMap.get("PROMOTION_GET"),
                        permissionMap.get("PROMOTION_USE"),
                        permissionMap.get("ORDER_GET"),
                        permissionMap.get("ORDER_CREATE"),
                        permissionMap.get("ORDER_CANCEL"),
                        permissionMap.get("ORDER_GET_DETAIL"),
                        permissionMap.get("ORDER_HISTORY"),
                        permissionMap.get("RATING_CREATE")
                )))
                .build();

        Role adminRole = Role.builder()
                .name(RoleEnum.ADMIN)
                .permissions(new HashSet<>(Arrays.asList(
                        permissionMap.get("USER_CHANGE_INFO"),
                        permissionMap.get("USER_CHANGE_PASSWORD"),
                        permissionMap.get("USER_READ_INFO"),
                        permissionMap.get("ORDER_GET"),
                        permissionMap.get("ORDER_GET_DETAIL"),
                        permissionMap.get("ORDER_HISTORY"),
                        permissionMap.get("ORDER_UPDATE"),
                        permissionMap.get("ORDER_SUBMIT")
                )))
                .build();

        Role shipperRole = Role.builder()
                .name(RoleEnum.SHIPPER)
                .permissions(new HashSet<>(Arrays.asList(
                        permissionMap.get("USER_CHANGE_INFO"),
                        permissionMap.get("USER_CHANGE_PASSWORD"),
                        permissionMap.get("USER_READ_INFO"),
                        permissionMap.get("PROMOTION_GET"),
                        permissionMap.get("PROMOTION_USE"),
                        permissionMap.get("ORDER_GET"),
                        permissionMap.get("ORDER_CREATE"),
                        permissionMap.get("ORDER_CANCEL"),
                        permissionMap.get("ORDER_GET_DETAIL"),
                        permissionMap.get("ORDER_HISTORY"),
                        permissionMap.get("RATING_CREATE"),
                        permissionMap.get("ORDER_UPDATE"),
                        permissionMap.get("ORDER_DELETE"),
                        permissionMap.get("ORDER_SUBMIT")
                )))
                .build();

        roleRepository.saveAll(Arrays.asList(userRole, adminRole, shipperRole));
    }
}
