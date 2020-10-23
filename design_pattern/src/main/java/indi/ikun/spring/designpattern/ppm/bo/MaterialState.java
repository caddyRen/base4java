package indi.ikun.spring.designpattern.ppm.bo;

import lombok.*;

/**
 * 物资状态
 * 不共享，
 * @author renqiankun
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MaterialState {

    /**
     * 物资状态
     */
    private Integer state;
    /**
     * 领用数量
     */
    private Double issueQty;

    /**
     * 退库数量
     */
    private Double issueReturn;

    /**
     * 实际已领用数量=领用数量-退库数量
     */
    private Double actualIssueQty;

}
