package indi.ikun.spring.designpattern.ppm;

/**
 * 物资详情
 * @author renqiankun
 */
public class MaterialDetail extends MaterialBill {

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
    private String unitName;

    /**
     * 计量单位id
     */
    private String unitId;

    /**
     * 申请领用数量
     */
    private Double reqIssueQty;


/*************    以下是会变化的量   ****************/

    /**
     * 物资状态
     */
    MaterialState materialState;

}
