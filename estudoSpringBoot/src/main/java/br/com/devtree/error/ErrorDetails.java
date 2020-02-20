package br.com.devtree.error;

public class ErrorDetails {
    private Long timestamp;
    private String title;
    private String details;
    private String developersMessage;
    private int status;

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getDevelopersMessage() {
        return developersMessage;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDevelopersMessage(String developersMessage) {
        this.developersMessage = developersMessage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    final public static class ErrorDetailsBuilder {
        private Long timestamp;
        private String title;
        private String details;
        private String developersMessage;
        private int status;

        private ErrorDetailsBuilder() {
        }

        public static ErrorDetailsBuilder newBuilder() {
            return new ErrorDetailsBuilder();
        }

        public ErrorDetailsBuilder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ErrorDetailsBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ErrorDetailsBuilder developersMessage(String developersMessage) {
            this.developersMessage = developersMessage;
            return this;
        }

        public ErrorDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.timestamp = this.timestamp;
            errorDetails.title = this.title;
            errorDetails.status = this.status;
            errorDetails.details = this.details;
            errorDetails.developersMessage = this.developersMessage;
            return errorDetails;
        }
    }
}
