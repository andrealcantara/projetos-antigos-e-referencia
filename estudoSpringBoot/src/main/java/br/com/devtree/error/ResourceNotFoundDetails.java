package br.com.devtree.error;

public class ResourceNotFoundDetails extends ErrorDetails {

    private ResourceNotFoundDetails() { }
    public static final class Builder {
        private Long timestamp;
        private String title;
        private String details;
        private String developersMessage;
        private int status;

        private Builder() { }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder developersMessage(String developersMessage) {
            this.developersMessage = developersMessage;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setTimestamp(this.timestamp);
            resourceNotFoundDetails.setTitle(this.title);
            resourceNotFoundDetails.setStatus(this.status);
            resourceNotFoundDetails.setDetails(this.details);
            resourceNotFoundDetails.setDevelopersMessage(this.developersMessage);
            return resourceNotFoundDetails;
        }


    }
}
