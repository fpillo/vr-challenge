package com.vivareal;

import com.google.gson.*;
import com.vivareal.domains.Boundaries;
import com.vivareal.domains.Province;
import com.vivareal.domains.Spotippos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ResourceLoader resourceLoader() {
		return new ResourceLoader();
	}

	@Bean
	public Spotippos spotippos(final ResourceLoader resourceLoader) throws Exception {
		final String provinces = resourceLoader.load("provinces.json");
		return new Spotippos(parseProvinces(provinces));
	}

	private Collection<Province> parseProvinces(final String stringProvinces) {
		final Collection<Province> provinces = new ArrayList<>();
		final JsonObject jsonObject = new JsonParser().parse(stringProvinces).getAsJsonObject();

		jsonObject.entrySet().forEach(entry -> {
			provinces.add(parseProvince(entry));
		});

		return provinces;
	}

	private Province parseProvince(final Map.Entry<String, JsonElement> entry) {
		final Gson gson = new GsonBuilder().create();
		final Province province = new Province();
		province.setName(entry.getKey());
		province.setBoundaries(gson.fromJson(entry.getValue().getAsJsonObject().get("boundaries"), Boundaries.class));

		return province;
	}

	class ResourceLoader {
		public String load(final String fileName) throws IOException {
			final ClassPathResource classPathResource = new ClassPathResource(fileName);
			return new String(FileCopyUtils.copyToByteArray(classPathResource.getInputStream()), StandardCharsets.UTF_8);
		}
	}

}
