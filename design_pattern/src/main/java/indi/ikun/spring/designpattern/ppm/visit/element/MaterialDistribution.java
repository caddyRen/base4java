package indi.ikun.spring.designpattern.ppm.visit.element;

import indi.ikun.spring.designpattern.ppm.visit.Visitor;
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
    public void accept(Visitor visitor) {
        visitor.distribution(this);
    }

}
