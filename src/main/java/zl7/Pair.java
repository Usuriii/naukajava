package zl7;

import lombok.RequiredArgsConstructor;

import java.util.function.Function;
@RequiredArgsConstructor(staticName = "of")
public class Pair<L, R> {
    private final L left;
    private final R right;

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public <NR> Pair<L, NR> mapRight(Function<R, NR> mapper) {
        return new Pair<>(left, mapper.apply(right));
    }

    public <NL> Pair<NL, R> mapLeft(Function<L, NL> mapper) {
        return new Pair<>(mapper.apply(left), right);
    }

}
