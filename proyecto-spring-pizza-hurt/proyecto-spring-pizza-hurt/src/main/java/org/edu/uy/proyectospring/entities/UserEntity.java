package org.edu.uy.proyectospring.entities;

import java.util.ArrayList;
import java.util.List;

import org.edu.yt.proyectospring.entities.constraints.EmailConstraint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class UserEntity extends BaseEntity {
	
	@NotNull
	private String fullName;
	
	@NotNull
	@EmailConstraint	
	@Column(unique=true)
	private String email;
	
	@NotNull
	private String telephone;
	
	@NotNull
	private String password;

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
	
	public UserEntity() {
		// TODO Auto-generated constructor stub
	}



}
