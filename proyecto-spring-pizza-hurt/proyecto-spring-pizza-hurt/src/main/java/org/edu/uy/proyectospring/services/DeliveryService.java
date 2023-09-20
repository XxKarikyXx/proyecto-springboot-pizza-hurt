package org.edu.uy.proyectospring.services;

import org.edu.uy.proyectospring.repositories.DeliveryRepository;
import org.edu.uy.proyectospring.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
	
	private DeliveryRepository deliveryRepository;
	
	private OrderRepository orderRepository;

	public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository) {
		super();
		this.deliveryRepository = deliveryRepository;
		this.orderRepository = orderRepository;
	}
	
	
}
