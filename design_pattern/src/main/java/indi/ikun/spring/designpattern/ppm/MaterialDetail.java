package indi.ikun.spring.designpattern.ppm;

/**
 * 物资详情
 * @author renqiankun
 */
public class MaterialDetail extends MaterialBill {

    /**
     * 领料单物资详情id
     */
    private String issueItemId;

    /**
     * 物资id
     */
    private String materialId;

    /**
     * 物资名称
     */
    private String materialName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 计量单位
     */
    private Unit unit;

    /**
     * 申请领用数量
     */
    private Double reqIssueQty;

    /**
     * 领料申请单id *************确认领料单里的字段是否为同一字段
     */
    private String issueReqID;

    /**
     * 领料申请明细ID
     */
    private String issueReqItemId;

    /**
     * 跨账调拨单ID
     */
    private String movementItemId;
    /**
     * 跨账调拨单明细ID
     */
    private String fromMovementItemId;



/*************    以下是会变化的量
 * 使用享元模式共享状态
 *
 * ****************/

    /**
     * 物资状态
     */
    MaterialState materialState;

}
