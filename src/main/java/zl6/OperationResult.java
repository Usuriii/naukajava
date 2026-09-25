package zl6;

public record OperationResult<T>(boolean success, T value, String message) {

    public static <T> OperationResult<T> success(T value) {
        if (value == null) {
            return OperationResult.failed("Podano wartość null");
        } else {
            return new OperationResult<>(true, value, "Operacja udała się");
        }
    }

    public static <T> OperationResult<T> failed(String failedMessage) {
        return new OperationResult<>(false, null, failedMessage);
    }
}
