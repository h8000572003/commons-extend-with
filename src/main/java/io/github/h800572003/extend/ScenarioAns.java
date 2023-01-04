package io.github.h800572003.extend;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.fail;

public class ScenarioAns<O> extends ScenarioTreeStage {

    private O o;
    private boolean isError;

    private String desc;

    public ScenarioAns(IStage upper, String desc, O o, boolean isError) {
        super(upper);
        this.addRoot(this);
        this.desc = desc;
        this.o = o;
        this.isError = isError;
    }


    public ScenarioAnsThat<O> then(String desc, Predicate<O> predicate) {
        if (StringUtils.isBlank(desc)) {
            throw new ScenarioException("描述不得空白");
        }
        print("↩");
        if (!predicate.test(o)) {
            Assertions.fail("THEN:" + desc + ":失敗");
        }
        print("THEN:" + desc + ":通過");
        return new ScenarioAnsThat(this.getRoot(), desc, o, isError);


    }


}
