package com.example.multijdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.example.multijdbc.model.User;

@Service
public class OneService {
    @Autowired
    @Qualifier("jdbcTemplateOne")
    JdbcTemplate jdbcTemplate;    
    
    public int addUser(User user) {
        int result = jdbcTemplate.update("insert into user (username,address) values(?,?)", user.getUsername(), user.getAddress());
        return result;
    }

    public int addUser2(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into user (username,address) values(?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getAddress());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return result;
    }

    public int deleteById(String tableName, Long id) throws Exception {
		String sqlString = "DELETE FROM "+tableName+" WHERE id=?";
		return jdbcTemplate.update(sqlString, id);
    }    

    public int updateById(String tableName, Long id,String username) {
    	String sqlString = "update " + tableName + " set username=? where id=?";
        return jdbcTemplate.update(sqlString, username, id);
    }

    public List<User> getAllUsers() {
        List<User> list = jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");
                long id = resultSet.getLong("id");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setAddress(address);
                return user;
            }
        });
        return list;
    }
    
    public List<User> getAllUsers2() {
        List<User> list = jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
        return list;
    }
}
