package indi.ikun.spring.designpattern.ppm.state;

import indi.ikun.spring.designpattern.ppm.bo.MaterialState;

/**
 * 退库/调拨   物资冻结后，甲方根据需求进行退库或者调拨
 * @author renqiankun
 */
public class MaterialInitWM implements State {

    Activity activity;

    public MaterialInitWM(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MaterialState init(MaterialState materialState) {
        System.err.println("物资已完成初始化");
        return materialState;
    }

    @Override
    public MaterialState distribution(MaterialState materialState) {
        System.err.println("物资已完成分配，可以施工");
        return materialState;
    }

    @Override
    public MaterialState clearingMaterial(MaterialState materialState) {
        System.err.println("物资已清算，可以冻结");
        return materialState;
    }

    @Override
    public MaterialState freezeMaterial(MaterialState materialState) {
        System.err.println("物资已冻结，可以退库/调拨");
        return materialState;
    }

    @Override
    public MaterialState withdrawalMovement(MaterialState materialState) {
        System.err.println("物资已冻结，选择退库/调拨");
        MaterialState result=new MaterialState();
        //改变物资状态...
        result.setState(5);
        //修改状态
        activity.getFlyWeight().use(result);

        System.err.println(activity.getFlyWeight());

        //设置下一个执行的状态
        return result;
    }
}
