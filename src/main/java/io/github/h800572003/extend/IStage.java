package io.github.h800572003.extend;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 步驟
 */
public interface IStage {

    /**
     * 步驟描述
     *
     * @return
     */


    List<IStage> list();

    /**
     * 上一層
     * @return
     */
    IStage getUpper();

    void addStage(IStage stage);

    IScenarioPrint getPrint();
}
