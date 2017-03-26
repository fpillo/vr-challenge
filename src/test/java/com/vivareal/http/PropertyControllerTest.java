package com.vivareal.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivareal.ApplicationTest;
import com.vivareal.WebConfigurationTest;
import com.vivareal.domains.Property;
import com.vivareal.exceptions.BusinessException;
import com.vivareal.exceptions.PropertyAlreadyExistsException;
import com.vivareal.exceptions.PropertyNotFoundException;
import com.vivareal.usecases.CreateProperty;
import com.vivareal.usecases.FindProperty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

/**
 * Created by fpillo on 3/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTest.class, WebConfigurationTest.class})
@WebAppConfiguration
public class PropertyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CreateProperty createProperty;

    @Autowired
    private FindProperty findProperty;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        Mockito.reset(createProperty);
        Mockito.reset(findProperty);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_create_should_return_201() throws Exception {
        final Property property = new Property();
        property.setX(399);
        property.setY(900);
        property.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        property.setPrice(1250000);
        property.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        property.setBeds(4);
        property.setBaths(3);
        property.setSquareMeters(210);

        Mockito.when(createProperty.create(Mockito.any(Property.class))).thenReturn(new Property());

        mockMvc.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convert(property))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void test_create_invalid_data_should_return_400() throws Exception {
        final Property property = new Property();
        property.setX(399);
        property.setY(900);
        property.setTitle("");
        property.setPrice(1250000);
        property.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        property.setBeds(4);
        property.setBaths(3);
        property.setSquareMeters(210);

        Mockito.when(createProperty.create(Mockito.any(Property.class))).thenThrow(BusinessException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convert(property))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void test_create_alreadyExists_should_return_409() throws Exception {
        final Property property = new Property();
        property.setX(399);
        property.setY(900);
        property.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        property.setPrice(1250000);
        property.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        property.setBeds(4);
        property.setBaths(3);
        property.setSquareMeters(210);

        Mockito.when(createProperty.create(Mockito.any(Property.class))).thenThrow(PropertyAlreadyExistsException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convert(property))
        ).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void test_byId_should_return_200() throws Exception {
        final Property property = new Property();
        property.setId("f4f37fcd-f67c-4c3a-8c55-391080283f92");
        property.setX(399);
        property.setY(900);

        Mockito.when(findProperty.byId("f4f37fcd-f67c-4c3a-8c55-391080283f92")).thenReturn(property);

        mockMvc.perform(MockMvcRequestBuilders.get("/properties/f4f37fcd-f67c-4c3a-8c55-391080283f92"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_byId_notFound_should_return_404() throws Exception {
        Mockito.when(findProperty.byId("f4f37fcd-f67c-4c3a-8c55-391080283f92")).thenThrow(PropertyNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/properties/f4f37fcd-f67c-4c3a-8c55-391080283f92"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private String convert(Object object) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }


}
