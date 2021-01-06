package org.bougainvillea.java.designpattern.ppm.state;

import org.bougainvillea.java.designpattern.ppm.bo.MaterialState;
import org.bougainvillea.java.designpattern.ppm.flyweight.FlyWeightFactory;
import org.bougainvillea.java.designpattern.ppm.flyweight.FlyWeight;
import lombok.Getter;
import lombok.Setter;

/**
 * 使用物资
 * @author renqiankun
 */
@Getter
@Setter
public class Activity {
    State state;
    FlyWeightFactory flyWeightFactory;
    FlyWeight flyWeight;

    private State materialInit=new MaterialInit(this);
    private State materialDistribution=new MaterialDistribution(this);
    private State materialClearing=new MaterialClearing(this);
    private State materialFreeze=new MaterialFreeze(this);
    private State materialInitWM=new MaterialInitWM(this);


    /**
     * 活动开始，即开始初始化物资
     * @param flyWeightFactory 从工厂获取数据
     */
    public Activity(String id ,FlyWeightFactory flyWeightFactory) {
        this.state = getMaterialInit();
        this.flyWeightFactory = flyWeightFactory;
        this.flyWeight=flyWeightFactory.get(id,null);
    }

    public MaterialState init(MaterialState materialState){
       return state.init(materialState);
    }
    public MaterialState distribution(MaterialState materialState){
        return state.distribution(materialState);
    }

    public MaterialState clearingMaterial(MaterialState materialState){
        return state.clearingMaterial(materialState);
    }

    public MaterialState freezeMaterial(MaterialState materialState){
        return state.freezeMaterial(materialState);
    }

    public  MaterialState withdrawalMovement(MaterialState materialState){
        return state.withdrawalMovement(materialState);
    }

}
