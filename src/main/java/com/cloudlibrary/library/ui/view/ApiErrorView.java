package com.cloudlibrary.library.ui.view;

import com.cloudlibrary.library.exception.CloudLibraryException;
import com.cloudlibrary.library.exception.MessageType;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ApiErrorView {
    private final List<Error> errors;

    public ApiErrorView(List<MessageType> messageTypes) {
        this.errors = messageTypes.stream().map(Error::errorWithMessageType).collect(Collectors.toList());
    }

    public ApiErrorView(CloudLibraryException exception) {
        this.errors = Collections.singletonList(Error.errorWithException(exception));
    }

    public ApiErrorView(MessageType messageType, String message) {
        this.errors = Collections.singletonList(Error.errorWithMessageTypeAndMessage(messageType, message));
    }

    @Getter
    @ToString
    public static class Error {
        private final String errorType;
        private final String errorMessage;

        public static Error errorWithMessageType(MessageType messageType) {
            return new Error(messageType.name(), messageType.getMessage());
        }

        public static Error errorWithMessageTypeAndMessage(MessageType messageType, String message) {
            return new Error(messageType.name(), message);
        }

        public static Error errorWithException(CloudLibraryException cloudLibraryException) {
            return new Error(cloudLibraryException);
        }

        private Error(String errorType, String errorMessage) {
            this.errorType = errorType;
            this.errorMessage = errorMessage;
        }

        private Error(CloudLibraryException cloudLibraryException) {
            this.errorType = ObjectUtils.isEmpty(cloudLibraryException.getType()) ? cloudLibraryException.getStatus().getReasonPhrase() :
                    cloudLibraryException.getType();
            this.errorMessage = cloudLibraryException.getMessage();
        }
    }
}
