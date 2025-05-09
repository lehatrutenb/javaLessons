package antiplag.apigateway.outAdapters;

import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;

public interface IfileStoringOutController {
    public FileMetaResponse storeFile(FileStoreRequest request);
    public FileBodyResponse loadFile(FileLoadRequest request);
}
