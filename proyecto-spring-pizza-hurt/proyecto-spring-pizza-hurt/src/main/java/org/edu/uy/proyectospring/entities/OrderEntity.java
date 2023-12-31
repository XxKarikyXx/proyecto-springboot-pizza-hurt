package org.edu.uy.proyectospring.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class OrderEntity extends BaseEntity{
	
	//Para que se creen las pizzas
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			  name = "OrderPizzas", 
			  joinColumns = @JoinColumn(name = "order_id"), 
			  inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	private List<Pizza> pizzas = new ArrayList<Pizza>();
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="payment_id")
	private Payment payment;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="delivery_id")
	private Delivery delivery;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDateTime;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	
	private Double totalPrice;


	public OrderEntity(List<Pizza> pizzas, Payment payment, Delivery delivery, Date orderDateTime, UserEntity user,
			Double totalPrice) {
		super();
		this.pizzas = pizzas;
		this.payment = payment;
		this.delivery = delivery;
		this.orderDateTime = orderDateTime;
		this.user = user;
		this.totalPrice = totalPrice;
	}
	
	public void addPizza(Pizza pizza) {
		this.pizzas.add(pizza);
	}

}
