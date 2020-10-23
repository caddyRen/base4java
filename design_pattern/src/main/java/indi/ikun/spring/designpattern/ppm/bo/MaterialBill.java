package indi.ikun.spring.designpattern.ppm.bo;

import java.util.Date;

/**
 * 领料单
 * @author renqiankun
 */
public class MaterialBill {

    /**
     * 领料单id
     */
    private String issueId;

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 领料时间，审批完成日期
     */
    private Date issueDate;

    /**
     * 是否由调拨单生成
     */
    private String isFromMovement;

    /**
     * 领料类型
     */
    private Integer issueType;

    /**
     * 项目信息
     */
    private ProjectInfo projectInfo;

    /**
     * 领用部门信息
     */
    private TakingDept takingDept;

    /**
     * 领料人id
     */
    private Receiver receiver;

    /**
     * 实际领料人id
     */
    private ActualReceiver actualReceiver;

    /**
     * 制单人
     */
    private Creator creator;
}
