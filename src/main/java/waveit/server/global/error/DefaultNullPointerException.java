package waveit.server.global.error;

import lombok.Getter;
import waveit.server.global.payload.ErrorCode;

@Getter
public class DefaultNullPointerException extends NullPointerException{
    
    private ErrorCode errorCode;

    public DefaultNullPointerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
