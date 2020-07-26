package com.cyberark.test.integration.items;

import com.cyberark.items.entities.Item;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;

public class ItemRestIntegrationTest {

	private RestTemplate restTemplate = new RestTemplate();
	private Item expectedItem;

	@Before
	public void init() {
		this.restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080/api"));
		restTemplate.setErrorHandler(new IgnoreExceptionErrorHandler());

		expectedItem = new Item(1l,"T-Shirt", 10, 20);
	}

	@Test
	public void testGetAllItems() {

		ResponseEntity<List<Item>> itemsResponse =
				restTemplate.exchange("/items", GET, null,
						new ParameterizedTypeReference<List<Item>>(){});

		assertThat(itemsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		List<Item> items = itemsResponse.getBody();

		assertThat(items).isNotNull();
		assertThat(items.size()).isGreaterThan(0);

		assertThat(items).usingFieldByFieldElementComparator().contains(expectedItem);
	}

	@Test
	public void testGetItem() {
		ResponseEntity<Item> itemResponse =
				restTemplate.getForEntity("/items/" + expectedItem.getId(), Item.class);

		assertThat(itemResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		Item item = itemResponse.getBody();

		assertThat(item).isNotNull();
		assertThat(item).isEqualToComparingFieldByField(expectedItem);
	}

	@Test
	public void testGetItemNotFound() {
		ResponseEntity<Item> returnItem = restTemplate.getForEntity("/items/-1", Item.class);

		assertThat(returnItem).isNotNull();
		assertThat(returnItem.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	private HttpEntity<String> getCreateRequest(Item itemToCreate) throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("name", itemToCreate.getName());
		personJsonObject.put("sellIn", itemToCreate.getSellIn());
		personJsonObject.put("quality", itemToCreate.getQuality());

		HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(),
				headers);
		return request;
	}

	@Test
	public void testCreateItem() throws JSONException {
		Item itemToCreate = new Item(-1, "Laptop", 1, 2);

		HttpEntity<String> request = getCreateRequest(itemToCreate);

		ResponseEntity<Item> itemCreateResponse = restTemplate.postForEntity("/items",
				request,
				Item.class);

		assertThat(itemCreateResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		Item createReturnedItem = itemCreateResponse.getBody();

		assertThat(createReturnedItem).isNotNull();
		assertThat(createReturnedItem).isEqualToIgnoringGivenFields(itemToCreate, "id");

		ResponseEntity<Item> itemGetResponse =
				restTemplate.getForEntity("/items/" + createReturnedItem.getId(), Item.class);

		assertThat(itemGetResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		Item getReturnedItem = itemCreateResponse.getBody();
		assertThat(getReturnedItem).isEqualToComparingFieldByField(createReturnedItem);
	}

	private class IgnoreExceptionErrorHandler implements ResponseErrorHandler {
		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			return response.getStatusCode() != HttpStatus.OK ||
					response.getStatusCode() != HttpStatus.ACCEPTED;
		}

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			// Do nothing in order to make sure no exception is thrown
		}
	}
}