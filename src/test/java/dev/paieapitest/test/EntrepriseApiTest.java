package dev.paieapitest.test;

import static org.assertj.core.api.Assertions.assertThat;

import dev.paieapitest.TestConfig;
import dev.paieapitest.dto.EntrepriseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class EntrepriseApiTest {

    private RestTemplate rt = new RestTemplate();
    @Value("${urlBasePaieApi}")
    private String urlBasePaieApi;

    @Test
    public void test_entreprises_informations_valide(){
        List<EntrepriseDto> entreprises = Arrays.asList(rt.getForObject(urlBasePaieApi +"/entreprises",EntrepriseDto[].class));
        entreprises.forEach(e ->{assertThat(e.getCode()).isNotEmpty();
                                    assertThat(e.getDenomination()).isNotEmpty();});

    }
}
