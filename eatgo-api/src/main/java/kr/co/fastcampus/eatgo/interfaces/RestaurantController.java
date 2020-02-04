package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		List<Restaurant> restaurants = new ArrayList<>();

		Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
		restaurants.add(restaurant);

		return restaurants;
	}

	@GetMapping("/restaurants/{id}")
	public Restaurant detail(@PathVariable("id") Long id) {
		List<Restaurant> restaurants = new ArrayList<>();

		restaurants.add(new Restaurant(20L, "Bob zip", "Seoul"));
		restaurants.add(new Restaurant(1004L, "Cyber Food", "Seoul"));

		// Java8 추가. filter 기능
		Restaurant restaurant = restaurants.stream()
				.filter(r -> r.getId().equals(id))
				.findFirst()
				.get();
		//      .orElse(null); // 없는 경우 테스트할 때 사용. 없는 경우 null.

		return restaurant;
	}
}
