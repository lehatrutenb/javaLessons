package antiplag.fileAnalysisService.adapters.inPorts.grpc;

import antiplag.fileAnalysisService.grpc.ImageFormat;
import antiplag.fileAnalysisService.grpc.WordCloudRequest;
import antiplag.fileAnalysisService.grpc.WordCloudResponse;
import antiplag.fileAnalysisService.grpc.WordCloudWrappedResponse;
import antiplag.fileAnalysisService.useCases.dtos.requests.GetWordCloudInRequest;
import antiplag.fileAnalysisService.useCases.dtos.responses.GetWordCloudInResponse;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

@Component
public class WordCloudInGrpcMapper {
    public GetWordCloudInRequest WordCloudRequestToGrtWordCloudInRequest(WordCloudRequest wordCloudRequest) {
        return new GetWordCloudInRequest(wordCloudRequest.getId());
    }

    public WordCloudWrappedResponse getWordCloudWrappedResponse(GetWordCloudInResponse response) {
        ByteBuffer buffer = ByteBuffer.allocate(response.rawImg().size());
        response.rawImg().forEach(buffer::put);
        return WordCloudWrappedResponse.newBuilder().setResponse(
                WordCloudResponse.newBuilder()
                        .setImg(ByteString.copyFrom(buffer.array()))
                        .setImgFormat(ImageFormat.valueOf(response.imgFormat())).build()
        ).build();
    }
}
