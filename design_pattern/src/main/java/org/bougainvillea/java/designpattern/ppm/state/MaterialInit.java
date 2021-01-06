package org.bougainvillea.java.designpattern.ppm.state;

import org.bougainvillea.java.designpattern.ppm.bo.MaterialState;

/**
 * 物资初始化，后续甲方分配物资给承包商
 * @author renqiankun
 */
public class MaterialInit implements State {
    Activity activity;

    public MaterialInit(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MaterialState init(MaterialState materialState) {

        System.err.println("物资初始化");
        MaterialState result=new MaterialState();
        //改变物资状态...
        result.setState(1);
        //修改状态
        activity.getFlyWeight().use(result);

        System.err.println(activity.getFlyWeight());

        //设置下一个执行的状态
        activity.setState(activity.getMaterialDistribution());
        return result;
    }

    @Override
    public MaterialState distribution(MaterialState materialState) {
        System.err.println("物资未初始化，不能分配");
        return materialState;
    }

    @Override
    public MaterialState clearingMaterial(MaterialState materialState) {
        System.err.println("物资未初始化，不能清算");
        return materialState;
    }

    @Override
    public MaterialState freezeMaterial(MaterialState materialState) {
        System.err.println("物资未初始化，不能冻结");
        return materialState;
    }

    @Override
    public MaterialState withdrawalMovement(MaterialState materialState) {
        System.err.println("物资未初始化，不能退库/调拨");
        return materialState;
    }
}
