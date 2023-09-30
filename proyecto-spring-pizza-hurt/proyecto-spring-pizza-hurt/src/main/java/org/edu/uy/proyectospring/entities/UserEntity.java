package org.edu.uy.proyectospring.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.edu.yt.proyectospring.entities.constraints.EmailConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class UserEntity implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String fullName;
	
	@NotNull
	@EmailConstraint	
	@Column(unique=true)
	private String email;
	
	@NotNull
	private String telephone;
	
	@OneToMany
	@JoinTable(
			  name = "UserCards", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "card_id"))
	private List<Card> cards = new ArrayList<Card>();
	
	
	@OneToMany(mappedBy = "user")
	private List<OrderEntity> orders= new ArrayList<OrderEntity>();;
	
	@Column(columnDefinition="boolean default true")
	private boolean active;

	public UserEntity(@NotNull String fullName, @NotNull String email, @NotNull String telephone,
			@NotNull String password, List<Card> cards, List<OrderEntity> orders, boolean active) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
		this.cards = cards;
		this.orders = orders;
		this.active = active;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
