package io.github.h800572003.extend;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.fail;

public class ScenarioGivesAnd<T> extends ScenarioTreeStage implements IStage {


    private String describe;
    private T data;


    public ScenarioGivesAnd(IStage upper, T data) {
        super(upper);
        this.data = data;
    }

    public ScenarioGivesAnd<T> and(String describe, Consumer<T> function) {
        function.accept(data);
        return and(describe, data);
    }

    protected ScenarioGivesAnd<T> and(String describe, T data) {
        if (StringUtils.isBlank(describe)) {
            throw new ScenarioException("描述不得空白");
        }
        this.print("AND：" + describe);
        ScenarioGivesAnd givesAnd = new ScenarioGivesAnd(this, data);
        givesAnd.describe = describe;
        givesAnd.data = data;
        super.addStage(givesAnd);
        return givesAnd;
    }




    public <O> ScenarioAns<O> when(String describe, Function<T, O> function) {

        print("↩");
        print("WHEN：" + describe);
        final O out = function.apply(data);
        return new ScenarioAns<>(this.getRoot(), describe, out, false);


    }

    /**
     * 會發生中斷行為
     *
     * @param describe
     * @param function
     * @param <E>
     * @param <O>
     * @return
     */
    public <E extends Throwable, O> ScenarioAns<E> whenWithThrow(String describe, Function<T, O> function) {
        if (StringUtils.isBlank(describe)) {
            throw new ScenarioException("描述不得空白");
        }
        print("↩");
        print("WHEN：" + describe);
        try {
            function.apply(data);
            fail("THEN:" + describe + ":失敗:需要發生中斷");
            throw new ScenarioException("THEN:" + describe + ":失敗:需要發生錯誤");
        } catch (Exception e) {
            return new ScenarioAns<>(this, describe, (E) e, true);
        }
    }

}
