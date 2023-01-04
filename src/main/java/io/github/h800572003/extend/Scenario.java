package io.github.h800572003.extend;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 場景(BDD)
 */
@Slf4j
public class Scenario extends ScenarioTreeStage {


    private String describe;



    public Scenario() {
        super(null);
    }


    public Scenario(IScenarioPrint out) {
        super(null, out);
    }


    public ScenarioGiveRoot describe(String describe) {
        if (StringUtils.isBlank(describe)) {
            throw new ScenarioException("描述不得空白");
        }

        this.describe = describe;
        this.print("Scenario:" + describe);
        this.print("↩");

        final ScenarioGiveRoot scenarioGive = new ScenarioGiveRoot(this);
        addStage(scenarioGive);
        return scenarioGive;
    }


}
