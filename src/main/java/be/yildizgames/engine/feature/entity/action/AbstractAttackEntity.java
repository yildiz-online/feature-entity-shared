/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.ActionId;
import be.yildizgames.engine.feature.entity.Entity;
import be.yildizgames.engine.feature.entity.fields.Target;

/**
 * @author Grégory Van den Borre
 */
public abstract class AbstractAttackEntity extends AbstractAttack {

    private final Follow follow;

    public AbstractAttackEntity(final ActionId id, final Move move) {
        super( id);
        this.follow = new Follow(move);
        //FIXME distance hardcoded
        this.follow.setDistance(200);
    }

    @Override
    protected final void initImpl(Entity e) {
        this.follow.init(e);
    }

    @Override
    protected final void runImpl(final long time, Entity e) {
        if (!e.hasTarget()) {
            this.stop(e);
        }
        this.follow.run(time, e);
        e.getTarget().ifPresent(t -> this.handleTarget(t,e));
    }

    private void handleTarget(Target target, Entity e) {
        if (Point3D.squaredDistance(e.getPosition(), target.getPosition()) - 1 <= this.range.distance * this.range.distance
                && e.getDirection().equals(target.getPosition().subtract(e.getPosition()))) {
            this.fire(e);
            if (this.timer.isTimeElapsed()) {
                target.hit(this.attackHit);
            }
        } else {
            this.stopFire(e);
        }
    }

    @Override
    protected final void stopImpl(Entity e) {
        this.follow.stop(e);
        this.stopFire(e);
    }
}
