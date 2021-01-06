package org.bougainvillea.java.designpattern.ppm.state;

import org.bougainvillea.java.designpattern.ppm.bo.MaterialState;

/**
 * 物资分派
 * 甲方分派物资给承包商，为后续承包商现场施工使用物资，方便作业完结后清点物资
 * @author renqiankun
 */
public class MaterialDistribution implements State {
    Activity activity;

    public MaterialDistribution(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MaterialState init(MaterialState materialState) {
        System.err.println("物资已完成初始化");
        return materialState;
    }

    @Override
    public MaterialState distribution(MaterialState materialState) {

        System.err.println("物资分配");
        MaterialState result=new MaterialState();
        //改变物资状态...
        result.setState(2);
        //修改状态
        activity.getFlyWeight().use(result);

        System.err.println(activity.getFlyWeight());

        //设置下一个执行的状态
        activity.setState(activity.getMaterialClearing());
        return result;
    }

    @Override
    public MaterialState clearingMaterial(MaterialState materialState) {
        System.err.println("物资未分配，不能清算");
        return materialState;
    }

    @Override
    public MaterialState freezeMaterial(MaterialState materialState) {
        System.err.println("物资未分配，不能冻结");
        return materialState;
    }

    @Override
    public MaterialState withdrawalMovement(MaterialState materialState) {
        System.err.println("物资未分配，不能退库/调拨");
        return materialState;
    }
}
