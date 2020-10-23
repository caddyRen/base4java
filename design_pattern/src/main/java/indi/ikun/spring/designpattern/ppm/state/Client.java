package indi.ikun.spring.designpattern.ppm.state;

import indi.ikun.spring.designpattern.ppm.bo.MaterialDetail;
import indi.ikun.spring.designpattern.ppm.bo.MaterialState;
import indi.ikun.spring.designpattern.ppm.flyweight.FlyWeightFactory;

/**
 * @author renqiankun
 */
public class Client {

    public static void main(String[] args) {

        //获取物资
        MaterialDetail materialDetail = new MaterialDetail();
        materialDetail.setIssueItemId("123");
        MaterialDetail materialDetail1 = new MaterialDetail();
        materialDetail1.setIssueItemId("234");

        //物资状态，未初始化状态数据
        MaterialState materialState = new MaterialState(0, 0.0, 0.0, 0.0);

        //物资享元工厂，存放物资不共享（状态）数据，共享数据（只有一个物资）
        FlyWeightFactory factory=new FlyWeightFactory();

        //将共享数据 和 不共享的状态 存到工厂,用户直接从工厂获取数据，共享数据只读，不共享数据读写
        //IssueItemId作为key鉴别物资信息
        factory.get(materialDetail.getIssueItemId(),materialDetail).use(materialState);
        factory.get(materialDetail1.getIssueItemId(),materialDetail1).use(materialState);


        //业务,通过IssueItemId,获取数据
        Activity activity = new Activity(materialDetail.getIssueItemId(),factory);


        MaterialState init = activity.init(materialState);
        MaterialState distribution = activity.distribution(init);
        MaterialState clearingMaterial = activity.clearingMaterial(distribution);
        MaterialState freezeMaterial = activity.freezeMaterial(clearingMaterial);
        activity.withdrawalMovement(freezeMaterial);

        System.err.println("---------------------------");
        //业务,通过IssueItemId,获取数据
        Activity activity1 = new Activity(materialDetail1.getIssueItemId(),factory);


        MaterialState init1 = activity1.init(materialState);
        MaterialState distribution1 = activity1.distribution(init1);
        MaterialState clearingMaterial1 = activity1.clearingMaterial(distribution1);
        MaterialState freezeMaterial1 = activity1.freezeMaterial(clearingMaterial1);
        activity1.withdrawalMovement(freezeMaterial1);

    }
}
