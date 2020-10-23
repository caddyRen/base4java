package indi.ikun.spring.designpattern.ppm.state;

import indi.ikun.spring.designpattern.ppm.bo.MaterialState;

/**
 * 承包商工作任务完成后，清点完剩余物资，甲方根据该清单，冻结物资，后续退库或者调拨
 * @author renqiankun
 */
public class MaterialFreeze implements State {

    Activity activity;

    public MaterialFreeze(Activity activity) {
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

        System.err.println("物资冻结");
        MaterialState result=new MaterialState();
        //改变物资状态...
        result.setState(4);
        //修改状态
        activity.getFlyWeight().use(result);

        System.err.println(activity.getFlyWeight());

        //设置下一个执行的状态
        activity.setState(activity.getMaterialInitWM());
        return result;
    }

    @Override
    public MaterialState withdrawalMovement(MaterialState materialState) {
        System.err.println("物资未冻结，不能退库/调拨");
        return materialState;
    }
}
