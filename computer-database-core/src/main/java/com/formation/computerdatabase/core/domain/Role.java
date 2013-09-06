package com.formation.computerdatabase.core.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable, GrantedAuthority {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum AuthorityType {
        ROLE_ADMIN, ROLE_BASIC;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false, unique = true)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private AuthorityType authority;

    public Role() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public void setAuthority(String role) {
        this.authority = AuthorityType.valueOf(role);
    }

    public void setRoleId(Integer roleId) {
        this.id = roleId;
    }

}