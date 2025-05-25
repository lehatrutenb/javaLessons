package antiplag.apigateway.inAdapters.controllers;


import antiplag.apigateway.use_cases.dtos.requests.FileAnalyseRequest;
import antiplag.apigateway.use_cases.dtos.requests.WordCloudRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileAnalyseResponse;
import antiplag.apigateway.use_cases.dtos.responses.WordCloudResponse;
import antiplag.apigateway.use_cases.services.AnalyseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;

@Slf4j
@RestController
@RequestMapping("antiplag/analyse")
@RequiredArgsConstructor
@Tag(name = "Проверка на антиплагиат : анализ файлов", description = "Проверка на антиплагиат : анализ файлов")
public class FileAnalysisController {
    private final AnalyseService analyseService;

    @GetMapping("antiplag/{id}")
    @Operation(summary = "найти ближайшие файлы к данному")
    public ResponseEntity<FileAnalyseResponse> analyseFile(@PathVariable int id, @RequestParam boolean forceRecalc) {
        return ResponseEntity.status(HttpStatus.OK).body(analyseService.analyseFile(new FileAnalyseRequest(id, forceRecalc)));
    }

    @GetMapping(path="wordcloud/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @Operation(summary = "построить облако слов по файлу")
    public @ResponseBody byte[] getWordCloud(@PathVariable int id) {
        var response = analyseService.getWordCloud(new WordCloudRequest(id)).rawImg();
        ByteBuffer byteBuffer = ByteBuffer.allocate(response.size());
        response.forEach(byteBuffer::put);
        return byteBuffer.array();
    }
}
