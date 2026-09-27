package zl6;

import java.util.Objects;

public final class OperationResult<T> {
    private final boolean success;
    private final T value;
    private final String message;

    private OperationResult(boolean success, T value, String message) {
        this.success = success;
        this.value = value;
        this.message = message;
    }

    public static <T> OperationResult<T> success(T value) {
        if (value == null) {
            throw new NullPointerException("Podano wartość null");
        } else {
            return new OperationResult<>(true, value, "Operacja udała się");
        }
    }

    public static <T> OperationResult<T> failed(String failedMessage) {
        return new OperationResult<>(false, null, failedMessage);
    }

    public boolean success() {
        return success;
    }

    public T value() {
        return value;
    }

    public String message() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (OperationResult) obj;
        return this.success == that.success &&
                Objects.equals(this.value, that.value) &&
                Objects.equals(this.message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, value, message);
    }

    @Override
    public String toString() {
        return "OperationResult[" +
                "success=" + success + ", " +
                "value=" + value + ", " +
                "message=" + message + ']';
    }
}
