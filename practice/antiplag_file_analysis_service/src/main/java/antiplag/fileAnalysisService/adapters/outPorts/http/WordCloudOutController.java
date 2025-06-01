package antiplag.fileAnalysisService.adapters.outPorts.http;

import antiplag.fileAnalysisService.useCases.dtos.requests.GetWordCloudOutRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.GetWordCloudOutResponse;
import antiplag.fileAnalysisService.useCases.interfaces.IwordCloudOutController;
import com.google.common.primitives.Bytes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class WordCloudOutController implements IwordCloudOutController {
    private ResponseEntity<byte[]> getResponse(String text, String format) {
        String url = "https://quickchart.io/wordcloud";
        String requestJson = String.format("{\"text\":\"%s\", \"format\":\"%s\"}", text, format);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(
                url,
                request,
                byte[].class
        );
    }

    @Override
    public GetWordCloudOutResponse createWordCloud(GetWordCloudOutRequest request) {
        ResponseEntity<byte[]> response = getResponse(request.text(), request.resultImgFormat());

        if (response.getBody() == null) {
            return new GetWordCloudOutResponse(List.of());
        }
        return new GetWordCloudOutResponse(Bytes.asList(response.getBody()));
    }
}
