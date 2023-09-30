package org.edu.uy.proyectospring.models.orders;

import org.edu.uy.proyectospring.models.DeliveryDTO;
import org.edu.uy.proyectospring.models.DeliveryInfo;
import org.edu.uy.proyectospring.models.PaymentDTO;
import org.edu.uy.proyectospring.models.PaymentInfo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderWithIdDTO extends OrderDTO{
	
	
	private Long id;
	
	private Double total;
	
	@NotNull(groups = PaymentInfo.class)
	private PaymentDTO payment;
	
	@NotNull(groups = DeliveryInfo.class)
	private DeliveryDTO delivery;
}
