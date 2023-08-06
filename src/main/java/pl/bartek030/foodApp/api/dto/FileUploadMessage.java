package pl.bartek030.foodApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
public class FileUploadMessage {
    StatusMessage message;

    public enum StatusMessage {
        SUCCESS, FAILURE
    }
}
