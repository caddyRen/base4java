package indi.ikun.spring.designpattern.ppm;

/**
 * 物资状态
 * @author renqiankun
 */
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
