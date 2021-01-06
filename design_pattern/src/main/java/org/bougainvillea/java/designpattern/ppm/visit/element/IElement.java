package org.bougainvillea.java.designpattern.ppm.visit.element;

import org.bougainvillea.java.designpattern.ppm.visit.IVisitor;

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
