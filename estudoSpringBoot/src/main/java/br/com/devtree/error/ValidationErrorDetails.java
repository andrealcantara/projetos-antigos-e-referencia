package br.com.devtree.error;

public class ValidationErrorDetails extends ErrorDetails {
    private String field;
    private String fieldMessage;


    private ValidationErrorDetails(){}

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String details;
        private Long timestamp;
        private String developersMessage;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developersMessage(String developersMessage) {
            this.developersMessage = developersMessage;
            return this;
        }

        public Builder field(String field){
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage){
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setDetails(this.details);
            validationErrorDetails.setDevelopersMessage(this.developersMessage);
            validationErrorDetails.setStatus(this.status);
            validationErrorDetails.setTimestamp(this.timestamp);
            validationErrorDetails.setTitle(this.title);
            validationErrorDetails.field = this.field;
            validationErrorDetails.fieldMessage = this.fieldMessage;
            return validationErrorDetails;
        }
    }
}
