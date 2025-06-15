package payment.apigateway.useCases.wrappers;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import payment.apigateway.shared.WrappedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@NoArgsConstructor
public abstract class WrappedObject<In, Out> {
    private List<Iwrapper<In, Out>> wrappers = new ArrayList<>();

    @SafeVarargs
    public final void setWrappers(Iwrapper<In, Out> ...wrappers) {
        this.wrappers = Arrays.stream(wrappers).toList();
    }

    public abstract Out endHandle(In req, WrappedError error);

    public final Out handle(In req, WrappedError error) {
        wrappers.forEach(wrapper -> wrapper.before(req, error));
        Out response = endHandle(req, error);
        wrappers.forEach(wrapper -> wrapper.after(response, error));
        return response;
    }
}
