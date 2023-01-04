package io.github.h800572003.extend;

import java.util.function.Supplier;

public class ScenarioGives<T> extends ScenarioTreeStage implements IStage {



    private T src;

    public ScenarioGives(IStage upper) {
        super(upper);
    }

    public ScenarioGivesAnd<T> add(String describe, Supplier<T> supplier) {
        this.src = supplier.get();
        ScenarioGivesAnd<T> givesAnd = new ScenarioGivesAnd<>(this, this.src);
        ScenarioGivesAnd<T> and = givesAnd.and(describe, src);
        this.addStage(and);
        return givesAnd;
    }


}
