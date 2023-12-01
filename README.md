# Backend Municipio

# Intro

                        .requestMatchers(HttpMethod.GET, "/api/user/exist/{userID}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.POST, "/api/user/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/update").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.DELETE, "/api/user/delete/{userID}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.GET, "/api/user/get/{userID}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.GET, "/api/user/id/all").hasAnyAuthority(SecurityConstants.ADMIN_ROLE_ID)

                        .requestMatchers(HttpMethod.POST, "/api/warning/create").hasAnyAuthority(SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.DELETE, "/api/warning/delete/{warningId}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID, SecurityConstants.ADMIN_ROLE_ID)
                        .requestMatchers(HttpMethod.GET, "/api/warning/get/user/{userId}").hasAnyAuthority(SecurityConstants.USER_ROLE_ID)
