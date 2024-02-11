package com.skyline.bert;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skyline.bert.config.AppConstant;
import com.skyline.bert.dao.RoleRepo;
import com.skyline.bert.entity.Role;



@SpringBootApplication
public class BlogAppApis1Application implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApis1Application.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		 
		System.out.println(this.passwordEncoder.encode("xyz"));
		System.out.println(passwordEncoder.matches("xyz", "$2a$12$w6xzpUJdb8378P4m0H4GSu7ZbB2KwTgI.Ej4fcCAWQl.9asvxjPNW"));
	    try {
	    	
	    	Role role1=new Role();
	    	role1.setId(AppConstant.ADMIN_USER);
	    	role1.setName("ADMIN_USER");
	    	
	    	Role role2=new Role();
	    	role2.setId(AppConstant.NORMAL_USER);
	    	role2.setName("NORMAL_USER");
	    	
	    	List<Role> roles=List.of(role1,role2);
	    	
	    	List<Role> result=this.roleRepo.saveAll(roles);
	    	
	    	result.forEach(r->System.out.println(r));
	    }	
	    catch(Exception e) {
	    	
	    }
	    	
	    		
	    
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
