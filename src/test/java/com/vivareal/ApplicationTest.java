package com.vivareal;

import com.vivareal.usecases.CreateProperty;
import com.vivareal.usecases.FindProperty;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fpillo on 3/26/2017.
 */
@Configuration
public class ApplicationTest {

    @Bean
    public CreateProperty createProperty() {
        return Mockito.mock(CreateProperty.class);
    }

    @Bean
    public FindProperty findProperty() {
        return Mockito.mock(FindProperty.class);
    }

}
