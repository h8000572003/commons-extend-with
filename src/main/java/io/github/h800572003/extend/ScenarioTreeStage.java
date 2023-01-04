package io.github.h800572003.extend;

import java.util.ArrayList;
import java.util.List;

/**
 * 結構樹步驟
 */
public abstract class ScenarioTreeStage implements IStage {

    private List<IStage> stages = new ArrayList<>();
    private IStage upper;
    private final IScenarioPrint out;

    private static final IScenarioPrint SCENARIOPRINT = desc -> System.out.println(desc);

    public ScenarioTreeStage(IStage upper, IScenarioPrint out) {
        this.upper = upper;
        this.out = out;
    }

    public ScenarioTreeStage(IStage upper) {
        this(upper, SCENARIOPRINT);
    }

    public void addRoot(IStage stage) {
        this.getRoot().addStage(stage);
    }

    public void addStage(IStage stage) {
        this.stages.add(stage);

    }

    public IStage getUpper() {
        return upper;
    }

    public List<IStage> list() {
        return stages;
    }

    protected void print(String text) {
        out.toPrint(text);
    }

    protected IStage getRoot() {
        return getRoot(this);
    }


    @Override
    public IScenarioPrint getPrint() {
        return this.getRoot(this).getPrint();
    }

    private IStage getRoot(IStage stage) {

        IStage up = stage.getUpper();
        if (up == null) {
            return stage;
        } else {
            return this.getRoot(up);
        }

    }
}
