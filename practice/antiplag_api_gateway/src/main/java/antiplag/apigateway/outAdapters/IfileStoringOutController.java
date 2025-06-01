package antiplag.apigateway.outAdapters;

import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;

import java.util.Optional;

public interface IfileStoringOutController {
    public FileMetaResponse storeFile(FileStoreRequest request);
    public Optional<FileBodyResponse> loadFile(FileLoadRequest request);
}
