package indi.ikun.spring.designpattern.ppm.visit.element;

import indi.ikun.spring.designpattern.ppm.visit.IVisitor;

/**
 * 被访问者，就是一个访问者
 * @author renqiankun
 */
public interface IElement {
    /**
     * 接收访问者
     * @param visitor
     */
    void accept(IVisitor visitor);
}
