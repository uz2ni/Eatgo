package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

	private List<Restaurant> restaurants = new ArrayList<>();

	public RestaurantRepositoryImpl() {
		restaurants.add(new Restaurant(1004L,"Bob zip", "Seoul"));
		restaurants.add(new Restaurant(2020L,"Cyber Food", "Seoul"));
	}

	@Override
	public List<Restaurant> findAll() {
		return this.restaurants;
	}

	@Override
	public Restaurant findById(Long id) {
		// Java8 추가. filter 기능
		return restaurants.stream()
				.filter(r -> r.getId().equals(id))
				.findFirst()
		        .orElse(null); // 없는 경우 테스트할 때 사용. 없는 경우 null.

	}
}
