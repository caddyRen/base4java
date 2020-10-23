package indi.ikun.spring.designpattern.ppm.state;

import indi.ikun.spring.designpattern.ppm.bo.MaterialState;

/**
 * 物资清算
 * 承包商现场作业完成后，清点物资并记录，方便后续冻结物资
 * @author renqiankun
 */
public class MaterialClearing implements State {

    Activity activity;

    public MaterialClearing( Activity activity) {
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
        System.err.println("清算物资");


        MaterialState result=new MaterialState();
        //改变物资状态...
        result.setState(3);
        //修改状态
        activity.getFlyWeight().use(result);

        System.err.println(activity.getFlyWeight());

        activity.setState(activity.getMaterialFreeze());
        return result;

    }

    @Override
    public MaterialState freezeMaterial(MaterialState materialState) {
        System.err.println("物资未清算，不能冻结");
        return materialState;
    }

    @Override
    public MaterialState withdrawalMovement(MaterialState materialState) {
        System.err.println("物资未清算，不能退库/调拨");
        return materialState;
    }
}
