package indi.ikun.spring.designpattern.ppm.visit.element;

import indi.ikun.spring.designpattern.ppm.visit.IVisitor;
import lombok.*;

/**
 * 物资初始化
 * @author renqiankun
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MaterialAllocation  implements IElement{

    /**
     * 物资状态
     */
    private Integer state;
    @Override
    public void accept(IVisitor visitor) {
        visitor.allocation(this);
    }



}
