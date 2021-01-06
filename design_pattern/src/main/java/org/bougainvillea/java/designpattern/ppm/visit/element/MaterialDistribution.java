package org.bougainvillea.java.designpattern.ppm.visit.element;

import org.bougainvillea.java.designpattern.ppm.visit.IVisitor;
import lombok.*;

/**
 * 物资分配
 * @author renqiankun
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDistribution  implements IElement{

    /**
     * 物资状态
     */
    private Integer state;
    @Override
    public void accept(IVisitor visitor) {
        visitor.distribution(this);
    }

}
