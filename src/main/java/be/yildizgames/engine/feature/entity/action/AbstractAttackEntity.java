/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
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

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.ActionId;
import be.yildizgames.engine.feature.entity.Entity;
import be.yildizgames.engine.feature.entity.fields.Target;

/**
 * @author Grégory Van den Borre
 */
public abstract class AbstractAttackEntity extends AbstractAttack {

    private final Follow follow;

    public AbstractAttackEntity(final ActionId id, final Entity attacker, final Move move) {
        super(attacker, id);
        this.follow = new Follow(move, attacker);
        //FIXME distance hardcoded
        this.follow.setDistance(200);
    }

    @Override
    protected final void initImpl() {
        this.follow.init();
    }

    @Override
    protected final void runImpl(final long time) {
        if (!this.entity.hasTarget()) {
            this.stop();
        }
        this.follow.run(time);
        this.entity.getTarget().ifPresent(this::handleTarget);
    }

    private void handleTarget(Target target) {
        if (Point3D.squaredDistance(this.entity.getPosition(), target.getPosition()) - 1 <= this.range.distance * this.range.distance
                && this.entity.getDirection().equals(target.getPosition().subtract(this.entity.getPosition()))) {
            this.fire();
            if (this.timer.isTimeElapsed()) {
                target.hit(this.attackHit);
            }
        } else {
            this.stopFire();
        }
    }

    @Override
    protected final void stopImpl() {
        this.follow.stop();
        this.stopFire();
    }
}
