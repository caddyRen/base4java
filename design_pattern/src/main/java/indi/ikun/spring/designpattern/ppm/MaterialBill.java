package indi.ikun.spring.designpattern.ppm;

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
     * 项目信息
     */
    private ProjectInfo projectInfo;

    /**
     * 领用部门信息
     */
    private TakingDept takingDept;

    /**
     * 是否由调拨单生成
     */
    private String isFromMovement;

    /**
     * 领料人id
     */
    private String receiverId;
    /**
     * 领料人
     */
    private String receiver;
    /**
     * 实际领料人身份证号
     */
    private String receiverIDCard;


    /**
     * 实际领料人id
     */
    private String actualReceiverId;
    /**
     * 实际领料人
     */
    private String actualReceiver;
    /**
     * 实际领料人身份证号
     */
    private String actualReceiverIDCard;

    /**
     * 领料类型
     */
    private Integer issueType;

    /**
     * 制单人
     */
    private String creator;
    /**
     * 制单人id
     */
    private String creatorId;
    /**
     * 制单日期
     */
    private Date creatorDate;

    /**
     * 领料申请单id
     */
    private String issueReqID;
}
