/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:42 PM - 10/08/2024
 * User: lam-nguyen
 **/
package com.spring.delivery.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.delivery.model.Role;
import com.spring.delivery.repository.RoleRepository;
import com.spring.delivery.util.enums.RoleEnum;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

//@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoleUserConfig {
	RoleRepository roleRepository;

	@Bean
	public boolean initRole() {
		List<String> roleInRepository =
				roleRepository.findAll().stream().map(Role::getName).toList();
		List<RoleEnum> roles = Arrays.asList(RoleEnum.values());
		if (roleInRepository.size() == roles.size()) return false;

		roles.forEach(role -> {
			String roleName = role.name();
			if (roleInRepository.contains(roleName)) return;

			roleRepository.save(Role.builder().name(roleName).build());
		});

		return true;
	}
}
