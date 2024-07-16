package com.example.firstproject.data.DTO.v1;

import com.example.firstproject.model.Role;

import java.util.Set;

public class UserDTO {
    private Integer id;
    private String login;
    private Role role;
    private Boolean enabled;
    private Set<String> authorities;

    public UserDTO() { }

    public UserDTO(Integer id, String login, Role role, Boolean enabled, Set<String> authorities) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
