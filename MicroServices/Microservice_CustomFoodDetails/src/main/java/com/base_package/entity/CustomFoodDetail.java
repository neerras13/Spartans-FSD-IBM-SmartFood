package com.base_package.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.base_package.DTO.CustomFoodDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomFoodDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String uuid;
	private String quantity;
	private String imageLink;
	private Double price;
	private Float rating;
	
	private String foodUuid;
	private String restaurantUuid;
	
	public CustomFoodDetail(String quantity, String imageLink, Double price, Float rating) {
		super();
		this.quantity = quantity;
		this.imageLink = imageLink;
		this.price = price;
		this.rating = rating;
		this.setUuid("CFD_"+UUID.randomUUID().toString());
	}

	public void setDetails(CustomFoodDetailDTO customFoodDetailDTO) {
		this.setQuantity(customFoodDetailDTO.getQuantity());
		this.setImageLink(customFoodDetailDTO.getImageLink());
		this.setPrice(customFoodDetailDTO.getPrice());
		this.setRating(customFoodDetailDTO.getRating());
	}
	
}
