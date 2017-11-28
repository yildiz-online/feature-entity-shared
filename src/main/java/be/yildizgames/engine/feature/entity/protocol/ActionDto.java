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

package be.yildizgames.engine.feature.entity.protocol;

import be.yildiz.common.id.ActionId;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.vector.Point3D;
import be.yildizgames.engine.feature.entity.action.Action;

/**
 * @author Grégory Van den Borre
 */
public class ActionDto {

    public final ActionId id;

    public final EntityId entity;

    public final Point3D destination;

    public final EntityId target;

    public ActionDto(ActionId id, EntityId entity, Point3D destination, EntityId target) {
        super();
        assert id != null;
        assert entity != null;
        assert destination != null;
        assert target != null;
        this.id = id;
        this.entity = entity;
        this.destination = destination;
        this.target = target;
    }

    public ActionDto(Action a) {
        this(a.id, a.getEntity(), a.getDestination(), a.getTargetId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionDto actionDto = (ActionDto) o;

        return id.equals(actionDto.id) && entity.equals(actionDto.entity) && destination.equals(actionDto.destination) && target.equals(actionDto.target);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entity.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + target.hashCode();
        return result;
    }
}