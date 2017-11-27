/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2017 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.engine.feature.entity.action;

import be.yildiz.common.id.ActionId;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.vector.Point3D;
import be.yildizgames.engine.feature.entity.fields.Target;

/**
 * @author Grégory Van den Borre
 */
public final class ActionMock extends Action {

    /**
     * @param entityId id
     */
    public ActionMock(EntityId entityId) {
        super(ActionId.WORLD, entityId, false);
    }

    /**
     *
     */
    public ActionMock() {
        super(ActionId.WORLD, EntityId.valueOf(5), false);
    }

    @Override
    public boolean checkPrerequisite() {
        return true;
    }

    @Override
    public void runImpl(long time) {

    }

    @Override
    public Point3D getDestination() {
        return Point3D.ZERO;
    }

    @Override
    public void setDestination(Point3D destination) {

    }

    @Override
    public void setTarget(Target target) {
    }

    @Override
    public EntityId getTargetId() {
        return EntityId.valueOf(5L);
    }

    @Override
    public void initImpl() {
    }

    @Override
    public void stopImpl() {
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }
}
