package waveit.server.global.error;


import lombok.Getter;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import waveit.server.global.payload.ErrorCode;

import java.util.List;

@Getter
public class InvalidParameterException extends DefaultException{

    private final Errors errors;

    public InvalidParameterException(Errors errors) {
        super(ErrorCode.INVALID_PARAMETER);
        this.errors = errors;
    }

    public List<FieldError> getFieldErrors() {
        return errors.getFieldErrors();
    }
    
}
