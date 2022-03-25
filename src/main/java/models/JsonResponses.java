package models;

public class JsonResponses {
    private Boolean output;
    private String message;
    private Object data;

    public JsonResponses() {
    }

    public JsonResponses(Boolean output, String message, Object data) {
        this.output = output;
        this.message = message;
        this.data = data;
    }

    public Boolean getOutput() {
        return output;
    }

    public void setOutput(Boolean output) {
        this.output = output;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResponses{" +
                "output=" + output +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
