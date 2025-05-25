package antiplag.apigateway.outAdapters.grpc.FileAnalysis;

import antiplag.fileAnalysisService.grpc.AnalyseWrappedResponse;
import antiplag.fileAnalysisService.grpc.FileCloseness;
import antiplag.apigateway.use_cases.dtos.responses.FileAnalyseResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileClosenessResponse;
import antiplag.apigateway.use_cases.dtos.responses.WordCloudResponse;
import org.springframework.stereotype.Component;

import com.google.protobuf.ByteString;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;

import static java.lang.Byte.valueOf;

@Component
public class GrpcFileAnalysisResponseMapping {
    private FileClosenessResponse getFileClosenessResponse(FileCloseness closeness) {
        return new FileClosenessResponse(closeness.getId(), closeness.getName(), closeness.getClosenessRank());
    }

    public Optional<FileAnalyseResponse> getFileAnalyseResponse(AnalyseWrappedResponse response) {
        if (response.hasNull()) {
            return Optional.empty();
        }
        return Optional.of(
                new FileAnalyseResponse(response.getResponse().getClosestFilesList().stream().map(
                this::getFileClosenessResponse).toList())
        );
    }

    public Optional<WordCloudResponse> getWordCloudResponse(antiplag.fileAnalysisService.grpc.WordCloudWrappedResponse response) {
        if (response.hasNull()) {
            return Optional.empty();
        }
        List<Byte> img = new ArrayList<Byte>(response.getResponse().getImg().size());
        for (byte b : response.getResponse().getImg()) {
            img.add(b);
        }
        return Optional.of(
                new WordCloudResponse(img, response.getResponse().getImgFormat().name())
        );
    }
}
