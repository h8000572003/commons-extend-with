package io.github.h800572003.extend;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

public class ScenarioGiveRoot extends ScenarioTreeStage implements IStage {



    ScenarioGiveRoot(IStage stage) {
        super(stage);
        this.addRoot(this);
    }

    public <T> ScenarioGivesAnd<T> give(String describe, Supplier<T> supplier) {
        if (StringUtils.isBlank(describe)) {
            throw new ScenarioException("描述不得空白");
        }
       this.print("GIVE：" + describe);
        final ScenarioGives<T> gives = new ScenarioGives(this);

        return gives.add(describe, supplier);
    }


}
