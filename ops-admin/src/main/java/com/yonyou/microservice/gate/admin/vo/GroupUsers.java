package com.yonyou.microservice.gate.admin.vo;

import com.yonyou.microservice.gate.admin.entity.User;

import java.util.List;

/**
 * @author joy
 */
public class GroupUsers {
    private Integer id;
    private String code;
	private String name;
    List<User> members ;
    List<User> leaders;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public GroupUsers() {
    }

    public GroupUsers(List<User> members, List<User> leaders) {
        this.members = members;
        this.leaders = leaders; 
    }
    
    public GroupUsers(List<User> members, List<User> leaders,int id,String code,String name) {
        this.members = members;
        this.leaders = leaders;
        this.id= id;
        this.name=name;
        this.code=code;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<User> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<User> leaders) {
        this.leaders = leaders;
    }
}
