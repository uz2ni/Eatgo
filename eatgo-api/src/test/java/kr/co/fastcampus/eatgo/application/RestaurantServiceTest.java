package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

class RestaurantServiceTest {

	private RestaurantService restaurantService;
	private RestaurantRepository restaurantRepository;
	private MenuItemRepository menuItemRepository;

	// 테스트에서 스프링으로 자동 주입하지 못하는 경우, 직접 주입하기 위해 사용. 모든 테스트 실행 전 이 부분 실행
	@BeforeEach
	public void setUp() {
		restaurantRepository = new RestaurantRepositoryImpl();
		menuItemRepository = new MenuItemRepositoryImpl();
		restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
	}

	@Test
	public void getRestaurants() {
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Restaurant restaurant = restaurants.get(0);
		assertThat(restaurant.getId(), is(1004L));
	}

	@Test
	public void getRestaurant() {
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		assertThat(restaurant.getId(), is(1004L));

		MenuItem menuItem = restaurant.getMenuItems().get(0);
		assertThat(menuItem.getName(), is("Kimchi"));
	}
}