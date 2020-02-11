package kr.co.fastcampus.eatgo.interfaces;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // spring 이용해 Test 실행할 수 있도록 함. get() 사용.
@WebMvcTest(RestaurantController.class) // 특정 컨트롤러를 테스트 한다는 것 명시. (=> RestaurantController 를 테스트 한다.)
class RestaurantControllerTest {

	@Autowired  // spring에서 알아서 해주도록 함. (이후에 자세히 설명)
	private MockMvc mvc;

	@Test
	public void list() throws Exception {
		mvc.perform(get("/restaurants"))
				.andExpect(status().isOk())
				.andExpect(content().string(    // ID 테스트
						containsString("\"id\":1004")
				))
				.andExpect(content().string(    // 가게이름 테스트 (반환 문자에 해당 문자열이 있는지)
						containsString("\"name\":\"Bob zip\"")  // json 형태로 들어가 있는지 테스트
				));

	}

	@Test
	public void detail() throws Exception {
		// case 1
		mvc.perform(get("/restaurants/1004"))
				.andExpect(status().isOk())
				.andExpect(content().string(
						containsString("\"id\":1004")
				))
				.andExpect(content().string(
						containsString("\"name\":\"Bob zip\"")
				));

		// case 2
		mvc.perform(get("/restaurants/2020"))
				.andExpect(status().isOk())
				.andExpect(content().string(
						containsString("\"id\":2020")
				))
				.andExpect(content().string(
						containsString("\"name\":\"Cyber Food\"")
				));
	}

}