package com.example.multijdbc.controller;

import com.example.multijdbc.jwt.AjaxResult;
import com.example.multijdbc.model.User;
import com.example.multijdbc.service.OneService;
import com.example.multijdbc.service.TwoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by flyniu666 on 17/06/22.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    private OneService oneSrv ;
    @Resource
    private TwoService twoSrv;
    
    @GetMapping("/{dbName}")
    public ResponseEntity<?> getUser1(@PathVariable(value = "dbName") String dbName) {
    	if(dbName.compareTo("one")==0) {
    		return ResponseEntity.ok().body(oneSrv.getAllUsers());
    	}else if(dbName.compareTo("two")==0) {
    		return ResponseEntity.ok().body(twoSrv.getAllUsers());
    	}else {
    		return ResponseEntity.status(500).body("Didn't find database");
    	}
    }
    
    
    @PostMapping("/{dbName}/user")
    public ResponseEntity<?> insertUser(@PathVariable(value = "dbName") String dbName, @Valid @RequestBody User user) {
    	Integer reslut=0;
    	try {
	    	if(dbName.compareTo("one")==0) {
	    		reslut = oneSrv.addUser(user);
	    	}else if(dbName.compareTo("two")==0) {
	    		reslut = twoSrv.addUser(user);
	    	}else {
	    		return ResponseEntity.status(500).body("Didn't find database");
	    	}
	    	
    		return ResponseEntity.ok().body("Insert  " + reslut + " row");
    	} catch (Exception e) {
			return ResponseEntity.status(500).body(e.toString());
		}
    }
    
    @PutMapping("/{dbName}/{table}/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "dbName") String dbName,@PathVariable(value = "table") String tableName,@PathVariable(value = "id") Long id, @Valid @RequestBody String name) {
    	Integer reslut=0;
    	try {
	    	if(dbName.compareTo("one")==0) {
	    		reslut = oneSrv.updateById(tableName, id, name);
	    	}else if(dbName.compareTo("two")==0) {
	    		reslut = twoSrv.updateById(tableName, id, name);
	    	}else {
	    		return ResponseEntity.status(500).body("Didn't find database");
	    	}
	    	
	    	return ResponseEntity.ok().body("Update  " + reslut + " row");
    	} catch (Exception e) {
			return ResponseEntity.status(500).body(e.toString());
		}
    }
    
    
    
    @DeleteMapping("/{dbName}/{table}/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "dbName") String dbName, @PathVariable(value = "table") String tableName, @PathVariable(value = "id") Long userId) {
    	Integer rows;
    	try {
        	if(dbName.compareTo("one")==0) {
        		rows = oneSrv.deleteById(tableName, userId);
        	}else if(dbName.compareTo("two")==0) {
        		rows = twoSrv.deleteById(tableName, userId);
        	}else {
        		return ResponseEntity.status(500).body("Didn't find database");
        	}        	
       		return ResponseEntity.ok().body("Delete " + rows + " rows");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.toString());
		}
    }   
}
