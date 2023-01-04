package io.github.h800572003.extend;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.fail;

public class ScenarioAnsThat<O> extends ScenarioTreeStage {
    private O o;
    private boolean isError;

    private String desc;



    public ScenarioAnsThat(IStage upper, String desc, O o, boolean isError) {
        super(upper);
        this.o = o;
        this.isError = isError;
        this.desc = desc;
    }

    public ScenarioAnsThat<O> but(String desc, Predicate<O> predicate) {
        if (StringUtils.isBlank(desc)) {
            throw new ScenarioException("描述不得空白");
        }
        if (!predicate.test(o)) {
            fail("BUT:" + desc + ":失敗");
        }
        print("BUT:" + desc + ":通過");
        return this;
    }
}
