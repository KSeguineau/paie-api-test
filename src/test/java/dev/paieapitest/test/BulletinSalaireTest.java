package dev.paieapitest.test;

import static org.assertj.core.api.Assertions.assertThat;

import dev.paieapitest.TestConfig;
import dev.paieapitest.dto.BulletinSalaireAvecMontantDto;
import dev.paieapitest.dto.BulletinSalaireDto;
import dev.paieapitest.dto.CollegueDto;
import dev.paieapitest.dto.RemunerationEmployeDto;
import dev.paieapitest.exception.BulletinSalaireNonTrouveException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class BulletinSalaireTest {

    private RestTemplate rt = new RestTemplate();
    @Value("${urlBasePaieApi}")
    private String urlBasePaieApi;
    @Value("${urlBaseCollegueApi}")
    private String urlBaseCollegueApi;

    @Test
    public void testCalculBulletinSalaire() throws JSONException {
        HttpEntity<String> request = getRequestAjoutCollegue();
        CollegueDto collegueDto = rt.postForObject(urlBaseCollegueApi+"/collegues", request, CollegueDto.class);
        assertThat(collegueDto).isNotNull();
        assertThat(collegueDto.getMatricule()).isNotNull();
        String matricule = collegueDto.getMatricule();


        request = getRequestAjoutRemunerationEmploye(matricule);
        RemunerationEmployeDto remunerationEmployeDto = rt.postForObject(urlBasePaieApi+"/remuneration_employe", request, RemunerationEmployeDto.class);
        assertThat(remunerationEmployeDto).isNotNull();

        request = getRequestAjoutBulletinSalaire(matricule);
        BulletinSalaireDto bulletinSalaireDto = rt.postForObject(urlBasePaieApi+"/bulletin_salaire", request, BulletinSalaireDto.class);
        assertThat(bulletinSalaireDto).isNotNull();
        String code = bulletinSalaireDto.getCode();

        List<BulletinSalaireAvecMontantDto> bulletinSalaireAvecMontantDtos = Arrays.asList(rt.getForObject(urlBasePaieApi+"/bulletin_salaire", BulletinSalaireAvecMontantDto[].class));
        assertThat(bulletinSalaireAvecMontantDtos).isNotNull();
        Optional<BulletinSalaireAvecMontantDto> bulletinSalaireAvecMontantDtoOptional = bulletinSalaireAvecMontantDtos.stream().filter(b -> b.getCode().equals(code)).findFirst();
        assertThat(bulletinSalaireAvecMontantDtoOptional.isPresent()).isTrue();
        BulletinSalaireAvecMontantDto bulletinSalaireAvecMontantDto = bulletinSalaireAvecMontantDtoOptional.get();
        assertThat(bulletinSalaireAvecMontantDto.getSalaireDto().getSalaireBrut().compareTo(new BigDecimal("2672.00"))==0).isTrue();
        assertThat(bulletinSalaireAvecMontantDto.getSalaireDto().getSalaireNetAPayer().compareTo(new BigDecimal("2079.62"))==0).isTrue();
        assertThat(bulletinSalaireAvecMontantDto.getSalaireDto().getSalaireNetImposable().compareTo(new BigDecimal("2157.11"))==0).isTrue();


    }

    private HttpEntity<String> getRequestAjoutCollegue() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("nom", "bob");
        personJsonObject.put("prenom", "bobby");
        personJsonObject.put("email", "a@a.fr");
        personJsonObject.put("ddn", "1990-09-04");
        personJsonObject.put("photoUrl", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/180px-SNice.svg.png");
        return new HttpEntity<String>(personJsonObject.toString(), headers);
    }

    private HttpEntity<String> getRequestAjoutRemunerationEmploye(String matricule) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("matricule", matricule);
        personJsonObject.put("codeEntreprise", "DEV");
        personJsonObject.put("codeProfil", "Cadre");
        personJsonObject.put("codeGrade", "GRADE_A");
        return new HttpEntity<String>(personJsonObject.toString(), headers);

    }

    private HttpEntity<String> getRequestAjoutBulletinSalaire(String matricule) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        String json = "{\"periode\":{\"id\": 1,\"dateDebut\": \"2019-01-01\",\"dateFin\": \"2019-01-31\"},\"matricule\":\""+matricule+"\",\"primeExceptionnelle\":1000.0}";
        return new HttpEntity<String>(json, headers);

    }
}
