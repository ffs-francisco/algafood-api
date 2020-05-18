package com.ffs.algafood.jpa;

import com.ffs.algafood.ApiApplication;
import com.ffs.algafood.domain.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static java.lang.String.format;

/**
 *
 * @author francisco
 */
@Slf4j
public class SearchPermissionMain {

    public static void main(String[] args) {
        var appContext = new SpringApplicationBuilder(ApiApplication.class).web(WebApplicationType.NONE).run(args);
        var permissionRepository = appContext.getBean(PermissionRepository.class);

        log.info("Todas as permissões");
        permissionRepository.findAll().forEach(permission -> {
            log.info(format("%s - %s", permission.getName(), permission.getDescription()));
        });
    }
}
