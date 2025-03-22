package com.mycompany.LeaveManagementSystem;

import com.mycompany.LeaveManagementSystem.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LeaveManagementSystemApplication implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public static void main(String[] args) {
        SpringApplication.run(LeaveManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql ="select * from Users";
        List<User> users=jdbcTemplate.query(sql, 
                BeanPropertyRowMapper.newInstance(User.class));
        users.forEach(System.out :: println );
        
    }
}
