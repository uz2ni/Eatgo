package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {

	private RestaurantService restaurantService;
	@Mock
	private RestaurantRepository restaurantRepository;
	@Mock
	private MenuItemRepository menuItemRepository;

	// 테스트에서 스프링으로 자동 주입하지 못하는 경우, 직접 주입하기 위해 사용. 모든 테스트 실행 전 이 부분 실행
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); // @Mock 사용할 경우 제대로 된 Mock객체를 할당해주어야 함. (@Mock 붙어있는 객체 초기화)
		MockRestaurantRepository();
		mockMenuItemRepository();

		restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
	}

	private void MockRestaurantRepository() {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
		restaurants.add(restaurant);

		given(restaurantRepository.findAll()).willReturn(restaurants);
		given(restaurantRepository.findById(1004L)).willReturn(restaurant);
	}

	private void mockMenuItemRepository() {
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add(new MenuItem("Kimchi"));

		given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
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