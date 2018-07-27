/**
 * 
 */
package com.university.college;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.university.college.config.MongoConfig;
import com.university.college.controller.CountryController;
import com.university.college.domain.Country;
import com.university.college.repository.CountryRepository;

/**
 * @author 553243
 *
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.yml")
@WebMvcTest(value = {MongoConfig.class, CountryController.class}, secure = false)
public class CountryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  @Autowired
  private CountryRepository countryRepository;

  Optional<Country> mockCountry = Optional.of(new Country("India", true));

  String countryJson = "  {\r\n" + "    \"activeStatus\": true,\r\n" + "    \"createdOn\": \'"
      + LocalDateTime.now() + "'\",\r\n" + "    \"name\": \"India\",\r\n" + "    \"updatedOn\": \'"
      + LocalDateTime.now() + "'\"\r\n" + "  }";

  @Test
  public void getCountryDetails() throws Exception {
    // Mockito.when(countryRepository.findById(Mockito.anyString())).thenReturn(mockCountry);

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("http://localhost:9000/collegeservices/countries").accept(MediaType.APPLICATION_JSON);

    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

    System.out.println(mvcResult.getResponse());

    String expectedJson = "[  {\r\n" + "    \"activeStatus\": true,\r\n"
        + "    \"createdOn\": \"2018-07-26\",\r\n" + "    \"id\": \"string\",\r\n"
        + "    \"name\": \"string\",\r\n" + "    \"updatedOn\": \"2018-07-26\"\r\n" + "  }]";

    JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
  }

  @Test
  public void createCountry() throws Exception {

    Country mockCountry = new Country("India", true);

    // Mockito.when(countryRepository.save(mockCountry)).thenReturn(mockCountry);

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("http://localhost:9000/collegeservices/country").accept(MediaType.APPLICATION_JSON)
        .content(countryJson).contentType(MediaType.APPLICATION_JSON);

    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

    MockHttpServletResponse httpServletResponse = mvcResult.getResponse();

    assertEquals(HttpStatus.CREATED.value(), httpServletResponse.getStatus());

    assertEquals("http://localhost:9000/collegeservices/countries/1",
        httpServletResponse.getHeader(HttpHeaders.LOCATION));

  }
}
