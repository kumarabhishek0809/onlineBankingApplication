package com.onlineBankingApplication.activiti.config;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringActivitiConfig {
	
//	 @Bean
//	  public CommandLineRunner init(final RepositoryService repositoryService,
//	                                final RuntimeService runtimeService,
//	                                final TaskService taskService) {
//
//	      return new CommandLineRunner() {
//	          @Override
//	          public void run(String... strings) throws Exception {
//	              Map<String, Object> variables = new HashMap<String, Object>();
//	              variables.put("applicantName", "John Doe");
//	              variables.put("email", "john.doe@activiti.com");
//	              variables.put("phoneNumber", "123456789");
//	              runtimeService.startProcessInstanceByKey("hireProcess", variables);
//	          }
//	      };
//
//	  }
		
	 
	@Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

//                Group group = identityService.newGroup("user");
//                group.setName("users");
//                group.setType("security-role");
//                identityService.saveGroup(group);
//
//                UserDetailsData admin = identityService.newUser("admin");
//                admin.setPassword("admin");
//                identityService.saveUser(admin);

            }
        };
    }
}
